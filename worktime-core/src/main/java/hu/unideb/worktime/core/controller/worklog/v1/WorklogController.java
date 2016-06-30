package hu.unideb.worktime.core.controller.worklog.v1;

import hu.unideb.worktime.api.model.worklog.GetWorklogResponse;
import hu.unideb.worktime.api.model.worklog.SaveWorklogRequest;
import hu.unideb.worktime.jdbc.worklog.SqlCallWorklog;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/worklog/v1")
public class WorklogController {
    
    @Autowired
    private SqlCallWorklog sqlCallSaveWorklog;
    /*
    --------------------
    Example JSON content
    --------------------
    {
        "begin": "2016-05-20T08:30:00.000",
        "workHour": 8,
        "workerId": 1
    }
     */
    @Async
    @RequestMapping(value = "/workerId/{workerId}", method = RequestMethod.PUT, headers = "Content-Type=application/json")
    public @ResponseBody Integer saveWorklog(@PathVariable("workerId") Integer workerId, @RequestBody SaveWorklogRequest request) {
        return this.sqlCallSaveWorklog.saveWorklog(workerId, request);
    }

    @Async
    @RequestMapping(value = "/workerId/{workerId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GetWorklogResponse> getWorklog(@PathVariable("workerId") Integer workerId) {
        return this.sqlCallSaveWorklog.getWorklog(workerId);
    }
}
