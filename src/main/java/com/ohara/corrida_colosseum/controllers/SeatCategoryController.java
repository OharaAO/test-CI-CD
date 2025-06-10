package com.ohara.corrida_colosseum.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.SeatCategoryDao;
import com.ohara.corrida_colosseum.models.SeatCategory;
import com.ohara.corrida_colosseum.view.ViewSeatCategory;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class SeatCategoryController {

    protected SeatCategoryDao seatCategoryDao;

    public SeatCategoryController(SeatCategoryDao seatCategoryDao) {
        this.seatCategoryDao = seatCategoryDao;
    }

    @GetMapping("/seat-category/{id}")
    @JsonView(ViewSeatCategory.class)
    public ResponseEntity<SeatCategory> get(@PathVariable long id) {
        Optional<SeatCategory> optionalSeatCategory = seatCategoryDao.findById(id);

        if (optionalSeatCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalSeatCategory.get(), HttpStatus.OK);
    }

    @GetMapping("/seat-category")
    @JsonView(ViewSeatCategory.class)
    public List<SeatCategory> getAll() {
        return seatCategoryDao.findAll();
    }

    @PostMapping("/seat-category")
    public ResponseEntity<SeatCategory> save(@RequestBody @Valid SeatCategory seatCategory) {
        seatCategoryDao.save(seatCategory);
        return new ResponseEntity<>(seatCategory, HttpStatus.CREATED);
    }


    @DeleteMapping("/seat-category/{id}")
    public ResponseEntity<SeatCategory> delete(@PathVariable long id) {
        Optional<SeatCategory> optionalSeatCategory = seatCategoryDao.findById(id);
        if (optionalSeatCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        seatCategoryDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/seat-category/{id}")
    public ResponseEntity<SeatCategory> update(
            @PathVariable long id,
            @RequestBody SeatCategory seatCategory) {
        Optional<SeatCategory> optionalSeatCategory = seatCategoryDao.findById(id);

        if (optionalSeatCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        seatCategory.setId(id);
        seatCategoryDao.save(seatCategory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
