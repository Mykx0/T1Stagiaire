package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.Student;
import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.repository.UserRepository;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final StudentRepository studentRepo;

    public UserService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void CreateStudent(String firstName, String lastName, String email, String password) {
        studentRepo.save(new Student(firstName, lastName, email, password));
    }
}
