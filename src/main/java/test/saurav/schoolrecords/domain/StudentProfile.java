package test.saurav.schoolrecords.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class StudentProfile {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    private String bio;

}
