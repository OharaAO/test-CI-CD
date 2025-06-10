package com.ohara.corrida_colosseum.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewVote;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewVote.class)
    protected Long id;

    protected LocalDateTime voteDate;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @JsonView(ViewVote.class)
    protected Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView(ViewVote.class)
    protected User user;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonView(ViewVote.class)
    protected Player player;

    @Column(nullable = false)
    @JsonView(ViewVote.class)
    @Min(0)
    @Max(10)
    protected Integer score;

    @JsonView(ViewVote.class)
    protected String comment;
}





