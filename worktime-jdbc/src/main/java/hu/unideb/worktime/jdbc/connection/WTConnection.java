package hu.unideb.worktime.jdbc.connection;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class WTConnection {

    private DriverManagerDataSource dataSourceManager;

    public WTConnection() {
        this.dataSourceManager = new DriverManagerDataSource();
        this.dataSourceManager.setDriverClassName("com.mysql.jdbc.Driver");
        this.dataSourceManager.setUrl("jdbc:mysql://localhost:3306");
        this.dataSourceManager.setUsername("root");
        this.dataSourceManager.setPassword("root");
    }

    public DataSource getDataSource() {
        return this.dataSourceManager;
    }
}
