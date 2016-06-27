package hu.unideb.worktime.jdbc.worklogadministration;

import hu.unideb.worktime.api.model.worklog.GetWorklogResponse;
import hu.unideb.worktime.api.model.worklogadministration.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallWorklogAdministration {
    
    @Autowired
    private SpGetEmployeeList spGetEmployeeList;
    @Autowired
    private SpGetEmployeeWorklogList spGetEmployeeWorklogList;
    private Logger logger;

    public SqlCallWorklogAdministration() {
        this(LoggerFactory.getLogger(SqlCallWorklogAdministration.class));
    }

    public SqlCallWorklogAdministration(Logger logger) {
        this.logger = logger;
    }
    
    public List<Employee> getEmloyees(Integer workerId){
        List<Employee> result = null;
        this.logger.info("Call get_employee_list SP with given parameters: {}", workerId);
        try {
            result = this.spGetEmployeeList.execute(workerId);
            if(result == null ||result.isEmpty() ){
                this.logger.debug("There is no suche worklog data in database! Key: {}", workerId);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_list SP: {}", result);
        return result;
    }
    
    public List<GetWorklogResponse> getEmloyeeWorklog(Employee key){
        List<GetWorklogResponse> result = null;
        this.logger.info("Call get_employee_worklog_list SP with given parameters: {}", key);
        try {
            result = this.spGetEmployeeWorklogList.execute(key.getFirstName(), key.getLastName());
            if(result == null ||result.isEmpty() ){
                this.logger.debug("There is no suche worklog data in database! Key: {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_worklog_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_worklog_list SP: {}", result);
        return result;
    }
}
