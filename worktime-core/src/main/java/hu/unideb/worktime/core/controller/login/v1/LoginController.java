package hu.unideb.worktime.core.controller.login.v1;

import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.api.model.login.LoginResponse;
import hu.unideb.worktime.api.model.login.Password;
import hu.unideb.worktime.api.model.login.UpdatePasswordRecord;
import hu.unideb.worktime.api.model.login.UpdatePasswordRequest;
import hu.unideb.worktime.jdbc.login.SqlCallLogin;
import hu.unideb.worktime.core.security.WTEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/login/v1", consumes = "application/json", produces = "application/json")
public class LoginController {

    @Autowired
    private SqlCallLogin sqlCallLogin;

    @Autowired
    private WTEncryption wtEncryption;

    private Logger logger;

    public LoginController() {
        this.logger = LoggerFactory.getLogger(LoginController.class);
    }

    /*
    --------------------
    Example JSON content
    --------------------
    {
	"password": "easy"
    }
     */
    @Async
    @RequestMapping(value = "/loginName/{loginName}", method = RequestMethod.POST)
    public @ResponseBody LoginResponse getLogin(@PathVariable("loginName") String loginName, @RequestBody Password password) {
        LoginResponse result = null;
    
        this.logger.info("Calling /api/login/v1/loginName/{} POST webservice with the following password: {}", loginName, password.getPassword());
        LoginRecord record = this.sqlCallLogin.getLoginRecord(loginName);
        this.logger.info("Result of /api/login/v1/loginName/{} POST webservice: {}", record);

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

    @Async
    @RequestMapping(value = "/loginName/{loginName}", method = RequestMethod.PUT)
    public @ResponseBody Integer updateLogin(@PathVariable("loginName") String loginName, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        Integer result = 0;
        
        this.logger.info("Calling /api/login/v1/loginName/{} PUT webservice with the following old password: {}", loginName, updatePasswordRequest.getOldPassword());
        LoginRecord loginRecord = this.sqlCallLogin.getLoginRecord(loginName);
        this.logger.info("Result of /api/login/v1/loginName/{} PUT webservice: {}", loginRecord);
        
        if (loginRecord != null) {
            if (this.wtEncryption.checkPassword(updatePasswordRequest.getOldPassword(), loginRecord.getPassword())) {
                UpdatePasswordRecord record = new UpdatePasswordRecord(loginName, loginRecord.getPassword(), this.wtEncryption.encryptPassword(updatePasswordRequest.getNewPassword()));
                result = this.sqlCallLogin.updatePassword(record);
            } else {
                this.logger.info("The password was not matching!");
            }
        }
        return result;
    }
}
