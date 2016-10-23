package hu.unideb.worktime.api.model

import hu.unideb.worktime.api.model.WorkStatus
import spock.lang.Specification
import spock.lang.Unroll

class WorkStatusTest extends Specification {
	
    @Unroll
    def "valueOf method test with #workStatus.id int parameter expected to be #workStatus"() {
        expect:
            WorkStatus.valueOf(workStatus.id) == workStatus
        where:
            workStatus << WorkStatus.values()
    }

    @Unroll
    def "valueOf method test with invalid #workStatus int parameter expected to be NOT_SET enum"() {
        expect:
            WorkStatus.valueOf(workStatus) == WorkStatus.NOT_SET
        where:
            workStatus << [-1, 0, 5, 6]
    }

}

