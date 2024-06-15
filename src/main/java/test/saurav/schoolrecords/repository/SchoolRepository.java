package test.saurav.schoolrecords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.saurav.schoolrecords.domain.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
