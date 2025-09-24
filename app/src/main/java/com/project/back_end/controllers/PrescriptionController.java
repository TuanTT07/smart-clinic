package com.project_back_end.controllers;

import com.project_back_end.models.Prescription;
import com.project_back_end.services.PrescriptionService;
import com.project_back_end.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private TokenService tokenService;

    // POST endpoint saves a prescription with token and request body validation
    @PostMapping
    public ResponseEntity<?> savePrescription(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody Prescription prescription
    ) {
        try {
            // validate token
            String email = tokenService.validateTokenAndGetEmail(token.replace("Bearer ", ""));
            if (email == null) {
                return ResponseEntity.status(401).body("Invalid or expired token.");
            }

            Prescription savedPrescription = prescriptionService.savePrescription(prescription);
            return ResponseEntity.ok().body(savedPrescription);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
