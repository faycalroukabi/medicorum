package io.medicorum.comprehension.models;

import com.amazonaws.services.comprehendmedical.model.Trait;
import com.sun.istack.internal.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class MedicalInfo {

    @MongoId
    private String id;
    @NotNull
    private MedicalInfosCategory medicalInfosCategory;
    @NonNull
    private MedicalInfosType medicalInfosType;
    @NonNull
    private String medicalInformation;
    private List<Trait> medicalInformationTraits;
    private List<MedicalInfo> relatedMedicalInfos = new ArrayList<>();
}
