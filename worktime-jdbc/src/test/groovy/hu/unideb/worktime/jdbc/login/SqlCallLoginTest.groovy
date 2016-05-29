package hu.unideb.worktime.jdbc.login

import hu.unideb.worktime.api.model.login.LoginRecord
import spock.lang.Specification
import org.slf4j.helpers.NOPLogger

class SqlCallLoginTest extends Specification {

    def "test for login jdbc call"(){
        given: "set sqlCall Mock object"
            def sqlLoginCall = new SqlCallLogin([
                spLogin: Mock(SpLogin){
                    execute(loginName) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlLoginCall.authenticate(loginName) == expected
        where:
            loginName || expected
            "test"    || new LoginRecord(1, "NOT-SET", "theThing")
            "test 2"  || null
    }
}

