package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.model.Discipline;
import com.lacouf.rsbjwt.presentation.dto.SignupDTO;
import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentRepository repo;
    @MockitoBean
    private UserService userService;

    @Test
    public void successfulStudentCreation() throws Exception {
        SignupDTO dto = new SignupDTO(
                "Edouard",
                "Lambert",
                "asdasd@gmail.com",
                "password",
                Discipline.ComputerScience
        );
        String signup =
                """
                {
                    "firstName": "Edouard",
                    "lastName": "Lambert",
                    "email": "asdasd@gmail.com",
                    "password": "password",
                    "discipline": "ComputerScience"
                }
                """;

        this.mockMvc
                .perform(
                        post("/api/register/student")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(signup)
                ).
                andExpect(status().isCreated());

        verify(userService).createStudent(dto.firstName(), dto.lastName(), dto.email(), dto.password(), dto.discipline());
    }

    @Test
    public void unsuccessfulStudentCreation() throws Exception {
        String signup =
                """
                {
                    "firstName": "",
                    "lastName": "Lambert",
                    "email": "asdasd@gmail.com",
                    "password": "password",
                    "discipline": "ComputerScience"
                }
                """;

        this.mockMvc
                .perform(
                        post("/api/register/student")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(signup)
                ).
                andExpect(status().is4xxClientError());
    }
}
