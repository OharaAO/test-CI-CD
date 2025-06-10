package com.ohara.corrida_colosseum.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VoteResponse {

    private Long voteId;
    private Long playerId;
    private Long userId;
    private String comment;
    private Integer score;
    private LocalDateTime voteDate;


}