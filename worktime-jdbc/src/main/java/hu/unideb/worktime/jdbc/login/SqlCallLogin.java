package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.api.model.login.LoginRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallLogin {

    @Autowired
    private SPLogin spLogin;
    private Logger logger;

    public SqlCallLogin() {
        this.logger = LoggerFactory.getLogger(SqlCallLogin.class);
    }

    public LoginRecord authenticate(String loginName) {
        LoginRecord result = null;
        logger.info("Call get_login SP with given parameters: {}", loginName);
        try {
            result = spLogin.execute(loginName);
            if(result == null){
                logger.debug("There is no such login user in database! Key: {}", loginName);
            }
        } catch (Exception ex) {
            logger.error("There is an exception during get_login SP call: {}", ex);
        }
        logger.info("Result of get_login SP: {}", result);
        return result;
    }
}
