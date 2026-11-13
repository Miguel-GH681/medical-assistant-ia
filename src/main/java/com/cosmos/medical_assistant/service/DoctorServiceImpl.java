package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.dto.DoctorInfo;
import com.cosmos.medical_assistant.model.Doctor;
import com.cosmos.medical_assistant.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;

    @Override
    public List<DoctorInfo> searchDoctors(String query){
        return doctorRepository
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrSpecialtyContainingIgnoreCase(
                query, query, query
            )
            .stream()
            .map(this::toDoctorInfo)
            .toList();
    }

    private DoctorInfo toDoctorInfo(Doctor doctor){
        return new DoctorInfo(
            doctor.getFirstName(),
            doctor.getLastName(),
            doctor.getSpecialty(),
            doctor.getLicenseNumber(),
            doctor.getPhone(),
            doctor.getOffice()
        );
    }
}
