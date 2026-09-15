package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.Discipline;
import com.lacouf.rsbjwt.model.Student;
import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.repository.UserRepository;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final StudentRepository studentRepo;
    private final UserRepository userRepo;

    public UserService(StudentRepository studentRepo, UserRepository userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }
    // Generic user methods
    public UserDTO getUser(long id) {
        return new UserDTO(userRepo.getReferenceById(id));
    }

    // Student specific methods
    public UserDTO createStudent(String firstName, String lastName, String email, String password, Discipline discipline) {
        var student = studentRepo.save(new Student(firstName, lastName, email, password, discipline));
        return new UserDTO(student);
    }

}
