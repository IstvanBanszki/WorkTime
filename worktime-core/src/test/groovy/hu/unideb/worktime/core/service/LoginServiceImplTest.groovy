package hu.unideb.worktime.core.service

import hu.unideb.worktime.api.model.login.LoginRecord
import hu.unideb.worktime.api.model.login.Password
import hu.unideb.worktime.api.model.login.UpdatePasswordRecord
import hu.unideb.worktime.api.model.login.UpdatePasswordRequest
import hu.unideb.worktime.core.security.WTEncryption
import hu.unideb.worktime.core.cache.ILoginCache
import hu.unideb.worktime.jdbc.login.SqlCallLogin;
import org.slf4j.Logger
import org.slf4j.helpers.NOPLogger
import spock.lang.Shared
import spock.lang.Specification

class LoginServiceImplTest extends Specification {
    
    def "test getLogin method"() {
        setup:
            def wtEncryptionMock = Mock(WTEncryption)
            def loginCacheMock = Mock(ILoginCache)
        and:
            def loginServiceObject = new LoginServiceImpl([
                wtEncryption: wtEncryptionMock,
                loginCache  : loginCacheMock,
                logger      : NOPLogger.NOP_LOGGER
            ])
        when:
            def response = loginServiceObject.getLogin(loginName, password)
        then:
            1 * loginCacheMock.getByName(loginName) >> loginRecord
            1 * wtEncryptionMock.checkPassword(password.getPassword(), loginRecord.getPassword()) >> passwordCheckResult
        and:
            passwordCheckResult ? response != null : response == null
        where:
            loginName = "admin"
            password = P("password")
            loginRecord = LR(1, loginName, "password")
            
            passwordCheckResult << [true, false]
    }
    
    def "test updateLogin method"() {
        setup:
            def wtEncryptionMock = Mock(WTEncryption)
            def loginCacheMock = Mock(ILoginCache)
            def sqlCallLoginMock = Mock(SqlCallLogin)
        and:
            def loginServiceObject = new LoginServiceImpl([
                wtEncryption: wtEncryptionMock,
                loginCache  : loginCacheMock, 
                sqlCallLogin: sqlCallLoginMock,
                logger      : NOPLogger.NOP_LOGGER
            ])
        when:
            def response = loginServiceObject.updateLogin(loginName, updatePassRequest)
        then:
            1 * loginCacheMock.getByName(loginName) >> loginRecord
            1 * wtEncryptionMock.checkPassword(updatePassRequest.getOldPassword(), loginRecord.getPassword()) >> passwordCheckResult
            1 * wtEncryptionMock.encryptPassword(paramNewPass) >> encryptResult
            1 * loginCacheMock.updateByName(_, _)
            1 * sqlCallLoginMock.updatePassword(updatePassRecord) >> sqlUpdateResult
        and:
            passwordCheckResult ? response == sqlUpdateResult : response == 0
        where:
            paramOldPass | paramNewPass | encryptResult || passwordCheckResult | sqlUpdateResult
            "password"   | "NagyonBaba" | "kl;asdfa"    || true                | 1
            "password"   | "NagyonBaba" | "kl;asdfa"    || true                | 0
        
            loginName = "admin"
            cachedPassword = "password"
            updatePassRequest = UPR(paramOldPass, paramNewPass)
            updatePassRecord = UPRE(loginName, cachedPassword, encryptResult)
            loginRecord = LR(1, loginName, cachedPassword)
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def P(password) {
        return new Password([password: password])
    }

    private def LR(workerId, loginName, password) {
        return new LoginRecord(workerId, loginName, password)
    }

    private def UPR(oldPassword, newPassword) {
        return new UpdatePasswordRequest([oldPassword: oldPassword, newPassword: newPassword])
    }

    private def UPRE(loginName, oldPassword, encryptedNewPassword) {
        return new UpdatePasswordRecord(loginName, oldPassword, encryptedNewPassword)
    }

}

