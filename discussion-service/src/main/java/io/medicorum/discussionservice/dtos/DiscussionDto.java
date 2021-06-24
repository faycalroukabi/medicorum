package io.medicorum.discussionservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionDto {
    private String discussionId;
    private String senderId;
    private String recipientId;
    private List<MessageDto> messageDtos;
}
