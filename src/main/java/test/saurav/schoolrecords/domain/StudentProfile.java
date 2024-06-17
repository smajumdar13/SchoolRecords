package test.saurav.schoolrecords.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_profile_seq")
    @SequenceGenerator(name = "student_profile_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    private String bio;

}
