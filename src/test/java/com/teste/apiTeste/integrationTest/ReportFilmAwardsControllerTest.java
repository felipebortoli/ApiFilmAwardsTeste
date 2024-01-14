package com.teste.apiTeste.integrationTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.teste.apiTeste.infra.dto.response.ReportFilmAwardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReportFilmAwardsControllerTest {


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
    public void testListFilmsAwardsWinners() throws Exception {

        MvcResult result = mockMvc.perform(get("/api/report/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok")).andReturn();
        String response = result.getResponse().getContentAsString();

        JsonNode responseBody = this.objectMapper.readTree(response);
        ReportFilmAwardResponse reportResponse = this.objectMapper.readValue(responseBody.get("body").toString(), ReportFilmAwardResponse.class);

        assertEquals(1,reportResponse.getMax().size());
        assertEquals(1,reportResponse.getMin().size());
        assertEquals(1,Integer.parseInt(reportResponse.getMin().get(0).interval()));
        assertEquals(13,Integer.parseInt(reportResponse.getMax().get(0).interval()));
    }
}
