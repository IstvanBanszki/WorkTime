package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.jdbc.addition.storedprocedure.SpGetAllSuperiorWorkers;
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpSaveWorker;
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpSaveUser;
import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.administration.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAddition implements ISqlCallAddition {
    
    @Autowired private SpSaveUser spSaveUser;
    @Autowired private SpSaveWorker spSaveWorker;
    @Autowired private SpGetAllSuperiorWorkers spGetAllSuperiorWorkers;
    private Logger logger = LoggerFactory.getLogger(SqlCallAddition.class);
    
    @Override
    public SaveResult saveUser(User user, String password) {
        SaveResult result = null;
        this.logger.info("Call save_user SP with given parameters: User - {}, passwordForSave - {}", user, password);
        try {
            result = this.spSaveUser.saveUser(user, password);
            if (result == null) {
                this.logger.debug("There is an error while save user in database! User - {}, passwordForSave - {}", user, password);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_user SP call: {}", ex);
        }
        this.logger.info("Result of save_user SP: {}", result);
        return result;
    }
    
    @Override
    public SaveResult saveWorker(Worker worker) {
        SaveResult result = null;
        this.logger.info("Call save_worker SP with given parameters: Worker - {}", worker);
        try {
            result = this.spSaveWorker.saveWorker(worker);
            if (result == null) {
                this.logger.debug("There is an error while save user in database! Worker - {}", worker);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_worker SP call: {}", ex);
        }
        this.logger.info("Result of save_worker SP: {}", result);
        return result;
    }

    @Override
    public List<Employee> getSuperiors() {
        List<Employee> result = null;
        this.logger.info("Call get_superiors SP with given parameters.");
        try {
            result = this.spGetAllSuperiorWorkers.getSuperiors();
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such superior in database!");
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_superiors SP call: {}", ex);
        }
        this.logger.info("Result of get_superiors SP: {}", result);
        return result;
    }

}
