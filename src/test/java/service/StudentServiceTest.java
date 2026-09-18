package service;

import com.lacouf.rsbjwt.model.Discipline;
import com.lacouf.rsbjwt.model.Student;
import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.repository.UserRepository;
import com.lacouf.rsbjwt.service.StudentService;
import com.lacouf.rsbjwt.service.UserService;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testFindStudent () {
        Student mockStudent = new Student("edouard", "lambert", "asdasd@gmail.com","password", Discipline.ComputerScience);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockStudent));

        UserDTO student = userService.getUser(1L);

        assertNotNull(student);
        assertEquals("edouard", student.getFirstName());
        //assert that
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateStudent() {
        Student mockStudent = new Student("edouard", "lambert", "asdasd@gmail.com","password", Discipline.ComputerScience);
        when(passwordEncoder.encode("password")).thenReturn("passwordEncoded");
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(mockStudent);

        UserDTO dto = studentService.createStudent
                ("edouard", "lambert", "asdasd@gmail.com", "password", Discipline.ComputerScience);

        assertNotNull(dto);
        assertEquals(mockStudent.getFirstName(), dto.getFirstName());
        verify(passwordEncoder, times(1)).encode("password");

    }
}
