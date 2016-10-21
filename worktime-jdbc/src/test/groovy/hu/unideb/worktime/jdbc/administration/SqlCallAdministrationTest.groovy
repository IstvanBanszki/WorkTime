package hu.unideb.worktime.jdbc.administration

import hu.unideb.worktime.api.model.AbsenceType
import hu.unideb.worktime.api.model.Status
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse
import hu.unideb.worktime.api.model.administration.WorkerData
import hu.unideb.worktime.api.model.administration.Employee
import hu.unideb.worktime.api.model.administration.Note
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpApproveEmployeeAbsence
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpGetEmployeeWorklogList
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpGetEmployeeAbsenceList
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpEditWorkerData
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpUpdateWorklogNote
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpUpdateAbsenceNote
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpGetEmployeeWorkerData
import hu.unideb.worktime.jdbc.administration.storedprocedure.SpGetEmployeeList
import spock.lang.Specification
import java.time.LocalDate
import java.time.LocalDateTime
import org.slf4j.helpers.NOPLogger

class SqlCallAdministrationTest extends Specification {
	
    def "test getEmloyees method"() {
        setup:
            def sqlCallAsministraionObject = new SqlCallAdministration([
                spGetEmployeeList: Mock(SpGetEmployeeList) {
                    getEmployees(workerId) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAsministraionObject.getEmloyees(workerId) == expectedResult
        where:
            workerId || expectedResult
            1        || null
            2        || []
            3        || [E(1, "Jack", "Sparrow"), E(2, "Abraham", "Lincoln")]
    }
	
    def "test getEmloyeeWorklog method"() {
        setup:
            def sqlCallAsministraionObject = new SqlCallAdministration([
                spGetEmployeeWorklogList: Mock(SpGetEmployeeWorklogList) {
                    getWorklogListForEmployee(workerId, dateFilter, showDailyWorkhours) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAsministraionObject.getEmloyeeWorklog(workerId, dateFilter, showDailyWorkhours) == expectedResult
        where:
            workerId | dateFilter | showDailyWorkhours || expectedResult
            1        | "All"      | true               || null
            2        | "All"      | true               || []
            2        | "All"      | true               || [AWE("Note 1", 1, 7), AWE("Note 2", 2, 8)]
    }
	
    def "test getEmloyeeAbsence method"() {
        setup:
            def sqlCallAsministraionObject = new SqlCallAdministration([
                spGetEmployeeAbsenceList: Mock(SpGetEmployeeAbsenceList) {
                    getAbsenceListForEmployee(workerId, dateFilter, notApprove) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAsministraionObject.getEmloyeeAbsence(workerId, dateFilter, notApprove) == expectedResult
        where:
            workerId | dateFilter | notApprove || expectedResult
            1        | "All"      | true       || null
            2        | "All"      | true       || []
            2        | "All"      | true       || [AAE("Note 1", 1, Status.NOT_APPROVE, AbsenceType.PAYED), AAE("Note 2", 2, Status.APPROVE, AbsenceType.UNPAYED)]
    }
	
    def "test acceptEmployeeAbsence method"() {
        setup:
            def sqlCallAsministraionObject = new SqlCallAdministration([
                spAcceptAbsenceStatus: Mock(SpApproveEmployeeAbsence) {
                    approve(absenceId) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAsministraionObject.acceptEmployeeAbsence(absenceId) == expectedResult
        where:
            absenceId || expectedResult
            1         || 0
            2         || 1
    }
    
    def "test updateWorklogNote method"() {
        setup:
            def sqlCallAsministraionObject = new SqlCallAdministration([
                spUpdateWorklogNote: Mock(SpUpdateWorklogNote) {
                    updateNote(worklogId, noteRequest) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAsministraionObject.updateWorklogNote(worklogId, noteRequest) == expectedResult
        where:
            noteRequest = new Note([note: "note number 1"])

            worklogId || expectedResult
            1         || 0
            2         || 1
    }
    
    def "test updateAbsenceNote method"() {
        setup:
            def sqlCallAsministraionObject = new SqlCallAdministration([
                spUpdateAbsencegNote: Mock(SpUpdateAbsenceNote) {
                    updateNote(absenceId, noteRequest) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAsministraionObject.updateAbsenceNote(absenceId, noteRequest) == expectedResult
        where:
            noteRequest = new Note([note: "note number 1"])

            absenceId || expectedResult
            1         || 0
            2         || 1
    }
    
    def "test getWorkerData method"() {
        setup:
            def sqlCallAsministraionObject = new SqlCallAdministration([
                spGetEmployeeWorkerData: Mock(SpGetEmployeeWorkerData) {
                    getWorkerData(id) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAsministraionObject.getWorkerData(id) == expectedResult
        where:
            id || expectedResult
            1  || null
            2  || WD("Tom", "Sawyer", "Coffe maker", "tomsawyer@email.com", 8)
    }
    
    def "test editWorkerData method"() {
        setup:
            def sqlCallAsministraionObject = new SqlCallAdministration([
                spEditWorkerData: Mock(SpEditWorkerData) {
                    editWorkerData(id, request) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallAsministraionObject.editWorkerData(id, request) == expectedResult
        where:
            request = WD("Tom", "Sawyer", "Coffe maker", "tomsawyer@email.com", 8)
        
            id || expectedResult
            1  || 0
            2  || 1
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def E(id, firstName, lastName) {
        return new Employee(id, firstName, lastName)
    }
    
    private def AWE(note, id, workHour) {
        return new AdministrationWorklogResponse(LocalDateTime.now(), LocalDateTime.now(), 
            note, id, LocalDate.now(), workHour)
    }
    
    private def AAE(note, id, status, type) {
        return new AdministrationAbsenceResponse(LocalDateTime.now(), LocalDateTime.now(), 
            note, id, LocalDate.now(), LocalDate.now(), status, type)
    }
    
    private def WD(firstName, lastName, position, emailAddress, dailyWorkHourTotal) {
        return new WorkerData(firstName, lastName, position, emailAddress, dailyWorkHourTotal)
    }
    
}

