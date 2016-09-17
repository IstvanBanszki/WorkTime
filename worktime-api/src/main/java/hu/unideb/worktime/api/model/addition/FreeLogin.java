package hu.unideb.worktime.api.model.addition;

import java.io.Serializable;

public class FreeLogin implements Serializable {
    
    private final int id;
    private final String loginName;

    public FreeLogin(int id, String loginName) {
        this.id = id;
        this.loginName = loginName;
    }

    public int getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + (this.loginName != null ? this.loginName.hashCode() : 0);
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
        final FreeLogin other = (FreeLogin) obj;
        return (this.id == other.id) &&
               (this.loginName != null ? this.loginName.equals(other.loginName) : other.loginName == null);
    }

    @Override
    public String toString() {
        return "FreeLogin{id=" + id + ", loginName=" + loginName + '}';
    }
    
}
