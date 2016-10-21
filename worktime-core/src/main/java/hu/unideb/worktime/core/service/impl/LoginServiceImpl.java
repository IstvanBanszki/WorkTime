package hu.unideb.worktime.core.service.impl;

import hu.unideb.worktime.api.model.Role;
import hu.unideb.worktime.api.model.Token;
import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.api.model.login.LoginResponse;
import hu.unideb.worktime.api.model.login.Password;
import hu.unideb.worktime.api.model.login.UpdatePasswordRecord;
import hu.unideb.worktime.api.model.login.UpdatePasswordRequest;
import hu.unideb.worktime.core.cache.ILoginCache;
import hu.unideb.worktime.core.security.IEncryptionUtility;
import hu.unideb.worktime.core.service.ILoginService;
import hu.unideb.worktime.jdbc.login.ISqlCallLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    
    @Autowired private IEncryptionUtility encryptionUtility;
    @Autowired private ILoginCache loginCache;
    @Autowired private ISqlCallLogin sqlCallLogin;
    
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    
    @Override
    public LoginResponse getLogin(String loginName, Password password) {
        LoginResponse result = null;
    
        this.logger.info("Calling get logins cache following parameters - loginName: {}, passwords: {}", loginName, password.getPassword());
        LoginRecord record = this.loginCache.getByName(loginName);
        this.logger.info("Result of get logins cache calling: {}", record);

        if (record != null) {
            if (this.encryptionUtility.checkPassword(password.getPassword(), record.getPassword())) {
                result = new LoginResponse(record.getWorkerId(), record.getRoleName());
                this.logger.info("Result: " + result);
            } else {
                this.logger.info("The password was not matching!");
            }
        }
        return result;
    }
    
    @Override
    public Integer updateLogin(String loginName, UpdatePasswordRequest updatePasswordRequest) {
        Integer result = 0;
        
        this.logger.info("Calling get logins cache following parameters - loginName: {}, passwords: {}", loginName, updatePasswordRequest);
        LoginRecord loginRecord = this.loginCache.getByName(loginName);
        this.logger.info("Result of get logins cache calling: {}", loginRecord);
        
        if (loginRecord != null) {
            if (this.encryptionUtility.checkPassword(updatePasswordRequest.getOldPassword(), loginRecord.getPassword())) {
                UpdatePasswordRecord newRecord = new UpdatePasswordRecord(loginName, loginRecord.getPassword(), 
                        this.encryptionUtility.encryptPassword(updatePasswordRequest.getNewPassword()));
                this.loginCache.updateByName(new LoginRecord(loginRecord.getWorkerId(), loginRecord.getRoleName(), newRecord.getNewPassword()),
                        loginName);
                result = this.sqlCallLogin.updatePassword(newRecord);
            } else {
                result = 0;
                this.logger.info("The password was not matching!");
            }
        }
        return result;
    }

    @Override
    public Token generateToken(String loginName, Role role) {

        this.logger.info("Calling get logins cache following parameters - loginName: {}", loginName);
        LoginRecord record = this.loginCache.getByName(loginName);
        this.logger.info("Result of get logins cache calling: {}", record);
        
        Token token = null;
        if(record != null) {
            token = new Token(Jwts.builder().setSubject(loginName)
                .claim("roles", role).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
        }
        this.logger.info("Newly created token - {}", token);
        return token;
    }

}
