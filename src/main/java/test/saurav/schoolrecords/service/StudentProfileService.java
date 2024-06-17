package test.saurav.schoolrecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import test.saurav.schoolrecords.domain.StudentProfile;
import test.saurav.schoolrecords.repository.StudentProfileRepository;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;

    @Autowired
    public StudentProfileService(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    public ResponseEntity<String> addStudentProfile(StudentProfile studentProfile) {
            studentProfileRepository.save(studentProfile);
            System.out.println("created");
            return new ResponseEntity<>("StudentProfile successfully added.", HttpStatus.CREATED);
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

    public void updateProfileByStudentId(Long studentId, StudentProfile studentProfile) {
        StudentProfile profileToUpdate = studentProfileRepository.findStudentProfileByStudent_Id(studentId).orElseThrow(() -> new NoSuchElementException("Profile with student id " + studentId + " not found."));
        profileToUpdate.setBio(studentProfile.getBio());
        studentProfileRepository.save(profileToUpdate);
    }

    public String deleteStudentProfileById(Long id) {
        try {
            studentProfileRepository.deleteById(id);
            return "Student profile deleted successfully.";
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
