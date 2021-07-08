package io.medicorum.auth.payload;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {

    @NonNull
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String jwt) {
        this.accessToken = jwt;
    }
}
