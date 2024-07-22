package com.example.cicdserver.service;

import com.example.cicdserver.dto.CiCdJobDTO;
import com.example.cicdserver.model.CiCdJob;
import com.example.cicdserver.repository.CiCdJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CiCdJobServiceExceptionTest {

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
    void testDeleteNonExistentJob() {
        when(ciCdJobRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ciCdJobService.deleteJob(1L));
    }

    @Test
    void testUpdateJobWithInvalidData() {
        when(ciCdJobRepository.findById(anyLong())).thenReturn(Optional.of(new CiCdJob()));

        CiCdJobDTO invalidJob = new CiCdJobDTO();
        invalidJob.setJobName(null);  // Invalid data

        assertThrows(RuntimeException.class, () -> ciCdJobService.updateJob(1L, invalidJob));
    }
}
