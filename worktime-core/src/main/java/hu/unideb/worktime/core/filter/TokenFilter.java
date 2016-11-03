package hu.unideb.worktime.core.filter;

import hu.unideb.worktime.core.service.ITokenService;
import hu.unideb.worktime.core.service.impl.TokenServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TokenFilter implements Filter {

    private ITokenService tokenService;

    private static final String AUTH_HEADER_BEGIN = "Basic ";
    private static final String TOKEN_SECRETKEY_HEADER = "WoRkTiMe";

    private static final String LOGIN_SERVICE_URI = "/api/login/v1";
    private static final String ENTRANCE_SERVICE_URI = "/api/entrance/v1";
    //Responses
    private static final String AUTH_ERROR_MSG = "No Authorization header";
    private static final String EXPIRE_ERROR_MSG = "Token has expired";
    private static final String INVALID_TOKEN_MSG = "Invalid Token";

    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.tokenService = WebApplicationContextUtils.
                            getRequiredWebApplicationContext(fc.getServletContext()).
                            getBean(TokenServiceImpl.class);
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        final String authHeader = request.getHeader("Authorization");
        final String uri = request.getRequestURI();

        if (checkLoginRequestUri(uri)) {
            fc.doFilter(request, sr1);
        } else if (checkAuthHeader(authHeader)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, AUTH_ERROR_MSG);
        } else {
            try {
                final Claims token = Jwts.parser().setSigningKey(TOKEN_SECRETKEY_HEADER)
                        .parseClaimsJws(authHeader.substring(6)).getBody();
 
                if (!this.tokenService.checkTokenValidity(token)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, INVALID_TOKEN_MSG);
                } else if (this.tokenService.checkTokenExpiration(token)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, EXPIRE_ERROR_MSG);
                } else if (!this.tokenService.checkTokenRole(token, uri)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, EXPIRE_ERROR_MSG);
                } else {
                    fc.doFilter(request, sr1);
                }
            } catch(Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, INVALID_TOKEN_MSG);
            }
        }
    }

    @Override
    public void destroy() {
    }

    private boolean checkLoginRequestUri(String uri) {
        return uri != null ? uri.contains(LOGIN_SERVICE_URI) || uri.contains(ENTRANCE_SERVICE_URI) : false;
    }

    boolean checkAuthHeader(String authHeader){
        return authHeader == null || authHeader.isEmpty() || !authHeader.startsWith(AUTH_HEADER_BEGIN);
    }

}
