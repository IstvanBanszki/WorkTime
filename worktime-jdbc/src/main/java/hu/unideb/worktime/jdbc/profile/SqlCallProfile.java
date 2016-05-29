package hu.unideb.worktime.jdbc.profile;

import hu.unideb.worktime.api.model.profile.ProfileRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallProfile {
    
    @Autowired
    private SpProfile spProfile;
    private Logger logger;

    public SqlCallProfile() {
        this(LoggerFactory.getLogger(SqlCallProfile.class));
    }

    public SqlCallProfile(Logger logger) {
        this.logger = logger;
    }
    
    public ProfileRecord getProfileData( int workerId ){
        ProfileRecord result = null;
        logger.info("Call get_profile_data SP with given parameters: {}", workerId);
        try {
            result = spProfile.execute(workerId);
            if(result == null){
                logger.debug("There is no such profile data in database! Key: {}", workerId);
            }
        } catch (Exception ex) {
            logger.error("There is an exception during get_profile_data SP call: {}", ex);
        }
        logger.info("Result of get_profile_data SP: {}", result);
        return result;
    }
}
