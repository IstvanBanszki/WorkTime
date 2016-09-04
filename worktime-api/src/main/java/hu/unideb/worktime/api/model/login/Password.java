package hu.unideb.worktime.api.model.login;

import java.io.Serializable;

public class Password implements Serializable {
    
    private String password;

    public Password() {
    }

    public String getPassword() {
        return this.password;
    }
}
