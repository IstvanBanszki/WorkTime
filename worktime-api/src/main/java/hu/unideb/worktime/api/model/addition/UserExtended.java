package hu.unideb.worktime.api.model.addition;

import hu.unideb.worktime.api.model.Role;
import hu.unideb.worktime.api.model.User;
import java.io.Serializable;

public class UserExtended extends User implements Serializable {
    
    private final String emailAddress;

    public UserExtended() {
        super();
        this.emailAddress = "";
    }

    public UserExtended(String emailAdress, String loginName, String password, Role role) {
        super(loginName, password, role);
        this.emailAddress = emailAdress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + (this.emailAddress != null ? this.emailAddress.hashCode() : 0);
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
        if(! super.equals(obj)) {
            return false;
        }
        final UserExtended other = (UserExtended) obj;
        return (this.emailAddress != null ? this.emailAddress.equals(other.emailAddress) : other.emailAddress == null);
    }

    @Override
    public String toString() {
        return "UserExtended{emailAddress=" + emailAddress + ", loginName=" + 
                this.getLoginName() + ", password=" + this.getPassword() + ", role=" + this.getRole() + '}';
    }
    
}
