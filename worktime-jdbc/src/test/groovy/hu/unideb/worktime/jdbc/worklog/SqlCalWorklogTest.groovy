package hu.unideb.worktime.jdbc.worklog

import hu.unideb.worktime.api.model.Status
import hu.unideb.worktime.api.model.worklog.GetWorklogResponse
import hu.unideb.worktime.api.model.worklog.SaveWorklogRequest
import java.time.LocalDateTime
import spock.lang.Specification
import org.slf4j.helpers.NOPLogger

class SqlCalWorklogTest extends Specification {

    def "test for save worklog jdbc call"(){
        given: "set sqlCall Mock object"
            def sqlCallWorklog = new SqlCallWorklog([
                spSaveWorklog: Mock(SpSaveWorklog){
                    execute(request) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlCallWorklog.saveWorklog(request) == expected
        where:
            request || expected
            SWR()   || 1
            SWR()   || null
    }
    def "test for get worklog jdbc call"(){
        given: "set sqlCall Mock object"
            def sqlCallWorklog = new SqlCallWorklog([
                spGetWorklog: Mock(SpGetWorklog){
                    execute(request) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlCallWorklog.getWorklog(request) == expected
        where:
            request || expected
            5       || [GWR()]
            3       || null
    }
    
    private def GWR(){
        return new GetWorklogResponse("test", LocalDateTime.now(), LocalDateTime.now(), Status.NOT_SET)
    }
    
    private def SWR(){
        return new SaveWorklogRequest("test", LocalDateTime.now(), LocalDateTime.now(), 1)
    }
}

