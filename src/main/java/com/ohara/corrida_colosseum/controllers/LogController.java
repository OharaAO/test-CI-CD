package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.LogDao;
import com.ohara.corrida_colosseum.models.Log;
import com.ohara.corrida_colosseum.security.IsAdministrateur;
import com.ohara.corrida_colosseum.view.ViewLog;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class LogController {

    protected LogDao logDao;

    public LogController(LogDao logDao) {
        this.logDao = logDao;
    }

    @GetMapping("/log/{id}")
    @JsonView(ViewLog.class)
    @IsAdministrateur
    public ResponseEntity<Log> get(@PathVariable int id) {
        Optional<Log> optionalLog = logDao.findById(id);

        if (optionalLog.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalLog.get(), HttpStatus.OK);
    }

    @GetMapping("/log")
    @JsonView(ViewLog.class)
    @IsAdministrateur
    public List<Log> getAll() {
        return logDao.findAll();
    }

    @PostMapping("/log")
    public ResponseEntity<Log> save(@RequestBody @Valid Log log) {
        logDao.save(log);
        return new ResponseEntity<>(log, HttpStatus.CREATED);
    }


    @DeleteMapping("/log/{id}")
    @IsAdministrateur
    public ResponseEntity<Log> delete(@PathVariable int id) {
        Optional<Log> optionalLog = logDao.findById(id);
        if (optionalLog.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/log/{id}")
    public ResponseEntity<Log> update(
            @PathVariable int id,
            @RequestBody Log log) {
        Optional<Log> optionalLog = logDao.findById(id);

        if (optionalLog.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.setId(id);
        logDao.save(log);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
