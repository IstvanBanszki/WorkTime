package hu.unideb.worktime.core.service;

import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.api.model.login.LoginResponse;
import hu.unideb.worktime.api.model.login.Password;
import hu.unideb.worktime.api.model.login.UpdatePasswordRecord;
import hu.unideb.worktime.api.model.login.UpdatePasswordRequest;
import hu.unideb.worktime.core.cache.ILoginCache;
import hu.unideb.worktime.core.security.WTEncryption;
import hu.unideb.worktime.jdbc.login.SqlCallLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    
    @Autowired private WTEncryption wtEncryption;
    @Autowired private ILoginCache loginCache;
    @Autowired private SqlCallLogin sqlCallLogin;
    
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    
    @Override
    public LoginResponse getLogin(String loginName, Password password) {
        LoginResponse result = null;
    
        this.logger.info("Calling get logins cache following parameters - loginName: {}, passwords: {}", loginName, password.getPassword());
        LoginRecord record = this.loginCache.getByName(loginName);
        this.logger.info("Result of get logins cache calling: {}", record);

        if (record != null) {
            if (this.wtEncryption.checkPassword(password.getPassword(), record.getPassword())) {
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
            if (this.wtEncryption.checkPassword(updatePasswordRequest.getOldPassword(), loginRecord.getPassword())) {
                UpdatePasswordRecord record = new UpdatePasswordRecord(loginName, loginRecord.getPassword(), 
                        this.wtEncryption.encryptPassword(updatePasswordRequest.getNewPassword()));
                this.loginCache.updateByName(new LoginRecord(loginRecord.getWorkerId(), loginRecord.getRoleName(), record.getNewPassword()),
                        loginName);
                result = this.sqlCallLogin.updatePassword(record);
            } else {
                result = 2;
                this.logger.info("The password was not matching!");
            }
        }
        return result;
    }

}
