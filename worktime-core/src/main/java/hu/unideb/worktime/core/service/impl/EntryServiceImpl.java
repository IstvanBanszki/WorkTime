package hu.unideb.worktime.core.service.impl;

import hu.unideb.worktime.api.model.entrance.EntryRecord;
import hu.unideb.worktime.jdbc.entrance.ISqlCallEntrance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.unideb.worktime.core.service.IEntranceService;

@Service
public class EntryServiceImpl implements IEntranceService {

    @Autowired
    private ISqlCallEntrance sqlCallEntrance;
    
    @Override
    public void saveNewEntryLog(EntryRecord record) {
        
        this.sqlCallEntrance.saveEntry(record);

        if (record.getInOut() == 0) {
            
            this.sqlCallEntrance.saveEntryByWorker(record.getWorkerId());
        }
    }

}
