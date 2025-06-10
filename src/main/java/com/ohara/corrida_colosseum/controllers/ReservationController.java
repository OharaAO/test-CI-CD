package com.ohara.corrida_colosseum.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.ReservationDao;
import com.ohara.corrida_colosseum.dto.ReservationRequest;
import com.ohara.corrida_colosseum.exception.ReservationException;
import com.ohara.corrida_colosseum.exception.ResourceNotFoundException;
import com.ohara.corrida_colosseum.models.Reservation;
import com.ohara.corrida_colosseum.models.User;
import com.ohara.corrida_colosseum.security.AppUserDetails;
//import com.ohara.corrida_colosseum.service.FileService;
import com.ohara.corrida_colosseum.service.ReservationService;
import com.ohara.corrida_colosseum.view.ViewReservationForAdmin;
import com.ohara.corrida_colosseum.view.ViewReservationForUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Optional;


  

@CrossOrigin
@RestController
public class ReservationController {

    protected ReservationDao reservationDao;

    @Autowired
    private ReservationService reservationService;

//    @Autowired
//    private FileService fileService;

    public ReservationController(ReservationDao reservationDao) {

        this.reservationDao = reservationDao;
//        this.fileService = fileService;
    }

    @GetMapping("/reservation/{id}")
    @JsonView({ViewReservationForAdmin.class, ViewReservationForUser.class})
    public ResponseEntity<Reservation> get(@PathVariable int id) {
        Optional<Reservation> optionalReservation = reservationDao.findById((long) id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalReservation.get(), HttpStatus.OK);
    }


    @GetMapping("reservation/user/{id}")
    @JsonView( ViewReservationForUser.class)
    public List<Reservation> findReservationsByUserId(@AuthenticationPrincipal AppUserDetails user) {
        return reservationDao.findReservationsByUserId(user.getId());
    }



    @GetMapping("/reservation")
    @JsonView({ViewReservationForAdmin.class,})
    public List<Reservation> getAll() {
        return reservationDao.findAll();
    }



    @PostMapping("/reservation")
    @JsonView(ViewReservationForUser.class)
    public ResponseEntity<?> save(@RequestBody @Valid ReservationRequest request,
                                  @AuthenticationPrincipal AppUserDetails userDetails) {
        try {
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Utilisateur non authentifié");
            }

            User user = userDetails.getUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Utilisateur non trouvé");
            }

            Reservation reservation = reservationService.createReservation(request, user);
//            File file = fileService.createReservationFile(request, user);
            return ResponseEntity.ok(reservation);
        } catch (ReservationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de la réservation: " + e.getMessage());
        }
    }



    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Reservation> delete(@PathVariable int id) {
        Optional<Reservation> optionalReservation = reservationDao.findById((long) id);
        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservationDao.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/reservation/{id}")
    public ResponseEntity<Reservation> update(
            @PathVariable int id,
            @RequestBody Reservation reservation) {
        Optional<Reservation> optionalReservation = reservationDao.findById((long) id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservation.setId((long) id);
        reservationDao.save(reservation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    }


