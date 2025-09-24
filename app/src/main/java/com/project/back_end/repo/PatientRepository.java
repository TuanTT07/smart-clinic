package com.example.clinic.repository;

import com.example.clinic.model.Patient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);

    // retrieve by email or phone
    @Query("SELECT p FROM Patient p WHERE p.email = :identifier OR p.phone = :identifier")
    Optional<Patient> findByEmailOrPhone(@Param("identifier") String identifier);
}
