package hu.unideb.worktime.core.controller

import hu.unideb.worktime.api.model.Gender
import hu.unideb.worktime.api.model.profile.ProfileRecord
import hu.unideb.worktime.core.controller.profile.v1.ProfileController
import hu.unideb.worktime.jdbc.profile.SqlCallProfile
import spock.lang.Specification
import java.time.LocalDateTime
import java.time.LocalDate

class ProfileControllerTest extends Specification {

    def "test getLogin method"() {
        setup:
            def sqlCallProfileMock = Mock(SqlCallProfile)
        and:
            def profileControllerObject = new ProfileController([
                sqlCallProfile: sqlCallProfileMock
            ])
        when:
            def response = profileControllerObject.getProfile(workerId)
        then:
            1 * sqlCallProfileMock.getProfileData(workerId) >> resultOfSqlCall
        and:
            response == resultOfSqlCall
        where:
            workerId = 1
            resultOfSqlCall << [null, PR()]
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def PR() {
        return new ProfileRecord(LocalDateTime.now(), "firstName", "lastName", 
            Gender.FEMALE, LocalDate.now(), "nationality", "position", "emailAddress",
            8, "departmentName", "officeName")
    }

}

