package test.saurav.schoolrecords.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public record StudentResponseDto(@Id @GeneratedValue Long id,
                                 String firstName,
                                 String lastName) {
}
