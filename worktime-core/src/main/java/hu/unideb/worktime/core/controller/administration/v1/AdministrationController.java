package hu.unideb.worktime.core.controller.administration.v1;

import hu.unideb.worktime.api.model.administration.AdministrationAbsenceRequest;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogRequest;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
import hu.unideb.worktime.api.model.administration.EditWorkerRequest;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.jdbc.administration.SqlCallAdministration;
import java.util.List;
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
    
    @Autowired
    private SqlCallAdministration sqlCallAdministration;

    @Async
    @RequestMapping(value = "/firstName/{firstName}/lastName/{lastName}/worklog", method = RequestMethod.POST)
    public @ResponseBody List<AdministrationWorklogResponse> getEmployeeWorklogList(@PathVariable("firstName") String firstName, 
           @PathVariable("lastName") String lastName, @RequestBody AdministrationWorklogRequest request) {
        return this.sqlCallAdministration.getEmloyeeWorklog(firstName, lastName, request);
    }

    @Async
    @RequestMapping(value = "/firstName/{firstName}/lastName/{lastName}/absence", method = RequestMethod.POST)
    public @ResponseBody List<AdministrationAbsenceResponse> getEmployeeAbsenceList(@PathVariable("firstName") String firstName, 
           @PathVariable("lastName") String lastName, @RequestBody AdministrationAbsenceRequest request) {
        return this.sqlCallAdministration.getEmloyeeAbsence(firstName, lastName, request);
    }

    @Async
    @RequestMapping(value = "/workerId/{workerId}", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployee(@PathVariable("workerId") Integer workerId ) {
        return this.sqlCallAdministration.getEmloyees(workerId);
    }
    
    @Async
    @RequestMapping(value = "/approveEmpolyeeAbsence/{id}", method = RequestMethod.POST)
    public @ResponseBody Integer acceptEmployeeAbsence(@PathVariable Integer id) {
        return this.sqlCallAdministration.acceptEmployeeAbsence(id);
    }
    
    @Async
    @RequestMapping(value = "/workerData/{id}", method = RequestMethod.PUT)
    public @ResponseBody Integer editWorkerData(@PathVariable Integer id, @RequestBody EditWorkerRequest request) {
        return this.sqlCallAdministration.editWorkerData(id, request);
    }
}
