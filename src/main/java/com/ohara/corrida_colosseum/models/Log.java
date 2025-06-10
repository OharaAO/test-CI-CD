package com.ohara.corrida_colosseum.models;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewLog;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewLog.class)
    protected Integer id;
    @JsonView(ViewLog.class)
    protected LocalDateTime date;
    protected String action;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView(ViewLog.class)
    protected User user;
}


