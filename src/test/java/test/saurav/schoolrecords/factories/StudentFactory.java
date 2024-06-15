package test.saurav.schoolrecords.factories;

import test.saurav.schoolrecords.domain.School;
import test.saurav.schoolrecords.domain.Student;
import test.saurav.schoolrecords.domain.StudentProfile;

import java.util.Collections;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static test.saurav.schoolrecords.factories.SchoolFactory.createSchool;
import static test.saurav.schoolrecords.factories.StudentProfileFactory.createStudentProfile;

public class StudentFactory {

    public static Student createStudent() {
        Student student = new Student();
        student.setId(nextLong());
        student.setFirstName(randomAlphabetic(10));
        student.setLastName(randomAlphabetic(10));
        student.setEmail(randomAlphabetic(8) + "@gmail.com");
//        School school = createSchool();
//        school.setStudents(Collections.singletonList(student));
//        student.setSchool(school);
//        StudentProfile profile = createStudentProfile();
//        profile.setStudent(student);
//        student.setProfile(profile);

        return student;
    }
}
