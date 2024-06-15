package test.saurav.schoolrecords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.saurav.schoolrecords.domain.School;
import test.saurav.schoolrecords.service.SchoolService;

import java.util.List;

@RestController
@RequestMapping("schools")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("")
    public ResponseEntity<String> addSchool(@RequestBody School school) {
        schoolService.addSchool(school);
        return new ResponseEntity<>("School successfully added.", HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<School>> getAllSchools() {
        return new ResponseEntity<>(schoolService.findAllSchools(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<School> findSchoolById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(schoolService.findSchoolById(id), HttpStatus.OK);
    }

}
