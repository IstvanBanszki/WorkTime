package hu.unideb.worktime.service.controller.login.v1;

import hu.unideb.worktime.jdbc.login.SqlCallLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	"password": "easy",
    }
    */
    @RequestMapping(value="getlogin", method=RequestMethod.POST)
    public String getLogin( @RequestParam(value = "login_name") String loginName,
                            @RequestParam(value = "password")   String password ){

        return sqlCallLogin.authenticate(loginName, password)+" value";
    }
	
    //TODO Dummy login service for test purposes
    @RequestMapping(value="/{user}", method=RequestMethod.GET)
    public String example( @PathVariable(value = "user") int userId ){
        System.out.println("Hello World" + userId);
	return "Hello World" + userId;
    }
}