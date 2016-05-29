package hu.unideb.worktime.core.controller.profile.v1;

import hu.unideb.worktime.api.model.profile.ProfileRecord;
import hu.unideb.worktime.jdbc.profile.SqlCallProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger;

    public ProfileController() {
        this.logger = LoggerFactory.getLogger(ProfileController.class);
    }

    @Async
    @RequestMapping(value = "/getprofile/{workerId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ProfileRecord getProfile(@PathVariable Integer workerId) {
        ProfileRecord result = null;

        this.logger.info("Calling /rest/profile/v1/getprofile webservice with the following key: {}", workerId);
        result = this.sqlCallProfile.getProfileData(workerId);
        this.logger.info("Result of /rest/profile/v1/getprofile webservice: {}", result);

        return result;
    }
}
