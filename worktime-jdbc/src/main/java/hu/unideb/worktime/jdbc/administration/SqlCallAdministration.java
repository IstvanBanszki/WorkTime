package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.administration.AdministrationRequest;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
import hu.unideb.worktime.api.model.worklogadministration.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAdministration {
    
    @Autowired private SpGetEmployeeList spGetEmployeeList;
    @Autowired private SpGetEmployeeWorklogList spGetEmployeeWorklogList;
    private Logger logger;

    public SqlCallAdministration() {
        this.logger = LoggerFactory.getLogger(SqlCallAdministration.class);
    }
    
    public List<AdministrationWorklogResponse> getEmloyeeWorklog(String firstName, String lastName, AdministrationRequest request) {
        List<AdministrationWorklogResponse> result = null;
        this.logger.info("Call get_employee_worklog_list SP with given parameters: FirstName - {}, LastName - {}, Request - {}", firstName, lastName, request);
        try {
            result = this.spGetEmployeeWorklogList.execute(firstName, lastName, request);
            if(result == null ||result.isEmpty() ){
                this.logger.debug("There is no such worklogs in database! Key: FirstName - {}, LastName - {}, Request - {}", firstName, lastName, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_worklog_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_worklog_list SP: {}", result);
        return result;
    }
    
    public List<Employee> getEmloyees(Integer workerId) {
        List<Employee> result = null;
        this.logger.info("Call get_employee_list SP with given parameters: {}", workerId);
        try {
            result = this.spGetEmployeeList.execute(workerId);
            if(result == null ||result.isEmpty() ){
                this.logger.debug("There is no such employees in database! Key: {}", workerId);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_list SP: {}", result);
        return result;
    }
}
