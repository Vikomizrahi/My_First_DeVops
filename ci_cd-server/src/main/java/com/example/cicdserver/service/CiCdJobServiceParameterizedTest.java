package com.example.cicdserver.service;

import com.example.cicdserver.dto.CiCdJobDTO;
import com.example.cicdserver.model.CiCdJob;
import com.example.cicdserver.repository.CiCdJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class CiCdJobServiceParameterizedTest {

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

    @ParameterizedTest
    @CsvSource({
            "Pending, encodedPassword1",
            "Running, encodedPassword2",
            "Completed, encodedPassword3"
    })
    void testAddJobWithVariousStatuses(String status, String encodedPassword) {
        CiCdJob job = new CiCdJob();
        job.setJobName("Test Job");
        job.setStatus(status);

        when(ciCdJobRepository.save(any(CiCdJob.class))).thenReturn(job);
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);

        CiCdJobDTO jobDTO = new CiCdJobDTO();
        jobDTO.setJobName("Test Job");
        jobDTO.setStatus(status);

        CiCdJobDTO result = ciCdJobService.createJob(jobDTO);

        assertNotNull(result);
        assertEquals("Test Job", result.getJobName());
        assertEquals(status, result.getStatus());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Test Job 1",
            "2, Test Job 2",
            "3, Test Job 3"
    })
    void testGetJobByDifferentIds(Long id, String jobName) {
        CiCdJob job = new CiCdJob();
        job.setId(id);
        job.setJobName(jobName);

        when(ciCdJobRepository.findById(id)).thenReturn(Optional.of(job));

        CiCdJobDTO result = ciCdJobService.getJobById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(jobName, result.getJobName());
    }
}
