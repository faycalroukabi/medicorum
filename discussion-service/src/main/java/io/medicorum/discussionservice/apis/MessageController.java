package io.medicorum.discussionservice.apis;

import io.medicorum.discussionservice.models.Message;
import io.medicorum.discussionservice.models.MessageNotification;
import io.medicorum.discussionservice.services.DiscussionService;
import io.medicorum.discussionservice.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MessageController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private MessageService messageService;
    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
        Optional<String> chatId = discussionService
                .getChatId(message.getSenderId(), message.getRecipientId(), true);
        message.setDiscussionId(chatId.get());
        Message saved = messageService.save(message);
        messagingTemplate.convertAndSendToUser(

        message.getRecipientId(),"/queue/messages",
                new MessageNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(messageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    @CrossOrigin("*")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(messageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    @CrossOrigin("*")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(messageService.findById(id));
    }
}
