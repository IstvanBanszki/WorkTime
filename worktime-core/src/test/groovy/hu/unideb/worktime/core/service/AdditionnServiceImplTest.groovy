package hu.unideb.worktime.core.service

import hu.unideb.worktime.api.model.SaveResult
import hu.unideb.worktime.api.model.Worker
import hu.unideb.worktime.api.model.addition.UserExtended
import hu.unideb.worktime.api.model.administration.Employee
import hu.unideb.worktime.core.security.IEncryptionUtility
import hu.unideb.worktime.core.service.IMailSenderService
import hu.unideb.worktime.core.service.impl.AdditionnServiceImpl
import hu.unideb.worktime.jdbc.addition.ISqlCallAddition
import org.slf4j.helpers.NOPLogger
import spock.lang.Specification

class AdditionnServiceImplTest extends Specification {
	
    def "createWorker method test"() {
        setup:
            def service = new AdditionnServiceImpl([
                logger          : NOPLogger.NOP_LOGGER,
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
            def service = new AdditionnServiceImpl([
                logger          : NOPLogger.NOP_LOGGER,
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
            def service = new AdditionnServiceImpl([
                logger          : NOPLogger.NOP_LOGGER,
                sqlCallAddition : Mock(ISqlCallAddition) {
                    saveUser(request, newPassword) >> expected
                },
                encryptionUtility: Mock(IEncryptionUtility) {
                    generateRandomPassword() >> newPassword
                },
                mailSenderService: mailSeviceMock
            ])
        when:
            def result = service.createUser(request)
        then:
            1 * mailSeviceMock.sendEmailForNewlyRegisteredUser(request, newPassword)
        and:
            result == expected
        where:
            request            | newPassword     || expected
            new UserExtended() | "fearOfTheDark" || new SaveResult(1, 1)
            new UserExtended() | "fearOfTheDark" || new SaveResult(0, -5)
            new UserExtended() | "fearOfTheDark" || null
    }

}

