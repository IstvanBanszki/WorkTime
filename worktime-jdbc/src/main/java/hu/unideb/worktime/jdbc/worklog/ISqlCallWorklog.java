package hu.unideb.worktime.jdbc.worklog;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.worklog.WorklogRequest;
import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import java.util.List;

public interface ISqlCallWorklog {
    
    SaveResult saveWorklog(Integer workerId, WorklogRequest values);
    List<WorklogResponse> getWorklog(Integer key, String request);
    Integer deleteWorklog(Integer key);
    Integer editWorklog(Integer id, WorklogRequest values);

}
