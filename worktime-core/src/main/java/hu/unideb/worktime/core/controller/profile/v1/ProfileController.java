package hu.unideb.worktime.core.controller.profile.v1;

import hu.unideb.worktime.core.controller.login.v1.LoginController;
import hu.unideb.worktime.jdbc.profile.SqlCallProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/profile/v1")
public class ProfileController {

    @Autowired
    private SqlCallProfile sqlCallProfile;
        
    private Logger logger;
    
    public ProfileController(){
        this.logger = LoggerFactory.getLogger(LoginController.class);
    }
    
}
