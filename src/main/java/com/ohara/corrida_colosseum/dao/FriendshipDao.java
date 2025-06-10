package com.ohara.corrida_colosseum.dao;


import com.ohara.corrida_colosseum.models.Friendship;
import com.ohara.corrida_colosseum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipDao extends JpaRepository<Friendship, Integer> {
}
