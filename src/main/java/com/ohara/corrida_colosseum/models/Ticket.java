package com.ohara.corrida_colosseum.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {
    @Id
    private Integer id;
    // UUID ou code unique
    private String qrCodeUrl;

    @ManyToOne
    private Reservation reservation;
}