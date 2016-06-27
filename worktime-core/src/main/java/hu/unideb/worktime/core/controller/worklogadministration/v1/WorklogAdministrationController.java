package hu.unideb.worktime.core.controller.worklogadministration.v1;

import hu.unideb.worktime.api.model.worklog.GetWorklogResponse;
import hu.unideb.worktime.api.model.worklogadministration.Employee;
import hu.unideb.worktime.jdbc.worklogadministration.SqlCallWorklogAdministration;
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
@RequestMapping(value = "/rest/worklogadministration/v1")
public class WorklogAdministrationController {
    
    @Autowired
    private SqlCallWorklogAdministration sqlCallWorklogAdministration;
    
    @Async
    @RequestMapping(value = "/workerId/{workerId}/employees", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody List<Employee> getEmployee(@PathVariable  Integer workerId ) {
        return this.sqlCallWorklogAdministration.getEmloyees(workerId);
    }
    
    @Async
    @RequestMapping(value = "/employees", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public @ResponseBody List<GetWorklogResponse> getEmployee(@RequestBody Employee key) {
        return this.sqlCallWorklogAdministration.getEmloyeeWorklog(key);
    }
}
