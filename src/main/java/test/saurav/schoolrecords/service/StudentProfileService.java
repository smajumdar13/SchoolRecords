package test.saurav.schoolrecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import test.saurav.schoolrecords.domain.StudentProfile;
import test.saurav.schoolrecords.repository.StudentProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;

    @Autowired
    public StudentProfileService(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    public StudentProfile addStudentProfile(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    public List<StudentProfile> findAllStudentProfiles() {
        return studentProfileRepository.findAll();
    }

    public StudentProfile findStudentProfileById(Long id) {
        Optional<StudentProfile> profile = studentProfileRepository.findById(id);
        if (profile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return profile.get();
    }

    public ResponseEntity<String> deleteStudentProfileById(Long id) {
        studentProfileRepository.deleteById(id);
        return new ResponseEntity<>("Student profile deleted successfully.", HttpStatus.ACCEPTED);
    }

}
