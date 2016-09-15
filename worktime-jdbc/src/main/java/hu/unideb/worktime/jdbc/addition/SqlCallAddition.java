package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAddition {
    
    @Autowired private SpSaveUser spSaveUser;
    @Autowired private SpSaveWorker spSaveWorker;
    private Logger logger = LoggerFactory.getLogger(SqlCallAddition.class);
    
    
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

}
