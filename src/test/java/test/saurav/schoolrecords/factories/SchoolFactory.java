package test.saurav.schoolrecords.factories;

import test.saurav.schoolrecords.domain.School;

import static org.apache.commons.lang3.RandomUtils.nextLong;

public class SchoolFactory {

    public static School createSchool() {
        School school = new School();
        school.setName("School" + nextLong());

        return school;
    }

}
