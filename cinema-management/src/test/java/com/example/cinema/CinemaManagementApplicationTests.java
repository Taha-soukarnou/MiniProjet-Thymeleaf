package com.example.cinema;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CinemaManagementApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void requiredPagesRender() throws Exception {
        mockMvc.perform(get("/dashboard")).andExpect(status().isOk());
        mockMvc.perform(get("/films")).andExpect(status().isOk());
        mockMvc.perform(get("/films/new")).andExpect(status().isOk());
        mockMvc.perform(get("/seances")).andExpect(status().isOk());
        mockMvc.perform(get("/seances/new")).andExpect(status().isOk());
        mockMvc.perform(get("/billets")).andExpect(status().isOk());
        mockMvc.perform(get("/billets/new")).andExpect(status().isOk());
        mockMvc.perform(get("/stats")).andExpect(status().isOk());
    }
}
