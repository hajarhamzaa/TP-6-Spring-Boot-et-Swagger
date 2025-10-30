package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testSaveStudent() {
        // Arrange
        Student student = new Student();
        student.setNom("Doe");
        student.setPrenom("John");
        student.setDateNaissance(new Date());
        
        Student savedStudent = new Student();
        savedStudent.setId(1);
        savedStudent.setNom(student.getNom());
        savedStudent.setPrenom(student.getPrenom());
        savedStudent.setDateNaissance(student.getDateNaissance());
        
        when(studentService.save(any(Student.class))).thenReturn(savedStudent);

        // Act
        ResponseEntity<Student> response = studentController.save(student);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        assertEquals("Doe", response.getBody().getNom());
        assertEquals("John", response.getBody().getPrenom());
    }

    @Test
    void testDeleteStudent() {
        // Arrange
        int studentId = 1;
        Student student = new Student();
        student.setId(studentId);
        when(studentService.findById(studentId)).thenReturn(student);
        
        // Act & Assert
        assertDoesNotThrow(() -> studentController.delete(studentId));
    }

    @Test
    void testFindAllStudents() {
        // Arrange
        Student student1 = new Student();
        student1.setId(1);
        student1.setNom("Doe");
        student1.setPrenom("John");
        
        Student student2 = new Student();
        student2.setId(2);
        student2.setNom("Smith");
        student2.setPrenom("Jane");
        
        List<Student> students = Arrays.asList(student1, student2);
        when(studentService.findAll()).thenReturn(students);

        // Act
        List<Student> result = studentController.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Doe", result.get(0).getNom());
        assertEquals("Smith", result.get(1).getNom());
    }

    @Test
    void testCountStudents() {
        // Arrange
        when(studentService.countStudents()).thenReturn(5L);

        // Act
        long count = studentController.countStudents();

        // Assert
        assertEquals(5L, count);
    }

    @Test
    void testFindByYear() {
        // Arrange
        Object[] yearCount1 = new Object[]{2000, 3L};
        Object[] yearCount2 = new Object[]{2001, 2L};
        Collection<Object[]> yearCounts = Arrays.asList(yearCount1, yearCount2);
        
        when(studentService.findNbrStudentByYear()).thenReturn(yearCounts);

        // Act
        Collection<Object[]> result = studentController.findByYear();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        // Arrange
        int studentId = 1;
        Student student = new Student();
        student.setId(studentId);
        student.setNom("Doe");
        student.setPrenom("John");
        
        when(studentService.findById(studentId)).thenReturn(student);

        // Act
        Student result = studentController.findById(studentId);

        // Assert
        assertNotNull(result);
        assertEquals(studentId, result.getId());
        assertEquals("Doe", result.getNom());
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        int nonExistentId = 999;
        when(studentService.findById(nonExistentId))
            .thenThrow(new ResourceNotFoundException("Étudiant", "id", nonExistentId));

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            studentController.findById(nonExistentId);
        });
    }
}
