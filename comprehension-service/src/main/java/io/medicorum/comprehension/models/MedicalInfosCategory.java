package io.medicorum.comprehension.models;


import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum MedicalInfosCategory {
    PROTECTED_HEALTH_INFORMATION("Protected health information", "PHI"),
    MEDICAL_CONDITION("Medical condition", "MC"),
    TIME_EXPRESSION("Time expression", "TE"),
    TEST_TREATMENT_PROCEDURE("Test treatment procedure", "TTP"),
    ANATOMY("Anatomy", "ANA"),
    MEDICATION("Medication","MED");
    private String displayName;
    private String code;
    MedicalInfosCategory(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }
    public static Stream<MedicalInfosCategory> stream(){
        return Stream.of(MedicalInfosCategory.values());
    }
    public static MedicalInfosCategory getByCode(String code){
        return stream().filter(medicalInfosCategory ->
            code.equals(medicalInfosCategory.getCode())
        ).findFirst().orElseThrow(RuntimeException::new);
    }
}
