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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public ConcreteMedicalData createNewComprehensionContext(ConcreteMedicalRequestDto concreteMedicalRequestDto) {
        ConcreteMedicalData concreteMedicalData = new ConcreteMedicalData();
        Optional.ofNullable(concreteMedicalRequestDto.getMessageText()).ifPresent(
                txt -> {
                    extractMedicalInfos(getComprehensionResults(txt), concreteMedicalRequestDto);
                    concreteMedicalData.setDiscussionId(concreteMedicalRequestDto.getDiscussionId());
                }
        );
        Optional.ofNullable(concreteMedicalRequestDto.getAllowVisibility()).ifPresent(
                visibility -> concreteMedicalData.setAllowVisibility(visibility)
        );
        concreteMedicalData.setDoctorId(concreteMedicalRequestDto.getDoctorId());
        concreteMedicalData.setPatientId(concreteMedicalRequestDto.getPatientId());

        return concreteMedicalData;
    }

    public void extractMedicalInfos(List<Entity> entities, ConcreteMedicalRequestDto concreteMedicalDataDto) {
        ConcreteMedicalData concreteMedicalData = concreteMedicalDataRepository.findByDiscussionId(concreteMedicalDataDto.getDiscussionId()).orElse(new ConcreteMedicalData());

        if (CollectionUtils.isNullOrEmpty(concreteMedicalData.getMedicalInfos())) {
            entities.forEach(entity -> concreteMedicalData.getMedicalInfos().add(extractMedicalInfo(entity)));
        } else {

            entities.stream().filter(
                    entity -> concreteMedicalData.getMedicalInfos().stream().noneMatch(
                            medicalInfo -> compareMedicalInfos(entity, medicalInfo)

                    )
            ).forEach(entity -> concreteMedicalData.getMedicalInfos().add(extractMedicalInfo(entity)));
        }
    }

    public Boolean compareMedicalInfos(Entity entity, MedicalInfo medicalInfo) {
        return entity.getText().equals(medicalInfo.getMedicalInformation()) &&
                entity.getType().equals(medicalInfo.getMedicalInfosType().name()) &&
                entity.getCategory().equals(medicalInfo.getMedicalInfosCategory().name());
    }
}
