package io.medicorum.comprehension.models;


import lombok.Getter;

import static io.medicorum.comprehension.models.MedicalInfosCategory.ANATOMY;
import static io.medicorum.comprehension.models.MedicalInfosCategory.MEDICATION;

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
    ;

    private MedicalInfosCategory category;
    private String displayName;
    private String code;

    MedicalInfosType(MedicalInfosCategory category, String displayName, String code) {
        this.category = category;
        this.displayName = displayName;
        this.code = code;
    }
}
