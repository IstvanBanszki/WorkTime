package hu.unideb.worktime.core.service;

import hu.unideb.worktime.api.model.login.LoginResponse;
import hu.unideb.worktime.api.model.login.Password;
import hu.unideb.worktime.api.model.login.UpdatePasswordRequest;

public interface ILoginService {
    
    LoginResponse getLogin(String loginName, Password password);

    Integer updateLogin(String loginName, UpdatePasswordRequest updatePasswordRequest);
}
