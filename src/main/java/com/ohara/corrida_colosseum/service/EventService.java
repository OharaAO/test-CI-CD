package com.ohara.corrida_colosseum.service;

import com.ohara.corrida_colosseum.dao.EventDao;
import com.ohara.corrida_colosseum.dao.PlayerDao;
import com.ohara.corrida_colosseum.exception.ResourceNotFoundException;
import com.ohara.corrida_colosseum.models.Event;
import com.ohara.corrida_colosseum.models.Player;
import com.ohara.corrida_colosseum.security.AppUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private PlayerDao playerDao;


    private AppUserDetails user;


    // Méthode pour récupérer tous les événements, sauf ceux anonymisés
    public List<Event> getAllEvents() {
        return eventDao.findByDeletedFalse();  // Filtrage des événements anonymisés
    }

    public List<Event> getAllVisibleEvents() {
        return eventDao.findAllNotDeleted();
    }


    public Event anonymizeEvent(Long eventId) {
        // On trouve l'événement par son ID
        Event event = eventDao.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Événement non trouvé"));

        // On marque l'événement comme supprimé
        event.setDeleted(true);  // Flag pour marquer comme supprimé
        event.setTitle("Événement supprimé");  // On peut aussi anonymiser des informations sensibles

        // On enregistre les modifications
        return eventDao.save(event);
    }


    public Event updateEvent(Long id, Event updatedEvent) {
        Event existing = eventDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Événement non trouvé"));

        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setStartDate(updatedEvent.getStartDate());
        existing.setLocation(updatedEvent.getLocation());
        existing.setMaxCapacity(updatedEvent.getMaxCapacity());
        // Ne pas toucher à deleted ou seatCategories sauf si besoin

        return eventDao.save(existing);
    }


    public void updatePlayersForEvent(Long eventId, List<Long> playerIds) {
        Event event = eventDao.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Événement introuvable avec l’ID : " + eventId));

        List<Player> players = playerDao.findAllById(playerIds);

        // Vérifie si tous les IDs ont été trouvés
        if (players.size() != playerIds.size()) {
            Set<Long> foundIds = players.stream().map(Player::getId).collect(Collectors.toSet());
            List<Long> missing = playerIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            throw new IllegalArgumentException("Joueurs non trouvés : " + missing);
        }

        event.setPlayers(players); // Remplace la liste actuelle de joueurs
        eventDao.save(event);
    }





}
