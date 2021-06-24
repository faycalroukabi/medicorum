package io.medicorum.comprehension.services;

import io.medicorum.comprehension.models.ConcreteMedicalData;
import io.medicorum.comprehension.repositories.ConcreteMedicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcreteMedicalDataService {
    @Autowired
    ConcreteMedicalDataRepository concreteMedicalDataRepository;
    public ConcreteMedicalData retreiveMedicalData(String discussionId){
        return concreteMedicalDataRepository.findByDiscussionIdAndAllowVisibilityTrue(discussionId).orElseThrow(RuntimeException::new);
    }
}
