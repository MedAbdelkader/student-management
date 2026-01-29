import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;
import tn.esprit.studentmanagement.services.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialiser les données de test
        student1 = new Student();
        student1.setIdStudent(1L);
        student1.setFirstName("Ahmed");
        student1.setLastName("Khedira");
        student1.setEmail("ahmed@example.com");
        student1.setPhone("21612345678");
        student1.setDateOfBirth(LocalDate.of(2000, 5, 15));
        student1.setAddress("Tunis");

        student2 = new Student();
        student2.setIdStudent(2L);
        student2.setFirstName("Fatima");
        student2.setLastName("Ben Ali");
        student2.setEmail("fatima@example.com");
        student2.setPhone("21698765432");
        student2.setDateOfBirth(LocalDate.of(2001, 3, 20));
        student2.setAddress("Sfax");
    }

    @Test
    void testGetAllStudents_WithStudents() {
        // Arrange
        List<Student> expectedStudents = Arrays.asList(student1, student2);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> actualStudents = studentService.getAllStudents();

        // Assert
        assertNotNull(actualStudents);
        assertEquals(2, actualStudents.size());
        assertEquals("Ahmed", actualStudents.get(0).getFirstName());
        assertEquals("Fatima", actualStudents.get(1).getFirstName());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetAllStudents_EmptyList() {
        // Arrange
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Student> actualStudents = studentService.getAllStudents();

        // Assert
        assertNotNull(actualStudents);
        assertTrue(actualStudents.isEmpty());
        assertEquals(0, actualStudents.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetAllStudents_VerifyRepositoryCall() {
        // Arrange
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1));

        // Act
        studentService.getAllStudents();

        // Assert
        verify(studentRepository, times(1)).findAll();
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void testGetAllStudents_SingleStudent() {
        // Arrange
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1));

        // Act
        List<Student> actualStudents = studentService.getAllStudents();

        // Assert
        assertEquals(1, actualStudents.size());
        assertEquals("Ahmed", actualStudents.get(0).getFirstName());
        assertEquals("ahmed@example.com", actualStudents.get(0).getEmail());
    }
}

