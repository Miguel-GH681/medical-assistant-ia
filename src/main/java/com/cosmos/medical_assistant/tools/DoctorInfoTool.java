package com.cosmos.medical_assistant.tools;

import com.cosmos.medical_assistant.dto.DoctorInfo;
import com.cosmos.medical_assistant.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DoctorInfoTool {
    private final DoctorService doctorService;

    @Tool(description = "Buscar información de un médico por nombre o especialidad. Usar cuando el usuario pregunte por datos de un doctor, " +
            "quién atiende una especialidad o información de contacto de un doctor.")
    public List<DoctorInfo> searchDoctors(
        @ToolParam(description = "Nombre o especialidad del médico que se están buscando") String query
    ){
        log.info("Tool invocada - searchDoctors: query = {}", query);
        return doctorService.searchDoctors(query);
    }
}
