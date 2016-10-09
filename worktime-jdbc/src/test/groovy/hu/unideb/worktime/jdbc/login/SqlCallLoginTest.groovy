package hu.unideb.worktime.jdbc.login

import hu.unideb.worktime.api.model.login.LoginRecord
import hu.unideb.worktime.api.model.login.UpdatePasswordRecord
import hu.unideb.worktime.jdbc.login.storedprocedure.SpUpdatePassword
import hu.unideb.worktime.jdbc.login.storedprocedure.SpGetLogin
import spock.lang.Specification
import org.slf4j.helpers.NOPLogger

class SqlCallLoginTest extends Specification {

    def "test getLoginRecord method"() {
        given: "set sqlCall Mock object"
            def sqlLoginCallObject = new SqlCallLogin([
                spGetLogin: Mock(SpGetLogin){
                    getLoginRecord(loginName) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlLoginCallObject.getLoginRecord(loginName) == expected
        where:
            loginName || expected
            "test"    || new LoginRecord(1, "NOT-SET", "theThing")
            "test 2"  || null
    }

    def "test updatePassword method"() {
        given: "set sqlCall Mock object"
            def sqlLoginCallObject = new SqlCallLogin([
                spUpdatePassword: Mock(SpUpdatePassword){
                    update(key) >> expected
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlLoginCallObject.updatePassword(key) == expected
        where:
            key                       || expected
            UPR("name", "old", "new") || 1
            UPR("name", "old", "new") || -1
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def UPR(loginName, oldPassword, newPassword) {
        return new UpdatePasswordRecord(loginName,  oldPassword, newPassword)
    }

}
