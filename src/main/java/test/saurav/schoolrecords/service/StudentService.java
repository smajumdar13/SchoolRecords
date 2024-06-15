package test.saurav.schoolrecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import test.saurav.schoolrecords.domain.Student;
import test.saurav.schoolrecords.repository.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
//    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        try {
            studentRepository.save(student);
        } catch (DuplicateKeyException ex) {
            throw new DuplicateKeyException("The email already exists in a different record.");
            // add update flow instead
        }
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(Long id) {
        try {
            return studentRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("The student with the id " + id + " doesn't exist.");
        }
    }

    public Student updateStudent(Long id, Student student) {
        Student studentDto = findStudentById(id);
        return studentRepository.save(student);
    }

    public String deleteStudentById(Long id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Student does not exist for the given id " + id);
        }
        studentRepository.deleteById(id);
        return "Student successfully deleted.";
    }

}
