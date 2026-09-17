package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.Discipline;
import com.lacouf.rsbjwt.model.Student;
import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository studentRepo;
    private final PasswordEncoder encoder;

    public StudentService(StudentRepository studentRepo, PasswordEncoder encoder) {
        this.studentRepo = studentRepo;
        this.encoder = encoder;
    }

    public UserDTO createStudent(String firstName, String lastName, String email, String password, Discipline discipline) {
        Student student = studentRepo.save(new Student(firstName, lastName, email, encoder.encode(password), discipline));
        return new UserDTO(student);
    }
}
