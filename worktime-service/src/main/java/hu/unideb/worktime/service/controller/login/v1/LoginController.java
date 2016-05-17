package hu.unideb.worktime.service.controller.login.v1;

import hu.unideb.worktime.api.model.login.LoginKey;
import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.jdbc.login.SqlCallLogin;
import hu.unideb.worktime.service.security.WTEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/login/v1")
public class LoginController {
    @Autowired
    private SqlCallLogin sqlCallLogin;

    @Autowired
    private WTEncryption wtEncryption;
        
    private Logger logger;
    
    public LoginController(){
        this.logger = LoggerFactory.getLogger(LoginController.class);
    }
    /*
    --------------------
    Example JSON content
    --------------------
    {
	"login_name": "login",
	"password": "easy"
    }
    */
    @RequestMapping(value = "/getlogin", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public String getLogin(@RequestBody LoginKey request) {
        logger.info("Calling getlogin webservice with the following key: {}", request);
        LoginRecord record = sqlCallLogin.authenticate(request.getLoginName());
        logger.info("Result of getlogin webservice: {}", record);
        return wtEncryption.checkPassword(request.getPassword(), record.getPassword()) + "";
    }

    //TODO Dummy service for test purposes, after the app is finished, it should be deleted
    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public String example(@PathVariable(value = "user") int userId) {
        System.out.println("Hello World" + userId);
        return "Hello World" + userId;
    }
}
