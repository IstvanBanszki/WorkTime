package hu.unideb.worktime.api.model.login;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private final String loginName;
    private final String password;
    
    public LoginRequest() {
        this.loginName = "";
        this.password = "";
    }

    public LoginRequest(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.loginName != null ? this.loginName.hashCode() : 0);
        hash = 31 * hash + (this.password != null ? this.password.hashCode() : 0);
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
        final LoginRequest other = (LoginRequest) obj;
        if( (other.loginName == this.loginName) || 
            (this.loginName != null && this.loginName.equals(other.loginName)) ){
            return false;
        }
        if( (other.password == this.password) || 
            (this.password != null && this.password.equals(other.password)) ){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoginRequest{" + "loginName=" + loginName + ", password=" + password + '}';
    }
    
}
