package com.ohara.corrida_colosseum.dao;


import com.ohara.corrida_colosseum.models.Event;
import com.ohara.corrida_colosseum.models.User;
import com.ohara.corrida_colosseum.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteDao extends JpaRepository<Vote, Long> {

    boolean existsByEventAndUser(Event event, User user);
}
