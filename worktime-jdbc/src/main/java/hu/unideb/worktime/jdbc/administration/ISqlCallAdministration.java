package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.api.model.administration.Note;
import hu.unideb.worktime.api.model.administration.WorkerData;
import java.util.List;

public interface ISqlCallAdministration {
    
    List<Employee> getEmloyees(Integer workerId);
    List<AdministrationWorklogResponse> getEmloyeeWorklog(Integer id, String dateFilter, boolean showDailyWorkhour);
    List<AdministrationAbsenceResponse> getEmloyeeAbsence(Integer id, String dateFilter, boolean notApprove);
    Integer acceptEmployeeAbsence(Integer id);
    Integer editWorkerData(Integer id, WorkerData request);
    WorkerData getWorkerData(Integer id);
    Integer updateWorklogNote(Integer key, Note request);
    Integer updateAbsenceNote(Integer key, Note request);

}
