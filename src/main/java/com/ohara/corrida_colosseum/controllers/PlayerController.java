package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.EventDao;
import com.ohara.corrida_colosseum.dao.PlayerDao;
import com.ohara.corrida_colosseum.models.Event;
import com.ohara.corrida_colosseum.models.Player;
import com.ohara.corrida_colosseum.service.EventService;
import com.ohara.corrida_colosseum.view.ViewEvent;
import com.ohara.corrida_colosseum.view.ViewPlayer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class PlayerController {

    protected PlayerDao playerDao;
    protected EventDao eventDao;
    protected EventService eventService;

    public PlayerController(PlayerDao playerDao, EventDao eventDao) {
        this.playerDao = playerDao;
        this.eventDao = eventDao;
    }

    @GetMapping("/player/{id}")
    @JsonView(ViewPlayer.class)
    public ResponseEntity<Player> get(@PathVariable Long id) {
        Optional<Player> optionalPlayer = playerDao.findById(id);

        if (optionalPlayer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalPlayer.get(), HttpStatus.OK);
    }

    @GetMapping("/player")
    @JsonView(ViewPlayer.class)
    public List<Player> getAll() {
        return playerDao.findAll();
    }

    @PostMapping("/player")
    public ResponseEntity<Player> save(@RequestBody @Valid Player player) {
        playerDao.save(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }


    @DeleteMapping("/player/{id}")
    public ResponseEntity<Player> delete(@PathVariable Long id) {
        Optional<Player> optionalPlayer = playerDao.findById(id);
        if (optionalPlayer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/player/search")
    @JsonView(ViewPlayer.class)
    public ResponseEntity<List<Player>> searchEvent(@RequestParam(required = false) String q) {
        if (q == null || q.isBlank()) {
            return ResponseEntity.ok(playerDao.findAll());
        }

        String searchTerm = "%" + q.toLowerCase() + "%";
        List<Player> players = playerDao.searchPlayer(searchTerm);
        return ResponseEntity.ok(players);
    }





}
