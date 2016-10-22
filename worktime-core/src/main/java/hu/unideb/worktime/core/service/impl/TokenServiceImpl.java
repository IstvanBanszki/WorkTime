package hu.unideb.worktime.core.service.impl;

import hu.unideb.worktime.api.model.Token;
import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.core.cache.ILoginCache;
import hu.unideb.worktime.core.service.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements ITokenService {
    
    @Autowired private ILoginCache loginCache;
    
    private Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    private static final String WORKERID_CLAIM = "workerId";
    private static final String ROLE_CLAIM = "role";
    private static final String TOKEN_SECRETKEY_HEADER = "secretkey";
    private static final String AUTH_HEADER_BEGIN = "Basic ";    

    @Override
    public Token generateToken(String loginName) {

        this.logger.info("Calling get logins cache following parameters - loginName: {}", loginName);
        LoginRecord record = this.loginCache.getByName(loginName);
        this.logger.info("Result of get logins cache calling: {}", record);
        
        Token token = null;
        if (record != null) {
            token = new Token(Jwts.builder().setSubject(loginName)
                .claim(ROLE_CLAIM, record.getRoleName()).claim(WORKERID_CLAIM, record.getWorkerId()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRETKEY_HEADER).compact());
        }
        this.logger.info("Newly created token - {}", token);
        return token;
    }

    @Override
    public boolean checkTokenValidity(String authorizationHeader) {
       
        if (authorizationHeader != null && authorizationHeader.startsWith(AUTH_HEADER_BEGIN)) {
            try {
                final Claims body = Jwts.parser().setSigningKey(TOKEN_SECRETKEY_HEADER)
                                    .parseClaimsJws(authorizationHeader.substring(6)).getBody();
                System.out.println(body);
                
                this.logger.info("Get token from the auth header: {}", body);
                LoginRecord record = this.loginCache.getByName(body.getSubject());
                this.logger.info("Result of get logins cache calling: {}", record);
                //this.logger.info("Comparisons: {}, {}, {}", record != null, record.getRoleName().equalsIgnoreCase(body.get(ROLE_CLAIM, String.class)), record.getWorkerId() == body.get(WORKERID_CLAIM, Integer.class));
                return record != null && record.getRoleName().equalsIgnoreCase(body.get(ROLE_CLAIM, String.class)) 
                        && record.getWorkerId() == body.get(WORKERID_CLAIM, Integer.class);
            } catch(Exception e) {
              e.printStackTrace();
            }
        }
        return false;
    }

}
