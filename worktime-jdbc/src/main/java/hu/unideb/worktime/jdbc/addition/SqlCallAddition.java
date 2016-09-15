package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Department;
import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAddition {
    
    @Autowired private SpGetAllDepartments spGetAllDepartments;
    @Autowired private SpGetAllOffices spGetAllOffices;
    @Autowired private SpEditOffice spEditOffice;
    @Autowired private SpEditDepartment spEditDepartment;
    @Autowired private SpSaveUser spSaveUser;
    @Autowired private SpSaveWorker spSaveWorker;
    @Autowired private SpSaveOffice spSaveOffice;
    @Autowired private SpSaveDepartment spSaveDepartment;
    private Logger logger = LoggerFactory.getLogger(SqlCallAddition.class);
    
    
    public List<Office> getOffices() {
        List<Office> result = null;
        this.logger.info("Call get_all_offices SP with given parameters.");
        try {
            result = this.spGetAllOffices.execute();
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such office in database!");
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_offices SP call: {}", ex);
        }
        this.logger.info("Result of get_all_offices SP: {}", result);
        return result;
    }

    public List<Department> getDepartments() {
        List<Department> result = null;
        this.logger.info("Call get_all_deparments SP with given parameters.");
        try {
            result = this.spGetAllDepartments.execute();
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such department in database!");
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_deparments SP call: {}", ex);
        }
        this.logger.info("Result of get_all_deparments SP: {}", result);
        return result;
    }
    
    public Map<Office, Department> getOfficesWithDepartment() {
        Map<Office, Department> result = new HashMap();
        List<Office> offices = getOffices();
        List<Department> departments = getDepartments();

        if ((offices != null) && (departments != null)) {
            offices.stream().forEach((Office office) -> {
                departments.stream().forEach((department) -> {
                    if(office.getId() == department.getOfficeId()) {
                        result.put(office, department);
                    }
                });
            });
        }
        this.logger.info("Result of getOfficesWithDepartment: {}", result);
        return result;
    }
    
    public Integer editOffice(Integer id, Office values) {
        Integer result = null;
        this.logger.info("Call edit_office SP with given parameters: Key - {}, values - {}", id, values);
        try {
            result = this.spEditOffice.execute(id, values);
            if (result == null) {
                this.logger.debug("There is an error while edit the office in database! Key - {}, values - {}", id, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_office SP call: {}", ex);
        }
        this.logger.info("Result of edit_office SP: {}", result);
        return result;
    }
    
    public Integer editDepartment(Integer id, Department values) {
        Integer result = null;
        this.logger.info("Call edit_department SP with given parameters: Key - {}, values - {}", id, values);
        try {
            result = this.spEditDepartment.execute(id, values);
            if (result == null) {
                this.logger.debug("There is an error while edit the department in database! Key - {}, values - {}", id, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_office SP call: {}", ex);
        }
        this.logger.info("Result of edit_department SP: {}", result);
        return result;
    }
    
    public Integer saveUser(User user, String password) {
        Integer result = null;
        this.logger.info("Call save_user SP with given parameters: User - {}, passwordForSave - {}", user, password);
        try {
            result = this.spSaveUser.execute(user, password);
            if (result == null) {
                this.logger.debug("There is an error while save user in database! User - {}, passwordForSave - {}", user, password);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_user SP call: {}", ex);
        }
        this.logger.info("Result of save_user SP: {}", result);
        return result;
    }
    
    public Integer saveWorker(Worker worker) {
        Integer result = null;
        this.logger.info("Call save_worker SP with given parameters: Worker - {}", worker);
        try {
            result = this.spSaveWorker.execute(worker);
            if (result == null) {
                this.logger.debug("There is an error while save user in database! Worker - {}", worker);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_worker SP call: {}", ex);
        }
        this.logger.info("Result of save_worker SP: {}", result);
        return result;
    }
    
    public Integer saveOffice(Office values) {
        Integer result = null;
        this.logger.info("Call save_office SP with given parameters: Key - {}, values - {}", values);
        try {
            result = this.spSaveOffice.execute(values);
            if (result == null) {
                this.logger.debug("There is an error while save the office in database! Key - {}, values - {}", values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_office SP call: {}", ex);
        }
        this.logger.info("Result of save_office SP: {}", result);
        return result;
    }
    
    public Integer saveDepartment(Department values) {
        Integer result = null;
        this.logger.info("Call save_department SP with given parameters: values - {}", values);
        try {
            result = this.spSaveDepartment.execute(values);
            if (result == null) {
                this.logger.debug("There is an error while save the department in database! values - {}", values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_department SP call: {}", ex);
        }
        this.logger.info("Result of save_department SP: {}", result);
        return result;
    }

}
