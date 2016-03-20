package com.worktime.service.login.v1;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/login")
public class LoginController {
	
    @RequestMapping(value="/{user}", method=RequestMethod.GET)
    public String example( @PathVariable Integer userId ){
	return "Hello World" + userId;
    }
}