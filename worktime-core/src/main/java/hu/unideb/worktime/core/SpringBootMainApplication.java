package hu.unideb.worktime.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = "hu.unideb.worktime")
@EnableAutoConfiguration
@EnableCaching
@EnableScheduling
public class SpringBootMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainApplication.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new CaffeineCacheManager();
    }
    
    @Bean
    public ObjectMapper jsonObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();

	javaTimeModule.addSerializer(LocalDateTime.class, 
            new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	javaTimeModule.addDeserializer(LocalDateTime.class, 
            new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

	javaTimeModule.addSerializer(LocalDate.class, 
            new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        javaTimeModule.addDeserializer(LocalDate.class, 
                new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy.MM.dd")));

        mapper.registerModule(javaTimeModule);
        return mapper;
    }
}
