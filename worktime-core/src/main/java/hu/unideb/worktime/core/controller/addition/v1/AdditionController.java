package hu.unideb.worktime.core.controller.addition.v1;

import hu.unideb.worktime.api.model.Department;
import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.core.security.WTEncryption;
import hu.unideb.worktime.jdbc.addition.SqlCallAddition;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/addition/v1", produces = "application/json")
public class AdditionController {

    @Autowired private SqlCallAddition sqlCallAddition;
    @Autowired private WTEncryption wTEncryption;

    @Async
    @RequestMapping(value = "/officeId/{officeId}", method = RequestMethod.PUT)
    public @ResponseBody Integer editOffice(@PathVariable("officeId") Integer officeId, @RequestBody Office request) {
        return this.sqlCallAddition.editOffice(officeId, request);
    }

    @Async
    @RequestMapping(value = "/offices", method = RequestMethod.GET)
    public @ResponseBody List<Office> getAbsence() {
        return this.sqlCallAddition.getOffices();
    }

    @Async
    @RequestMapping(value = "/departmentId/{departmentId}", method = RequestMethod.PUT)
    public @ResponseBody Integer editDepartment(@PathVariable("departmentId") Integer departmentId, @RequestBody Department request) {
        return this.sqlCallAddition.editDepartment(departmentId, request);
    }

    @Async
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody List<Department> getDepartment() {
        return this.sqlCallAddition.getDepartments();
    }

    @Async
    @RequestMapping(value = "/officesWithDepartments", method = RequestMethod.GET)
    public @ResponseBody Map<Office, Department> getOfficesWIthDepartments() {
        return this.sqlCallAddition.getOfficesWithDepartment();
    }

    @Async
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public @ResponseBody Integer createUser(@RequestBody User request) {
        String passwordForSave = this.wTEncryption.encryptPassword(request.getPassword());
        return this.sqlCallAddition.saveUser(request, passwordForSave);
    }
    
    @Async
    @RequestMapping(value = "/worker", method = RequestMethod.PUT)
    public @ResponseBody Integer createWorker(@RequestBody Worker request) {
        return this.sqlCallAddition.saveWorker(request);
    }
    
}
