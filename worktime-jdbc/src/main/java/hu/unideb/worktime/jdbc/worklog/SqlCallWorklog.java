package hu.unideb.worktime.jdbc.worklog;

import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpGetWorklog;
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpEditWorklog;
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpDeleteWorklog;
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpSaveWorklog;
import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.worklog.MontlyStatRequest;
import hu.unideb.worktime.api.model.worklog.MontlyStatResponse;
import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import hu.unideb.worktime.api.model.worklog.WorklogRequest;
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpGetMonthlyStatictics;
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpGetMonthlyWorklogs;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallWorklog implements ISqlCallWorklog {
    
    @Autowired private SpSaveWorklog spSaveWorklog;
    @Autowired private SpGetWorklog spGetWorklog;
    @Autowired private SpDeleteWorklog spDeleteWorklog;
    @Autowired private SpEditWorklog spEditWorklog;
    @Autowired private SpGetMonthlyStatictics spGetMonthlyStatictics;
    @Autowired private SpGetMonthlyWorklogs spGetMonthlyWorklogs;
    private Logger logger = LoggerFactory.getLogger(SqlCallWorklog.class);
    
    @Override
    public SaveResult saveWorklog(Integer workerId, WorklogRequest values) {
        SaveResult result = null;
        this.logger.info("Call save_worklog SP with given parameters: Key - {}, values - {}", workerId, values);
        try {
            result = this.spSaveWorklog.saveWorklog(workerId, values);
            if(result == null){
                this.logger.debug("There is an error while saving the worklog in database! Key - {}, values - {}", workerId, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_worklog SP call: {}", ex);
        }
        this.logger.info("Result of save_worklog SP: {}", result);
        return result;
    }
    
    @Override
    public List<WorklogResponse> getWorklog(Integer key, String request) {
        List<WorklogResponse> result = null;
        this.logger.info("Call get_all_worklog_by_worker SP with given parameters - Key: {}, DateFilter: {}", key, request);
        try {
            result = this.spGetWorklog.getWorklogs(key, request);
            if(result == null || result.isEmpty()) {
                this.logger.debug("There is no such worklogs in database! Key: {}, DateFilter: {}", key, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_worklog_by_worker SP call: {}", ex);
        }
        this.logger.info("Result of get_all_worklog_by_worker SP: {}", result);
        return result;
    }
    
    @Override
    public Integer deleteWorklog(Integer key) {
        Integer result = null;
        this.logger.info("Call delete_worklog SP with given parameters: Key - {}", key);
        try {
            result = this.spDeleteWorklog.deleteWorklog(key);
            if(result == null) {
                this.logger.debug("There is an error while delete the worklog in database! Key - {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during delete_worklog SP call: {}", ex);
        }
        this.logger.info("Result of delete_worklog SP: {}", result);
        return result;
    }
    
    @Override
    public Integer editWorklog(Integer id, WorklogRequest values) {
        Integer result = null;
        this.logger.info("Call edit_worklog SP with given parameters: Key - {}, values - {}", id, values);
        try {
            result = this.spEditWorklog.editWorklog(id, values);
            if(result == null) {
                this.logger.debug("There is an error while edit the worklog in database! Key - {}, values - {}", id, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_worklog SP call: {}", ex);
        }
        this.logger.info("Result of edit_worklog SP: {}", result);
        return result;
    }

    @Override
    public List<WorklogResponse> getMonthlyWorklog(MontlyStatRequest key) {
        List<WorklogResponse> result = null;
        this.logger.info("Call get_monthly_worklogs_by_worker SP with given parameters: Key - {}", key);
        try {
            result = this.spGetMonthlyWorklogs.getMonthlyWorklogs(key);
            if(result == null) {
                this.logger.debug("There is an error while edit the worklog in database! Key - {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_monthly_worklogs_by_worker SP call: {}", ex);
        }
        this.logger.info("Result of get_monthly_worklogs_by_worker SP: {}", result);
        return result;
    }

    @Override
    public MontlyStatResponse getMonthlyStatictics(MontlyStatRequest key) {
        MontlyStatResponse result = null;
        this.logger.info("Call get_monthly_statictics_by_worker SP with given parameters: Key - {}", key);
        try {
            result = this.spGetMonthlyStatictics.getMonthlyStatictics(key);
            if(result == null) {
                this.logger.debug("There is an error while edit the worklog in database! Key - {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_monthly_statictics_by_worker SP call: {}", ex);
        }
        this.logger.info("Result of get_monthly_statictics_by_worker SP: {}", result);
        return result;
    }

}
