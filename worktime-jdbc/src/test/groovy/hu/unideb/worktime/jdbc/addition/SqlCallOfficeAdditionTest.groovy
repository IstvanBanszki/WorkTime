package hu.unideb.worktime.jdbc.addition

import hu.unideb.worktime.api.model.Office
import hu.unideb.worktime.api.model.SaveResult
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpSaveOffice
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpGetAllOffices
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpEditOffice
import spock.lang.Specification
import java.time.LocalDate
import java.time.LocalDateTime
import org.slf4j.helpers.NOPLogger

class SqlCallOfficeAdditionTest extends Specification {
	
    def "test getOffices method"() {
        setup:
            def sqlCallOfficeAdditionObject = new SqlCallOfficeAddition([
                spGetAllOffices: Mock(SpGetAllOffices) {
                    getOffices() >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallOfficeAdditionObject.getOffices() == expectedResult
        where:
            expectedResult << [null, [], [O(1, "Debrecen Office", "Csapo street 42"), O(2, "Budapest Office", "Andrassy street 11.")]]
    }

    def "test saveOffice method"() {
        setup:
            def sqlCallOfficeAdditionObject = new SqlCallOfficeAddition([
                spSaveOffice: Mock(SpSaveOffice) {
                    saveOffice(request) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallOfficeAdditionObject.saveOffice(request) == expectedResult
        where:
            request                        || expectedResult
            O(1, "Office", "Csapo street") || null
            O(1, "Office", "Csapo street") || SR(32,1)
    }

    def "test editOffice method"() {
        setup:
            def sqlCallOfficeAdditionObject = new SqlCallOfficeAddition([
                spEditOffice: Mock(SpEditOffice) {
                    editOffice(officeId, value) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER  
            ])
        expect:
            sqlCallOfficeAdditionObject.editOffice(officeId, value) == expectedResult 
        where:
            officeId = 1
            value = O(1, "Office", "Csapo street")
            expectedResult << [0,1]
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
}

