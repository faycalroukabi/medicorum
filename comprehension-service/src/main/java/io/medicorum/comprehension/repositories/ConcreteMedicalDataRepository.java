package io.medicorum.comprehension.repositories;

import io.medicorum.comprehension.models.ConcreteMedicalData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcreteMedicalDataRepository extends MongoRepository<ConcreteMedicalData,String> {

    Optional<ConcreteMedicalData> findByDiscussionId(String discussionId);

}
