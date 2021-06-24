package io.medicorum.discussionservice.repositories;

import io.medicorum.discussionservice.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {
    List<Message> findByDiscussionId(String discussionId);
}
