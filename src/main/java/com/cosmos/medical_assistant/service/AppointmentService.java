package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.dto.AppointmentInfo;
import com.cosmos.medical_assistant.model.Appointment;
import com.cosmos.medical_assistant.model.Doctor;
import com.cosmos.medical_assistant.repository.AppointmentRepository;
import com.cosmos.medical_assistant.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public List<AppointmentInfo> findAvailableAppointments(String specialty, LocalDate date){
        var doctors = doctorRepository.findBySpecialtyIgnoreCase(specialty);

        if(doctors.isEmpty()){
            log.info("No se encontraron médicos con la especialidad: {}", specialty);
            return List.of();
        }

        var doctorNames = buildDoctorNameMap(doctors);
        var doctorIds = new ArrayList<>(doctorNames.keySet());
        var appointments = appointmentRepository.findByDoctorIdInAndDateAndAvailableTrue(doctorIds, date);

        return toApointmentInfoList(appointments, doctorNames, specialty);
    }


    private Map<Long, String> buildDoctorNameMap(List<Doctor> doctors){
        return doctors
            .stream()
            .collect(Collectors.toMap(
                Doctor::getId,
                d -> d.getFirstName() + " " + d.getLastName()
            ));
    }

    private List<AppointmentInfo> toApointmentInfoList(
        List<Appointment> appointments,
        Map<Long, String> doctorNames,
        String specialty
    ){
        return appointments.stream()
            .map( a -> new AppointmentInfo(
                doctorNames.get(a.getDoctorId()),
                specialty,
                a.getDate().toString(),
                a.getStartTime().toString()
            )).toList();
    }
}
