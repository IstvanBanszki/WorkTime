package hu.unideb.worktime.core.controller

import hu.unideb.worktime.api.model.SaveResult
import hu.unideb.worktime.api.model.worklog.WorklogRequest
import hu.unideb.worktime.api.model.worklog.WorklogResponse
import hu.unideb.worktime.core.controller.worklog.v1.WorklogController
import hu.unideb.worktime.jdbc.worklog.ISqlCallWorklog
import spock.lang.Shared
import spock.lang.Specification
import java.time.LocalDate

class WorklogControllerTest extends Specification {

    @Shared def workerId = 1
    
    def "test saveWorklog method"() {
        setup:
            def sqlCallSaveWorklogMock = Mock(ISqlCallWorklog)
        and:
            def worklogControllerObject = new WorklogController(
                sqlCallSaveWorklog: sqlCallSaveWorklogMock
            )
        when:
            def response = worklogControllerObject.saveWorklog(workerId, worklogRequest)
        then:
            1 * sqlCallSaveWorklogMock.saveWorklog(workerId, worklogRequest) >> expectedResult
        and:
            response == expectedResult
        where:
            worklogRequest = WR(8, workerId)
            expectedResult << [null, SR(4, 1)]
    }
    
    def "test getWorklog method"() {
        setup:
            def sqlCallSaveWorklogMock = Mock(ISqlCallWorklog)
        and:
            def worklogControllerObject = new WorklogController(
                sqlCallSaveWorklog: sqlCallSaveWorklogMock
            )
        when:
            def response = worklogControllerObject.getWorklog(workerId, request)
        then:
            1 * sqlCallSaveWorklogMock.getWorklog(workerId, request) >> expectedResult
        and:
            response == expectedResult
        where:
            request = "request"
            expectedResult << [null, [], [WRE(1, 8), WRE(2, 7)]]
    }
    
    def "test deleteWorklog method"() {
        setup:
            def worklogControllerObject = new WorklogController(
                sqlCallSaveWorklog: Mock(ISqlCallWorklog) {
                    deleteWorklog(workerId) >> expectedResult
                }
            )
        expect:
            worklogControllerObject.deleteWorklog(workerId) == expectedResult
        where:
            expectedResult << [0, 1]
    }
    
    def "test editWorklog method"() {
        setup:
            def worklogControllerObject = new WorklogController(
                sqlCallSaveWorklog: Mock(ISqlCallWorklog) {
                    editWorklog(workerId, worklogRequest) >> expectedResult
                }
            )
        expect:
            worklogControllerObject.editWorklog(workerId, worklogRequest) == expectedResult
        where:
            worklogRequest = WR(8, workerId)
            expectedResult << [0, 1]
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

