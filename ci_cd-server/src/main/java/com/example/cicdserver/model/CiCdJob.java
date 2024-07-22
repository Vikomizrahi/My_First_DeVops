package com.example.cicdserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "cicd_jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CiCdJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String jobName;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String jobType;

    @Column(nullable = false)
    private String password;

    public CiCdJob(Long id, String jobName, String status, LocalDateTime createdAt, LocalDateTime updatedAt, String jobType) {
        this.id = id;
        this.jobName = jobName;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jobType = jobType;
    }
}
