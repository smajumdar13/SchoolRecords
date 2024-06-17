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
    private final StudentProfileService studentProfileService;
//    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          StudentProfileService studentProfileService) {
        this.studentRepository = studentRepository;
        this.studentProfileService = studentProfileService;
    }

    public void addStudent(Student student) {
        try {
            studentRepository.save(student);
            studentProfileService.addStudentProfile(student.getProfile());
        } catch (DuplicateKeyException ex) {
//            throw new DuplicateKeyException("The email already exists in a different record.");
            // add update flow instead
            System.out.println("Student already exists. Trying to update...");
            Student existingStudent = studentRepository.findByEmail(student.getEmail());
            updateStudent(existingStudent.getId(), student);
        }
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("The student with the id " + id + " doesn't exist."));
    }

    public Student updateStudent(Long id, Student student) {

        Student studentToUpdate = findStudentById(id);

        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setEmail(student.getEmail());
        studentToUpdate.setProfile(student.getProfile());
        studentToUpdate.setSchool(student.getSchool());

        System.out.println("Student updated successfully.");
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
