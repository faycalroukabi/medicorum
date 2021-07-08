package io.medicorum.comprehension.services;

import com.amazonaws.services.comprehendmedical.AWSComprehendMedical;
import com.amazonaws.services.comprehendmedical.model.Attribute;
import com.amazonaws.services.comprehendmedical.model.DetectEntitiesV2Request;
import com.amazonaws.services.comprehendmedical.model.Entity;
import com.amazonaws.util.CollectionUtils;
import io.medicorum.comprehension.configuration.AwsComprehendMedicalClient;
import io.medicorum.comprehension.dtos.ConcreteMedicalRequestDto;
import io.medicorum.comprehension.models.ConcreteMedicalData;
import io.medicorum.comprehension.models.MedicalInfo;
import io.medicorum.comprehension.models.MedicalInfosCategory;
import io.medicorum.comprehension.models.MedicalInfosType;
import io.medicorum.comprehension.repositories.ConcreteMedicalDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicalInformationsExtractor {

    @Autowired
    AWSComprehendMedical comprehendMedical;
    @Autowired
    ConcreteMedicalDataRepository concreteMedicalDataRepository;

    @Bean
    private AWSComprehendMedical getClient() {
        return AwsComprehendMedicalClient.getClient();
    }

    public List<Entity> getComprehensionResults(String text){
        DetectEntitiesV2Request request = new DetectEntitiesV2Request();
        request.setText(text);
        return comprehendMedical.detectEntitiesV2(request).getEntities();
    }

    public MedicalInfo extractMedicalInfo(Entity entity) {
        MedicalInfo medicalInfo = new MedicalInfo();
        medicalInfo.setMedicalInfosType(MedicalInfosType.valueOf(entity.getType()));
        medicalInfo.setMedicalInfosCategory(MedicalInfosCategory.valueOf(entity.getCategory()));
        medicalInfo.setMedicalInformation(entity.getText());
        medicalInfo.setMedicalInformationTraits(entity.getTraits());
        Optional.ofNullable(entity.getAttributes()).ifPresent(entities -> entities.forEach(
                att -> medicalInfo.getRelatedMedicalInfos().add(extractMedicalInfo(att))
        ));

        return medicalInfo;
    }

    public MedicalInfo extractMedicalInfo(Attribute attribute) {
        MedicalInfo medicalInfo = new MedicalInfo();
        medicalInfo.setMedicalInfosType(MedicalInfosType.valueOf(attribute.getType()));
        medicalInfo.setMedicalInfosCategory(MedicalInfosCategory.valueOf(attribute.getCategory()));
        medicalInfo.setMedicalInformation(attribute.getText());
        medicalInfo.setMedicalInformationTraits(attribute.getTraits());
        return medicalInfo;
    }



    public ConcreteMedicalData extractMedicalInfos(ConcreteMedicalRequestDto concreteMedicalDataDto) {
        ConcreteMedicalData concreteMedicalData = concreteMedicalDataRepository.findByDiscussionId(concreteMedicalDataDto.getDiscussionId()).orElseGet(()->ConcreteMedicalData.builder().discussionId(concreteMedicalDataDto.getDiscussionId()).doctorId(concreteMedicalDataDto.getDoctorId()).patientId(concreteMedicalDataDto.getPatientId()).medicalInfos(new ArrayList<>()).allowVisibility(true).build());
        List<Entity> entities = getComprehensionResults(concreteMedicalDataDto.getMessageText());
        log.info(String.valueOf(entities));
        concreteMedicalData.setMedicalInfos(new ArrayList<>());
        entities.stream().forEach(entity -> concreteMedicalData.getMedicalInfos().add(extractMedicalInfo(entity)));

        return concreteMedicalDataRepository.save(concreteMedicalData);
    }

    public Boolean compareMedicalInfos(Entity entity, MedicalInfo medicalInfo) {
        return entity.getText().equals(medicalInfo.getMedicalInformation()) &&
                entity.getType().equals(medicalInfo.getMedicalInfosType().name()) &&
                entity.getCategory().equals(medicalInfo.getMedicalInfosCategory().name());
    }
}
