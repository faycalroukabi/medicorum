package io.medicorum.discussionservice.mappers;

import io.medicorum.discussionservice.dtos.MessageDto;
import io.medicorum.discussionservice.models.Message;

public class MessageMapper {
    public static Message mapMessage(MessageDto messageDto){
        return new Message();
    }
    public static MessageDto mapMessageDto(Message message){
        return new MessageDto();
    }
}
