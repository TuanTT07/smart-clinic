# Smart Clinic Management System - MySQL Schema Design

## Tables

### 1. Doctor
- doctor_id (INT, PK, AUTO_INCREMENT)
- name (VARCHAR(100), NOT NULL)
- email (VARCHAR(100), UNIQUE, NOT NULL)
- speciality (VARCHAR(100), NOT NULL)
- phone (VARCHAR(20))
- available_times (TEXT)

### 2. Patient
- patient_id (INT, PK, AUTO_INCREMENT)
- name (VARCHAR(100), NOT NULL)
- email (VARCHAR(100), UNIQUE, NOT NULL)
- phone (VARCHAR(20), UNIQUE)
- dob (DATE)
- address (VARCHAR(255))

### 3. Appointment
- appointment_id (INT, PK, AUTO_INCREMENT)
- doctor_id (INT, FK → Doctor.doctor_id)
- patient_id (INT, FK → Patient.patient_id)
- appointment_time (DATETIME, NOT NULL)
- status (ENUM('BOOKED', 'COMPLETED', 'CANCELLED'))

### 4. Prescription
- prescription_id (INT, PK, AUTO_INCREMENT)
- appointment_id (INT, FK → Appointment.appointment_id)
- patient_id (INT, FK → Patient.patient_id)
- doctor_id (INT, FK → Doctor.doctor_id)
- medication (TEXT, NOT NULL)
- notes (TEXT)

## Relationships
- A Doctor can have many Appointments.
- A Patient can have many Appointments.
- Each Appointment may have one Prescription.
