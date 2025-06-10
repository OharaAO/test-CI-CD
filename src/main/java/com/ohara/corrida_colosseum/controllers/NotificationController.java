package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.NotificationDao;
import com.ohara.corrida_colosseum.models.Notification;
import com.ohara.corrida_colosseum.view.ViewNotification;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class NotificationController {

    protected NotificationDao notificationDao;

    public NotificationController(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    @GetMapping("/notification/{id}")
    @JsonView(ViewNotification.class)
    public ResponseEntity<Notification> get(@PathVariable int id) {
        Optional<Notification> optionalNotification = notificationDao.findById(id);

        if (optionalNotification.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalNotification.get(), HttpStatus.OK);
    }

    @GetMapping("/notification")
    @JsonView(ViewNotification.class)
    public List<Notification> getAll() {
        return notificationDao.findAll();
    }

    @PostMapping("/notification")
    public ResponseEntity<Notification> save(@RequestBody @Valid Notification notification) {
        notificationDao.save(notification);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }


    @DeleteMapping("/notification/{id}")
    public ResponseEntity<Notification> delete(@PathVariable int id) {
        Optional<Notification> optionalNotification = notificationDao.findById(id);
        if (optionalNotification.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notificationDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/notification/{id}")
    public ResponseEntity<Notification> update(
            @PathVariable int id,
            @RequestBody Notification notification) {
        Optional<Notification> optionalNotification = notificationDao.findById(id);

        if (optionalNotification.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notification.setId(id);
        notificationDao.save(notification);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
