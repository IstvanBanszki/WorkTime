package hu.unideb.worktime.api.model.login;

import java.io.Serializable;

public class LoginRecord implements Serializable {

    private final int workerId;
    private final String roleName;
    private final String password;

    public LoginRecord(int workerId, String roleName, String password) {
        this.workerId = workerId;
        this.roleName = roleName;
        this.password = password;
    }

    public int getWorkerId() {
        return workerId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.workerId;
        hash = 59 * hash + (this.roleName != null ? this.roleName.hashCode() : 0);
        hash = 59 * hash + (this.password != null ? this.password.hashCode() : 0);
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
        final LoginRecord other = (LoginRecord) obj;
        return this.workerId != other.workerId &&
               (this.roleName != null ? this.roleName.equals(other.roleName) : other.roleName == null) &&
               (this.password != null ? this.password.equals(other.password) : other.password == null);
    }

    @Override
    public String toString() {
        return "LoginRecord{" + "workerId=" + workerId + ", roleName=" + roleName + ", password=" + password + '}';
    }
}
