package hu.unideb.worktime.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "hu.unideb.worktime")
@EnableAutoConfiguration
public class SpringBootMainApplication {
    
    public static void main(String[] args) {
        hu.unideb.worktime.service.security.WTEncryption enc = new hu.unideb.worktime.service.security.WTEncryption();
        System.out.println(enc.encryptPassword("stalin"));
        System.out.println(enc.encryptPassword("thunder"));
        System.out.println(enc.encryptPassword("zacsko"));
        //SpringApplication.run(SpringBootMainApplication.class, args);
    }
}
