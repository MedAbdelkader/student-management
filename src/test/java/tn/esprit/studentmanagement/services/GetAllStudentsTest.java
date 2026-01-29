package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllStudentsTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    void setUp() {
        // Créer 3 étudiants pour le test
        student1 = new Student();
        student1.setIdStudent(1L);
        student1.setFirstName("Ali");
        student1.setLastName("Belgacem");
        student1.setEmail("ali.belgacem@example.com");
        student1.setPhone("21612345678");
        student1.setDateOfBirth(LocalDate.of(2000, 1, 15));
        student1.setAddress("Tunis");

        student2 = new Student();
        student2.setIdStudent(2L);
        student2.setFirstName("Soumia");
        student2.setLastName("Mansour");
        student2.setEmail("soumia.mansour@example.com");
        student2.setPhone("21698765432");
        student2.setDateOfBirth(LocalDate.of(2001, 5, 20));
        student2.setAddress("Sfax");

        student3 = new Student();
        student3.setIdStudent(3L);
        student3.setFirstName("Karim");
        student3.setLastName("Bouaziz");
        student3.setEmail("karim.bouaziz@example.com");
        student3.setPhone("21625489630");
        student3.setDateOfBirth(LocalDate.of(2002, 3, 10));
        student3.setAddress("Bizerte");
    }

    @Test
    void testGetAllStudents_With3Students_Success() {
        // Arrange - Préparer une liste de 3 étudiants
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act - Exécuter la méthode getAllStudents
        List<Student> actualStudents = studentService.getAllStudents();

        // Assert - Vérifier les résultats
        assertNotNull(actualStudents, "La liste d'étudiants ne doit pas être null");
        assertEquals(3, actualStudents.size(), "La liste doit contenir 3 étudiants");

        // Vérifier les détails de chaque étudiant
        assertEquals("Ali", actualStudents.get(0).getFirstName(), "Le premier étudiant doit être Ali");
        assertEquals("Belgacem", actualStudents.get(0).getLastName());
        assertEquals("ali.belgacem@example.com", actualStudents.get(0).getEmail());
        assertEquals("Tunis", actualStudents.get(0).getAddress());

        assertEquals("Soumia", actualStudents.get(1).getFirstName(), "Le deuxième étudiant doit être Soumia");
        assertEquals("Mansour", actualStudents.get(1).getLastName());
        assertEquals("soumia.mansour@example.com", actualStudents.get(1).getEmail());
        assertEquals("Sfax", actualStudents.get(1).getAddress());

        assertEquals("Karim", actualStudents.get(2).getFirstName(), "Le troisième étudiant doit être Karim");
        assertEquals("Bouaziz", actualStudents.get(2).getLastName());
        assertEquals("karim.bouaziz@example.com", actualStudents.get(2).getEmail());
        assertEquals("Bizerte", actualStudents.get(2).getAddress());

        // Vérifier que le repository a été appelé une seule fois
        verify(studentRepository, times(1)).findAll();
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void testGetAllStudents_With3Students_VerifyAllData() {
        // Arrange
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert - Vérification complète de l'intégrité des données
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(3, result.size()),
                () -> assertEquals(1L, result.get(0).getIdStudent()),
                () -> assertEquals(2L, result.get(1).getIdStudent()),
                () -> assertEquals(3L, result.get(2).getIdStudent()),
                () -> assertEquals("Ali", result.get(0).getFirstName()),
                () -> assertEquals("Soumia", result.get(1).getFirstName()),
                () -> assertEquals("Karim", result.get(2).getFirstName()),
                () -> assertEquals("21612345678", result.get(0).getPhone()),
                () -> assertEquals("21698765432", result.get(1).getPhone()),
                () -> assertEquals("21625489630", result.get(2).getPhone())
        );
    }

    @Test
    void testGetAllStudents_With3Students_OrderPreserved() {
        // Arrange - Vérifier que l'ordre est préservé
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert
        assertEquals(3, result.size());
        assertEquals("Ali", result.get(0).getFirstName());
        assertEquals("Soumia", result.get(1).getFirstName());
        assertEquals("Karim", result.get(2).getFirstName());

        // Vérifier que les IDs sont dans l'ordre correct
        assertEquals(1L, result.get(0).getIdStudent());
        assertEquals(2L, result.get(1).getIdStudent());
        assertEquals(3L, result.get(2).getIdStudent());
    }
}

