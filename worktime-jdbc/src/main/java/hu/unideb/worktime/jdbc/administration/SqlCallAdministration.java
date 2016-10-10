package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.administration.AdministrationAbsenceRequest;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogRequest;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
import hu.unideb.worktime.api.model.administration.WorkerData;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.api.model.administration.Note;
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpApproveEmployeeAbsence;
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpGetEmployeeWorklogList;
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpGetEmployeeAbsenceList;
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpEditWorkerData;
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpUpdateAbsenceNote;
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpUpdateWorklogNote;
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpGetEmployeeWorkerData;
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpGetEmployeeList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAdministration implements ISqlCallAdministration {
    
    @Autowired private SpGetEmployeeList spGetEmployeeList;
    @Autowired private SpGetEmployeeWorklogList spGetEmployeeWorklogList;
    @Autowired private SpGetEmployeeAbsenceList spGetEmployeeAbsenceList;
    @Autowired private SpApproveEmployeeAbsence spAcceptAbsenceStatus;
    @Autowired private SpEditWorkerData spEditWorkerData;
    @Autowired private SpGetEmployeeWorkerData spGetEmployeeWorkerData;
    @Autowired private SpUpdateWorklogNote spUpdateWorklogNote;
    @Autowired private SpUpdateAbsenceNote spUpdateAbsencegNote;
    private Logger logger = LoggerFactory.getLogger(SqlCallAdministration.class);
    
    @Override
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
    
    @Override
    public List<AdministrationWorklogResponse> getEmloyeeWorklog(Integer employeeId, AdministrationWorklogRequest request) {
        List<AdministrationWorklogResponse> result = null;
        this.logger.info("Call get_employee_worklog_list SP with given parameters: EmployeeId - {}, Request - {}", employeeId, request);
        try {
            result = this.spGetEmployeeWorklogList.getWorklogListForEmployee(employeeId, request);
            if (result == null ||result.isEmpty()) {
                this.logger.debug("There is no such worklogs in database! EmployeeId - {}, Request - {}", employeeId, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_worklog_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_worklog_list SP: {}", result);
        return result;
    }
    
    @Override
    public List<AdministrationAbsenceResponse> getEmloyeeAbsence(Integer employeeId, AdministrationAbsenceRequest request) {
        List<AdministrationAbsenceResponse> result = null;
        this.logger.info("Call get_employee_absence_list SP with given parameters: EmployeeId - {}, Request - {}", employeeId, request);
        try {
            result = this.spGetEmployeeAbsenceList.getAbsenceListForEmployee(employeeId, request);
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such absence in database! EmployeeId - {}, Request - {}", employeeId, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_employee_absence_list SP call: {}", ex);
        }
        this.logger.info("Result of get_employee_absence_list SP: {}", result);
        return result;
    }
    
    @Override
    public Integer acceptEmployeeAbsence(Integer absenceId) {
        Integer result = null;
        this.logger.info("Call accept_absence SP with given parameters: Key - {}", absenceId);
        try {
            result = this.spAcceptAbsenceStatus.approve(absenceId);
            if (result == null) {
                this.logger.debug("There is an error while accept the absence in database! Key - {}", absenceId);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during accept_absence SP call: {}", ex);
        }
        this.logger.info("Result of accept_absence SP: {}", result);
        return result;
    }
    
    @Override
    public Integer editWorkerData(Integer workerId, WorkerData request) {
        Integer result = null;
        this.logger.info("Call edit_worker_data SP with given parameters: Key - {}, Request - {}", workerId, request);
        try {
            result = this.spEditWorkerData.editWorkerData(workerId, request);
            if (result == null) {
                this.logger.debug("There is an error while edit the worker data in database! Key - {}, Request - {}", workerId, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_worker_data SP call: {}", ex);
        }
        this.logger.info("Result of edit_worker_data SP: {}", result);
        return result;
    }
    
    @Override
    public WorkerData getWorkerData(Integer workerId) {
        WorkerData result = null;
        this.logger.info("Call get_worker_data SP with given parameters: Key - {}", workerId);
        try {
            result = this.spGetEmployeeWorkerData.getWorkerData(workerId);
            if (result == null) {
                this.logger.debug("There is an error while get the worker data in database! Key - {}", workerId);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_worker_data SP call: {}", ex);
        }
        this.logger.info("Result of get_worker_data SP: {}", result);
        return result;
    }
    
    @Override
    public Integer updateWorklogNote(Integer worklogId, Note request) {
        Integer result = null;
        this.logger.info("Call update_worklog_note SP with given parameters: Key - {}, Note - {}", worklogId, request.getNote());
        try {
            result = this.spUpdateWorklogNote.updateNote(worklogId, request);
            if (result == null) {
                this.logger.debug("There is an error while update the worklog note in database! Key - {}, Note - {}", worklogId, request.getNote());
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during update_worklog_note SP call: {}", ex);
        }
        this.logger.info("Result of update_worklog_note SP: {}", result);
        return result;
    }
    
    @Override
    public Integer updateAbsenceNote(Integer absenceId, Note request) {
        Integer result = null;
        this.logger.info("Call update_absence_note SP with given parameters: Key - {}, Note - {}", absenceId, request.getNote());
        try {
            result = this.spUpdateAbsencegNote.updateNote(absenceId, request);
            if (result == null) {
                this.logger.debug("There is an error while update the absence note in database! Key - {}, Note - {}", absenceId, request.getNote());
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during update_absence_note SP call: {}", ex);
        }
        this.logger.info("Result of update_absence_note SP: {}", result);
        return result;
    }

}
