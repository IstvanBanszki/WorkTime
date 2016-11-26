package hu.unideb.worktime.core.controller.addition.v1;

import hu.unideb.worktime.api.model.Department;
import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.core.service.IAdditionService;
import hu.unideb.worktime.jdbc.addition.ISqlCallDepartmentAddition;
import hu.unideb.worktime.jdbc.addition.ISqlCallOfficeAddition;
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

    @Autowired private ISqlCallDepartmentAddition sqlCallDepartmentAddition;
    @Autowired private ISqlCallOfficeAddition sqlCallOfficeAddition;
    @Autowired private IAdditionService additionService;

    @Async
    @RequestMapping(value = "/offices/officeIds/{officeId}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/departments/departmentIds/{departmentId}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public @ResponseBody SaveResult createUser(@RequestBody User request) {
        return this.additionService.createUser(request);
    }
    
    @Async
    @RequestMapping(value = "/workers", method = RequestMethod.PUT)
    public @ResponseBody SaveResult createWorker(@RequestBody Worker request) {
        return this.additionService.createWorker(request);
    }
    
    @Async
    @RequestMapping(value = "/superiors", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getSuperior() {
        return this.additionService.getSuperiors();
    }

}
