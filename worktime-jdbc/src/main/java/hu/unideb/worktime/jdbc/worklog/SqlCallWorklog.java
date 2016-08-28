package hu.unideb.worktime.jdbc.worklog;

import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import hu.unideb.worktime.api.model.worklog.WorklogRequest;
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
        this.logger = LoggerFactory.getLogger(SqlCallWorklog.class);
    }
    
    public Integer saveWorklog(Integer workerId, WorklogRequest values ){
        Integer result = null;
        this.logger.info("Call save_worklog SP with given parameters: Key - {}, values - {}", workerId, values);
        try {
            result = this.spSaveWorklog.execute(workerId, values);
            if(result == null){
                this.logger.debug("There is an erro in saving the worklog data in database! Key - {}, values - {}", workerId, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_worklog SP call: {}", ex);
        }
        this.logger.info("Result of save_worklog SP: {}", result);
        return result;
    }
    
    public List<WorklogResponse> getWorklog( Integer key ){
        List<WorklogResponse> result = null;
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
