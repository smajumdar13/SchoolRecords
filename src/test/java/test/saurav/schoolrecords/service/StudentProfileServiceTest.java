package test.saurav.schoolrecords.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import test.saurav.schoolrecords.domain.StudentProfile;
import test.saurav.schoolrecords.repository.StudentProfileRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static test.saurav.schoolrecords.factories.StudentFactory.createStudent;
import static test.saurav.schoolrecords.factories.StudentProfileFactory.createStudentProfile;

class StudentProfileServiceTest {

    @Mock
    private StudentProfileRepository profileRepository;

    @InjectMocks
    private StudentProfileService profileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void addsStudentProfileToTheDatabase() {
//        // Given
//        StudentProfile profile = createStudentProfile();
//
//        // When
//        profileService.addStudentProfile(profile);
//
//        // Then
//        verify(profileRepository, times(1)).save(profile);
//    }
//
//    @Test
//    public void addStudentThrowsEmailAlreadyExistsException() {
//        // Given
//        StudentProfile profile = createStudentProfile();
//        when(profileRepository.save(profile)).thenThrow(DuplicateKeyException.class);
//
//        // When/Then
//        assertThatThrownBy(() -> profileService.addStudentProfile(profile)).isInstanceOf(DuplicateKeyException.class)
//                .hasMessage("The email already exists in a different record.");
//    }

    @Test
    public void findAllStudentProfilesReturnsListOfStudentProfiles() {
        // Given
        List<StudentProfile> expected = List.of(createStudentProfile());
        when(profileRepository.findAll()).thenReturn(expected);

        // When
        List<StudentProfile> actual = profileService.findAllStudentProfiles();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllStudentProfilessReturnsEmptyListWhenNoStudentPresent() {
        // Given
        when(profileRepository.findAll()).thenReturn(emptyList());

        // When
        List<StudentProfile> actual = profileService.findAllStudentProfiles();

        // Then
        assertThat(actual).isEqualTo(emptyList());
    }

    @Test
    public void findStudentProfileByIdReturnsAStudentProfile() {
        // Given
        StudentProfile expected = createStudentProfile();
        Long id = expected.getId();
        when(profileRepository.findById(id)).thenReturn(Optional.of(expected));

        // When
        StudentProfile actual = profileService.findStudentProfileById(id);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findStudentProfileByIdThrowsNotFoundExceptionIfProfileDoesNotExist() {
        // Given
        Long id = nextLong();
        when(profileRepository.findById(id)).thenThrow(NoSuchElementException.class);

        // When/Then
        assertThatThrownBy(() -> profileService.findStudentProfileById(id)).isInstanceOf(NoSuchElementException.class)
                .hasMessage("The student with the id " + id + " doesn't exist.");
    }

//    @Test
//    public void deleteStudentProfileByIdDeletesTheProfileRecord() {
//        // Given
//        StudentProfile profile = createStudentProfile();
//        Long id = profile.getId();
//        when(profileRepository.findById(id)).thenReturn(Optional.of(createStudentProfile()));
//
//        // When
//        profileService.deleteStudentById(id);
//
//        // When/Then
//        verify(profileRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    public void deleteStudentByIdThrowsExceptionWhenStudentDoesNotExist() {
//        // Given
//        Long id = nextLong();
//        when(profileRepository.findById(id)).thenReturn(Optional.empty());
//
//        // When/Then
//        assertThatThrownBy(() -> profileService.deleteStudentById(id)).isInstanceOf(NoSuchElementException.class)
//                .hasMessage("Student does not exist for the given id " + id);
//    }




}