package io.medicorum.auth.models;


import ch.qos.logback.classic.Logger;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Role {
    USER,
    HEALTHPROF,
    HEALTHINST,
    ADMIN,
    MODERATOR,
    HEALTHSTUD,
    APPRENTICE,
    COACH, SERVICE;

    public static Stream<Role> streamPrivileges() {
        return Stream.of(Role.values()).filter(role -> Arrays.asList(USER,HEALTHINST,HEALTHPROF).contains(role) ? false : true);
    }

    public static Stream<Role> stream(){
        return Stream.of(Role.values());
    }


}
