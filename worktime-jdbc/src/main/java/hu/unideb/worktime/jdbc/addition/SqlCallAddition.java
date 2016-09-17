package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.addition.FreeLogin;
import hu.unideb.worktime.api.model.addition.Superior;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAddition {
    
    @Autowired private SpSaveUser spSaveUser;
    @Autowired private SpSaveWorker spSaveWorker;
    @Autowired private SpGetAllSuperiorWorkers spGetAllSuperiorWorkers;
    @Autowired private SpGetFreeLogins spGetFreeLogins;
    private Logger logger = LoggerFactory.getLogger(SqlCallAddition.class);
    
    
    public Integer saveUser(User user, String password) {
        Integer result = null;
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
    
    public Integer saveWorker(Worker worker) {
        Integer result = null;
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

    public List<Superior> getSuperiors() {
        List<Superior> result = null;
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

    public List<FreeLogin> getFreeLogins() {
        List<FreeLogin> result = null;
        this.logger.info("Call get_free_logins SP with given parameters.");
        try {
            result = this.spGetFreeLogins.getFreeLogins();
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such free login in database!");
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_free_logins SP call: {}", ex);
        }
        this.logger.info("Result of get_free_logins SP: {}", result);
        return result;
    }

}
