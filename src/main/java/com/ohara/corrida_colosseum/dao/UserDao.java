package com.ohara.corrida_colosseum.dao;


import com.ohara.corrida_colosseum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.firstName) LIKE LOWER(:searchTerm) OR " +
            "LOWER(u.email) LIKE LOWER(:searchTerm) OR " +
            "LOWER(u.role) LIKE LOWER(:searchTerm) OR " +
            "CAST(u.id AS string) LIKE :searchTerm")
    List<User> searchUsers(@Param("searchTerm") String searchTerm);


}
