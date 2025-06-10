package com.ohara.corrida_colosseum.models;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewEvent;
import com.ohara.corrida_colosseum.view.ViewReservationForUser;
import com.ohara.corrida_colosseum.view.ViewSeatCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
@Entity
public class SeatCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonView({ViewSeatCategory.class, ViewEvent.class})
    protected Long id;

    @JsonView({ViewSeatCategory.class, ViewReservationForUser.class, ViewEvent.class})
    protected String name;

    @JsonView({ViewSeatCategory.class, ViewEvent.class})
    protected int capacity;

    @JsonView({ViewSeatCategory.class, ViewEvent.class})
    protected float price;

    @ManyToOne
    @JoinColumn(name="event_id")
    protected Event event;


    @OneToMany(mappedBy = "seatCategory")
    private List<Reservation> reservations;



}





