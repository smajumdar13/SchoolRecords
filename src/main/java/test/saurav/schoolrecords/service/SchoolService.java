package test.saurav.schoolrecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.saurav.schoolrecords.domain.School;
import test.saurav.schoolrecords.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public ResponseEntity<String> addOrUpdateSchool(School school) {
        try {
            schoolRepository.save(school);
            System.out.println("School added");
            return new ResponseEntity<>("School successfully added.", HttpStatus.CREATED);
        } catch (Exception ex) {
            schoolRepository.findSchoolByName(school.getName()).get();
            System.out.println("School already exists. Updated accordingly.");
            return new ResponseEntity<>("School already exists. Updated accordingly.", HttpStatus.CONFLICT);
        }
//        if (!schoolRepository.findSchoolByName(school.getName()).isEmpty()) {
//            return schoolRepository.findSchoolByName(school.getName()).get();
//        }
//        return schoolRepository.save(school);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public School findSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public Optional<School> findSchoolByName(String name) {
        return schoolRepository.findSchoolByName(name);
    }

}
