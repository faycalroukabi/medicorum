package com.medicorum.discussionservice.models;

import java.util.List;


public class Discussion {
    private String discussionId;
    private String senderId;
    private String recipientId;
    private List<Message> messageList;
}
