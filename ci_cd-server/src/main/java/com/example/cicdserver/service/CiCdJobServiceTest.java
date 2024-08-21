package com.example.cicdserver.service;

import com.example.cicdserver.dto.CiCdJobDTO;
import com.example.cicdserver.model.CiCdJob;
import com.example.cicdserver.repository.CiCdJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CiCdJobServiceTest {

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

    @Test
    void getJobById() {
        CiCdJob job = new CiCdJob();
        job.setId(1L);
        job.setJobName("Test Job");

        when(ciCdJobRepository.findById(1L)).thenReturn(Optional.of(job));

        CiCdJobDTO result = ciCdJobService.getJobById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Job", result.getJobName());
    }

    @Test
    void createJob() {
        // Given
        CiCdJobDTO jobDTO = new CiCdJobDTO();
        jobDTO.setJobName("New Job");
        jobDTO.setStatus("Pending");
        jobDTO.setJobType("Type B");

        // Mock repository save method
        when(ciCdJobRepository.save(any())).thenAnswer(invocation -> {
            CiCdJob savedJob = invocation.getArgument(0);
            savedJob.setId(1L); // Simulate saving with generated ID
            return savedJob;
        });

        // When
        CiCdJobDTO result = ciCdJobService.createJob(jobDTO);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId()); // Ensure ID is generated
        assertEquals("New Job", result.getJobName());
        assertEquals("Pending", result.getStatus());
        assertEquals("Type B", result.getJobType());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        verify(ciCdJobRepository, times(1)).save(any());
    }

    @Test
    void updateJob() {
        // Given
        Long jobId = 1L;
        CiCdJobDTO jobDetails = new CiCdJobDTO();
        jobDetails.setJobName("Updated Job");
        jobDetails.setStatus("In Progress");
        jobDetails.setJobType("Type C");

        CiCdJob existingJob = new CiCdJob();
        existingJob.setId(jobId);
        existingJob.setJobName("Old Job");
        existingJob.setStatus("Pending");
        existingJob.setCreatedAt(LocalDateTime.now());
        existingJob.setUpdatedAt(LocalDateTime.now());
        existingJob.setJobType("Type B");

        // Mock repository find and save methods
        when(ciCdJobRepository.findById(jobId)).thenReturn(Optional.of(existingJob));
        when(ciCdJobRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CiCdJobDTO result = ciCdJobService.updateJob(jobId, jobDetails);

        // Then
        assertNotNull(result);
        assertEquals(jobId, result.getId());
        assertEquals("Updated Job", result.getJobName());
        assertEquals("In Progress", result.getStatus());
        assertEquals("Type C", result.getJobType());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        verify(ciCdJobRepository, times(1)).findById(jobId);
        verify(ciCdJobRepository, times(1)).save(any());
    }

    @Test
    void getAllJobs() {
        // Given
        CiCdJob job1 = new CiCdJob(1L, "Job 1", "Pending", LocalDateTime.now(), LocalDateTime.now(), "Type A");
        CiCdJob job2 = new CiCdJob(2L, "Job 2", "In Progress", LocalDateTime.now(), LocalDateTime.now(), "Type B");
        List<CiCdJob> jobs = List.of(job1, job2);

        // Mock repository findAll method
        when(ciCdJobRepository.findAll()).thenReturn(jobs);

        // When
        List<CiCdJobDTO> result = ciCdJobService.getAllJobs();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming two jobs are returned
        assertEquals(1L, result.get(0).getId());
        assertEquals("Job 1", result.get(0).getJobName());
        assertEquals("Pending", result.get(0).getStatus());
        assertEquals("Type A", result.get(0).getJobType());
        assertNotNull(result.get(0).getCreatedAt());
        assertNotNull(result.get(0).getUpdatedAt());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Job 2", result.get(1).getJobName());
        assertEquals("In Progress", result.get(1).getStatus());
        assertEquals("Type B", result.get(1).getJobType());
        assertNotNull(result.get(1).getCreatedAt());
        assertNotNull(result.get(1).getUpdatedAt());
        verify(ciCdJobRepository, times(1)).findAll();
    }

    @Test
    void deleteJob() {
        // Given
        Long jobId = 1L;
        CiCdJob existingJob = new CiCdJob();
        existingJob.setId(jobId);
        existingJob.setJobName("Job to delete");
        existingJob.setStatus("Pending");
        existingJob.setCreatedAt(LocalDateTime.now());
        existingJob.setUpdatedAt(LocalDateTime.now());
        existingJob.setJobType("Type A");

        // Mock repository findById and delete methods
        when(ciCdJobRepository.findById(jobId)).thenReturn(Optional.of(existingJob));

        // When
        ciCdJobService.deleteJob(jobId);

        // Then
        verify(ciCdJobRepository, times(1)).findById(jobId);
        verify(ciCdJobRepository, times(1)).delete(existingJob);
    }

    @Test
    void getJobsByStatus() {
        // Given
        String status = "Pending";
        CiCdJob job1 = new CiCdJob(1L, "Job 1", "Pending", LocalDateTime.now(), LocalDateTime.now(), "Type A");
        CiCdJob job2 = new CiCdJob(2L, "Job 2", "Pending", LocalDateTime.now(), LocalDateTime.now(), "Type B");
        List<CiCdJob> jobs = List.of(job1, job2);

        // Mock repository findByStatus method
        when(ciCdJobRepository.findByStatus(status)).thenReturn(jobs);

        // When
        List<CiCdJobDTO> result = ciCdJobService.getJobsByStatus(status);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming two jobs are returned
        assertEquals(1L, result.get(0).getId());
        assertEquals("Job 1", result.get(0).getJobName());
        assertEquals("Pending", result.get(0).getStatus());
        assertEquals("Type A", result.get(0).getJobType());
        assertNotNull(result.get(0).getCreatedAt());
        assertNotNull(result.get(0).getUpdatedAt());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Job 2", result.get(1).getJobName());
        assertEquals("Pending", result.get(1).getStatus());
        assertEquals("Type B", result.get(1).getJobType());
        assertNotNull(result.get(1).getCreatedAt());
        assertNotNull(result.get(1).getUpdatedAt());
        verify(ciCdJobRepository, times(1)).findByStatus(status);
    }

    @Test
    void getJobsByJobType() {
        // Given
        String jobType = "Type A";
        CiCdJob job1 = new CiCdJob(1L, "Job 1", "Pending", LocalDateTime.now(), LocalDateTime.now(), "Type A");
        CiCdJob job2 = new CiCdJob(2L, "Job 2", "In Progress", LocalDateTime.now(), LocalDateTime.now(), "Type A");
        List<CiCdJob> jobs = List.of(job1, job2);

        // Mock repository findByJobType method
        when(ciCdJobRepository.findByJobType(jobType)).thenReturn(jobs);

        // When
        List<CiCdJobDTO> result = ciCdJobService.getJobsByJobType(jobType);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming two jobs are returned
        assertEquals(1L, result.get(0).getId());
        assertEquals("Job 1", result.get(0).getJobName());
        assertEquals("Pending", result.get(0).getStatus());
        assertEquals("Type A", result.get(0).getJobType());
        assertNotNull(result.get(0).getCreatedAt());
        assertNotNull(result.get(0).getUpdatedAt());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Job 2", result.get(1).getJobName());
        assertEquals("In Progress", result.get(1).getStatus());
        assertEquals("Type A", result.get(1).getJobType());
        assertNotNull(result.get(1).getCreatedAt());
        assertNotNull(result.get(1).getUpdatedAt());
        verify(ciCdJobRepository, times(1)).findByJobType(jobType);
    }


    @Test
    void getJobsByDateRange() {
        // Given
        LocalDateTime start = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2023, 12, 31, 23, 59);
        CiCdJob job1 = new CiCdJob(1L, "Job 1", "Pending", LocalDateTime.of(2023, 3, 15, 10, 0), LocalDateTime.now(), "Type A");
        CiCdJob job2 = new CiCdJob(2L, "Job 2", "In Progress", LocalDateTime.of(2023, 6, 20, 15, 30), LocalDateTime.now(), "Type B");
        List<CiCdJob> jobs = List.of(job1, job2);

        // Mock repository findByCreatedAtBetween method
        when(ciCdJobRepository.findByCreatedAtBetween(start, end)).thenReturn(jobs);

        // When
        List<CiCdJobDTO> result = ciCdJobService.getJobsByDateRange(start, end);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming two jobs are returned
        assertEquals(1L, result.get(0).getId());
        assertEquals("Job 1", result.get(0).getJobName());
        assertEquals("Pending", result.get(0).getStatus());
        assertEquals("Type A", result.get(0).getJobType());
        assertNotNull(result.get(0).getCreatedAt());
        assertNotNull(result.get(0).getUpdatedAt());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Job 2", result.get(1).getJobName());
        assertEquals("In Progress", result.get(1).getStatus());
        assertEquals("Type B", result.get(1).getJobType());
        assertNotNull(result.get(1).getCreatedAt());
        assertNotNull(result.get(1).getUpdatedAt());
        verify(ciCdJobRepository, times(1)).findByCreatedAtBetween(start, end);
    }

}