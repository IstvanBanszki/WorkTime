package hu.unideb.worktime.core.service

import hu.unideb.worktime.api.model.login.LoginRecord
import hu.unideb.worktime.core.cache.ILoginCache
import hu.unideb.worktime.core.service.impl.TokenServiceImpl
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.util.Calendar;
import org.slf4j.helpers.NOPLogger
import spock.lang.Specification
import spock.lang.Unroll

class TokenServiceImplTest extends Specification {
	
    def "generateToken method test"() {
        setup:
            def service = new TokenServiceImpl([
                logger     : NOPLogger.NOP_LOGGER,
                loginCache : Mock(ILoginCache) {
                    getByName(loginName) >> record
                }
            ])
        when:
            def token = service.generateToken(loginName)
            def tokenBody = Jwts.parser().setSigningKey("WoRkTiMe").parseClaimsJws(token.token).getBody()
        then:
            tokenBody.getSubject() == loginName
            tokenBody.get("role", String.class) == role
            tokenBody.get("workerId", Integer.class) == workerId
        where:
            workerId = 1
            loginName = "Lion"
            role = "sleeps"
            pass = "tonight"
            record = new LoginRecord(workerId, role, pass)
    }

    @Unroll
    def "checkTokenValidity method test"() {
        setup:
            def service = new TokenServiceImpl([
                logger     : NOPLogger.NOP_LOGGER,
                loginCache : Mock(ILoginCache) {
                    getByName(cachedLoginName) >> record
                }
            ])
        and:
            def token = Mock(Claims) {
                    getSubject() >> loginName
                    get("role", String.class) >> role
                    get("workerId", Integer.class) >> workerId
            }
        expect:
            service.checkTokenValidity(token) == expected
        where:
            workerId | role  | loginName | cachedWorkerId | cachedLoginName | cachedRole || expected
            1        | "war" | "what"    | 2              | "what"          | "war"      || false
            1        | "war" | "what"    | 1              | "war"           | "goodFor"  || false
            1        | "war" | "what"    | 1              | "is"            | "what"     || false
            1        | "war" | "what"    | 2              | "is"            | "goodFor"  || false
            1        | "war" | "what"    | 1              | "what"          | "war"      || true

            record = new LoginRecord(cachedWorkerId, cachedRole, "")
    }

    def "checkTokenExpiration method test"() {
        setup:
            def token = Mock(Claims) { 
                getExpiration() >> expirationTime 
            }
        expect:
            new TokenServiceImpl().checkTokenExpiration(token) == expected
        where:

            expirationTime     || expected
            new Date(7)        || true
            getExpiratedDate() || false
    }

    @Unroll
    def "checkTokenRole method test with #role and #uri should be #expected"() {
        setup:
            def token = Mock(Claims) { 
                get("role", String.class) >> role
            }
        expect:
            new TokenServiceImpl().checkTokenRole(token, uri) == expected
        where:

            role                 | uri                      || expected
            null                 | "/api/*/v1"              || false
            ""                   | "/api/*/v1"              || false
            "unknown"            | "/api/*/v1"              || false
            "NOT_SET"            | "/api/*/v1"              || false
            "NOT-SET"            | "/api/*/v1"              || false
            "WORKER-ROLE"        | "/api/administration/v1" || false
            "WORKER-ROLE"        | "/api/administration/v1" || false
            "WORKER-ROLE"        | "/api/addition/v1"       || false
            "WORKER-ROLE"        | "/api/*/v1"              || true
            "SUPERIOR-ROLE"      | "/api/administration/v1" || true
            "SUPERIOR-ROLE"      | "/api/addition/v1"       || false
            "SUPERIOR-ROLE"      | "/api/*/v1"              || true
            "COMPANY-ADMIN-ROLE" | "/api/administration/v1" || true
            "COMPANY-ADMIN-ROLE" | "/api/addition/v1"       || true
            "COMPANY-ADMIN-ROLE" | "/api/*/v1"              || true
            "COMPANY-ADMIN"      | "/api/*/v1"              || false
    }

    private def getExpiratedDate() {
        Calendar cal = Calendar.getInstance()
        cal.setTime(new Date())
        cal.add(Calendar.MINUTE, 15)
        cal.getTime()
    }

}

