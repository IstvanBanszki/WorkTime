package hu.unideb.worktime.jdbc.absence;

import hu.unideb.worktime.api.model.absence.GetAbsenceResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAbsence {
    @Autowired
    private SpGetAbsence spGetAbsence;
    private Logger logger;

    public SqlCallAbsence() {
        this(LoggerFactory.getLogger(SqlCallAbsence.class));
    }

    public SqlCallAbsence(Logger logger) {
        this.logger = logger;
    }
    
    
    public List<GetAbsenceResponse> getAbsence( Integer key ){
        List<GetAbsenceResponse> result = null;
        logger.info("Call get_all_absence_by_worker SP with given parameters: {}", key);
        try {
            result = spGetAbsence.execute(key);
            if(result == null ||result.isEmpty() ){
                logger.debug("There is no suche worklog data in database! Key: {}", key);
            }
        } catch (Exception ex) {
            logger.error("There is an exception during get_all_absence_by_worker SP call: {}", ex);
        }
        logger.info("Result of get_all_absence_by_worker SP: {}", result);
        return result;
    }
}
