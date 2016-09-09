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
    
    @Autowired private SpSaveWorklog spSaveWorklog;
    @Autowired private SpGetWorklog spGetWorklog;
    @Autowired private SpDeleteWorklog spDeleteWorklog;
    @Autowired private SpEditWorklog spEditWorklog;
    private Logger logger;

    public SqlCallWorklog() {
        this.logger = LoggerFactory.getLogger(SqlCallWorklog.class);
    }
    
    public Integer saveWorklog(Integer workerId, WorklogRequest values) {
        Integer result = null;
        this.logger.info("Call save_worklog SP with given parameters: Key - {}, values - {}", workerId, values);
        try {
            result = this.spSaveWorklog.execute(workerId, values);
            if(result == null){
                this.logger.debug("There is an error while saving the worklog in database! Key - {}, values - {}", workerId, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_worklog SP call: {}", ex);
        }
        this.logger.info("Result of save_worklog SP: {}", result);
        return result;
    }
    
    public List<WorklogResponse> getWorklog(Integer key, String request) {
        List<WorklogResponse> result = null;
        this.logger.info("Call get_all_worklog_by_worker SP with given parameters - Key: {}, DateFilter: {}", key, request);
        try {
            result = this.spGetWorklog.execute(key, request);
            if(result == null ||result.isEmpty()) {
                this.logger.debug("There is no such worklogs in database! Key: {}, DateFilter: {}", key, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_worklog_by_worker SP call: {}", ex);
        }
        this.logger.info("Result of get_all_worklog_by_worker SP: {}", result);
        return result;
    }
    
    public Integer deleteWorklog(Integer key) {
        Integer result = null;
        this.logger.info("Call delete_worklog SP with given parameters: Key - {}", key);
        try {
            result = this.spDeleteWorklog.execute(key);
            if(result == null) {
                this.logger.debug("There is an error while delete the worklog in database! Key - {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during delete_worklog SP call: {}", ex);
        }
        this.logger.info("Result of delete_worklog SP: {}", result);
        return result;
    }
    
    public Integer editWorklog(Integer id, WorklogRequest values) {
        Integer result = null;
        this.logger.info("Call edit_worklog SP with given parameters: Key - {}, values - {}", id, values);
        try {
            result = this.spEditWorklog.execute(id, values);
            if(result == null) {
                this.logger.debug("There is an error while edit the worklog in database! Key - {}, values - {}", id, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_worklog SP call: {}", ex);
        }
        this.logger.info("Result of edit_worklog SP: {}", result);
        return result;
    }
}
