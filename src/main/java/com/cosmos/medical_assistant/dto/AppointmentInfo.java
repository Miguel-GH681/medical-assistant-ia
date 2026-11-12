package com.cosmos.medical_assistant.dto;

public record AppointmentInfo(
   String doctorName,
   String specialty,
   String date,
   String time
) {
}
