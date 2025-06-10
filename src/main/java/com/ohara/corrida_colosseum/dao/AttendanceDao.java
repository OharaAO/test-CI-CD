package com.ohara.corrida_colosseum.dao;

import com.ohara.corrida_colosseum.models.Attendance;
import com.ohara.corrida_colosseum.models.Event;
import com.ohara.corrida_colosseum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceDao extends JpaRepository<Attendance, Integer> {

    boolean existsByEventAndUser(Event event, User user);
}
