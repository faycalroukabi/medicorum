package io.medicorum.discussionservice.repositories;

import io.medicorum.discussionservice.models.Discussion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DiscussionRepository extends MongoRepository<Discussion,String> {
    Optional<Discussion> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
