package hu.unideb.worktime.jdbc.profile

import hu.unideb.worktime.api.model.profile.ProfileRecord
import hu.unideb.worktime.api.model.Gender
import hu.unideb.worktime.jdbc.profile.storedprocedure.SpGetProfile
import java.time.LocalDateTime
import java.time.LocalDate
import spock.lang.Specification
import org.slf4j.helpers.NOPLogger

class SqlCalProfileTest extends Specification {

    def "test for get profile jdbc call"(){
        setup:
            def sqlCallProfileObject = new SqlCallProfile([
                spGetProfile: Mock(SpGetProfile) {
                    getProfileRecord(key) >> expectedResult
                },
                logger: NOPLogger.NOP_LOGGER
            ])
        expect:
            sqlCallProfileObject.getProfileData(key) == expectedResult
        where:
            key || expectedResult
            0   || null
            1   || PR()
    }

    /*******************************/
    /*       Utility Methods       */
    /*******************************/
    private def PR() {
        return new ProfileRecord(LocalDateTime.now(), "homer", "simpson",
                     Gender.MALE, LocalDate.now(), "American", "Nuclear Facility Admin",
                     "homer.simpson@springfield.com", 4, "Nuclear Department", "SpringField Nuclear Facility")
    }
}

