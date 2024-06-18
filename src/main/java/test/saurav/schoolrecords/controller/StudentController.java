package test.saurav.schoolrecords.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.saurav.schoolrecords.domain.Student;
import test.saurav.schoolrecords.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("")
    public ResponseEntity<String> addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>("Student successfully added.", HttpStatus.CREATED);
    }


    @GetMapping("")
    public ResponseEntity<List<Student>> findAllStudents() {
        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }

    /**
     * Finds a student by their ID.
     *
     * @param  id  the ID of the student to find
     * @return     the student with the given ID, or throws a NoSuchElementException if the student does not exist
     * @throws NoSuchElementException if the student with the given ID does not exist
     */
    @GetMapping("{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.findStudentById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {

        return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.deleteStudentById(id), HttpStatus.ACCEPTED);
    }


}
