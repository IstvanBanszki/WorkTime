package hu.unideb.worktime.core.service

import hu.unideb.worktime.api.model.SaveResult
import hu.unideb.worktime.api.model.User
import hu.unideb.worktime.api.model.Worker
import hu.unideb.worktime.api.model.administration.Employee
import hu.unideb.worktime.core.security.IEncryptionUtility
import hu.unideb.worktime.core.service.IMailSenderService
import hu.unideb.worktime.core.service.impl.AdditionServiceImpl
import hu.unideb.worktime.jdbc.addition.ISqlCallAddition
import spock.lang.Specification
	
class AdditionnServiceImplTest extends Specification {
	
    def "createWorker method test"() {
        setup:
            def service = new AdditionServiceImpl([
                sqlCallAddition : Mock(ISqlCallAddition) {
                    saveWorker(request) >> result
}
            ])
        expect:
            service.createWorker(request) == result
        where:
            request      || result
            new Worker() || new SaveResult(1, 1)
            new Worker() || new SaveResult(0, -5)
            new Worker() || null
    }

    def "getSuperior method test"() {
        setup:
            def service = new AdditionServiceImpl([
                sqlCallAddition : Mock(ISqlCallAddition) {
                    getSuperiors() >> result
                }
            ])
        expect:
            service.getSuperiors() == result
        where:
            result << [[], null, [new Employee(1, "first", "last")]]
    }
	
    def "createUser method test"() {
        setup:
            def mailSeviceMock = Mock(IMailSenderService)
        and:
            def service = new AdditionServiceImpl([
                sqlCallAddition : Mock(ISqlCallAddition) {
                    saveUser(request, newPassword) >> expected
                },
                encryptionUtility: Mock(IEncryptionUtility) {
                    encryptPassword("") >> newPassword
                }
            ])
        expect:
            service.createUser(request) == expected
        where:
            request    | newPassword     || expected
            new User() | "fearOfTheDark" || new SaveResult(1, 1)
            new User() | "fearOfTheDark" || new SaveResult(0, -5)
            new User() | "fearOfTheDark" || null
    }

}

