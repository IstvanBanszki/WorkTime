package hu.unideb.worktime.api.model.addition;

import hu.unideb.worktime.api.model.Role;
import hu.unideb.worktime.api.model.User;
import java.io.Serializable;

public class UserExtended extends User implements Serializable {
    
    private final String emailAdress;

    public UserExtended(String emailAdress, String loginName, String password, Role role) {
        super(loginName, password, role);
        this.emailAdress = emailAdress;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + (this.emailAdress != null ? this.emailAdress.hashCode() : 0);
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
        return (this.emailAdress != null ? this.emailAdress.equals(other.emailAdress) : other.emailAdress == null);
    }

    @Override
    public String toString() {
        return "UserExtended{emailAdress=" + emailAdress + ", loginName=" + 
                this.getLoginName() + ", password=" + this.getPassword() + ", role=" + this.getRole() + '}';
    }
    
}
