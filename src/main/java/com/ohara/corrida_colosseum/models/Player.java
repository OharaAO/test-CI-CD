package com.ohara.corrida_colosseum.models;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewEvent;
import com.ohara.corrida_colosseum.view.ViewPlayer;
import com.ohara.corrida_colosseum.view.ViewPlayerParticipations;
import com.ohara.corrida_colosseum.view.ViewVote;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ViewPlayer.class, ViewEvent.class, ViewPlayerParticipations.class, ViewVote.class})
    protected Long id;


    @Column(nullable = false)
    @JsonView({ViewPlayer.class, ViewEvent.class, ViewPlayerParticipations.class,ViewVote.class})
    protected String firstName;

    @Column(nullable = false)
    @JsonView({ViewPlayer.class, ViewEvent.class, ViewPlayerParticipations.class,ViewVote.class})
    protected String lastName;


    @Column(nullable = false)
    @JsonView({ViewPlayer.class, ViewEvent.class, ViewPlayerParticipations.class,ViewVote.class})
    protected Integer number;

    @ManyToMany(mappedBy = "players")
//    @JsonView(ViewPlayerParticipations.class)
    protected List<Event> events;
}
