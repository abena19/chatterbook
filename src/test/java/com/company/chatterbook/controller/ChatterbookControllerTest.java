package com.company.chatterbook.controller;

import com.company.chatterbook.controllers.ChatterbookController;
import com.company.chatterbook.models.ChatterPost;
import com.company.chatterbook.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ChatterbookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Standard set up method, for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnListOfUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        mockMvc.perform(get("/users"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    @Test
    void shouldReturnUserWithGivenUsername() throws Exception {
        String testUserName = "Luis";

        mockMvc.perform(get("/user/" + testUserName))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value(testUserName));
    }

    @Test
    void shouldReturnChatterPostsForGivenUsername() throws Exception {
        String testUserName = "Luis";

        mockMvc.perform(get("/chatterposts/" + testUserName))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].message").exists());
    }

    @Test
    void shouldReturnNotFoundForNonExistingUser() throws Exception {
        String testUserName = "NonExistingUser";

        mockMvc.perform(get("/user/" + testUserName))
                .andExpect(status().isNotFound());
    }

}
