package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.administration.AdministrationAbsenceRequest;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogRequest;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
import hu.unideb.worktime.api.model.administration.EditWorker;
import hu.unideb.worktime.api.model.administration.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAdministration {
    
    @Autowired private SpGetEmployeeList spGetEmployeeList;
    @Autowired private SpGetEmployeeWorklogList spGetEmployeeWorklogList;
    @Autowired private SpGetEmployeeAbsenceList spGetEmployeeAbsenceList;
    @Autowired private SpApproveEmployeeAbsence spAcceptAbsenceStatus;
    @Autowired private SpEditWorkerData spEditWorkerData;
    @Autowired private SpGetEmployeeWorkerData spGetEmployeeWorkerData;
    private Logger logger = LoggerFactory.getLogger(SqlCallAdministration.class);
    
    public List<Employee> getEmloyees(Integer workerId) {
        List<Employee> result = null;
        this.logger.info("Call get_employee_list SP with given parameters: {}", workerId);
        try {
            result = this.spGetEmployeeList.execute(workerId);
            if (result == null ||result.isEmpty()) {
                this.logger.debug("There is no such employees in database! Key: {}", workerId);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_list SP: {}", result);
        return result;
    }
    
    public List<AdministrationWorklogResponse> getEmloyeeWorklog(String firstName, String lastName, AdministrationWorklogRequest request) {
        List<AdministrationWorklogResponse> result = null;
        this.logger.info("Call get_employee_worklog_list SP with given parameters: FirstName - {}, LastName - {}, Request - {}", firstName, lastName, request);
        try {
            result = this.spGetEmployeeWorklogList.execute(firstName, lastName, request);
            if (result == null ||result.isEmpty()) {
                this.logger.debug("There is no such worklogs in database! Key: FirstName - {}, LastName - {}, Request - {}", firstName, lastName, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_worklog_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_worklog_list SP: {}", result);
        return result;
    }
    
    public List<AdministrationAbsenceResponse> getEmloyeeAbsence(String firstName, String lastName, AdministrationAbsenceRequest request) {
        List<AdministrationAbsenceResponse> result = null;
        this.logger.info("Call get_employee_absence_list SP with given parameters: FirstName - {}, LastName - {}, Request - {}", firstName, lastName, request);
        try {
            result = this.spGetEmployeeAbsenceList.execute(firstName, lastName, request);
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such absence in database! Key: FirstName - {}, LastName - {}, Request - {}", firstName, lastName, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_absence_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_absence_list SP: {}", result);
        return result;
    }
    
    public Integer acceptEmployeeAbsence(Integer id ){
        Integer result = null;
        this.logger.info("Call accept_absence SP with given parameters: Key - {}", id);
        try {
            result = this.spAcceptAbsenceStatus.execute(id);
            if (result == null) {
                this.logger.debug("There is an error while accept the absence in database! Key - {}", id);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during accept_absence SP call: {}", ex);
        }
        this.logger.info("Result of accept_absence SP: {}", result);
        return result;
    }
    
    public Integer editWorkerData(Integer id, EditWorker request){
        Integer result = null;
        this.logger.info("Call edit_worker_data SP with given parameters: Key - {}, Request - {}", id, request);
        try {
            result = this.spEditWorkerData.execute(id, request);
            if (result == null) {
                this.logger.debug("There is an error while edit the worker data in database! Key - {}, Request - {}", id, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_worker_data SP call: {}", ex);
        }
        this.logger.info("Result of edit_worker_data SP: {}", result);
        return result;
    }
    
    public EditWorker getWorkerData(Integer id){
        EditWorker result = null;
        this.logger.info("Call get_worker_data SP with given parameters: Key - {}", id);
        try {
            result = this.spGetEmployeeWorkerData.execute(id);
            if (result == null) {
                this.logger.debug("There is an error while get the worker data in database! Key - {}", id);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_worker_data SP call: {}", ex);
        }
        this.logger.info("Result of get_worker_data SP: {}", result);
        return result;
    }
}
