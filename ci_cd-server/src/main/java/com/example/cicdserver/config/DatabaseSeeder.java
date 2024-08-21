package com.example.cicdserver.config;

import com.example.cicdserver.model.CiCdJob;
import com.example.cicdserver.repository.CiCdJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final CiCdJobRepository ciCdJobRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DatabaseSeeder(CiCdJobRepository ciCdJobRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.ciCdJobRepository = ciCdJobRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @Override
    public void run(String... args)  {

        CiCdJob job1 = new CiCdJob(null, "Job 1", "Pending", LocalDateTime.now(), LocalDateTime.now(), "Type A", bCryptPasswordEncoder.encode("password1"));
        CiCdJob job2 = new CiCdJob(null, "Job 2", "Running", LocalDateTime.now(), LocalDateTime.now(), "Type B", bCryptPasswordEncoder.encode("password2"));
        CiCdJob job3 = new CiCdJob(null, "Job 3", "Completed", LocalDateTime.now(), LocalDateTime.now(), "Type C", bCryptPasswordEncoder.encode("password3"));

        // Save jobs to the database
        ciCdJobRepository.saveAll(Arrays.asList(job1, job2, job3));
    }
}