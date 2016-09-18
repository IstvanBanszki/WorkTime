package hu.unideb.worktime.core.controller.addition.v1;

import hu.unideb.worktime.api.model.Department;
import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.addition.UserExtended;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.core.security.WTEncryption;
import hu.unideb.worktime.jdbc.addition.SqlCallAddition;
import hu.unideb.worktime.jdbc.addition.SqlCallDepartmentAddition;
import hu.unideb.worktime.jdbc.addition.SqlCallOfficeAddition;
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
@RequestMapping(value = "/api/addition/v1", produces = "application/json")
public class AdditionController {

    @Autowired private SqlCallAddition sqlCallAddition;
    @Autowired private SqlCallDepartmentAddition sqlCallDepartmentAddition;
    @Autowired private SqlCallOfficeAddition sqlCallOfficeAddition;
    @Autowired private WTEncryption wTEncryption;

    @Async
    @RequestMapping(value = "/officeId/{officeId}", method = RequestMethod.PUT)
    public @ResponseBody Integer editOffice(@PathVariable("officeId") Integer officeId, @RequestBody Office request) {
        return this.sqlCallOfficeAddition.editOffice(officeId, request);
    }

    @Async
    @RequestMapping(value = "/offices", method = RequestMethod.GET)
    public @ResponseBody List<Office> getOffice() {
        return this.sqlCallOfficeAddition.getOffices();
    }

    @Async
    @RequestMapping(value = "/offices", method = RequestMethod.PUT)
    public @ResponseBody SaveResult saveOffice(@RequestBody Office request) {
        return this.sqlCallOfficeAddition.saveOffice(request);
    }

    @Async
    @RequestMapping(value = "/departmentId/{departmentId}", method = RequestMethod.PUT)
    public @ResponseBody Integer editDepartment(@PathVariable("departmentId") Integer departmentId, @RequestBody Department request) {
        return this.sqlCallDepartmentAddition.editDepartment(departmentId, request);
    }

    @Async
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody List<Department> getDepartment() {
        return this.sqlCallDepartmentAddition.getDepartments();
    }

    @Async
    @RequestMapping(value = "/departments", method = RequestMethod.PUT)
    public @ResponseBody SaveResult saveDepartment(@RequestBody Department request) {
        return this.sqlCallDepartmentAddition.saveDepartment(request);
    }

    @Async
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public @ResponseBody SaveResult createUser(@RequestBody UserExtended request) {
        String passwordForSave = this.wTEncryption.generateRandomPassword();
        return this.sqlCallAddition.saveUser(request, passwordForSave);
    }
    
    @Async
    @RequestMapping(value = "/worker", method = RequestMethod.PUT)
    public @ResponseBody SaveResult createWorker(@RequestBody Worker request) {
        return this.sqlCallAddition.saveWorker(request);
    }
    
    @Async
    @RequestMapping(value = "/superior", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getSuperior() {
        return this.sqlCallAddition.getSuperiors();
    }

}
