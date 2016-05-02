package hu.unideb.worktime.jdbc.login

import spock.lang.Specification

class LoginSqlCallTest extends Specification {

    def "test for login jdbc call"(){
        setup: "set sqlCall Mock object"
            def loginSqlCall = new LoginSqlCall([
               loginSp: Mock( LoginSP ){
                   execute(loginName, password) >> expected
               }
            ])
        expect:
            loginSqlCall.authenticate(loginName, password) == expected
        where:
            loginName | password || expected
            "test"    | "easy"   || "1"
    }
}

