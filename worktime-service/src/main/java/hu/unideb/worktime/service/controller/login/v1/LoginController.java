package hu.unideb.worktime.service.controller.login.v1;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/login/v1")
public class LoginController {
	
    //TODO Dummy login service for test purposes
    @RequestMapping(value="/{user}", method=RequestMethod.GET)
    public String example( @PathVariable(value = "user") int userId ){
        System.out.println("Hello World" + userId);
	return "Hello World" + userId;
    }
}