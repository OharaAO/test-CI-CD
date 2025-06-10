package com.ohara.corrida_colosseum.models;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewNotification;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewNotification.class)
    protected Integer id;
    @JsonView(ViewNotification.class)
    protected String message;
    @JsonView(ViewNotification.class)
    protected String type;
    protected boolean isRead;
    protected LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView(ViewNotification.class)
    protected User user;
}


