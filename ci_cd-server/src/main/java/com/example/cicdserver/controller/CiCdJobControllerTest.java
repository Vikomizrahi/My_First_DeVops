package com.example.cicdserver.controller;

import com.example.cicdserver.dto.CiCdJobDTO;
import com.example.cicdserver.service.CiCdJobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CiCdJobControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CiCdJobService ciCdJobService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testCreateJob() throws Exception {
        CiCdJobDTO job = new CiCdJobDTO();
        job.setJobName("Test Job");
        job.setStatus("Pending");

        when(ciCdJobService.createJob(any(CiCdJobDTO.class))).thenReturn(job);

        mockMvc.perform(post("/api/jobs")
                        .contentType("application/json")
                        .content("{ \"jobName\": \"Test Job\", \"status\": \"Pending\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobName").value("Test Job"))
                .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    void testGetAllJobs() throws Exception {
        CiCdJobDTO job = new CiCdJobDTO();
        job.setJobName("Test Job");
        job.setStatus("Pending");

        when(ciCdJobService.getAllJobs()).thenReturn(Collections.singletonList(job));

        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobName").value("Test Job"))
                .andExpect(jsonPath("$[0].status").value("Pending"));
    }
}