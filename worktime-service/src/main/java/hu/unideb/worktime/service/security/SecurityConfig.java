package hu.unideb.worktime.service.security;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    AuthenticationEntryPointImpl authenticationEntryPointImpl;
//    @Autowired
//    AuthenticationFailureHandler authenticationFailureHandler;
//    @Autowired
//    AuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/rest/**").authenticated();        
//        http.csrf().disable();
//        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl);
//        http.formLogin().successHandler(authenticationSuccessHandler);
//        http.formLogin().failureHandler(authenticationFailureHandler);
//        http.logout().logoutSuccessUrl("/");
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and()
//                .withUser("admin").password("admin").roles("ADMIN");
//    }
//
//}
