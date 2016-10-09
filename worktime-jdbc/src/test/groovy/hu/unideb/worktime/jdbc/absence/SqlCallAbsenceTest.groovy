package hu.unideb.worktime.jdbc.absence

import hu.unideb.worktime.api.model.AbsenceType
import hu.unideb.worktime.api.model.Status
import hu.unideb.worktime.api.model.SaveResult
import hu.unideb.worktime.api.model.absence.AbsenceDataResponse
import hu.unideb.worktime.api.model.absence.AbsenceRequest
import hu.unideb.worktime.api.model.absence.AbsenceResponse
import hu.unideb.worktime.jdbc.absence.storedprocedure.SpGetAbsence
import hu.unideb.worktime.jdbc.absence.storedprocedure.SpSaveAbsence
import hu.unideb.worktime.jdbc.absence.storedprocedure.SpDeleteAbsence
import hu.unideb.worktime.jdbc.absence.storedprocedure.SpGetAbsenceData
import hu.unideb.worktime.jdbc.absence.storedprocedure.SpEditAbsence
import spock.lang.Specification
import java.time.LocalDate
import java.time.LocalDateTime
import org.slf4j.helpers.NOPLogger

class SqlCallAbsenceTest extends Specification {

    def "test getAbsence method"() {
        setup:
            def sqlCallAbsenceObject = new SqlCallAbsence([
                spGetAbsence: Mock(SpGetAbsence) {
                    getAbsences(key, request) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAbsenceObject.getAbsence(key, request) == expectedResult
        where:
            key | request || expectedResult
            1   | "ALL"   ||  null
            2   | "ALL"   ||  []
            3   | "ALL"   ||  [ARE(1, Status.NOT_APPROVE, AbsenceType.PAYED), ARE(2, Status.APPROVE, AbsenceType.UNPAYED)]
    }
    
    def "test saveAbsence method"() {
        setup:
            def sqlCallAbsenceObject = new SqlCallAbsence([
                spSaveAbsence: Mock(SpSaveAbsence) {
                    saveAbsence(workerId, request) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAbsenceObject.saveAbsence(workerId, request) == expectedResult
        where:
            workerId | request                  || expectedResult
            1        | AR(1, AbsenceType.PAYED) ||  null
            2        | AR(2, AbsenceType.PAYED) ||  SR(1,1)
    }
    
    def "test getAbsenceData method"() {
        setup:
            def sqlCallAbsenceObject = new SqlCallAbsence([
                spGetAbsenceData: Mock(SpGetAbsenceData) {
                    getAbsenceData(key) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAbsenceObject.getAbsenceData(key) == expectedResult
        where:
            key || expectedResult
            1   ||  null
            2   ||  []
            3   ||  [ADR(1, 2, 3, 4, 5, 6, 7, 8), ADR(10, 20, 30, 40, 50, 60, 70, 80)]
    }
    
    def "test deleteAbsence method"() {
        setup:
            def sqlCallAbsenceObject = new SqlCallAbsence([
                spDeleteAbsence: Mock(SpDeleteAbsence) {
                    deleteAbsence(key) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAbsenceObject.deleteAbsence(key) == expectedResult
        where:
            key || expectedResult
            1   ||  0
            2   ||  1
    }
    
    def "test editAbsence method"() {
        setup:
            def sqlCallAbsenceObject = new SqlCallAbsence([
                spEditAbsence: Mock(SpEditAbsence) {
                    editAbsence(key, request) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAbsenceObject.editAbsence(key, request) == expectedResult
        where:
            key | request                  || expectedResult
            1   | AR(1, AbsenceType.PAYED) ||  0
            2   | AR(1, AbsenceType.PAYED) ||  1
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

