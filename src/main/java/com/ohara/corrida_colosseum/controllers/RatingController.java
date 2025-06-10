package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.RatingDao;
import com.ohara.corrida_colosseum.dao.UserDao;
import com.ohara.corrida_colosseum.dto.RatingRequest;
import com.ohara.corrida_colosseum.dto.VoteResponse;
import com.ohara.corrida_colosseum.models.Rating;
import com.ohara.corrida_colosseum.models.User;
import com.ohara.corrida_colosseum.models.Vote;
import com.ohara.corrida_colosseum.security.AppUserDetails;
import com.ohara.corrida_colosseum.security.AppUserDetailsService;
import com.ohara.corrida_colosseum.security.IsAdministrateur;
import com.ohara.corrida_colosseum.service.FeedbackService;
import com.ohara.corrida_colosseum.view.ViewRating;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.NotAcceptableStatusException;

import java.nio.file.AccessDeniedException;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@CrossOrigin
@RestController
public class RatingController {

    protected RatingDao ratingDao;

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected FeedbackService feedbackService;

    public RatingController(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }

    @GetMapping("/rating/{id}")
    @JsonView(ViewRating.class)

    public ResponseEntity<Rating> get(@PathVariable Long id) {
        Optional<Rating> optionalRating = ratingDao.findById(id);

        if (optionalRating.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalRating.get(), HttpStatus.OK);
    }

    @GetMapping("/rating")
    @JsonView(ViewRating.class)
    @IsAdministrateur
    public List<Rating> getAll() {
        return ratingDao.findAll();
    }

    /**
     * Note un joueur (1 à 10).
     *
     */
    @PostMapping("/events/{eventId}/vote-player")
    public ResponseEntity<VoteResponse> rateEvent(
            @PathVariable Long eventId,
            @RequestBody @Valid RatingRequest request,
            @AuthenticationPrincipal AppUserDetails userDetails
    ) {
        User user = userDetails.getUser();

        try {
            Vote vote = feedbackService.votePlayer(
                    eventId,
                    user.getId(),
                    request.getPlayerId(),
                    request.getComment(),
                    request.getScore()
            );

            VoteResponse response = new VoteResponse(
                    vote.getId(),
                    vote.getPlayer().getId(),
                    vote.getUser().getId(),
                    vote.getComment(),
                    vote.getScore(),
                    vote.getVoteDate()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (NotAcceptableStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/rating/{id}")
    @IsAdministrateur
    public ResponseEntity<Rating> delete(@PathVariable Long id) {
        Optional<Rating> optionalRating = ratingDao.findById(id);
        if (optionalRating.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ratingDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/rating/{id}")
    public ResponseEntity<Rating> update(
            @PathVariable Long id,
            @RequestBody Rating rating) {
        Optional<Rating> optionalRating = ratingDao.findById(id);

        if (optionalRating.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rating.setId(id);
        ratingDao.save(rating);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
