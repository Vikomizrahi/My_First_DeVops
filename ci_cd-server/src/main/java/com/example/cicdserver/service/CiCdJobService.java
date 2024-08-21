package com.example.cicdserver.service;

/*
 * This service class was developed with the assistance of ChatGPT.
 */


import ch.qos.logback.classic.Logger;
import com.example.cicdserver.dto.CiCdJobDTO;
import com.example.cicdserver.model.CiCdJob;
import com.example.cicdserver.repository.CiCdJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CiCdJobService {

    @Autowired
    private CiCdJobRepository ciCdJobRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    private  Logger logger;


    @Autowired
    public CiCdJobService(CiCdJobRepository ciCdJobRepository, BCryptPasswordEncoder passwordEncoder) {
        this.ciCdJobRepository = ciCdJobRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public CiCdJobDTO createJob(CiCdJobDTO jobDTO) {
        CiCdJob job = new CiCdJob();
        job.setJobName(jobDTO.getJobName());
        job.setStatus(jobDTO.getStatus());
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());
        job.setJobType(jobDTO.getJobType());
        job.setPassword(passwordEncoder.encode("defaultPassword")); // Placeholder for password

        CiCdJob savedJob = ciCdJobRepository.save(job);
        return convertToDto(savedJob);
    }

    public CiCdJobDTO updateJob(Long id, CiCdJobDTO jobDetails) {
        try {
            CiCdJob job = ciCdJobRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Job not found"));

            // Update fields
            job.setJobName(jobDetails.getJobName());
            job.setStatus(jobDetails.getStatus());
            job.setUpdatedAt(LocalDateTime.now());
            job.setJobType(jobDetails.getJobType());

            // Save and return
            CiCdJob updatedJob = ciCdJobRepository.save(job);
            return convertToDto(updatedJob);
        } catch (Exception e) {
            logger.error("Error updating job with id: {}", id, e);
            throw e; // Re-throw to let the controller handle it
        }
    }

    public List<CiCdJobDTO> getAllJobs() {
        return ciCdJobRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CiCdJobDTO getJobById(Long id) {
        CiCdJob job = ciCdJobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        return convertToDto(job);
    }

    public void deleteJob(Long id) {
        CiCdJob job = ciCdJobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        ciCdJobRepository.delete(job);
    }

    public List<CiCdJobDTO> getJobsByStatus(String status) {
        return ciCdJobRepository.findByStatus(status).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CiCdJobDTO> getJobsByJobType(String jobType) {
        return ciCdJobRepository.findByJobType(jobType).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CiCdJobDTO> getJobsByDateRange(LocalDateTime start, LocalDateTime end) {
        return ciCdJobRepository.findByCreatedAtBetween(start, end).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CiCdJobDTO convertToDto(CiCdJob job) {
        CiCdJobDTO jobDTO = new CiCdJobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setJobName(job.getJobName());
        jobDTO.setStatus(job.getStatus());
        jobDTO.setCreatedAt(job.getCreatedAt());
        jobDTO.setUpdatedAt(job.getUpdatedAt());
        jobDTO.setJobType(job.getJobType());
        return jobDTO;
    }


}
