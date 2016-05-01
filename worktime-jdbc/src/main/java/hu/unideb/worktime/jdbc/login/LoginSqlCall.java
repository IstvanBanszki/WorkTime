package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginSqlCall extends WTConnection{
    
    @Autowired
    private LoginSP loginSp;
    
    private Logger logger;
    
    public LoginSqlCall(){
        this.logger = LoggerFactory.getLogger(LoginSqlCall.class);
        try {
            this.loginSp.install(this.getDataSource());
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        }
    }
    
    public Integer authenticate(String loginName, String password){
        return loginSp.execute(loginName, password);
    }
}
