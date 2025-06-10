package com.ohara.corrida_colosseum.dao;



import com.ohara.corrida_colosseum.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDao extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE " +
            "LOWER(p.lastName) LIKE LOWER(:searchTerm) OR " +
            "LOWER(p.firstName) LIKE LOWER(:searchTerm) OR " +
            "CAST(p.number AS string) LIKE :searchTerm")
    List<Player> searchPlayer(@Param("searchTerm") String searchTerm);






}
