package com.cosmos.medical_assistant.repository;

import com.cosmos.medical_assistant.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdInAndDateAndAvailableTrue(List<Long> doctorIds, LocalDate date);
}
