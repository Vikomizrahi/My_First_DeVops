package com.example.cicdserver.controller;

import com.example.cicdserver.dto.CiCdJobDTO;
import com.example.cicdserver.service.CiCdJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class CiCdJobController {

    private static final Logger logger = LoggerFactory.getLogger(CiCdJobController.class);

    private final CiCdJobService ciCdJobService;

    @Autowired
    public CiCdJobController(CiCdJobService ciCdJobService) {
        this.ciCdJobService = ciCdJobService;
    }

    @PostMapping
    public ResponseEntity<CiCdJobDTO> createJob(@RequestBody CiCdJobDTO job) {
        logger.info("Creating new job with name: {}", job.getJobName());
        CiCdJobDTO createdJob = ciCdJobService.createJob(job);
        logger.info("Job created: {}", createdJob);  // Add this line
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CiCdJobDTO> updateJob(@PathVariable Long id, @RequestBody CiCdJobDTO jobDetails) {
        logger.info("Updating job with id: {}", id);
        CiCdJobDTO updatedJob = ciCdJobService.updateJob(id, jobDetails);
        return ResponseEntity.ok(updatedJob);
    }

    @GetMapping
    public ResponseEntity<List<CiCdJobDTO>> getAllJobs() {
        logger.info("Fetching all jobs");
        List<CiCdJobDTO> jobs = ciCdJobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiCdJobDTO> getJobById(@PathVariable Long id) {
        logger.info("Fetching job with id: {}", id);
        CiCdJobDTO job = ciCdJobService.getJobById(id);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        logger.info("Deleting job with id: {}", id);
        ciCdJobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CiCdJobDTO>> getJobsByStatus(@PathVariable String status) {
        logger.info("Fetching jobs with status: {}", status);
        List<CiCdJobDTO> jobs = ciCdJobService.getJobsByStatus(status);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/type/{jobType}")
    public ResponseEntity<List<CiCdJobDTO>> getJobsByJobType(@PathVariable String jobType) {
        logger.info("Fetching jobs with type: {}", jobType);
        List<CiCdJobDTO> jobs = ciCdJobService.getJobsByJobType(jobType);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/date")
    public ResponseEntity<List<CiCdJobDTO>> getJobsByDateRange(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        logger.info("Fetching jobs created between: {} and {}", start, end);
        List<CiCdJobDTO> jobs = ciCdJobService.getJobsByDateRange(start, end);
        return ResponseEntity.ok(jobs);
    }
}
