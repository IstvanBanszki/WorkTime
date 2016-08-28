package hu.unideb.worktime.api.model.login;

import java.io.Serializable;

public class UpdatePasswordRecord implements Serializable {
    
    private final String loginName;
    private final String oldPassword;
    private final String newPassword;

    public UpdatePasswordRecord(String loginName, String oldPassword, String newPassword) {
        this.loginName = loginName;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.loginName != null ? this.loginName.hashCode() : 0;
        hash = 37 * hash + this.oldPassword != null ? this.oldPassword.hashCode() : 0;
        hash = 37 * hash + this.newPassword != null ? this.newPassword.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UpdatePasswordRecord other = (UpdatePasswordRecord) obj;
        return (this.loginName != null ? this.loginName.equals(other.loginName) : other.loginName == null) &&
               (this.oldPassword != null ? this.oldPassword.equals(other.oldPassword) : other.oldPassword == null) &&
               (this.newPassword != null ? this.newPassword.equals(other.newPassword) : other.newPassword == null);
    }

    @Override
    public String toString() {
        return "UpdatePasswordRecord{loginName=" + loginName + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword + '}';
    }
}
