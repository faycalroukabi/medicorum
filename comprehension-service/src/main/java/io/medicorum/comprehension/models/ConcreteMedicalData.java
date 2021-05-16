package io.medicorum.comprehension.models;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class ConcreteMedicalData {
    @MongoId
    private String id;
    @NonNull
    private String patientId;
    @NonNull
    private String doctorId;
    @NonNull
    private String discussionId;
    @NonNull
    private Boolean allowVisibility = false;
    private List<MedicalInfo> medicalInfos = new ArrayList<>();
}
