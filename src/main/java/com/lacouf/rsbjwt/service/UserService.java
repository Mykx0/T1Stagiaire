package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.Student;
import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final StudentRepository studentRepo;

    public UserService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public UserDTO CreateStudent(String firstName, String lastName, String email, String password) {
        var student = studentRepo.save(new Student(firstName, lastName, email, password));
        return new UserDTO(student);
    }
    public UserDTO GetUser (long id) {
        return new UserDTO(studentRepo.getReferenceById(id));
    }
}
