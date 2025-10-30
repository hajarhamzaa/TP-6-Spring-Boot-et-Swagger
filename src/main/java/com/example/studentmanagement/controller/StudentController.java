package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Créer un nouvel étudiant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Étudiant créé avec succès",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Student.class)) }),
        @ApiResponse(responseCode = "400", description = "Données invalides fournies"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<Student> save(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un étudiant par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Étudiant supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Étudiant non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        studentService.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir tous les étudiants")
    @ApiResponse(responseCode = "200", description = "Liste des étudiants récupérée avec succès")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir le nombre total d'étudiants")
    @ApiResponse(responseCode = "200", description = "Nombre d'étudiants récupéré avec succès")
    public long countStudents() {
        return studentService.countStudents();
    }

    @GetMapping(value = "/by-year", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir le nombre d'étudiants par année de naissance")
    @ApiResponse(responseCode = "200", description = "Statistiques par année récupérées avec succès")
    public Collection<Object[]> findByYear() {
        return studentService.findNbrStudentByYear();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtenir un étudiant par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Étudiant trouvé"),
        @ApiResponse(responseCode = "404", description = "Étudiant non trouvé",
                    content = @Content)
    })
    public Student findById(@PathVariable int id) {
        return studentService.findById(id);
    }
}
