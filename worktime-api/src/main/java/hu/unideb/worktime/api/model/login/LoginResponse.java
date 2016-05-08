package hu.unideb.worktime.api.model.login;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private final int workerId;
    private final String roleName;

    public LoginResponse(int workerId, String roleName) {
        this.workerId = workerId;
        this.roleName = roleName;
    }

    public int getWorkerId() {
        return workerId;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.workerId;
        hash = 59 * hash + (this.roleName != null ? this.roleName.hashCode() : 0);
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
        final LoginResponse other = (LoginResponse) obj;
        if (this.workerId != other.workerId) {
            return false;
        }
        if ((other.roleName == this.roleName)
                || (this.roleName != null && this.roleName.equals(other.roleName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoginResponse{" + "workerId=" + workerId + ", roleName=" + roleName + '}';
    }
}
