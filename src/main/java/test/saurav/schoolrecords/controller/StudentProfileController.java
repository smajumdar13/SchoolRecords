package test.saurav.schoolrecords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.saurav.schoolrecords.domain.StudentProfile;
import test.saurav.schoolrecords.service.StudentProfileService;

import java.util.List;

//@RestController
//@RequestMapping("studentProfiles")
class StudentProfileController {

//    private final StudentProfileService studentProfileService;

//    @Autowired
//    public StudentProfileController(StudentProfileService studentProfileService) {
//        this.studentProfileService = studentProfileService;
//    }
//
//    @PostMapping("")
//    public ResponseEntity<String> addStudentProfile(@RequestBody StudentProfile studentProfile) {
//        studentProfileService.addStudentProfile(studentProfile);
//        return new ResponseEntity<>("Student profile successfully added.", HttpStatus.CREATED);
//    }
//
//    @GetMapping("")
//    public ResponseEntity<List<StudentProfile>> findAllStudentProfiles() {
//        return new ResponseEntity<>(studentProfileService.findAllStudentProfiles(), HttpStatus.OK);
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<StudentProfile> findStudentProfileById(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(studentProfileService.findStudentProfileById(id), HttpStatus.OK);
//    }
//
//    @GetMapping("/student/{id}")
//    public ResponseEntity<StudentProfile> findByStudentId(@PathVariable("id") Long id) {
//        return studentProfileService.findProfileByStudentId(id);
//    }
//
//    @PutMapping("/student/{id}")
//    public ResponseEntity<StudentProfile> updateProfileByStudentId(@PathVariable("id") Long id, @RequestBody StudentProfile studentProfile) {
//        return new ResponseEntity<>(studentProfileService.updateProfileByStudentId(id, studentProfile), HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteStudentProfileById(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(studentProfileService.deleteStudentProfileById(id), HttpStatus.ACCEPTED);
//    }

}
