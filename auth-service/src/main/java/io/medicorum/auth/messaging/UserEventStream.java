package io.medicorum.auth.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserEventStream {

    String OUTPUT = "medicorumUserChanged";

    @Output( OUTPUT)
    MessageChannel momentsUserChanged();
}
