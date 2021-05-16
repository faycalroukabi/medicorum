package io.medicorum.comprehension.dtos;

import com.amazonaws.services.comprehendmedical.model.Trait;
import io.medicorum.comprehension.models.MedicalInfosCategory;
import io.medicorum.comprehension.models.MedicalInfosType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicalInfoDto {

    private String id;
    private String typeDisplayName;
    private String typeCode;
    private String categoryDisplayName;
    private String categoryCode;
    private String medicalInformation;
    private List<Trait> medicalInformationTraits;
    private List<MedicalInfoDto> relatedMedicalInfoDtos = new ArrayList<>();

}
