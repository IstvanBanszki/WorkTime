package hu.unideb.worktime.core.controller

import hu.unideb.worktime.api.model.AbsenceType
import hu.unideb.worktime.api.model.Status
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceRequest
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse
import hu.unideb.worktime.api.model.administration.AdministrationWorklogRequest
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse
import hu.unideb.worktime.api.model.administration.Employee
import hu.unideb.worktime.api.model.administration.Note
import hu.unideb.worktime.api.model.administration.WorkerData
import hu.unideb.worktime.core.controller.administration.v1.AdministrationController
import hu.unideb.worktime.jdbc.administration.SqlCallAdministration
import spock.lang.Shared
import spock.lang.Specification
import java.time.LocalDate
import java.time.LocalDateTime

class AdministrationControllerTest extends Specification {

    @Shared def employeeId = 1

    def "test getEmployeeWorklogList method"() {
        setup:
            def administrationControllerObject = new AdministrationController(
                sqlCallAdministration: Mock(SqlCallAdministration) {
                    getEmloyeeWorklog(employeeId, request) >> expectedResult
                }
            )
        expect:
            administrationControllerObject.getEmployeeWorklogList(employeeId, request) == expectedResult
        where:
            request = new AdministrationWorklogRequest("All", true)
            expectedResult << [null, [], [AWE("Note 1", 1, 7), AWE("Note 2", 2, 8)]]
    }

    def "test getEmployeeAbsenceList method"() {
        setup:
            def administrationControllerObject = new AdministrationController(
                sqlCallAdministration: Mock(SqlCallAdministration) {
                    getEmloyeeAbsence(employeeId, request) >> expectedResult
                }
            )
        expect:
            administrationControllerObject.getEmployeeAbsenceList(employeeId, request) == expectedResult
        where:
            request = new AdministrationAbsenceRequest("All", true)
            expectedResult << [null, [], [AAE("Note 1", 1, Status.NOT_APPROVE, AbsenceType.PAYED), AAE("Note 2", 2, Status.APPROVE, AbsenceType.UNPAYED)]]
    }
    
    def "test getEmployee method"() {
        setup:
            def administrationControllerObject = new AdministrationController(
                sqlCallAdministration: Mock(SqlCallAdministration) {
                    getEmloyees(superiorWorkerId) >> expectedResult
                }
            )
        expect:
            administrationControllerObject.getEmployee(superiorWorkerId) == expectedResult
        where:
            superiorWorkerId = 5
            expectedResult << [null, [], [E(1, "Jack", "Sparrow"), E(2, "Abraham", "Lincoln")]]
    }

    def "test acceptEmployeeAbsence method"() {
        setup:
            def administrationControllerObject = new AdministrationController(
                sqlCallAdministration: Mock(SqlCallAdministration) {
                    acceptEmployeeAbsence(absenceId) >> expectedResult
                }
            )
        expect:
            administrationControllerObject.acceptEmployeeAbsence(absenceId) == expectedResult
         where:
            absenceId = 1
            expectedResult << [0, 1]
    }

    def "test updateWorklogNote method"() {
        setup:
            def administrationControllerObject = new AdministrationController(
                sqlCallAdministration: Mock(SqlCallAdministration) {
                    updateWorklogNote(worklogId, noteRequest) >> expectedResult
                }
            )
        expect:
            administrationControllerObject.updateWorklogNote(worklogId, noteRequest) == expectedResult
         where:
            worklogId = 1
            noteRequest = new Note([note: "note number 1"])
            expectedResult << [0, 1]
    }

    def "test updateAbsenceNote method"() {
        setup:
            def administrationControllerObject = new AdministrationController(
                sqlCallAdministration: Mock(SqlCallAdministration) {
                    updateAbsenceNote(absenceId, noteRequest) >> expectedResult
                }
            )
        expect:
            administrationControllerObject.updateAbsenceNote(absenceId, noteRequest) == expectedResult
         where:
            absenceId = 1
            noteRequest = new Note([note: "note number 1"])
            expectedResult << [0, 1]
    }
    
    def "test editWorkerData method"() {
        setup:
            def administrationControllerObject = new AdministrationController(
                sqlCallAdministration: Mock(SqlCallAdministration) {
                    editWorkerData(employeeId, request) >> expectedResult
                }
            )
        expect:
            administrationControllerObject.editWorkerData(employeeId, request) == expectedResult
         where:
            request = WD("Tom", "Sawyer", "Coffe maker", "tomsawyer@email.com", 8)
            expectedResult << [0, 1]
    }
    
    def "test getWorkerData method"() {
        setup:
            def administrationControllerObject = new AdministrationController(
                sqlCallAdministration: Mock(SqlCallAdministration) {
                    getWorkerData(employeeId) >> expectedResult
                }
            )
        expect:
            administrationControllerObject.getWorkerData(employeeId) == expectedResult
         where:
            expectedResult << [null, WD("Peter", "Griffn", "idiot", "peter.griffin@email.com", 8)]
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

