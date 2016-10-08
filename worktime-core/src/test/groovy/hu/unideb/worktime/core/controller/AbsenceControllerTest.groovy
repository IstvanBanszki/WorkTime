package hu.unideb.worktime.core.controller

import hu.unideb.worktime.api.model.AbsenceType
import hu.unideb.worktime.api.model.SaveResult
import hu.unideb.worktime.api.model.Status
import hu.unideb.worktime.api.model.absence.AbsenceDataResponse
import hu.unideb.worktime.api.model.absence.AbsenceRequest
import hu.unideb.worktime.api.model.absence.AbsenceResponse
import hu.unideb.worktime.core.controller.absence.v1.AbsenceController
import hu.unideb.worktime.jdbc.absence.SqlCallAbsence
import spock.lang.Shared
import spock.lang.Specification
import java.time.LocalDate

class AbsenceControllerTest extends Specification {

    @Shared def workerId = 1
    
    def "test saveAbsence method"() {
        setup:
            def absenceControllerObject = new AbsenceController(
                sqlCallAbsence: Mock(SqlCallAbsence) {
                    saveAbsence(workerId, absenceRequest) >> expectedResult
                }
            )
        expect:
            absenceControllerObject.saveAbsence(workerId, absenceRequest) == expectedResult
        where:
            absenceRequest = AR(workerId, AbsenceType.PAYED)
            expectedResult << [null, SR(3, 1)]
    }
    
    def "test getAbsence method"() {
        setup:
            def absenceControllerObject = new AbsenceController(
                sqlCallAbsence: Mock(SqlCallAbsence) {
                    getAbsence(workerId, dateFilter) >> expectedResult
                }
            )
        expect:
            absenceControllerObject.getAbsence(workerId, dateFilter) == expectedResult
        where:
            dateFilter = "ALL"
            expectedResult << [null, [], [ARE(1, Status.NOT_APPROVE, AbsenceType.PAYED), ARE(2, Status.APPROVE, AbsenceType.UNPAYED)]]
    }
    
    def "test getAbsenceData method"() {
        setup:
            def absenceControllerObject = new AbsenceController(
                sqlCallAbsence: Mock(SqlCallAbsence) {
                    getAbsenceData(workerId) >> expectedResult
                }
            )
        expect:
            absenceControllerObject.getAbsenceData(workerId) == expectedResult
        where:
            expectedResult << [null, [], [ADR(1, 2, 3, 4, 5, 6, 7, 8), ADR(10, 20, 30, 40, 50, 60, 70, 80)]]
    }
    
    def "test deleteAbsence method"() {
        setup:
            def absenceControllerObject = new AbsenceController(
                sqlCallAbsence: Mock(SqlCallAbsence) {
                    deleteAbsence(workerId) >> expectedResult
                }
            )
        expect:
            absenceControllerObject.deleteAbsence(workerId) == expectedResult
        where:
            expectedResult << [0, 1]
    }
    
    def "test editAbsence method"() {
        setup:
            def absenceControllerObject = new AbsenceController(
                sqlCallAbsence: Mock(SqlCallAbsence) {
                    editAbsence(workerId, absenceRequest) >> expectedResult
                }
            )
        expect:
            absenceControllerObject.editAbsence(workerId, absenceRequest) == expectedResult
        where:
            absenceRequest = AR(workerId, AbsenceType.UNPAYED)
            expectedResult << [0, 1]
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def SR(newId, status) {
        return new SaveResult(newId, status)
    }

    private def AR(workerId, type) {
        return new AbsenceRequest(LocalDate.now(), LocalDate.now(), workerId, type)
    }
    
    private def ARE(id, status, type) {
        return new AbsenceResponse(id, LocalDate.now(), LocalDate.now(), status, type)
    }
    
    private def ADR(year, holidayNumber, absenceNumber, notSetAbsenceNumber, payedAbsenceNumber, 
                    unPayedAbsenceNumber, sickPayedAbsenceNumber, verifiedAbsenceNumber) {
        return new AbsenceDataResponse(year, holidayNumber, absenceNumber, notSetAbsenceNumber, payedAbsenceNumber, 
                    unPayedAbsenceNumber, sickPayedAbsenceNumber, verifiedAbsenceNumber)
    }
}

