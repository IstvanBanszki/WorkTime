package hu.unideb.worktime.api.model

import hu.unideb.worktime.api.model.Gender
import spock.lang.Specification
import spock.lang.Unroll

class GenderTest extends Specification {
	
    @Unroll
    def "valueOf method test with #gender.id int parameter expected to be #gender"() {
        expect:
            Gender.valueOf(gender.id) == gender
        where:
            gender << Gender.values()
    }

    @Unroll
    def "valueOf method test with invalid #gender int parameter expected to be NOT_SET enum"() {
        expect:
            Gender.valueOf(gender) == Gender.NOT_SET
        where:
            gender << [-2, -1, 3, 4]
    }

}

