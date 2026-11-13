package com.cosmos.medical_assistant.dto;

public record PatientInfo(
    String firstName,
    String lastName,
    String dateOfBirth,
    String allergies,
    String conditions
) {
}
