package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.api.model.login.UpdatePasswordRecord;

public interface ISqlCallLogin {
    
    LoginRecord getLoginRecord(String loginName);
    Integer updatePassword(UpdatePasswordRecord key);

}
