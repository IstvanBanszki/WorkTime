package hu.unideb.worktime.api.model;

import java.io.Serializable;

public class User implements Serializable {

    private final String loginName;
    private final String password;
    private final Role role;

    public User() {
        this.loginName = "";
        this.password = "";
        this.role = Role.NOT_SET;        
    }
    
    public User(String loginName, String password, Role role) {
        this.loginName = loginName;
        this.password = password;
        this.role = role;
    }
    
    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (this.loginName != null ? this.loginName.hashCode() : 0);
        hash = 13 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 13 * hash + (this.role != null ? this.role.hashCode() : 0);
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
        final User other = (User) obj;
        return (this.loginName != null ? this.loginName.equals(other.loginName) : other.loginName == null) &&
               (this.password != null ? this.password.equals(other.password) : other.password == null) &&
               (this.role != null ? this.role.equals(other.role) : other.role == null);
    }

    @Override
    public String toString() {
        return "User{loginName=" + loginName + ", password=" + password + ", role=" + role + '}';
    }

}
