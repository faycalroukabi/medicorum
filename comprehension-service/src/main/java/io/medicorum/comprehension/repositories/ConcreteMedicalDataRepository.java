package io.medicorum.comprehension.repositories;

import io.medicorum.comprehension.models.ConcreteMedicalData;
import io.medicorum.comprehension.models.MedicalInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcreteMedicalDataRepository extends MongoRepository<ConcreteMedicalData,String> {

    Optional<ConcreteMedicalData> findByDiscussionIdAndAllowVisibilityTrue(String discussionId);
    List<ConcreteMedicalData> findAllByDoctorIdAndAllowVisibilityTrue(String doctorId);
    ConcreteMedicalData findByDoctorIdAndPatientIdAndAllowVisibilityTrue(String doctorId, String patientId);
    Optional<ConcreteMedicalData> findByDiscussionId(String discussionId);
}
