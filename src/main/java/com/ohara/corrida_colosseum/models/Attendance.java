package com.ohara.corrida_colosseum.models;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewAttendance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewAttendance.class)
    protected Integer id;

    protected LocalDateTime checkInTime;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @JsonView(ViewAttendance.class)
    protected Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView(ViewAttendance.class)
    protected User user;


}


