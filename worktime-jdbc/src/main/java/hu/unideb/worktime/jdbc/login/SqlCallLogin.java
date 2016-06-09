package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.api.model.login.LoginRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallLogin {

    @Autowired
    private SpLogin spLogin;
    private Logger logger;

    public SqlCallLogin() {
        this(LoggerFactory.getLogger(SqlCallLogin.class));
    }

    public SqlCallLogin(Logger logger) {
        this.logger = logger;
    }

    public LoginRecord authenticate(String loginName) {
        LoginRecord result = null;
        this.logger.info("Call get_login SP with given parameters: {}", loginName);
        try {
            result = this.spLogin.execute(loginName);
            if(result == null){
                this.logger.debug("There is no such login user in database! Key: {}", loginName);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_login SP call: {}", ex);
        }
        this.logger.info("Result of get_login SP: {}", result);
        return result;
    }
}
