package test.saurav.schoolrecords.factories;

import test.saurav.schoolrecords.domain.StudentProfile;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class StudentProfileFactory {

    public static StudentProfile createStudentProfile() {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setBio(randomAlphabetic(30));

        return studentProfile;
    }
}
