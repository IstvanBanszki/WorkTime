package hu.unideb.worktime.core.controller

import hu.unideb.worktime.api.model.login.Password
import hu.unideb.worktime.api.model.login.UpdatePasswordRequest
import hu.unideb.worktime.api.model.login.LoginResponse
import hu.unideb.worktime.core.controller.login.v1.LoginController
import hu.unideb.worktime.core.service.ILoginService
import spock.lang.Specification

class LoginControllerTest extends Specification {
	
    def "test getLogin method"() {
        setup:
            def losingServiceMock = Mock(ILoginService)
        and:
            def loginControllerObject = new LoginController([
                losingService: losingServiceMock
            ])
        when:
            def result = loginControllerObject.getLogin(loginName, password)
        then:
            1 * losingServiceMock.getLogin(loginName, password) >> serviceResult
        and:
            result == serviceResult
        where:
            loginName = "admin"
            password = P("password")
            serviceResult << [LR(1, "ADMIN-ROLE"), null]
    }
	
    def "test updateLogin method"() {
        setup:
            def losingServiceMock = Mock(ILoginService)
        and:
            def loginControllerObject = new LoginController([
                losingService: losingServiceMock
            ])
        when:
            def result = loginControllerObject.updateLogin(loginName, updatePassRequest)
        then:
            1 * losingServiceMock.updateLogin(loginName, updatePassRequest) >> serviceResult
        and:
            result == serviceResult
        where:
            loginName = "admin"
            password = P("password")
            updatePassRequest = UPR("password", "newPassword")

            serviceResult << [0, 1]
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def P(password) {
        return new Password([password: password])
    }

    private def LR(workerId, roleName) {
        return new LoginResponse(workerId, roleName)
    }

    private def UPR(oldPassword, newPassword) {
        return new UpdatePasswordRequest([oldPassword: oldPassword, newPassword: newPassword])
    }

}

