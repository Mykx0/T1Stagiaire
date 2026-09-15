package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.Discipline;
import com.lacouf.rsbjwt.model.Student;
import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.repository.UserRepository;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import com.lacouf.rsbjwt.service.exceptions.BadInputException;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

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

    private boolean areUserInputsCorrect(String firstName, String lastName, String email, String password) {
        return Stream.of(firstName, lastName, email, password)
                .noneMatch(userInput -> userInput == null || userInput.isBlank());
    }
    // Student specific methods
    public UserDTO createStudent(String firstName, String lastName, String email, String password, Discipline discipline) throws BadInputException {
        if (!areUserInputsCorrect(firstName, lastName, email, password)) {
            throw new BadInputException();
        }

        var student = studentRepo.save(new Student(firstName, lastName, email, password, discipline));
        return new UserDTO(student);
    }

}
