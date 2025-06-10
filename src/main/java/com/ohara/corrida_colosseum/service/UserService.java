package com.ohara.corrida_colosseum.service;

import com.ohara.corrida_colosseum.dao.UserDao;
import com.ohara.corrida_colosseum.models.User;
import com.ohara.corrida_colosseum.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    protected AppUserDetails userDetails;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder ) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;

    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void anonymizeUser(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName("Anonyme");
            user.setEmail("anonyme_" + id + "@example.com");
            user.setPhone(null);

            userDao.save(user);
        }
    }






}
