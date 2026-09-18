package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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
    private StudentService studentService;

    @Test
    public void successfulStudentCreation() throws Exception {
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

    @Test
    public void passwordTooShortUnsuccessfulStudentCreation() throws Exception {
        String signup =
                """
                {
                    "firstName": "Edouard",
                    "lastName": "Lambert",
                    "email": "asdasd@gmail.com",
                    "password": "ouiallo",
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

    @Test
    public void emailNotCorrectUnsuccessfulStudentCreation() throws Exception{
        String signup =
                """
                {
                    "firstName": "Edouard",
                    "lastName": "Lambert",
                    "email": "asdasd@",
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
