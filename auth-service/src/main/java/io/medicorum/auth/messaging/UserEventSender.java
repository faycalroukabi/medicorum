package io.medicorum.auth.messaging;

import io.medicorum.auth.models.User;
import io.medicorum.auth.payload.UserEventPayload;
import org.springframework.messaging.support.MessageBuilder;

public class UserEventSender {

    private UserEventStream channels;

    public UserEventSender(UserEventStream channels) {
        this.channels = channels;
    }
/*
    public void sendUserCreated(User user) {
        log.info("sending user created event for user {}", user.getUsername());
        sendUserChangedEvent(convertTo(user, UserEventType.CREATED));
    }

    public void sendUserUpdated(User user) {
        log.info("sending user updated event for user {}", user.getUsername());
        sendUserChangedEvent(convertTo(user, UserEventType.UPDATED));
    }

    public void sendUserUpdated(User user, String oldPicUrl) {
        log.info("sending user updated (profile pic changed) event for user {}",
                user.getUsername());

        UserEventPayload payload = convertTo(user, UserEventType.UPDATED);
        payload.setOldProfilePicUrl(oldPicUrl);

        sendUserChangedEvent(payload);
    }

    private void sendUserChangedEvent(UserEventPayload payload) {

        Message<UserEventPayload> message =
                MessageBuilder
                        .withPayload(payload)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, payload.getId())
                        .build();

        channels.momentsUserChanged().send(message);

        log.info("user event {} sent to topic {} for user {}",
                message.getPayload().getEventType().name(),
                channels.OUTPUT,
                message.getPayload().getUsername());
    }

    private UserEventPayload convertTo(User user, UserEventType eventType) {
        return UserEventPayload
                .builder()
                .eventType(eventType)
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .displayName(user.getUserProfile().getDisplayName())
                .profilePictureUrl(user.getUserProfile().getProfilePictureUrl()).build();
    }*/
}
