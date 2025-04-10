package io.medicorum.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private String displayName;
    private String profilePictureUrl;
    private Date birthday;
    private Adress firstAdress;
    private Adress secondAdress;
    private PhoneNumber personalPhoneNumber;
    private PhoneNumber professionalPhoneNumber;

}
