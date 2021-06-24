package io.medicorum.comprehension.models;


import lombok.Getter;

import static io.medicorum.comprehension.models.MedicalInfosCategory.*;

@Getter
public enum MedicalInfosType {
    STRENGTH(MEDICATION, "Strength", "STR"),
    DOSAGE(MEDICATION, "Dosage", "DOSE"),
    ROUTE_OR_MODE(MEDICATION, "Route or mode", "ROM"),
    FREQUENCY(MEDICATION, "Frequency", "FREQ"),
    GENERIC_NAME(MEDICATION, "Generic name", "GN"),
    BRAND_NAME(MEDICATION, "Brand name", "BN"),
    SYSTEM_ORGAN_SITE(ANATOMY, "System organ site", "SOS"),
    DIRECTION(ANATOMY, "Direction", "DIR"),
    DX_NAME(MEDICAL_CONDITION, "Diagnosis Name","DXN"),
    NAME(PROTECTED_HEALTH_INFORMATION, "Name", "NAME"),
    AGE(PROTECTED_HEALTH_INFORMATION,"Age", "AGE"),
    TREATMENT_NAME(null,"Treatment name","TN");

    private MedicalInfosCategory category;
    private String displayName;
    private String code;

    MedicalInfosType(MedicalInfosCategory category, String displayName, String code) {
        this.category = category;
        this.displayName = displayName;
        this.code = code;
    }
}
