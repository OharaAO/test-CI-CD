package com.ohara.corrida_colosseum.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ViewEvent.class, ViewAttendance.class, ViewRating.class,
            ViewReservationForAdmin.class, ViewVote.class, ViewPlayerParticipations.class})
    protected Long id;

    @Column(nullable = false)
    @Length(min = 5, max = 50)
    @NotBlank
    @JsonView({ViewEvent.class,ViewAttendance.class, ViewRating.class,
            ViewReservationForAdmin.class,ViewVote.class,ViewReservationForUser.class, ViewPlayerParticipations.class})
    protected String title;

    @Length(max = 100)
    @JsonView({ViewEvent.class,ViewReservationForUser.class})
    protected String description;


    @Length(max = 500)
    @JsonView(ViewEvent.class)
    protected String subject;

    @Column(nullable = false)
    @JsonView({ViewEvent.class,ViewReservationForUser.class})
    protected Date startDate;

    @JsonView({ViewEvent.class,ViewReservationForUser.class})
    protected Date endDate;

    @Column(nullable = false)
    @JsonView(ViewEvent.class)
    protected String location;

    @JsonView(ViewEvent.class)
    protected boolean deleted;

    @Column(nullable = false)
    @JsonView(ViewEvent.class)
    protected int maxCapacity;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonView(ViewEvent.class)
    protected List<SeatCategory> seatCategories;

    @ManyToMany
    @JoinTable(name = "event_player",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    @JsonView({ViewEvent.class,ViewPlayerParticipations.class})
    protected List<Player> players;


    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    @JsonView(ViewEvent.class)
    protected User Organizer;


    @OneToMany(mappedBy = "event")
    private List<Reservation> reservations;

}



