package com.ohara.corrida_colosseum.dao;

import com.ohara.corrida_colosseum.models.Event;
import com.ohara.corrida_colosseum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE " +
            "LOWER(e.title) LIKE LOWER(:searchTerm) OR " +
            "LOWER(e.description) LIKE LOWER(:searchTerm) OR " +
            //"LOWER(e.startDate) LIKE LOWER(:searchTerm) OR " +
            "CAST(e.id AS string) LIKE :searchTerm")
    List<Event> searchEvent(@Param("searchTerm") String searchTerm);


    List<Event> findByDeletedFalse();


    @Query("SELECT e FROM Event e WHERE e.deleted = false")
    List<Event> findAllNotDeleted();





}
