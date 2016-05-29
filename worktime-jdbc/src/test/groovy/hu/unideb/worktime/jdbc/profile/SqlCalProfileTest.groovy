package hu.unideb.worktime.jdbc.profile

import hu.unideb.worktime.api.model.profile.ProfileRecord
import hu.unideb.worktime.api.model.Gender
import java.time.LocalDateTime
import spock.lang.Specification
import org.slf4j.helpers.NOPLogger

class SqlCalProfileTest extends Specification {

    def "test for get profile jdbc call"(){
        setup: "set sqlCall Mock object"
            def sqlProfileCall = new SqlCallProfile([
               spProfile: Mock( SpProfile ){
                   execute(workerId) >> expected
               },
               logger: NOPLogger.NOP_LOGGER
            ])
        expect: "compare the result with the expectations"
            sqlProfileCall.getProfileData(workerId) == expected
        where:
            workerId || expected 
            159      || PR()
            2        || null
    }

    private def PR(){
        return new ProfileRecord( LocalDateTime.now(), "homer", "simpson",
                     Gender.MALE, LocalDateTime.now(), "American", "Nuclear Facility Admin",
                     "homer.simpson@springfield.com", 4, "Nuclear Department", "SpringField Nuclear Facility")
    }
}

