package test.saurav.schoolrecords.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import test.saurav.schoolrecords.domain.Student;
import test.saurav.schoolrecords.repository.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Collections.*;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static test.saurav.schoolrecords.factories.StudentFactory.createStudent;

//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addsStudentToTheDatabase() {
        // Given
        Student student = createStudent();

        // When
        studentService.addStudent(student);

        // Then
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void addStudentThrowsEmailAlreadyExistsException() {
        // Given
        Student student = createStudent();
        when(studentRepository.save(student)).thenThrow(DuplicateKeyException.class);

        // When/Then
        assertThatThrownBy(() -> studentService.addStudent(student)).isInstanceOf(DuplicateKeyException.class)
                .hasMessage("The email already exists in a different record.");
    }

    @Test
    public void findAllStudentsReturnsListOfStudents() {
        // Given
        List<Student> expected = List.of(createStudent());
        when(studentRepository.findAll()).thenReturn(expected);

        // When
        List<Student> actual = studentService.findAllStudents();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllStudentsReturnsEmptyListWhenNoStudentPresent() {
        // Given
        when(studentRepository.findAll()).thenReturn(emptyList());

        // When
        List<Student> actual = studentService.findAllStudents();

        // Then
        assertThat(actual).isEqualTo(emptyList());
    }

    @Test
    public void findStudentByIdReturnsAStudent() {
        // Given
        Long id = nextLong();
        Student expected = new Student();
        expected.setId(id);
        when(studentRepository.findById(id)).thenReturn(Optional.of(expected));

        // When
        Student test = studentRepository.findById(id).get();
        Student actual = studentService.findStudentById(id);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findStudentByIdThrowsNotFoundExceptionIfStudentDoesNotExist() {
        // Given
        Long id = nextLong();
        when(studentRepository.findById(id)).thenThrow(NoSuchElementException.class);

        // When/Then
        assertThatThrownBy(() -> studentService.findStudentById(id)).isInstanceOf(NoSuchElementException.class)
                .hasMessage("The student with the id " + id + " doesn't exist.");
    }

    @Test
    public void deleteStudentByIdDeletesTheStudentRecord() {
        // Given
        Student student = new Student();
        Long id = nextLong();
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        // When
        studentService.deleteStudentById(id);

        // When/Then
        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    public void deleteStudentByIdThrowsExceptionWhenStudentDoesNotExist() {
        // Given
        Long id = nextLong();
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() ->studentService.deleteStudentById(id)).isInstanceOf(NoSuchElementException.class)
                .hasMessage("Student does not exist for the given id " + id);
    }




}