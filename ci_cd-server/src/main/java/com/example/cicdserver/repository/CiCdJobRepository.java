package com.example.cicdserver.repository;

import com.example.cicdserver.model.CiCdJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface CiCdJobRepository extends JpaRepository<CiCdJob, Long> {
    List<CiCdJob> findByStatus(String status);

    List<CiCdJob> findByJobType(String jobType);

    List<CiCdJob> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
