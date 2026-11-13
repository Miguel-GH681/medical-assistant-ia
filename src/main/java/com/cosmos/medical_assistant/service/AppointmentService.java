package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.dto.AppointmentInfo;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    List<AppointmentInfo> findAvailableAppointments(String specialty, LocalDate date);
}
