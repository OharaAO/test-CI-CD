package com.ohara.corrida_colosseum.dto;


import lombok.Getter;

@Getter
public class RatingRequest {
    public Long playerId;
    public Integer score;
    public  String comment;
}
