package hu.unideb.worktime.jdbc.entrance;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.entrance.EntryRecord;
import hu.unideb.worktime.jdbc.entrance.storedprocedure.SpSaveEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallEntrance implements ISqlCallEntrance {

    @Autowired private SpSaveEntry spSaveEntry;
    private Logger logger = LoggerFactory.getLogger(SqlCallEntrance.class);
    
    @Override
    public void saveEntry(EntryRecord values) {
        SaveResult result = null;
        this.logger.info("Call save_entry_log SP with given parameters: Value - {}", values);
        try {
            result = this.spSaveEntry.saveEntry(values);
            if (result == null) {
                this.logger.debug("There is an error while saving the entry in database! Value - {}", values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_entry_log SP call: {}", ex);
        }
        this.logger.info("Result of save_entry_log SP: {}", result);
    }
    
}
