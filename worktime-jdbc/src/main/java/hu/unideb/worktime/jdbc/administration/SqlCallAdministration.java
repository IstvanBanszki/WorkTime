package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.administration.AdministrationAbsenceRequest;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogRequest;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
import hu.unideb.worktime.api.model.administration.WorkerData;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.api.model.administration.Note;
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
    @Autowired private SpUpdateWorklogNote spUpdateWorklogNote;
    private Logger logger = LoggerFactory.getLogger(SqlCallAdministration.class);
    
    public List<Employee> getEmloyees(Integer workerId) {
        List<Employee> result = null;
        this.logger.info("Call get_employee_list SP with given parameters: {}", workerId);
        try {
            result = this.spGetEmployeeList.getEmployees(workerId);
            if (result == null ||result.isEmpty()) {
                this.logger.debug("There is no such employees in database! Key: {}", workerId);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_list SP: {}", result);
        return result;
    }
    
    public List<AdministrationWorklogResponse> getEmloyeeWorklog(Integer id, AdministrationWorklogRequest request) {
        List<AdministrationWorklogResponse> result = null;
        this.logger.info("Call get_employee_worklog_list SP with given parameters: EmployeeId - {}, Request - {}", id, request);
        try {
            result = this.spGetEmployeeWorklogList.getWorklogListForEmployee(id, request);
            if (result == null ||result.isEmpty()) {
                this.logger.debug("There is no such worklogs in database! EmployeeId - {}, Request - {}", id, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_worklog_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_worklog_list SP: {}", result);
        return result;
    }
    
    public List<AdministrationAbsenceResponse> getEmloyeeAbsence(Integer id, AdministrationAbsenceRequest request) {
        List<AdministrationAbsenceResponse> result = null;
        this.logger.info("Call get_employee_absence_list SP with given parameters: EmployeeId - {}, Request - {}", id, request);
        try {
            result = this.spGetEmployeeAbsenceList.getAbsenceListForEmployee(id, request);
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such absence in database! EmployeeId - {}, Request - {}", id, request);
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
            result = this.spAcceptAbsenceStatus.approve(id);
            if (result == null) {
                this.logger.debug("There is an error while accept the absence in database! Key - {}", id);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during accept_absence SP call: {}", ex);
        }
        this.logger.info("Result of accept_absence SP: {}", result);
        return result;
    }
    
    public Integer editWorkerData(Integer id, WorkerData request){
        Integer result = null;
        this.logger.info("Call edit_worker_data SP with given parameters: Key - {}, Request - {}", id, request);
        try {
            result = this.spEditWorkerData.editWorkerData(id, request);
            if (result == null) {
                this.logger.debug("There is an error while edit the worker data in database! Key - {}, Request - {}", id, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_worker_data SP call: {}", ex);
        }
        this.logger.info("Result of edit_worker_data SP: {}", result);
        return result;
    }
    
    public WorkerData getWorkerData(Integer id){
        WorkerData result = null;
        this.logger.info("Call get_worker_data SP with given parameters: Key - {}", id);
        try {
            result = this.spGetEmployeeWorkerData.getWorkerData(id);
            if (result == null) {
                this.logger.debug("There is an error while get the worker data in database! Key - {}", id);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_worker_data SP call: {}", ex);
        }
        this.logger.info("Result of get_worker_data SP: {}", result);
        return result;
    }
    
    public Integer updateWorklogNote(Integer key, Note request) {
        Integer result = null;
        this.logger.info("Call update_worklog_note SP with given parameters: Key - {}, Note - {}", key, request);
        try {
            result = this.spUpdateWorklogNote.updateNote(key, request);
            if (result == null) {
                this.logger.debug("There is an error while update the worklog note in database! Key - {}, Note - {}", key, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during update_worklog_note SP call: {}", ex);
        }
        this.logger.info("Result of update_worklog_note SP: {}", result);
        return result;
    }
}
