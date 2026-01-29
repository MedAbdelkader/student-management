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

/**
 * Classe de test pour la méthode getAllStudents du StudentService
 * Tests avec 3 étudiants et vérifications complètes
 */
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
        // Initialiser les 3 étudiants de test
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

    /**
     * Test 1: Vérifier que getAllStudents retourne 3 étudiants avec succès
     */
    @Test
    void testGetAllStudents_Returns3Students_Success() {
        // Arrange - Préparer les données de mock
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act - Exécuter la méthode à tester
        List<Student> actualStudents = studentService.getAllStudents();

        // Assert - Vérifier que le test est un succès
        assertNotNull(actualStudents, "❌ FAIL: La liste d'étudiants ne doit pas être null");
        assertEquals(3, actualStudents.size(), "❌ FAIL: La liste doit contenir exactement 3 étudiants");

        // ✅ SUCCESS: Le nombre d'étudiants est correct
        System.out.println("✅ SUCCESS: La liste contient " + actualStudents.size() + " étudiants");

        // Vérifier les détails du premier étudiant
        assertEquals("Ali", actualStudents.get(0).getFirstName());
        assertEquals("Belgacem", actualStudents.get(0).getLastName());
        assertEquals("ali.belgacem@example.com", actualStudents.get(0).getEmail());
        assertEquals("Tunis", actualStudents.get(0).getAddress());

        // Vérifier les détails du deuxième étudiant
        assertEquals("Soumia", actualStudents.get(1).getFirstName());
        assertEquals("Mansour", actualStudents.get(1).getLastName());
        assertEquals("soumia.mansour@example.com", actualStudents.get(1).getEmail());
        assertEquals("Sfax", actualStudents.get(1).getAddress());

        // Vérifier les détails du troisième étudiant
        assertEquals("Karim", actualStudents.get(2).getFirstName());
        assertEquals("Bouaziz", actualStudents.get(2).getLastName());
        assertEquals("karim.bouaziz@example.com", actualStudents.get(2).getEmail());
        assertEquals("Bizerte", actualStudents.get(2).getAddress());

        // Vérifier que le repository a été appelé une seule fois
        verify(studentRepository, times(1)).findAll();

        // ✅ SUCCESS: Tous les checks sont passés
        System.out.println("✅ SUCCESS: Tous les checks pour 3 étudiants sont passés");
    }

    /**
     * Test 2: Vérifier l'intégrité complète des données pour 3 étudiants
     */
    @Test
    void testGetAllStudents_VerifyCompleteDataIntegrity_Success() {
        // Arrange
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert - Vérifications complètes avec assertAll
        assertAll(
                "✅ SUCCESS: Intégrité complète des 3 étudiants",
                () -> assertNotNull(result),
                () -> assertEquals(3, result.size()),

                // Vérifications étudiant 1
                () -> assertEquals(1L, result.get(0).getIdStudent()),
                () -> assertEquals("Ali", result.get(0).getFirstName()),
                () -> assertEquals("Belgacem", result.get(0).getLastName()),
                () -> assertEquals("21612345678", result.get(0).getPhone()),
                () -> assertEquals(LocalDate.of(2000, 1, 15), result.get(0).getDateOfBirth()),

                // Vérifications étudiant 2
                () -> assertEquals(2L, result.get(1).getIdStudent()),
                () -> assertEquals("Soumia", result.get(1).getFirstName()),
                () -> assertEquals("Mansour", result.get(1).getLastName()),
                () -> assertEquals("21698765432", result.get(1).getPhone()),
                () -> assertEquals(LocalDate.of(2001, 5, 20), result.get(1).getDateOfBirth()),

                // Vérifications étudiant 3
                () -> assertEquals(3L, result.get(2).getIdStudent()),
                () -> assertEquals("Karim", result.get(2).getFirstName()),
                () -> assertEquals("Bouaziz", result.get(2).getLastName()),
                () -> assertEquals("21625489630", result.get(2).getPhone()),
                () -> assertEquals(LocalDate.of(2002, 3, 10), result.get(2).getDateOfBirth())
        );

        System.out.println("✅ SUCCESS: Vérification complète de l'intégrité des 3 étudiants réussie");
    }

    /**
     * Test 3: Vérifier que l'ordre des 3 étudiants est préservé
     */
    @Test
    void testGetAllStudents_OrderPreserved_Success() {
        // Arrange
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert - Vérifier l'ordre
        assertEquals(3, result.size());
        assertEquals("Ali", result.get(0).getFirstName(), "Premier étudiant doit être Ali");
        assertEquals("Soumia", result.get(1).getFirstName(), "Deuxième étudiant doit être Soumia");
        assertEquals("Karim", result.get(2).getFirstName(), "Troisième étudiant doit être Karim");

        // Vérifier les IDs sont dans l'ordre correct
        assertEquals(1L, result.get(0).getIdStudent());
        assertEquals(2L, result.get(1).getIdStudent());
        assertEquals(3L, result.get(2).getIdStudent());

        System.out.println("✅ SUCCESS: L'ordre des 3 étudiants est préservé correctement");
    }

    /**
     * Test 4: Vérifier que chaque étudiant contient tous les champs requis
     */
    @Test
    void testGetAllStudents_AllFieldsPopulated_Success() {
        // Arrange
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert - Vérifier que tous les champs sont remplis pour chaque étudiant
        for (int i = 0; i < result.size(); i++) {
            Student student = result.get(i);
            assertNotNull(student.getIdStudent(), "ID ne doit pas être null pour étudiant " + (i + 1));
            assertNotNull(student.getFirstName(), "FirstName ne doit pas être null pour étudiant " + (i + 1));
            assertNotNull(student.getLastName(), "LastName ne doit pas être null pour étudiant " + (i + 1));
            assertNotNull(student.getEmail(), "Email ne doit pas être null pour étudiant " + (i + 1));
            assertNotNull(student.getPhone(), "Phone ne doit pas être null pour étudiant " + (i + 1));
            assertNotNull(student.getDateOfBirth(), "DateOfBirth ne doit pas être null pour étudiant " + (i + 1));
            assertNotNull(student.getAddress(), "Address ne doit pas être null pour étudiant " + (i + 1));
        }

        System.out.println("✅ SUCCESS: Tous les champs sont remplis pour les 3 étudiants");
    }

    /**
     * Test 5: Vérifier que le repository a été appelé exactement une fois
     */
    @Test
    void testGetAllStudents_RepositoryCalledOnce_Success() {
        // Arrange
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act - Exécuter la méthode à tester
        List<Student> result = studentService.getAllStudents();

        // Assert - Vérifier les interactions avec le mock
        verify(studentRepository, times(1)).findAll();
        verifyNoMoreInteractions(studentRepository);

        System.out.println("✅ SUCCESS: Le repository a été appelé exactement une fois");
    }
}

