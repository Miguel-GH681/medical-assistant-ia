package com.cosmos.medical_assistant.tools;

import com.cosmos.medical_assistant.dto.AppointmentInfo;
import com.cosmos.medical_assistant.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppointmentSearchTool {
    private final AppointmentService appointmentService;

    @Tool(description = "Buscar turnos médicos disponibles para una especialidad y fecha. Usar cuando el " +
            "usuario pregunte por disponibilidad de turnos o citas médicas.")
    public List<AppointmentInfo> searchAppointment(
            @ToolParam(description = "Especialidad médica, por ejemplo: cardiología, pediatría") String specialty,
            @ToolParam(description = "Fecha de la cita en formato yyyy-MM-dd") String date
    ){
        log.info("Tool invocada - searchAppointments: specialty = {}, date = {}", specialty, date);
        return appointmentService.findAvailableAppointments(specialty, LocalDate.parse(date));
    }
}
