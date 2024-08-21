package com.example.cicdserver.service;

/*
 * This test class was developed with the assistance of ChatGPT.
 */

import com.example.cicdserver.dto.CiCdJobDTO;
import com.example.cicdserver.model.CiCdJob;
import com.example.cicdserver.repository.CiCdJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CiCdJobServiceNestedTest {

    @Mock
    private CiCdJobRepository ciCdJobRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private CiCdJobService ciCdJobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreateJobTests {

        @Test
        void testAddJob() {
            CiCdJob job = new CiCdJob();
            job.setJobName("Test Job");
            job.setStatus("Pending");

            when(ciCdJobRepository.save(any(CiCdJob.class))).thenReturn(job);
            when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

            CiCdJobDTO jobDTO = new CiCdJobDTO();
            jobDTO.setJobName("Test Job");
            jobDTO.setStatus("Pending");

            CiCdJobDTO result = ciCdJobService.createJob(jobDTO);

            assertNotNull(result);
            assertEquals("Test Job", result.getJobName());
        }
    }

    @Nested
    class GetJobTests {

        @Test
        void testGetJob() {
            CiCdJob job = new CiCdJob();
            job.setId(1L);
            job.setJobName("Test Job");

            when(ciCdJobRepository.findById(1L)).thenReturn(Optional.of(job));

            CiCdJobDTO result = ciCdJobService.getJobById(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("Test Job", result.getJobName());
        }
    }
}
