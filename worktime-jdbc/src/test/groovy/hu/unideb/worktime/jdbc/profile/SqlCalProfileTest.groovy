package hu.unideb.worktime.jdbc.profile

import hu.unideb.worktime.api.model.profile.ProfileRecord
import hu.unideb.worktime.api.model.Gender
import java.time.LocalDateTime
import spock.lang.Specification

class SqlCalProfileTest extends Specification {

    def "test for profile jdbc call"(){
        setup: "set sqlCall Mock object"
            def sqlProfileCall = new SqlCallProfile([
               spProfile: Mock( SpProfile ){
                   execute(workerId) >> expected
               }
            ])
        expect:
            sqlProfileCall.getProfileData(workerId) == expected
        where:
            expected || workerId 
            159      || PR()
    }

    private def PR(){
        return new ProfileRecord( LocalDateTime.now(), "homer", "simpson",
                     Gender.MALE, LocalDateTime.now(), "American", "Nuclear Facility Admin",
                     "homer.simpson@springfield.com", 4, "Nuclear Department", "SpringField Nuclear Facility")
    }
}

