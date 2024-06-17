package test.saurav.schoolrecords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.saurav.schoolrecords.domain.StudentProfile;

import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
//    @Query(value = "SELECT * FROM StudentProfile s WHERE s.student_id = :studentId", nativeQuery = true)
    Optional<StudentProfile> findStudentProfileByStudent_Id(Long studentId);

}
