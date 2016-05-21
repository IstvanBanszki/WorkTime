package hu.unideb.worktime.core.filter;

import hu.unideb.worktime.core.security.WTEncryption;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private WTEncryption encryption;
    
    @Override
    protected void doFilterInternal(HttpServletRequest hsr, HttpServletResponse hsr1, FilterChain fc) throws ServletException, IOException {

        String authorization = hsr.getHeader("Authorization");
        if( authorization != null ){
            //System.out.println(encryption.base64Decode(authorization));
        }
        fc.doFilter(hsr, hsr1);
    }
    /*
    private String splitAuthorization( String authorization ){
        
    }*/
}
