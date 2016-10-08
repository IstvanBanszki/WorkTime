package hu.unideb.worktime.core.controller

import hu.unideb.worktime.api.model.Department
import hu.unideb.worktime.api.model.Office
import hu.unideb.worktime.api.model.SaveResult
import hu.unideb.worktime.core.controller.addition.v1.AdditionController
import hu.unideb.worktime.jdbc.addition.SqlCallAddition
import hu.unideb.worktime.jdbc.addition.SqlCallDepartmentAddition
import hu.unideb.worktime.jdbc.addition.SqlCallOfficeAddition
import spock.lang.Shared
import spock.lang.Specification
import java.time.LocalDate

class AdditionControllerTest extends Specification {
    
    @Shared def officeId = 1
    @Shared def departmentId = 1
    
    def "test editOffice method"() {
        setup:
            def additionControllerObject = new AdditionController(
                sqlCallOfficeAddition: Mock(SqlCallOfficeAddition) {
                    editOffice(officeId, office) >> expectedResult
                }
            )
        expect:
            additionControllerObject.editOffice(officeId, office) == expectedResult
        where:
            office = O(1, "Debrecen Office", "Csapo street 42.")
            expectedResult << [0, 1]
    }
    
    def "test getOffice method"() {
        setup:
            def additionControllerObject = new AdditionController(
                sqlCallOfficeAddition: Mock(SqlCallOfficeAddition) {
                    getOffices() >> expectedResult
                }
            )
        expect:
            additionControllerObject.getOffice() == expectedResult
        where:
            expectedResult << [null, [], [O(1, "Debrecen Office", "Csapo street 42"), O(2, "Budapest Office", "Andrassy street 11.")]]
    }
    
    def "test saveOffice method"() {
        setup:
            def additionControllerObject = new AdditionController(
                sqlCallOfficeAddition: Mock(SqlCallOfficeAddition) {
                    saveOffice(office) >> expectedResult
                }
            )
        expect:
            additionControllerObject.saveOffice(office) == expectedResult
        where:
            office = O(1, "Debrecen Office", "Csapo street 42.")
            expectedResult << [null, SR(1, 1)]
    }
    
    def "test editDepartment method"() {
        setup:
            def additionControllerObject = new AdditionController(
                sqlCallDepartmentAddition: Mock(SqlCallDepartmentAddition) {
                    editDepartment(departmentId, department) >> expectedResult
                }
            )
        expect:
            additionControllerObject.editDepartment(departmentId, department) == expectedResult
        where:
            department = D(1, "HR Department", 1, 8)
            expectedResult << [0, 1]
    }
    
    def "test getDepartment method"() {
        setup:
            def additionControllerObject = new AdditionController(
                sqlCallDepartmentAddition: Mock(SqlCallDepartmentAddition) {
                    getDepartments() >> expectedResult
                }
            )
        expect:
            additionControllerObject.getDepartment() == expectedResult
        where:
            expectedResult << [null, [], [D(1, "HR Department", 1, 8), D(2, "Accountant Department", 2, 4)]]
    }
    
    def "test saveDepartment method"() {
        setup:
            def additionControllerObject = new AdditionController(
                sqlCallDepartmentAddition: Mock(SqlCallDepartmentAddition) {
                    saveDepartment(department) >> expectedResult
                }
            )
        expect:
            additionControllerObject.saveDepartment(department) == expectedResult
        where:
            department = D(3, "New Department", 6, 5)
            expectedResult << [null, SR(1, 1)]
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def SR(newId, status) {
        return new SaveResult(newId, status)
    }

    private def O(id, name, address) {
        return new Office(id, name, address, LocalDate.now())
    }

    private def D(id, name, officeId, workerNumber) {
        return new Department(id, name, LocalDate.now(), officeId, workerNumber)
    }
    
}

