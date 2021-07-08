package io.medicorum.discussionservice.repositories;

import io.medicorum.discussionservice.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message,String> {
    List<Message> findByDiscussionId(String discussionId);
}
