package hu.unideb.worktime.core.controller.administration.v1;

import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
import hu.unideb.worktime.api.model.administration.WorkerData;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.api.model.administration.Note;
import hu.unideb.worktime.core.export.IExportService;
import hu.unideb.worktime.jdbc.administration.ISqlCallAdministration;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/administration/v1", produces = "application/json")
public class AdministrationController {
    
    @Autowired private ISqlCallAdministration sqlCallAdministration;
    @Autowired private IExportService exportService;

    @Async
    @RequestMapping(value = "worklogs/employees/{employeeId}/dateFilters/{dateFilter}/showDailyWorkhours/{showDailyWorkhour}", method = RequestMethod.GET)
    public @ResponseBody List<AdministrationWorklogResponse> getEmployeeWorklogList(
                    @PathVariable("employeeId") Integer employeeId, @PathVariable("dateFilter") String dateFilter, 
                    @PathVariable("showDailyWorkhour") boolean showDailyWorkhour) {
        return this.sqlCallAdministration.getEmloyeeWorklog(employeeId, dateFilter, showDailyWorkhour);
    }

    @Async
    @RequestMapping(value = "/absences/employees/{employeeId}/dateFilters/{dateFilter}/notApproves/{notApprove}", method = RequestMethod.GET)
    public @ResponseBody List<AdministrationAbsenceResponse> getEmployeeAbsenceList(
                    @PathVariable("employeeId") Integer employeeId, @PathVariable("dateFilter") String dateFilter, 
                    @PathVariable("notApprove") boolean notApprove) {
        return this.sqlCallAdministration.getEmloyeeAbsence(employeeId, dateFilter, notApprove);
    }

    @Async
    @RequestMapping(value = "/superiors/{superiorWorkerId}", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployee(@PathVariable("superiorWorkerId") Integer superiorWorkerId ) {
        return this.sqlCallAdministration.getEmloyees(superiorWorkerId);
    }

    @Async
    @RequestMapping(value = "/absences/{absenceId}/approve", method = RequestMethod.POST)
    public @ResponseBody Integer acceptEmployeeAbsence(@PathVariable("absenceId") Integer absenceId) {
        return this.sqlCallAdministration.acceptEmployeeAbsence(absenceId);
    }
    
    @Async
    @RequestMapping(value = "/worklogs/{worklogId}", method = RequestMethod.PUT)
    public @ResponseBody Integer updateWorklogNote(@PathVariable("worklogId") Integer worklogId, 
                    @RequestBody Note request) {
        return this.sqlCallAdministration.updateWorklogNote(worklogId, request);
    }
    
    @Async
    @RequestMapping(value = "/absences/{absenceId}", method = RequestMethod.PUT)
    public @ResponseBody Integer updateAbsenceNote(@PathVariable("absenceId") Integer absenceId, 
                    @RequestBody Note request) {
        return this.sqlCallAdministration.updateAbsenceNote(absenceId, request);
    }
    
    @Async
    @RequestMapping(value = "/employees/{employeeId}/workerData", method = RequestMethod.PUT)
    public @ResponseBody Integer editWorkerData(@PathVariable("employeeId") Integer employeeId, 
                    @RequestBody WorkerData request) {
        return this.sqlCallAdministration.editWorkerData(employeeId, request);
    }
    
    @Async
    @RequestMapping(value = "/employees/{employeeId}/workerData", method = RequestMethod.GET)
    public @ResponseBody WorkerData getWorkerData(@PathVariable("employeeId") Integer employeeId) {
        return this.sqlCallAdministration.getWorkerData(employeeId);
    }

    @Async
    @RequestMapping(value = "/employees/{employeeId}/dates/{dateFilter}/{showDailyWorkhours}/types/{type}/worklog/export", method = RequestMethod.GET)
    public void exportWorklogs(@PathVariable("employeeId") Integer employeeId, 
                    @PathVariable("type") Integer excelType, @PathVariable("dateFilter") String dateFilter, 
                    @PathVariable("showDailyWorkhours") Boolean showDailyWorkhours, HttpServletResponse response) {
        this.exportService.exportAdminWorklogs(employeeId, dateFilter, showDailyWorkhours, excelType, response);
    }

    @Async
    @RequestMapping(value = "/employees/{employeeId}/dates/{dateFilter}/{notApprove}/types/{type}/absence/export", method = RequestMethod.GET)
    public void exportAbsences(@PathVariable("employeeId") Integer employeeId, 
                    @PathVariable("type") Integer excelType, @PathVariable("dateFilter") String dateFilter, 
                    @PathVariable("notApprove") Boolean notApprove, HttpServletResponse response) {
        this.exportService.exportAdminAbsences(employeeId, dateFilter, notApprove, excelType, response);
    }

}
