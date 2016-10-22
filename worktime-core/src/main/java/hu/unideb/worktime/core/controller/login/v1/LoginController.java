package hu.unideb.worktime.core.controller.login.v1;

import hu.unideb.worktime.api.model.Role;
import hu.unideb.worktime.api.model.Token;
import hu.unideb.worktime.api.model.login.LoginResponse;
import hu.unideb.worktime.api.model.login.Password;
import hu.unideb.worktime.api.model.login.UpdatePasswordRequest;
import hu.unideb.worktime.core.service.ILoginService;
import hu.unideb.worktime.core.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/login/v1", produces = "application/json")
public class LoginController {

    @Autowired private ILoginService losingService;
    @Autowired private ITokenService tokenService;

    /*
    --------------------
    Example JSON content
    --------------------
    {
	"password": "easy"
    }
     */
    @Async
    @RequestMapping(value = "/logins/loginNames/{loginName}", method = RequestMethod.POST)
    public @ResponseBody LoginResponse getLogin(@PathVariable("loginName") String loginName, @RequestBody Password password) {
        return this.losingService.getLogin(loginName, password);
    }
    
    @Async
    @RequestMapping(value = "/logins/loginNames/{loginName}", method = RequestMethod.PUT)
    public @ResponseBody Integer updateLogin(@PathVariable("loginName") String loginName, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        return this.losingService.updateLogin(loginName, updatePasswordRequest);
    }

    @Async
    @RequestMapping(value = "/tokens/loginNames/{loginName}", method = RequestMethod.GET)
    public @ResponseBody Token getToken(@PathVariable("loginName") String loginName) {
        return this.tokenService.generateToken(loginName);
    }

}
