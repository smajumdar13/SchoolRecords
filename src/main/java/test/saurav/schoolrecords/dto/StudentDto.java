package test.saurav.schoolrecords.dto;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "First name cannot be empty.") String firstName,
        @NotEmpty(message = "Last name cannot be empty.") String lastName,
        @NotEmpty(message = "Email cannot be empty.") String email,
        Long schoolId) {
}
