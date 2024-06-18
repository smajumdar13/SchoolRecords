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
    private final SchoolService schoolService;
//    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          StudentProfileService studentProfileService,
                          SchoolService schoolService) {
        this.studentRepository = studentRepository;
        this.studentProfileService = studentProfileService;
        this.schoolService = schoolService;
    }

    /**
     * Adds a new student to the system. If a duplicate key exception occurs,
     * it tries to update the existing student instead.
     *
     * @param  student   the student to be added or updated
     */
    public void addStudent(Student student) {
        try {
//            schoolService.addOrUpdateSchool(student.getSchool());
            if (student.getSchool().getName()!=null) {
                schoolService.addOrUpdateSchool(student.getSchool());
            }
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

    /**
     * Retrieves all students from the database.
     *
     * @return          a list of all student records
     */
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves a student from the repository by their ID.
     *
     * @param  id    the ID of the student to retrieve
     * @return       the student with the given ID, or throws a NoSuchElementException if not found
     * @throws NoSuchElementException if the student with the given ID does not exist
     */
    public Student findStudentById(Long id) {
        try {
            return studentRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("The student with the id " + id + " doesn't exist.");
        }
        // easier method, but somehow does not carry the message, needs fix, working on it
//        return studentRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("The student with the id " + id + " doesn't exist."));
    }

    /**
     * Updates a student's information with the given id.
     *
     * @param  id          the id of the student to update
     * @param  student     the updated student object
     * @return             the updated student object
     */
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

    /**
     * Deletes a student by the given id.
     *
     * @param  id    the id of the student to delete
     * @return       a message indicating the successful deletion of the student
     */
    public String deleteStudentById(Long id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Student does not exist for the given id " + id);
        }
        studentRepository.deleteById(id);
        return "Student successfully deleted.";
    }

}
