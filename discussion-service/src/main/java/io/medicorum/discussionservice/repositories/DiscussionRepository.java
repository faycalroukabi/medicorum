package io.medicorum.discussionservice.repositories;

import io.medicorum.discussionservice.models.Discussion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscussionRepository extends MongoRepository<Discussion,String> {
    Optional<Discussion> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
