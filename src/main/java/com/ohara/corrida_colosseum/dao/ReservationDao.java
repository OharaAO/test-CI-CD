package com.ohara.corrida_colosseum.dao;


import com.ohara.corrida_colosseum.models.Reservation;
import com.ohara.corrida_colosseum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Long> {





        @Query("SELECT COALESCE(SUM(r.quantity), 0) FROM Reservation r WHERE r.user.id = :userId AND r.event.id = :eventId")
        Integer sumQuantityByUserAndEvent(@Param("userId") Long userId, @Param("eventId") Long eventId);

        List<Reservation> findByUserId(Long userId);

        List<Reservation> findByEventId(Long eventId);



        @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
        List<Reservation> findReservationsByUserId(@Param("userId") Long userId);

}


