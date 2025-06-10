package com.ohara.corrida_colosseum.service;

import com.ohara.corrida_colosseum.dao.EventDao;
import com.ohara.corrida_colosseum.dao.ReservationDao;
import com.ohara.corrida_colosseum.dao.SeatCategoryDao;
import com.ohara.corrida_colosseum.dto.ReservationRequest;
import com.ohara.corrida_colosseum.exception.ReservationException;
import com.ohara.corrida_colosseum.exception.ResourceNotFoundException;
import com.ohara.corrida_colosseum.models.Event;
import com.ohara.corrida_colosseum.models.Reservation;
import com.ohara.corrida_colosseum.models.SeatCategory;
import com.ohara.corrida_colosseum.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class ReservationService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private SeatCategoryDao seatCategoryDao;

    @Transactional
    public Reservation createReservation(ReservationRequest request, User user) {
        if (user == null) {
            throw new IllegalArgumentException("L'utilisateur ne peut pas être null");
        }

        if (request.getEventId() == null) {
            throw new IllegalArgumentException("L'ID d'événement ne peut pas être null");
        }

        if (request.getSeatCategoryId() == null) {
            throw new IllegalArgumentException("L'ID de catégorie de siège ne peut pas être null");
        }

        // Vérifier le quota (cette méthode est maintenant sécurisée avec COALESCE)
        Integer totalReserved = reservationDao.sumQuantityByUserAndEvent(
                user.getId(),
                request.getEventId()
        );

        if (totalReserved + request.getQuantity() > 2) {
            throw new ReservationException("Vous ne pouvez pas réserver plus de 2 places pour cet événement");
        }

        // Vérifier la disponibilité
        SeatCategory seatCategory = seatCategoryDao.findById(request.getSeatCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie de siège non trouvée"));




        // Récupérer l'événement séparément
        Event event = eventDao.findById(request.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Événement non trouvé"));

        if (seatCategory.getCapacity() < request.getQuantity()) {
            throw new ReservationException("Plus assez de places disponibles");
        }

        // Mettre à jour la capacité
        seatCategory.setCapacity(seatCategory.getCapacity() - request.getQuantity());
        seatCategoryDao.save(seatCategory);

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setEvent(event);
        reservation.setSeatCategory(seatCategory);
        reservation.setQuantity(request.getQuantity());
        reservation.setReservationDate(new Date());
        reservation.setPaid(false);

        // Première sauvegarde pour obtenir l'ID
        reservation = reservationDao.save(reservation);

        // Générer et définir le QR code
        reservation.setQrCode(generateQrCode(reservation));

        // Sauvegarde finale
        return reservationDao.save(reservation);
    }

    private String generateQrCode(Reservation reservation) {
        try {
            // Approche compatible avec la plupart des versions JJWT
            return Jwts.builder()
                    .claim("reservationId", reservation.getId())
                    .claim("userId", reservation.getUser().getId())
                    .claim("eventId", reservation.getEvent().getId())
                    .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                    .compact();
        } catch (Exception e) {
            // En cas d'échec, générer un identifiant simple
            return "RES-" + reservation.getId() + "-" + System.currentTimeMillis();
        }
    }
}



