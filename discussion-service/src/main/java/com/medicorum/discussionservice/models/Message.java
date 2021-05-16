package com.medicorum.discussionservice.models;

import java.util.Date;

public class Message {
    private String id;
    private String discussionId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
    private MessageStatus status;
}
