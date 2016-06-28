package hu.unideb.worktime.core.controller.profile.v1;

import hu.unideb.worktime.api.model.profile.ProfileRecord;
import hu.unideb.worktime.jdbc.profile.SqlCallProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/profile/v1")
public class ProfileController {

    @Autowired
    private SqlCallProfile sqlCallProfile;

    @Async
    @RequestMapping(value = "/get/{workerId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ProfileRecord getProfile(@PathVariable Integer workerId) {
        return this.sqlCallProfile.getProfileData(workerId);
    }
}
