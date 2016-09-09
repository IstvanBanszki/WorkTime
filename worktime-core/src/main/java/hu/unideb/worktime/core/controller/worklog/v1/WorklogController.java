package hu.unideb.worktime.core.controller.worklog.v1;

import hu.unideb.worktime.api.model.DateFilterRequest;
import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import hu.unideb.worktime.api.model.worklog.WorklogRequest;
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
@RequestMapping(value = "/api/worklog/v1", produces = "application/json")
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
    @RequestMapping(value = "/workerId/{workerId}", method = RequestMethod.PUT)
    public @ResponseBody Integer saveWorklog(@PathVariable("workerId") Integer workerId, @RequestBody WorklogRequest request) {
        return this.sqlCallSaveWorklog.saveWorklog(workerId, request);
    }

    @Async
    @RequestMapping(value = "/workerId/{workerId}/dateFilter/{dateFilter}", method = RequestMethod.GET)
    public @ResponseBody List<WorklogResponse> getWorklog(@PathVariable("workerId") Integer workerId, @PathVariable("dateFilter") String request) {
        return this.sqlCallSaveWorklog.getWorklog(workerId, request);
    }

    @Async
    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Integer deleteWorklog(@PathVariable("id") Integer id) {
        return this.sqlCallSaveWorklog.deleteWorklog(id);
    }

    @Async
    @RequestMapping(value = "/id/{id}", method = RequestMethod.PUT)
    public @ResponseBody Integer editWorklog(@PathVariable("id") Integer id, @RequestBody WorklogRequest request) {
        return this.sqlCallSaveWorklog.editWorklog(id, request);
    }
}
