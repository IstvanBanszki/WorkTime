package hu.unideb.worktime.jdbc.worklog;

import hu.unideb.worktime.api.model.worklog.GetWorklogResponse;
import hu.unideb.worktime.jdbc.worklog.SpSaveWorklog;
import hu.unideb.worktime.api.model.worklog.SaveWorklogRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallWorklog {
    
    @Autowired
    private SpSaveWorklog spSaveWorklog;
    @Autowired
    private SpGetWorklog spGetWorklog;
    private Logger logger;

    public SqlCallWorklog() {
        this(LoggerFactory.getLogger(SqlCallWorklog.class));
    }

    public SqlCallWorklog(Logger logger) {
        this.logger = logger;
    }
    
    public Integer saveWorklog( SaveWorklogRequest key ){
        Integer result = null;
        this.logger.info("Call save_worklog SP with given parameters: {}", key);
        try {
            result = this.spSaveWorklog.execute(key);
            if(result == null){
                this.logger.debug("There is an erro in saving the worklog data in database! Key: {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_worklog SP call: {}", ex);
        }
        this.logger.info("Result of save_worklog SP: {}", result);
        return result;
    }
    
    public List<GetWorklogResponse> getWorklog( Integer key ){
        List<GetWorklogResponse> result = null;
        this.logger.info("Call get_all_worklog_by_worker SP with given parameters: {}", key);
        try {
            result = this.spGetWorklog.execute(key);
            if(result == null ||result.isEmpty() ){
                this.logger.debug("There is no suche worklog data in database! Key: {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_worklog_by_worker SP call: {}", ex);
        }
        this.logger.info("Result of get_all_worklog_by_worker SP: {}", result);
        return result;
    }
}
