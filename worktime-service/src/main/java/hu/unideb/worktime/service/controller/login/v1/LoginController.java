package hu.unideb.worktime.service.controller.login.v1;

import hu.unideb.worktime.api.model.login.LoginRequest;
import hu.unideb.worktime.jdbc.login.SqlCallLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/login/v1")
public class LoginController {
    
    @Autowired
    SqlCallLogin sqlCallLogin;    
    
    /*
    --------------------
    Example JSON content
    --------------------
    {
	"login_name": "login",
	"password": "easy"
    }
    */
    @RequestMapping(value="/getlogin", method=RequestMethod.POST, headers = "Content-Type=application/json")
    public String getLogin( @RequestBody LoginRequest request ){
        return sqlCallLogin.authenticate(request.getLoginName(), request.getPassword())+"";
    }
	
    //TODO Dummy login service for test purposes
    @RequestMapping(value="/{user}", method=RequestMethod.GET)
    public String example( @PathVariable(value = "user") int userId ){
        System.out.println("Hello World" + userId);
	return "Hello World" + userId;
    }
}