package hu.unideb.worktime.core.service;

import hu.unideb.worktime.api.model.entrance.EntryRecord;

public interface IEntranceService {
    
    void saveNewEntryLog(EntryRecord record);
}
