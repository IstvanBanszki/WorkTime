package hu.unideb.worktime.jdbc.absence;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.absence.AbsenceDataResponse;
import hu.unideb.worktime.api.model.absence.AbsenceRequest;
import hu.unideb.worktime.api.model.absence.AbsenceResponse;
import java.util.List;

public interface ISqlCallAbsence {
    
    SaveResult saveAbsence(Integer workerId, AbsenceRequest values);
    List<AbsenceResponse> getAbsence(Integer key, String request);
    List<AbsenceDataResponse> getAbsenceData(Integer key);
    Integer deleteAbsence(Integer key);
    Integer editAbsence(Integer id, AbsenceRequest values);

}
