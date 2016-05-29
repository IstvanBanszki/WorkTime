package hu.unideb.worktime.jdbc.login

import hu.unideb.worktime.api.model.login.LoginRecord
import spock.lang.Specification

class SqlCallLoginTest extends Specification {

    def "test for login jdbc call"(){
        given: "set sqlCall Mock object"
            def spLoginMock = Mock(SpLogin){
                execute(loginName) >> expected
            }
            def sqlLoginCall = new SqlCallLogin([
                spLogin: spLoginMock
            ])
        expect:
            sqlLoginCall.authenticate(loginName) == expected
        where:
            loginName || expected
            "test"    || new LoginRecord(1, "NOT-SET", "theThing")
    }
}

