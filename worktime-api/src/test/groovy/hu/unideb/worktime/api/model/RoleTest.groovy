package hu.unideb.worktime.api.model

import hu.unideb.worktime.api.model.Role
import spock.lang.Specification
import spock.lang.Unroll

class RoleTest extends Specification {

    @Unroll
    def "valueOf method test with #role.id int parameter expected to be #role"() {
        expect:
            Role.valueOf(role.id) == role
        where:
            role << Role.values()
    }

    @Unroll
    def "valueOf method test with invalid #role int parameter expected to be NOT_SET enum"() {
        expect:
            Role.valueOf(role) == Role.NOT_SET
        where:
            role << [-1, 0, 5, 6]
    }

    @Unroll
    def "getValue method test with #role.id string parameter expected to be #role"() {
        expect:
            Role.getValue(role.name) == role
        where:
            role << Role.values()
    }

    @Unroll
    def "getValue method test with invalid #role string parameter expected to be NOT_SET enum"() {
        expect:
            Role.getValue(role) == Role.NOT_SET
        where:
            role << ["", "Unknown", null]
    }
}

