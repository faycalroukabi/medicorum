package io.medicorum.auth.repositories;

import io.medicorum.auth.models.HealthProfessional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthProfessionalRepository extends MongoRepository<HealthProfessional,String> {
}
