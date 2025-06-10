package com.ohara.corrida_colosseum.service;

import com.ohara.corrida_colosseum.dao.*;
import com.ohara.corrida_colosseum.models.*;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;


@Service
public class FeedbackService {

    @Autowired
    UserDao userDao;

    @Autowired
    EventDao eventDao;
    @Autowired
    AttendanceDao attendanceDao; // présences
    @Autowired
    RatingDao ratingDao;
    @Autowired
    VoteDao voteDao;

    @Autowired
    PlayerDao playerDao;

    public Rating rateEvent(Long eventId, Long userId, int score, String comment) throws BadRequestException, AccessDeniedException {
        Event event = eventDao.findById(eventId).orElseThrow();
        User user = userDao.findById(userId).orElseThrow();

        if (!attendanceDao.existsByEventAndUser(event, user))
            throw new AccessDeniedException("Non présent à l'événement");

        if (ratingDao.existsByEventAndUser(event, user))
            throw new BadRequestException("Déjà noté");

        Rating r = new Rating();
        r.setEvent(event);
        r.setUser(user);
        r.setScore(score);
        r.setRatingDate(LocalDateTime.now());
        r.setComment(comment);

        Rating rate = ratingDao.save(r);

        rate.getEvent();
        rate.getUser();
        rate.getRatingDate();
        rate.getComment();
        rate.getScore();
        return rate;
    }

    public Vote votePlayer(Long eventId, Long userId, Long playerId, String comment, Integer score)
            throws NotAcceptableStatusException, BadRequestException {

        Event event = eventDao.findById(eventId).orElseThrow();
        User user = userDao.findById(userId).orElseThrow();

        if (!attendanceDao.existsByEventAndUser(event, user)) {
            System.out.println("Non présent à l'événement");
            throw new BadRequestException("Non présent à l'événement");

        }

        if (voteDao.existsByEventAndUser(event, user)) {
            System.out.println("Déjà voté");
            throw new NotAcceptableStatusException("Déjà voté");
        }

        Player player = playerDao.findById(playerId).orElseThrow();

        Vote vote = new Vote();
        vote.setEvent(event);
        vote.setUser(user);
        vote.setPlayer(player);
        vote.setVoteDate(LocalDateTime.now());
        vote.setComment(comment);
        vote.setScore(score);

        Vote saved = voteDao.save(vote);

        // ⚠️ Force l'initialisation des relations pour éviter les null
        saved.getPlayer().getId();
        saved.getUser().getId();
        saved.getEvent().getId();
        saved.getVoteDate();

        return saved;
    }
}
