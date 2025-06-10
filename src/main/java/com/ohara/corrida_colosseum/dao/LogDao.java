package com.ohara.corrida_colosseum.dao;


import com.ohara.corrida_colosseum.models.Log;
import com.ohara.corrida_colosseum.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<Log, Integer> {
}
