package hu.unideb.worktime.jdbc.worklog

import hu.unideb.worktime.api.model.Status
import hu.unideb.worktime.api.model.SaveResult
import hu.unideb.worktime.api.model.worklog.WorklogRequest
import hu.unideb.worktime.api.model.worklog.WorklogResponse
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpGetWorklog
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpEditWorklog
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpDeleteWorklog
import hu.unideb.worktime.jdbc.worklog.storedprocedure.SpSaveWorklog
import java.time.LocalDate
import java.time.LocalDateTime
import spock.lang.Specification
import org.slf4j.helpers.NOPLogger

class SqlCalWorklogTest extends Specification {

    def "test for save worklog jdbc call"(){
        given: "set sqlCall Mock object"
            def sqlCallWorklogObject = new SqlCallWorklog([
                spSaveWorklog: Mock(SpSaveWorklog){
                    saveWorklog(key, request) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlCallWorklogObject.saveWorklog(key, request) == expected
        where:
            key | request  || expected
            1   | WR(8, 4) || SR(1, 1)
            2   | WR(8, 2) || null
    }

    def "test for get worklog jdbc call"(){
        given: "set sqlCall Mock object"
            def sqlCallWorklogObject = new SqlCallWorklog([
                spGetWorklog: Mock(SpGetWorklog){
                    getWorklogs(key, request) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlCallWorklogObject.getWorklog(key, request) == expected
        where:
            key | request || expected
            1   | "ALL"   || [WRE(1, 8), WRE(2, 8)]
            1   | "ALL"   || []
            1   | "ALL"   || null
    }

    def "test deleteWorklog method"(){
        given: "set sqlCall Mock object"
            def sqlCallWorklogObject = new SqlCallWorklog([
                spDeleteWorklog: Mock(SpDeleteWorklog){
                    deleteWorklog(key) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlCallWorklogObject.deleteWorklog(key) == expected
        where:
            key || expected
            1   || 0
            2   || 1
    }

    def "test editWorklog method"(){
        given: "set sqlCall Mock object"
            def sqlCallWorklogObject = new SqlCallWorklog([
                spEditWorklog: Mock(SpEditWorklog){
                    editWorklog(key, request) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlCallWorklogObject.editWorklog(key, request) == expected
        where:
            key | request  || expected
            1   | WR(8, 4) || 1
            2   | WR(8, 2) || 0
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def WR(hour, workerId) {
        return new WorklogRequest(LocalDate.now(), hour, workerId)
    }
    
    private def SR(newId, status) {
        return new SaveResult(newId, status)
    }

    private def WRE(id, workHour) {
        return new WorklogResponse(id, LocalDate.now(), workHour)
    }
}

