package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.FriendshipDao;
import com.ohara.corrida_colosseum.models.Friendship;
import com.ohara.corrida_colosseum.view.ViewFriendship;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class FriendshipController {

    protected FriendshipDao friendshipDao;

    public FriendshipController(FriendshipDao friendshipDao) {
        this.friendshipDao = friendshipDao;
    }

    @GetMapping("/friendship/{id}")
    @JsonView(ViewFriendship.class)
    public ResponseEntity<Friendship> get(@PathVariable int id) {
        Optional<Friendship> optionalFriendship = friendshipDao.findById(id);

        if (optionalFriendship.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalFriendship.get(), HttpStatus.OK);
    }

    @GetMapping("/friendship")
    @JsonView(ViewFriendship.class)
    public List<Friendship> getAll() {
        return friendshipDao.findAll();
    }

    @PostMapping("/friendship")
    public ResponseEntity<Friendship> save(@RequestBody @Valid Friendship friendship) {
        friendshipDao.save(friendship);
        return new ResponseEntity<>(friendship, HttpStatus.CREATED);
    }


    @DeleteMapping("/friendship/{id}")
    public ResponseEntity<Friendship> delete(@PathVariable int id) {
        Optional<Friendship> optionalFriendship = friendshipDao.findById(id);
        if (optionalFriendship.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendshipDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/friendship/{id}")
    public ResponseEntity<Friendship> update(
            @PathVariable int id,
            @RequestBody Friendship friendship) {
        Optional<Friendship> optionalFriendship = friendshipDao.findById(id);

        if (optionalFriendship.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendship.setId(id);
        friendshipDao.save(friendship);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
