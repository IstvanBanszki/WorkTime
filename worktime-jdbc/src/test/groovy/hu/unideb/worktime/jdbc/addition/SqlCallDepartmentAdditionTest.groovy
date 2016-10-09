package hu.unideb.worktime.jdbc.addition

import hu.unideb.worktime.jdbc.addition.storedprocedure.SpSaveDepartment
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpGetAllDepartments
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpEditDepartment
import hu.unideb.worktime.api.model.Department
import hu.unideb.worktime.api.model.SaveResult
import spock.lang.Specification
import java.time.LocalDate
import org.slf4j.Logger
import org.slf4j.helpers.NOPLogger

class SqlCallDepartmentAdditionTest extends Specification {
	
    def "test getDepartments method"() {
        setup:
            def sqlCallDepartmentAdditionObject = new SqlCallDepartmentAddition([
                spGetAllDepartments: Mock(SpGetAllDepartments) {
                    getDepartments() >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallDepartmentAdditionObject.getDepartments() == expectedResult
        where:
            expectedResult << [null, [], [D(1, "HR Department", 1, 1), D(2, "IT Department", 1, 5)]]
    }

    def "test saveDepartment method"() {
        setup:
            def sqlCallDepartmentAdditionObject = new SqlCallDepartmentAddition([
                spSaveDepartment: Mock(SpSaveDepartment) {
                    saveDepartment(request) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallDepartmentAdditionObject.saveDepartment(request) == expectedResult
        where:
            request                  || expectedResult
            D(1, "Department", 1, 5) || null
            D(1, "Department", 1, 5) || SR(32,1)
    }

    def "test editDepartment method"() {
        setup:
            def sqlCallDepartmentAdditionObject = new SqlCallDepartmentAddition([
                spEditDepartment: Mock(SpEditDepartment) {
                    editDepartment(departmentId, value) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER  
            ])
        expect:
            sqlCallDepartmentAdditionObject.editDepartment(departmentId, value) == expectedResult 
        where:
            departmentId = 1
            value = D(1, "Department", 1, 5)
            expectedResult << [0,1]
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def SR(newId, status) {
        return new SaveResult(newId, status)
    }

    private def D(id, name, officeId, workerNumber) {
        return new Department(id, name, LocalDate.now(), officeId, workerNumber)
    }

}

