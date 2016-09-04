package hu.unideb.worktime.api.model.login;

import java.io.Serializable;

public class UpdatePasswordRequest implements Serializable {
    
    private String oldPassword;
    private String newPassword;

    public UpdatePasswordRequest() {
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }
}
