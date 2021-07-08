package io.medicorum.discussionservice.services;

import io.medicorum.discussionservice.models.Discussion;
import io.medicorum.discussionservice.repositories.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    public Optional<String> getChatId(String senderId, String recipientId,boolean createIfNotExist){
        return Optional.ofNullable(discussionRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(Discussion::getDiscussionId)
                .orElseGet(() -> {
                    if(!createIfNotExist){return  null;}
                    String discussionId = String.format("%s_%s", senderId, recipientId);
                    Discussion senderRecipient = Discussion.builder().discussionId(discussionId)
                            .senderId(senderId).recipientId(recipientId).build();
                    Discussion recipientSender = Discussion.builder().discussionId(discussionId)
                            .senderId(recipientId).recipientId(senderId).build();
                    discussionRepository.save(senderRecipient);
                    discussionRepository.save(recipientSender);
                    return discussionId;}));
    }
}

