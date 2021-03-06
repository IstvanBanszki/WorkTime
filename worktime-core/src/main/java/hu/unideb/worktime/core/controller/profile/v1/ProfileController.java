package hu.unideb.worktime.core.controller.profile.v1;

import hu.unideb.worktime.api.model.profile.ProfileRecord;
import hu.unideb.worktime.jdbc.profile.ISqlCallProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/profile/v1", produces = "application/json")
public class ProfileController {

    @Autowired
    private ISqlCallProfile sqlCallProfile;

    @Async
    @RequestMapping(value = "/profiles/workerIds/{workerId}", method = RequestMethod.GET)
    public @ResponseBody ProfileRecord getProfile(@PathVariable("workerId") Integer workerId) {
        return this.sqlCallProfile.getProfileData(workerId);
    }
}
