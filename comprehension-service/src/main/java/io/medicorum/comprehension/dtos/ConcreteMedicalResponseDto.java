package io.medicorum.comprehension.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConcreteMedicalResponseDto {
    private String id;
    private String patientId;
    private String doctorId;
    private String discussionId;
    private Boolean allowVisibility;
    private List<MedicalInfoDto> medicalInfoDtos = new ArrayList<>();
}
