package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.UserDao;
import com.ohara.corrida_colosseum.dao.VoteDao;
import com.ohara.corrida_colosseum.dto.RatingResponse;
import com.ohara.corrida_colosseum.dto.VoteRequest;
import com.ohara.corrida_colosseum.models.Rating;
import com.ohara.corrida_colosseum.models.User;
import com.ohara.corrida_colosseum.models.Vote;
import com.ohara.corrida_colosseum.security.AppUserDetails;
import com.ohara.corrida_colosseum.service.FeedbackService;
import com.ohara.corrida_colosseum.service.UserService;
import com.ohara.corrida_colosseum.view.ViewVote;
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
public class VoteController {

    protected VoteDao voteDao;

    @Autowired
    protected FeedbackService feedbackService;

    @Autowired
    protected UserDao userDao;


    public VoteController(VoteDao voteDao) {
        this.voteDao = voteDao;
    }

    @GetMapping("/vote/{id}")
    @JsonView(ViewVote.class)
    public ResponseEntity<Vote> get(@PathVariable Long id) {
        Optional<Vote> optionalVote = voteDao.findById(id);

        if (optionalVote.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalVote.get(), HttpStatus.OK);
    }

    @GetMapping("/vote")
    @JsonView(ViewVote.class)
    public List<Vote> getAll() {
        return voteDao.findAll();
    }

    /**
     * Vote pour le meilleur joueur d'un événement.
     * Reçoit un JSON {"playerId": Long}.
     */
    @PostMapping("/events/{eventId}/vote")
    public ResponseEntity<RatingResponse> votePlayer(
            @PathVariable Long eventId,
            @RequestBody VoteRequest request,
            @AuthenticationPrincipal AppUserDetails userDetails

            ) throws AccessDeniedException, BadRequestException {

        User user = userDetails.getUser();

        try {
            Rating rate = feedbackService.rateEvent(
                    eventId,
                    user.getId(),
                    request.getNote(),
                    request.getComment()
            );

            RatingResponse response = new RatingResponse(
                    rate.getId(),
                    rate.getEvent().getId(),
                    rate.getUser().getId(),
                    rate.getComment(),
                    rate.getScore(),
                    rate.getRatingDate()

            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch (NotAcceptableStatusException e) {
            return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }catch (BadRequestException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (NoSuchElementException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/vote/{id}")
    public ResponseEntity<Vote> delete(@PathVariable Long id) {
        Optional<Vote> optionalVote = voteDao.findById(id);
        if (optionalVote.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        voteDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/vote/{id}")
    public ResponseEntity<Vote> update(
            @PathVariable Long id,
            @RequestBody Vote vote) {
        Optional<Vote> optionalVote = voteDao.findById(id);

        if (optionalVote.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vote.setId(id);
        voteDao.save(vote);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
