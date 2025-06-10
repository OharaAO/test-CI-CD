package com.ohara.corrida_colosseum.models;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewRating;
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
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewRating.class)
    protected Long id;

    @JsonView(ViewRating.class)
    @Column(nullable = false)
    @Min(1)
    @Max(5)
    protected int score;

    @JsonView(ViewRating.class)
    protected String comment;

    @JsonView(ViewRating.class)
    protected LocalDateTime ratingDate;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @JsonView(ViewRating.class)
    protected Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView(ViewRating.class)
    protected User user;


    @PrePersist
    protected void ensureUnique() {

    }

}


