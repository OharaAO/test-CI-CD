package com.ohara.corrida_colosseum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class RatingResponse {

    private Long ratingId;
    private Long eventId;
    private Long userId;
    private String comment;
    private Integer score;
    private LocalDateTime ratingDate;
}
