package com.ohara.corrida_colosseum.exception;

public class ReservationException extends RuntimeException {

    public ReservationException(String message) {
        super(message);
    }

    // Optionnel : Ajouter des constructeurs pour inclure la cause
    public ReservationException(String message, Throwable cause) {
        super(message, cause);
    }
}