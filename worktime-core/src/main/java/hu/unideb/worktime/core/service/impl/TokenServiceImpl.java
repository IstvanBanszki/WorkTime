package hu.unideb.worktime.core.service.impl;

import hu.unideb.worktime.api.model.Role;
import hu.unideb.worktime.api.model.Token;
import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.core.cache.ILoginCache;
import hu.unideb.worktime.core.service.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static hu.unideb.worktime.api.model.Role.NOT_SET;
import static hu.unideb.worktime.api.model.Role.WORKER_ROLE;
import static hu.unideb.worktime.api.model.Role.SUPEROR_ROLE;

@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired private ILoginCache loginCache;

    private Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String WORKERID_CLAIM = "workerId";
    private static final String ROLE_CLAIM = "role";
    private static final String TOKEN_SECRETKEY_HEADER = "WoRkTiMe";
    private static final int EXPIRATION_TIME_MIN = 15;

    private static final String ADMINISTRATION_SERVICE_URI = "/api/administration/v1";
    private static final String ADDITION_SERVICE_URI = "/api/addition/v1";

    @Override
    public Token generateToken(String loginName) {

        LoginRecord record = this.loginCache.getByName(loginName);
        this.logger.info("Result of get logins cache calling: {}", record);

        Token token = null;
        if (record != null) {

            Date expiration = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(expiration);
            cal.add(Calendar.MINUTE, EXPIRATION_TIME_MIN);

            try {
                token = new Token(Jwts.builder().setSubject(loginName)
                        .claim(ROLE_CLAIM, record.getRoleName()).claim(WORKERID_CLAIM, record.getWorkerId()).setExpiration(cal.getTime())
                        .signWith(SignatureAlgorithm.HS256, TOKEN_SECRETKEY_HEADER).compact());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        this.logger.info("Newly created token - {}", token);
        return token;
    }

    @Override
    public boolean checkTokenValidity(Claims token) {

        this.logger.info("Get the JWT token from the auth header: {}", token);
        LoginRecord record = this.loginCache.getByName(token.getSubject());
        this.logger.info("Result of get logins cache calling: {}", record);

        return record != null && record.getRoleName().equalsIgnoreCase(token.get(ROLE_CLAIM, String.class))
                && record.getWorkerId() == token.get(WORKERID_CLAIM, Integer.class);
    }

    @Override
    public boolean checkTokenExpiration(Claims token) {
        return token.getExpiration().before(new Date());
    }

    @Override
    public boolean checkTokenRole(Claims token, String uri) {
        boolean result = true;
        Role role = Role.getValue(token.get(ROLE_CLAIM, String.class));
        if ((role == NOT_SET) ||
            (role == WORKER_ROLE && (uri.startsWith(ADMINISTRATION_SERVICE_URI) || uri.startsWith(ADDITION_SERVICE_URI))) ||
            (role == SUPEROR_ROLE && uri.startsWith(ADDITION_SERVICE_URI))) {
            result = false;
        }
        return result;
    }

}
