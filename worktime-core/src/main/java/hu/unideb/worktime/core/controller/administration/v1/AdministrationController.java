package hu.unideb.worktime.core.controller.administration.v1;

import hu.unideb.worktime.api.model.administration.AdministrationAbsenceRequest;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogRequest;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
import hu.unideb.worktime.api.model.administration.EditWorker;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.core.export.IExportService;
import hu.unideb.worktime.jdbc.administration.SqlCallAdministration;
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
    
    @Autowired private SqlCallAdministration sqlCallAdministration;
    @Autowired private IExportService exportService;

    @Async
    @RequestMapping(value = "/worklog/{employeeId}", method = RequestMethod.POST)
    public @ResponseBody List<AdministrationWorklogResponse> getEmployeeWorklogList(
                    @PathVariable("employeeId") Integer employeeId, 
                    @RequestBody AdministrationWorklogRequest request) {
        return this.sqlCallAdministration.getEmloyeeWorklog(employeeId, request);
    }

    @Async
    @RequestMapping(value = "/absence/{employeeId}", method = RequestMethod.POST)
    public @ResponseBody List<AdministrationAbsenceResponse> getEmployeeAbsenceList(
                    @PathVariable("employeeId") Integer employeeId, 
                    @RequestBody AdministrationAbsenceRequest request) {
        return this.sqlCallAdministration.getEmloyeeAbsence(employeeId, request);
    }

    @Async
    @RequestMapping(value = "/employee/{superiorWorkerId}", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployee(@PathVariable("superiorWorkerId") Integer superiorWorkerId ) {
        return this.sqlCallAdministration.getEmloyees(superiorWorkerId);
    }

    @Async
    @RequestMapping(value = "/approve/{absenceId}", method = RequestMethod.POST)
    public @ResponseBody Integer acceptEmployeeAbsence(@PathVariable("absenceId") Integer absenceId) {
        return this.sqlCallAdministration.acceptEmployeeAbsence(absenceId);
    }
    
    @Async
    @RequestMapping(value = "/workerData/{employeeId}", method = RequestMethod.PUT)
    public @ResponseBody Integer editWorkerData(@PathVariable("employeeId") Integer employeeId, 
                    @RequestBody EditWorker request) {
        return this.sqlCallAdministration.editWorkerData(employeeId, request);
    }
    
    @Async
    @RequestMapping(value = "/workerData/{employeeId}", method = RequestMethod.GET)
    public @ResponseBody EditWorker editWorkerData(@PathVariable("employeeId") Integer employeeId) {
        return this.sqlCallAdministration.getWorkerData(employeeId);
    }

    @Async
    @RequestMapping(value = "/worklog/{employeeId}/{dateFilter}/{showDailyWorkhours}/{type}/export", method = RequestMethod.GET)
    public void exportWorklogs(@PathVariable("employeeId") Integer employeeId, 
                    @PathVariable("type") Integer excelType, @PathVariable("dateFilter") String dateFilter, 
                    @PathVariable("showDailyWorkhours") Boolean showDailyWorkhours, HttpServletResponse response) {
        this.exportService.exportAdminWorklogs(employeeId, dateFilter, showDailyWorkhours, excelType, response);
    }

    @Async
    @RequestMapping(value = "/absence/{employeeId}/{dateFilter}/{notApprove}/{type}/export", method = RequestMethod.GET)
    public void exportAbsences(@PathVariable("employeeId") Integer employeeId, 
                    @PathVariable("type") Integer excelType, @PathVariable("dateFilter") String dateFilter, 
                    @PathVariable("notApprove") Boolean notApprove, HttpServletResponse response) {
        this.exportService.exportAdminAbsences(employeeId, dateFilter, notApprove, excelType, response);
    }
}
