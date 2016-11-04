package hu.unideb.worktime.jdbc.connection;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class WorkTimeConnection {

    private DriverManagerDataSource dataSourceManager;

    public WorkTimeConnection() {
        this.dataSourceManager = new DriverManagerDataSource();
        this.dataSourceManager.setDriverClassName("com.mysql.jdbc.Driver");
        this.dataSourceManager.setUrl("jdbc:mysql://localhost:3306/worktime?"
                + "useUnicode=yes&characterEncoding=UTF-8"
                + "&verifyServerCertificate=false&useSSL=false&requireSSL=false");
        this.dataSourceManager.setUsername("root");
        this.dataSourceManager.setPassword("root");
    }

    public DataSource getDataSource() {
        return this.dataSourceManager;
    }
}
