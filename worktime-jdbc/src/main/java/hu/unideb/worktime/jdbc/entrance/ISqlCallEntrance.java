package hu.unideb.worktime.jdbc.entrance;

import hu.unideb.worktime.api.model.entrance.EntryRecord;

public interface ISqlCallEntrance {
    
    void saveEntry(EntryRecord values);
    void saveEntryByWorker(Integer workerId);
}
