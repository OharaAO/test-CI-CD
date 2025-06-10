package com.ohara.corrida_colosseum.models;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.security.Role;
import com.ohara.corrida_colosseum.view.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
@Entity
public class User {
    @JsonView({ViewEvent.class,ViewAttendance.class, ViewFriendship.class,
            ViewLog.class, ViewNotification.class, ViewRating.class, ViewReservationForAdmin.class,
            ViewUser.class, ViewVote.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @JsonView({ViewEvent.class,ViewAttendance.class,
            ViewFriendship.class,ViewLog.class,ViewNotification.class,ViewRating.class,
            ViewReservationForAdmin.class,ViewUser.class,ViewVote.class,ViewReservationForUser.class})
    @Column(nullable = false)
    protected String firstName;

    @JsonView({ViewEvent.class,ViewAttendance.class,
            ViewFriendship.class,ViewLog.class,ViewNotification.class, ViewRating.class,
            ViewReservationForAdmin.class,ViewUser.class,ViewVote.class,ViewReservationForUser.class})
    @Column(nullable = false)
    @Email
    protected String email;

    @NotBlank
    @Column(nullable = false)
    protected String passwordHash;


    @JsonView(ViewUser.class)
    protected String phone;


    @Column
    protected LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('UTILISATEUR', 'ADMINISTRATEUR', 'RESERVATION')")
    @JsonView(ViewUser.class)
    protected Role role;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;
}






