package com.example.cicdserver.service;

/*
 * This test class was developed with the assistance of ChatGPT.
 */

import com.example.cicdserver.dto.CiCdJobDTO;
import com.example.cicdserver.model.CiCdJob;
import com.example.cicdserver.repository.CiCdJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Ensures the database state is rolled back after each test
@Rollback
public class CiCdJobServiceIntegrationTest {

    @Autowired
    private CiCdJobService ciCdJobService;

    @Autowired
    private CiCdJobRepository ciCdJobRepository;

    @BeforeEach
    void setUp() {
        ciCdJobRepository.deleteAll(); // Clean up the repository before each test
    }

    @ParameterizedTest
    @CsvSource({
            "Pending",
            "Running",
            "Completed"
    })
    void testCreateJobWithVariousStatuses(String status) {
        // Given
        CiCdJobDTO jobDTO = new CiCdJobDTO();
        jobDTO.setJobName("Integration Test Job");
        jobDTO.setStatus(status);
        jobDTO.setJobType("TypeA");

        // When
        CiCdJobDTO result = ciCdJobService.createJob(jobDTO);

        // Then
        assertNotNull(result);
        assertEquals("Integration Test Job", result.getJobName());
        assertEquals(status, result.getStatus());
        assertNotNull(result.getId());

        // Verify that the job was persisted
        CiCdJob persistedJob = ciCdJobRepository.findById(result.getId()).orElse(null);
        assertNotNull(persistedJob);
        assertEquals(status, persistedJob.getStatus());
    }

    @Test
    void testGetAllJobs() {
        // Given
        CiCdJobDTO job1 = new CiCdJobDTO();
        job1.setJobName("Job 1");
        job1.setStatus("Pending");
        job1.setJobType("TypeA");

        CiCdJobDTO job2 = new CiCdJobDTO();
        job2.setJobName("Job 2");
        job2.setStatus("Running");
        job2.setJobType("TypeB");

        ciCdJobService.createJob(job1);
        ciCdJobService.createJob(job2);

        // When
        List<CiCdJobDTO> jobs = ciCdJobService.getAllJobs();

        // Then
        assertEquals(2, jobs.size());
    }

    @Test
    void testUpdateJob() {
        // Given
        CiCdJobDTO jobDTO = new CiCdJobDTO();
        jobDTO.setJobName("Original Job");
        jobDTO.setStatus("Pending");
        jobDTO.setJobType("TypeA");
        CiCdJobDTO savedJob = ciCdJobService.createJob(jobDTO);

        // When
        savedJob.setJobName("Updated Job");
        CiCdJobDTO updatedJob = ciCdJobService.updateJob(savedJob.getId(), savedJob);

        // Then
        assertNotNull(updatedJob);
        assertEquals("Updated Job", updatedJob.getJobName());
    }

    @Test
    void testDeleteJob() {
        // Given
        CiCdJobDTO jobDTO = new CiCdJobDTO();
        jobDTO.setJobName("Job to Delete");
        jobDTO.setStatus("Pending");
        jobDTO.setJobType("TypeA");
        CiCdJobDTO savedJob = ciCdJobService.createJob(jobDTO);

        // When
        ciCdJobService.deleteJob(savedJob.getId());

        // Then
        assertFalse(ciCdJobRepository.findById(savedJob.getId()).isPresent());
    }
}
