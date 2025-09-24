package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    // GET endpoint to fetch doctor availability by id and date
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestParam String date,
            @RequestHeader("Authorization") String token
    ) {
        try {
            // Validate JWT token
            String email = tokenService.validateTokenAndGetEmail(token.replace("Bearer ", ""));
            if (email == null) {
                return ResponseEntity.status(401).body("Invalid or expired token.");
            }

            LocalDate requestedDate = LocalDate.parse(date);
            List<String> availableSlots = doctorService.getAvailableSlots(doctorId, requestedDate);

            return ResponseEntity.ok().body(availableSlots);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}
