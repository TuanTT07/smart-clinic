package com.example.clinic.service;

import com.example.clinic.model.Doctor;
import com.example.clinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // return available time slots for doctor on given date
    public List<String> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        Optional<Doctor> opt = doctorRepository.findById(doctorId);
        if (opt.isEmpty()) return Collections.emptyList();
        Doctor d = opt.get();
        String json = d.getAvailableTimes();
        // parse JSON (use Jackson or other) — simple mock parsing
        // For demo return mock
        return List.of("09:00","10:00","14:00");
    }

    // validate doctor login credentials
    public Map<String, Object> validateLogin(String email, String password) {
        Optional<Doctor> opt = doctorRepository.findByEmail(email);
        if (opt.isPresent() && opt.get().getPassword().equals(password)) {
            return Map.of("success", true, "doctorId", opt.get().getId());
        } else {
            return Map.of("success", false, "message", "Invalid credentials");
        }
    }
}
