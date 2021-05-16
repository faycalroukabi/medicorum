package io.medicorum.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privileges {
    private Boolean admin = false;
    private Boolean moderator = false;
    private Boolean healthstud = false;
    private Boolean apprentice = false;
    private Boolean coach = false;

    @SneakyThrows
    public Boolean get(String role) throws NoSuchFieldException {
        Class privileges = this.getClass();
        String getterNameFromRole =
                "get" + role.substring(0, 1).toUpperCase() + role.substring(1);
        Method getter = privileges.getDeclaredMethod(getterNameFromRole);
        return (Boolean) getter.invoke(this);
    }
}
