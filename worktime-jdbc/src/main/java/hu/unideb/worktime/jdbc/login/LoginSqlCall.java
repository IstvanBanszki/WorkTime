package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginSqlCall extends WTConnection {

    @Autowired
    private LoginSP loginSp;
    private Logger logger;

    public LoginSqlCall() {
        this.logger = LoggerFactory.getLogger(LoginSqlCall.class);
    }

    @PostConstruct
    public void setSP() {
        try {
            this.loginSp.install(this.getDataSource());
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        }
    }

    public Integer authenticate(String loginName, String password) {
        Integer result = null;
        logger.info("Call get_login SP with given parameters: {}, {}", loginName, password);
        try {
            result = loginSp.execute(loginName, password);
        } catch(Exception ex){            
            logger.debug("There is an exception during get_login SP call: {}", ex);
        }
        logger.info("Result of get_login SP: {}", result);
        return result;
    }
}
