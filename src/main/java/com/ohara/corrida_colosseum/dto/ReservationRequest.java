package com.ohara.corrida_colosseum.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReservationRequest {
    @NotNull(message = "L'ID de l'événement est obligatoire")
    private Long eventId;

    @NotNull(message = "L'ID de la catégorie de siège est obligatoire")
    private Long seatCategoryId;

    @Min(value = 1, message = "La quantité minimum est 1")
    @Max(value = 2, message = "La quantité maximum est 2")
    private int quantity;

    private String qrCode;

    // Getters et Setters
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public Long getSeatCategoryId() { return seatCategoryId; }
    public void setSeatCategoryId(Long seatCategoryId) { this.seatCategoryId = seatCategoryId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
}