package hu.unideb.worktime.jdbc.worklog

import hu.unideb.worktime.api.model.worklog.SaveWorklogRequest
import java.time.LocalDateTime
import spock.lang.Specification

class SqlCalWorklogTest extends Specification {

    def "test for login jdbc call"(){
        given: "set sqlCall Mock object"
            def spSaveWorklogMock = Mock(SpSaveWorklog){
                execute(request) >> expected
            }
            def sqlCallSaveWorklog = new SqlCallSaveWorklog([
                spSaveWorklog: spSaveWorklogMock
            ])
        expect:
            sqlCallSaveWorklog.saveWorklog(request) == expected
        where:
            request || expected
            SWR()   || 1
    }
    
    private def SWR(){
        return new SaveWorklogRequest("test", LocalDateTime.now(), LocalDateTime.now(), 1)
    }
}

