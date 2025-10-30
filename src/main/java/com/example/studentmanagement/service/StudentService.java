package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void delete(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant", "id", id));
        studentRepository.delete(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public Collection<Object[]> findNbrStudentByYear() {
        return studentRepository.findNbrStudentByYear();
    }

    public Student findById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant", "id", id));
    }
}
