package com.ohara.corrida_colosseum.dao;

import com.ohara.corrida_colosseum.models.Event;
import com.ohara.corrida_colosseum.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Integer> {
}
