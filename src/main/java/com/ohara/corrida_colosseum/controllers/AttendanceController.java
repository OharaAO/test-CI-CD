package com.ohara.corrida_colosseum.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.dao.AttendanceDao;
import com.ohara.corrida_colosseum.models.Attendance;
import com.ohara.corrida_colosseum.view.ViewAttendance;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class AttendanceController {

    protected AttendanceDao attendanceDao;

    public AttendanceController(AttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    @GetMapping("/attendance/{id}")
    @JsonView(ViewAttendance.class)
    public ResponseEntity<Attendance> get(@PathVariable int id) {
        Optional<Attendance> optionalAttendance = attendanceDao.findById(id);

        if (optionalAttendance.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalAttendance.get(), HttpStatus.OK);
    }

    @GetMapping("/attendance")
    @JsonView(ViewAttendance.class)
    public List<Attendance> getAll() {
        return attendanceDao.findAll();
    }

    @PostMapping("/attendance")
    public ResponseEntity<Attendance> save(@RequestBody @Valid Attendance attendance) {
        attendanceDao.save(attendance);
        return new ResponseEntity<>(attendance, HttpStatus.CREATED);
    }


    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<Attendance> delete(@PathVariable int id) {
        Optional<Attendance> optionalAttendance = attendanceDao.findById(id);
        if (optionalAttendance.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        attendanceDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/attendance/{id}")
    public ResponseEntity<Attendance> update(
            @PathVariable int id,
            @RequestBody Attendance attendance) {
        Optional<Attendance> optionalAttendance = attendanceDao.findById(id);

        if (optionalAttendance.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        attendance.setId(id);
        attendanceDao.save(attendance);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
