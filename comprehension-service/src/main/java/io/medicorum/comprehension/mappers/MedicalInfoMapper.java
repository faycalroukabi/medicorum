package io.medicorum.comprehension.mappers;

import io.medicorum.comprehension.dtos.MedicalInfoDto;
import io.medicorum.comprehension.models.MedicalInfo;

import java.util.Optional;

public class MedicalInfoMapper {

    public static MedicalInfoDto mapToMedicalInfoDto(MedicalInfo medicalInfo){
        MedicalInfoDto medicalInfoDto = new MedicalInfoDto();
        medicalInfoDto.setId(medicalInfo.getId());
        medicalInfoDto.setCategoryDisplayName(medicalInfo.getMedicalInfosCategory().getDisplayName());
        medicalInfoDto.setCategoryCode(medicalInfo.getMedicalInfosCategory().getCode());
        medicalInfoDto.setTypeDisplayName(medicalInfo.getMedicalInfosType().getDisplayName());
        medicalInfoDto.setTypeCode(medicalInfo.getMedicalInfosType().getCode());
        medicalInfoDto.setMedicalInformation(medicalInfo.getMedicalInformation());
        medicalInfoDto.setMedicalInformationTraits(medicalInfo.getMedicalInformationTraits());
        Optional.ofNullable(medicalInfo.getRelatedMedicalInfos()).ifPresent(entities-> entities.forEach(
                att -> medicalInfoDto.getRelatedMedicalInfoDtos().add(mapToMedicalInfoDto(att))
        ));

        return medicalInfoDto;
    }

}
