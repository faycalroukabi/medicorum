package io.medicorum.auth.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@Component
public class JwtConfiguration {

    @Value("${security.jwt.uri:/auth/**}")
    private String uri;
    @Value("${security.jwt.header:Authorization}")
    private String header;
    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;
    @Value("${security.jwt.expiration:#{24*60*60*1000}}")
    private int expiration;
    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;
}
