package com.ohara.corrida_colosseum.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewReservationForAdmin;
import com.ohara.corrida_colosseum.view.ViewReservationForUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ViewReservationForAdmin.class,ViewReservationForUser.class})
    protected Long id;

    @Column(nullable = false)
    @JsonView({ViewReservationForAdmin.class,ViewReservationForUser.class})
    protected Integer quantity;

    //@Column(nullable = false)
    @JsonView({ViewReservationForAdmin.class,ViewReservationForUser.class})
    protected Date reservationDate;

    //@Column(nullable = false)
    @JsonView(ViewReservationForAdmin.class)
    protected boolean isPaid;

    //@Column(nullable = false)
    @JsonView({ViewReservationForAdmin.class, ViewReservationForUser.class})
    protected String paymentId;

    @Column(name = "qr_code", length = 500) // Longueur adaptée pour stocker le JWT
    @JsonView(ViewReservationForUser.class)
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    @JsonView({ViewReservationForAdmin.class,ViewReservationForUser.class})
    protected User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id" , nullable = false)
    @JsonView({ViewReservationForAdmin.class,ViewReservationForUser.class})
    protected Event event;


    @ManyToOne
    @JoinColumn(name = "seat_category_id")
    @JsonView(ViewReservationForUser.class)
    protected SeatCategory seatCategory;


}


