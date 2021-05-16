package io.medicorum.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @MongoId
    private String id;
    @NotBlank
    @Size(max = 16)
    private String username;
    @NotBlank
    @Size(max = 750)
    private String firstName;
    @NotBlank
    @Size(max = 750)
    private String lastName;
    @NotBlank
    @Size(max = 350)
    @Email
    private String email;
    @NotBlank
    @Size(max = 256)
    @JsonIgnore
    private String password;
    private Boolean active;
    private Privileges privileges;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    private Profile userProfile;
    private Set<Role> assignedRoles;

    {

        this.privileges = new Privileges();
        this.assignedRoles = new HashSet();
        this.roleAssign();
    }

    public void roleAssign() {
        this.getAssignedRoles().add(Role.USER);
        Role.streamPrivileges().filter(role -> {
            try {
                return this.privileges.get(StringUtils.lowerCase(role.name()));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        }).forEach(this.getAssignedRoles()::add);
    }

    public User(@NotBlank @Size(max = 16) String username,
                @NotBlank @Size(max = 350) @Email String email,
                @NotBlank @Size(max = 256) String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = true;
        this.roleAssign();
    }

    public User(@NotBlank @Size(max = 16) String username,
                @NotBlank @Size(max = 750) String firstName,
                @NotBlank @Size(max = 750) String lastName,
                @NotBlank @Size(max = 350) @Email String email,
                @NotBlank @Size(max = 256) String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = true;
        this.roleAssign();
    }


    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.active = user.active;
        this.userProfile = user.userProfile;
        this.assignedRoles = user.assignedRoles;
        this.roleAssign();
    }

}
