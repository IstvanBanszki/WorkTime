package hu.unideb.worktime.jdbc.profile;

import hu.unideb.worktime.api.model.profile.ProfileRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallProfile {
    
    @Autowired
    private SpGetProfile spProfile;
    private Logger logger;

    public SqlCallProfile() {
        this.logger = LoggerFactory.getLogger(SqlCallProfile.class);
    }
    
    public ProfileRecord getProfileData( int workerId ){
        ProfileRecord result = null;
        this.logger.info("Call get_profile_data SP with given parameters: {}", workerId);
        try {
            result = this.spProfile.execute(workerId);
            if(result == null){
                this.logger.debug("There is no such profile data in database! Key: {}", workerId);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_profile_data SP call: {}", ex);
        }
        this.logger.info("Result of get_profile_data SP: {}", result);
        return result;
    }
}
