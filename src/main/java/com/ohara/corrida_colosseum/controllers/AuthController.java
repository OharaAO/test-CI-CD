package com.ohara.corrida_colosseum.controllers;


import com.ohara.corrida_colosseum.dao.UserDao;
import com.ohara.corrida_colosseum.models.User;
import com.ohara.corrida_colosseum.security.AppUserDetails;
import com.ohara.corrida_colosseum.security.Role;
import com.ohara.corrida_colosseum.security.SecuriteUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class AuthController {

    protected UserDao userDao;
    protected PasswordEncoder passwordEncoder;
    protected AuthenticationProvider authenticationProvider;
    protected SecuriteUtils securiteUtils;

    public AuthController(UserDao userDao, SecuriteUtils securiteUtils, AuthenticationProvider authenticationProvider, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.securiteUtils = securiteUtils;
        this.authenticationProvider = authenticationProvider;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/inscription")
    public ResponseEntity<User> inscription(@RequestBody @Valid User user) {

        user.setRole(Role.UTILISATEUR);
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userDao.save(user);

        // on masque le mot de passe
        user.setPasswordHash(null);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody @Valid User user) {

        try {
            AppUserDetails userDetails = (AppUserDetails) authenticationProvider.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    user.getPasswordHash()
                            ))
                    .getPrincipal();
            return new ResponseEntity<>(securiteUtils.generateToken(userDetails), HttpStatus.OK);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


    }
}
