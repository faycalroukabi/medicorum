package io.medicorum.auth.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public class UserEventStream {

    String OUTPUT = "momentsUserChanged";

   /* @Output(OUTPUT)
    MessageChannel momentsUserChanged();*/
}
