package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.UserDao;
import com.ohara.corrida_colosseum.dto.ChangePasswordRequest;
import com.ohara.corrida_colosseum.dto.CreateAccountRequest;
import com.ohara.corrida_colosseum.models.User;
import com.ohara.corrida_colosseum.security.AppUserDetails;
import com.ohara.corrida_colosseum.security.IsAdministrateur;
import com.ohara.corrida_colosseum.security.Role;
import com.ohara.corrida_colosseum.service.UserService;
import com.ohara.corrida_colosseum.view.ViewUser;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class UserController {

    protected UserDao userDao;

    protected AppUserDetails userDetails;
    protected UserService userService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserDao userDao, UserService userService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/user/{id}")
    @JsonView(ViewUser.class)

    public ResponseEntity<User> get(@PathVariable long id) {
        Optional<User> optionalUser = userDao.findById(id);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    @GetMapping("/user")
    @JsonView(ViewUser.class)
    @IsAdministrateur
    public List<User> getAll() {

        return userDao.findAll();
    }



    @GetMapping("/user/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(required = false) String q) {
        if (q == null || q.isBlank()) {
            return ResponseEntity.ok(userDao.findAll());
        }

        String searchTerm = "%" + q.toLowerCase() + "%";
        List<User> users = userDao.searchUsers(searchTerm);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user")
    public ResponseEntity<User> save(@Valid @RequestBody CreateAccountRequest request) {

        User user = new User();

        try {
            if (request.getPassword1().equals(request.getPassword2())) {
                user.setPasswordHash(passwordEncoder.encode(request.getPassword1()));

            }
            user.setFirstName(request.getFirstName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setCreatedAt(LocalDateTime.now());
            user.setRole(Role.UTILISATEUR);

            userDao.save(user);
            return  ResponseEntity.ok(user);

        }catch (IllegalArgumentException e ) {
            return ResponseEntity.badRequest().build();

        }


    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> delete(@PathVariable long id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(
            @PathVariable long id,
            @RequestBody User userUpdateData) {

        Optional<User> optionalUser = userDao.findById(id);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User existingUser = optionalUser.get();

        // Ne mettre à jour que les champs autorisés
        existingUser.setFirstName(userUpdateData.getFirstName());
        existingUser.setEmail(userUpdateData.getEmail());
        existingUser.setPhone(userUpdateData.getPhone());

        // Ne PAS toucher à : id, passwordHash, role, etc.

        userDao.save(existingUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{id}/anonymize")
    public ResponseEntity<Void> anonymizeUser(@PathVariable Long id) {
        userService.anonymizeUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/{id}/reset-password")
    public ResponseEntity<?> ChangePassword(
            @PathVariable long id,
            @AuthenticationPrincipal AppUserDetails userDetails,
            @RequestBody ChangePasswordRequest request) {

        User existingUser = userDetails.getUser();




        if (request.getNewPassword1().equals(existingUser.getPasswordHash())) {
            System.out.println("Votre nouveau mot de passe ne peut pas être identique à l'ancien");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (request.getNewPassword1().equals(request.getNewPassword2())) {
            existingUser.setPasswordHash(passwordEncoder.encode(request.getNewPassword1()));

            userDao.save(existingUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }







        userDao.save(existingUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
