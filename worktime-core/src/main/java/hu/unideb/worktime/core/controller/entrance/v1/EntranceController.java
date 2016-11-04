package hu.unideb.worktime.core.controller.entrance.v1;

import hu.unideb.worktime.api.model.entrance.EntryRecord;
import hu.unideb.worktime.jdbc.entrance.ISqlCallEntrance;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/entrance/v1", produces = "application/json")
public class EntranceController {
    
    @Autowired private ISqlCallEntrance sqlCallEntrance;
    
    @Async
    @RequestMapping(value = "/entry/workerIds/{workerId}/inOuts/{inOut}", method = RequestMethod.GET)
    public void getProfile(@PathVariable("workerId") Integer workerId, @PathVariable("inOut") Integer inOut) {
        this.sqlCallEntrance.saveEntry(new EntryRecord(workerId, LocalDateTime.now(), inOut));
    }

}
