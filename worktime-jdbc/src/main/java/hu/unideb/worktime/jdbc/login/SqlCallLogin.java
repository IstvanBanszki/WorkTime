package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.jdbc.login.storedprocedure.SpUpdatePassword;
import hu.unideb.worktime.jdbc.login.storedprocedure.SpGetLogin;
import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.api.model.login.UpdatePasswordRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallLogin implements ISqlCallLogin {

    @Autowired private SpGetLogin spGetLogin;
    @Autowired private SpUpdatePassword spUpdatePassword;
    private Logger logger = LoggerFactory.getLogger(SqlCallLogin.class);

    @Override
    public LoginRecord getLoginRecord(String loginName) {
        LoginRecord result = null;
        this.logger.info("Call get_login SP with given parameters: {}", loginName);
        try {
            result = this.spGetLogin.getLoginRecord(loginName);
            if (result == null) {
                this.logger.debug("There is no such login user in database! Key: {}", loginName);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_login SP call: {}", ex);
        }
        this.logger.info("Result of get_login SP: {}", result);
        return result;
    }

    @Override
    public Integer updatePassword(UpdatePasswordRecord key) {
        Integer result = null;
        this.logger.info("Call change_password SP with given parameters: {}", key);
        try {
            result = this.spUpdatePassword.update(key);
            if (result == -1) {
                this.logger.debug("There is an error during update password! Key: {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during change_password SP call: {}", ex);
        }
        this.logger.info("Result of change_password SP: {}", result);
        return result;
    }

}
