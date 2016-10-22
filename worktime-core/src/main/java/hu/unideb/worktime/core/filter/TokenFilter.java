package hu.unideb.worktime.core.filter;

import hu.unideb.worktime.core.service.ITokenService;
import hu.unideb.worktime.core.service.impl.TokenServiceImpl;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TokenFilter implements Filter {

    private ITokenService tokenService;

    //Responses
    private static final String AUTH_ERROR_MSG = "Please make sure your request has an Authorization header";
    private static final String EXPIRE_ERROR_MSG = "Token has expired";
    private static final String ERROR_TOKEN_MSG = "Unable to parse Token";
    private static final String INVALID_TOKEN_MSG = "Invalid Token";

    private boolean checkLoginRequestUri(String uri) {
        return (uri != null) ? uri.contains("/api/login/v1/") : false;
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.tokenService = WebApplicationContextUtils.
                            getRequiredWebApplicationContext(fc.getServletContext()).
                            getBean(TokenServiceImpl.class);
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) sr;
        final String authHeader = request.getHeader("Authorization");
        final String uri = request.getRequestURI();
        
        //Continue the flow if the there is a valid auth header or the requested url is a login
        if (this.tokenService.checkTokenValidity(authHeader) || checkLoginRequestUri(uri)) {
            fc.doFilter(request, sr1);
        }
    }

    @Override
    public void destroy() {
    }

}
