package hu.unideb.worktime.api.model.login;

import java.util.Objects;

public class LoginResponse {

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
        int hash = 5;
        hash = 53 * hash + this.workerId;
        hash = 53 * hash + Objects.hashCode(this.roleName);
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
        return this.workerId != other.workerId &&
              (this.roleName != null ? this.roleName.equals(other.roleName) : other.roleName == null);
    }

    @Override
    public String toString() {
        return "LoginResponse{" + "workerId=" + workerId + ", roleName=" + roleName + '}';
    }    
}
