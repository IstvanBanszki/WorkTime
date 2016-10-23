package hu.unideb.worktime.core.service;

import hu.unideb.worktime.api.model.Token;
import io.jsonwebtoken.Claims;

public interface ITokenService {
    
    Token generateToken(String loginName);
    boolean checkTokenValidity(Claims token);
    boolean checkTokenExpiration(Claims token);
    boolean checkTokenRole(Claims token, String uri);

}
