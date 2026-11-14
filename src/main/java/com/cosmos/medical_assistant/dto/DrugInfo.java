package com.cosmos.medical_assistant.dto;

public record DrugInfo(
    String brandName,
    String genericName,
    String purpose,
    String warnings,
    String dosageAndAdministration
) {
}
