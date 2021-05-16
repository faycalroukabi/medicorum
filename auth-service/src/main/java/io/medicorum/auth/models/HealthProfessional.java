package io.medicorum.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HealthProfessional extends User{
    public String professionalIdemnitySerialNumber;
    private Boolean valid;

    public HealthProfessional(@NotBlank @Size(max = 16) String username,
                              @NotBlank @Size(max = 350) @Email String email,
                              @NotBlank @Size(max = 256) String password,
                              Boolean valid) {
        super(username, email, password);
        this.valid = valid;
        if (Boolean.TRUE.equals(this.valid)) {
            this.getAssignedRoles().add(Role.HEALTHPROF);
        }
    }

    public HealthProfessional(final User user, Boolean valid) {
        super(user);
        this.valid = valid;
        if (Boolean.TRUE.equals(this.valid)) {
            this.getAssignedRoles().add(Role.HEALTHPROF);
        }
    }

    public HealthProfessional(@NotBlank @Size(max = 16) String username,
                              @NotBlank @Size(max = 750) String firstName,
                              @NotBlank @Size(max = 750) String lastName,
                              @NotBlank @Size(max = 350) @Email String email,
                              @NotBlank @Size(max = 256) String password,
                              Boolean valid) {
        super(username, firstName, lastName, email, password);
        this.valid = valid;
        if (Boolean.TRUE.equals(this.valid)) {
            this.getAssignedRoles().add(Role.HEALTHPROF);
        }
    }
}
