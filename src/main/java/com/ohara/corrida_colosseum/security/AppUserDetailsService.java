package com.ohara.corrida_colosseum.security;

import com.ohara.corrida_colosseum.dao.UserDao;
import com.ohara.corrida_colosseum.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AppUserDetailsService implements UserDetailsService {

    protected UserDao userDao;

    public AppUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDao.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        return new AppUserDetails(optionalUser.get());
    }
}
