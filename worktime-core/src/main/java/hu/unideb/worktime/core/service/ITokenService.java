package hu.unideb.worktime.core.service;

import hu.unideb.worktime.api.model.Token;

public interface ITokenService {
    
    Token generateToken(String loginName);
    boolean checkTokenValidity(String authorizationHeader);

}
