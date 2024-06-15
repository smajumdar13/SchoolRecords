package test.saurav.schoolrecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.saurav.schoolrecords.domain.School;
import test.saurav.schoolrecords.repository.SchoolRepository;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public School findSchoolById(Long id) {
        return schoolRepository.findById(id).get();
    }

}
