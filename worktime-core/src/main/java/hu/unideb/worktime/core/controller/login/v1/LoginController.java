package hu.unideb.worktime.core.controller.login.v1;

import hu.unideb.worktime.api.model.login.LoginRequest;
import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.api.model.login.LoginResponse;
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
@RequestMapping(value = "/rest/login/v1")
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
	"loginName": "login",
	"password": "easy"
    }
     */
    @Async
    @RequestMapping(value = "/get", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public @ResponseBody LoginResponse getLogin(@RequestBody LoginRequest request) {
        LoginResponse result = null;

        this.logger.info("Calling /rest/login/v1/get webservice with the following key: {}", request);
        LoginRecord record = this.sqlCallLogin.authenticate(request.getLoginName());
        this.logger.info("Result of /rest/login/v1/get webservice: {}", record);

        if (record != null && this.wtEncryption.checkPassword(request.getPassword(), record.getPassword())) {
            result = new LoginResponse(record.getWorkerId(), record.getRoleName());
        }
        return result;
    }

    //TODO Dummy service for test purposes, after the app is finished, it should be deleted
    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public String example(@PathVariable(value = "user") int userId) {
        System.out.println("Hello World" + userId);
        return "Hello World" + userId;
    }
}
