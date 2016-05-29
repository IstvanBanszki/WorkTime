package hu.unideb.worktime.jdbc.worklog;

import hu.unideb.worktime.jdbc.worklog.SpSaveWorklog;
import hu.unideb.worktime.api.model.worklog.SaveWorklogRequest;
import hu.unideb.worktime.jdbc.profile.SqlCallProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallSaveWorklog {
    
    @Autowired
    private SpSaveWorklog spSaveWorklog;
    private Logger logger;

    public SqlCallSaveWorklog() {
        this.logger = LoggerFactory.getLogger(SqlCallProfile.class);
    }
    
    public Integer saveWorklog( SaveWorklogRequest key ){
        Integer result = null;
        logger.info("Call save_worklog SP with given parameters: {}", key);
        try {
            result = spSaveWorklog.execute(key);
            if(result == null){
                logger.debug("There is an erro in saving the worklog data in database! Key: {}", key);
            }
        } catch (Exception ex) {
            logger.error("There is an exception during save_worklog SP call: {}", ex);
        }
        logger.info("Result of save_worklog SP: {}", result);
        return result;
    }
}
