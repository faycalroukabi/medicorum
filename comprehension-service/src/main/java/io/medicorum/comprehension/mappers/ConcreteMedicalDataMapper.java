package io.medicorum.comprehension.mappers;

import io.medicorum.comprehension.dtos.ConcreteMedicalResponseDto;
import io.medicorum.comprehension.models.ConcreteMedicalData;

public class ConcreteMedicalDataMapper {

    public static ConcreteMedicalResponseDto mapToConcreteMedicalDataDto(ConcreteMedicalData concreteMedicalData){

        ConcreteMedicalResponseDto concreteMedicalResponseDto = new ConcreteMedicalResponseDto();
        concreteMedicalResponseDto.setId(concreteMedicalData.getId());
        concreteMedicalResponseDto.setPatientId(concreteMedicalData.getPatientId());
        concreteMedicalResponseDto.setDoctorId(concreteMedicalData.getDoctorId());
        concreteMedicalResponseDto.setDiscussionId(concreteMedicalData.getDiscussionId());
        concreteMedicalResponseDto.setAllowVisibility(concreteMedicalData.getAllowVisibility());
        concreteMedicalData.getMedicalInfos().forEach(medicalInfo ->
                concreteMedicalResponseDto.getMedicalInfoDtos().add(MedicalInfoMapper.mapToMedicalInfoDto(medicalInfo)));

        return concreteMedicalResponseDto;
    }

}
