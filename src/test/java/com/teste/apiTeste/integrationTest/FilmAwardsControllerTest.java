package com.teste.apiTeste.integrationTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.apiTeste.infra.dto.request.FilmAwardsRequest;
import com.teste.apiTeste.infra.dto.response.FilmAwardsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FilmAwardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testList() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/filmaward/list"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();;

        String response = result.getResponse().getContentAsString();

        JsonNode responseBody = this.objectMapper.readTree(response);
        List<FilmAwardsResponse> listFilms = this.objectMapper.readValue(responseBody.get("body").toString(), List.class);

        assertEquals(206,listFilms.size());
    }

    @Test
    public void testShow() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/filmaward/show/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        FilmAwardsRequest request = new FilmAwardsRequest("teste 1",2024, new ArrayList<>(), new ArrayList<>(),true);
         mockMvc.perform(MockMvcRequestBuilders.post("/api/filmaward/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        UUID id = UUID.randomUUID();
        FilmAwardsRequest request = new FilmAwardsRequest("teste 1",2024, new ArrayList<>(), new ArrayList<>(),true);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/filmaward/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/filmaward/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}