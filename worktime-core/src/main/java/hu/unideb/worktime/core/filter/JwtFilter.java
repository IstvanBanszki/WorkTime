package hu.unideb.worktime.core.filter;

import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.core.cache.ILoginCache;
import hu.unideb.worktime.core.security.IEncryptionUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.security.SignatureException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {
/*
    @Autowired private IEncryptionUtility encryptionUtility;
    @Autowired private ILoginCache cache;
*/
    //Responses
    private static final String AUTH_ERROR_MSG = "Please make sure your request has an Authorization header";
    private static final String EXPIRE_ERROR_MSG = "Token has expired";
    private static final String ERROR_TOKEN_MSG = "Unable to parse Token";
    private static final String INVALID_TOKEN_MSG = "Invalid Token";
    
    @Override
    protected void doFilterInternal(HttpServletRequest hsr, HttpServletResponse hsr1, FilterChain fc) throws ServletException, IOException {


        final String authHeader = hsr.getHeader("Authorization");
        final String uri = hsr.getRequestURI();
        System.out.println(authHeader);
        System.out.println(uri);
        System.out.println(checkAuthorizationHeader(authHeader));
        System.out.println(!checkLoginRequestUri(uri));
        if (checkAuthorizationHeader(authHeader) || !checkLoginRequestUri(uri)) {
            fc.doFilter(hsr, hsr1);
        } else {
            fc.doFilter(hsr, hsr1);
        }
    }

    private boolean checkAuthorizationHeader(String authorization) {
        if (authorization != null && !authorization.startsWith("Basic")) {
            try {
                /*
                String auth = this.encryptionUtility.base64Decode(authorization);
                String[] splited = auth.split(":");
                LoginRecord record = this.cache.getByName(splited[0]);
                return record.getWorkerId() == Integer.valueOf(splited[1]) &&
                       record.getRoleName().equalsIgnoreCase(splited[2]);
                */
            } catch(Exception e) {
              e.printStackTrace();
            }
        }
        return false;
    }

    private boolean checkLoginRequestUri(String uri) {
        return (uri != null) ? uri.contains("/api/login/v1") : false;
    }

}
