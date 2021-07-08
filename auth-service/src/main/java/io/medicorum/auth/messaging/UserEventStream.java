package io.medicorum.auth.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface UserEventStream {

    String OUTPUT = "medicorumUserChanged";

    @Output(OUTPUT)
    MessageChannel medicorumUserChanged();
}
