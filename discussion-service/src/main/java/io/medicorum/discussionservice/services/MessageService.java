package io.medicorum.discussionservice.services;

import io.medicorum.discussionservice.dtos.MessageDto;
import io.medicorum.discussionservice.mappers.MessageMapper;
import io.medicorum.discussionservice.models.Message;
import io.medicorum.discussionservice.models.MessageStatus;
import io.medicorum.discussionservice.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private DiscussionService  discussionService;
    @Autowired
    private MongoOperations mongoOperations;

    public MessageDto save(MessageDto messageDto){
        messageDto.setStatus(MessageStatus.RECEIVED);
        messageRepository.save(MessageMapper.mapMessage(messageDto));
        return messageDto;
    }

    public List<MessageDto> findChatMessages(String senderId, String recipientId){
        Optional<String> discussion = discussionService.getChatId(senderId,recipientId, false);
        List<Message> messages = discussion.map(discId -> messageRepository.findByDiscussionId(discId)).orElse(new ArrayList<>());
        if(messages.size()>0){
            updateMessagesStatus(senderId,recipientId,MessageStatus.DELIVERED);
        }
        return messages.stream().map(MessageMapper::mapMessageDto).collect(Collectors.toCollection(ArrayList::new));
    }

    private void updateMessagesStatus(String senderId, String recipientId, MessageStatus status) {
        Query query = new Query(Criteria.where("senderId").is(senderId).and("recipientId").is(recipientId));
        Update update = Update.update("status",status);
        mongoOperations.updateMulti(query,update,MessageStatus.class);
    }


}
