package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.EventDao;
import com.ohara.corrida_colosseum.dao.PlayerDao;
import com.ohara.corrida_colosseum.models.Event;
import com.ohara.corrida_colosseum.models.Player;
import com.ohara.corrida_colosseum.models.User;
import com.ohara.corrida_colosseum.security.AppUserDetails;
import com.ohara.corrida_colosseum.service.EventService;
import com.ohara.corrida_colosseum.service.UserService;
import com.ohara.corrida_colosseum.view.ViewEvent;
import com.ohara.corrida_colosseum.view.ViewPlayerParticipations;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
public class EventController {

    protected EventDao eventDao;

    protected PlayerDao playerDao;



    @Autowired
    protected EventService eventService;
    @Autowired
    private UserService userService;

    public EventController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @GetMapping("/event/{id}")
    @JsonView(ViewEvent.class)
    public ResponseEntity<Event> get(@PathVariable long id) {
        Optional<Event> optionalEvent = eventDao.findById(id);

        if (optionalEvent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEvent.get(), HttpStatus.OK);
    }

    @GetMapping("/event")
    @JsonView(ViewEvent.class)
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllVisibleEvents();
        return ResponseEntity.ok(events);
    }



    @GetMapping("/event/search")
    @JsonView(ViewEvent.class)
    public ResponseEntity<List<Event>> searchEvent(@RequestParam(required = false) String q) {
        if (q == null || q.isBlank()) {
            return ResponseEntity.ok(eventDao.findAll());
        }

        String searchTerm = "%" + q.toLowerCase() + "%";
        List<Event> events = eventDao.searchEvent(searchTerm);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/event")
    public ResponseEntity<Event> save(@RequestBody @Valid Event event, Authentication authentication) {

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        User organizer = userDetails.getUser();


        event.setOrganizer(organizer);

        eventDao.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }


    @DeleteMapping("/event/{id}")
    public ResponseEntity<Event> delete(@PathVariable long id) {
        Optional<Event> optionalEvent = eventDao.findById(id);
        if (optionalEvent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eventDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/event/{id}")
    public ResponseEntity<Void> updateEvent(
            @PathVariable long id,
            @RequestBody Event updatedEvent) {

        eventService.updateEvent(id, updatedEvent);
        return ResponseEntity.ok().build();

    }



    // Endpoint pour anonymiser un événement
    @PutMapping("event/{id}/anonymize")
    public ResponseEntity<Event> anonymizeEvent(@PathVariable("id") Long eventId) {
        eventService.anonymizeEvent(eventId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("event/{eventId}/players")
    public ResponseEntity<Void> updateEventPlayers(@PathVariable Long eventId,
                                                   @RequestBody List<Long> playerIds) {
        try {
            eventService.updatePlayersForEvent(eventId, playerIds);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/{eventId}/players")
    @JsonView(ViewPlayerParticipations.class)
    public ResponseEntity<?> getEventPlayers(@PathVariable Long eventId) {
        Optional<Event> eventOptional = eventDao.findById(eventId);

        if (eventOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Événement non trouvé avec l'ID : " + eventId);
        }

        Event event = eventOptional.get();
        List<Player> players = event.getPlayers();

        if (players.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(players);
    }


}
