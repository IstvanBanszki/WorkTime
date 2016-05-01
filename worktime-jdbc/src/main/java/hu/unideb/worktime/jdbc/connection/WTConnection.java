package hu.unideb.worktime.jdbc.connection;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class WTConnection {

    private DriverManagerDataSource dataSourceManager;
    
    public WTConnection( ){
    }
    
    @Autowired
    public void setDataSourceManager(DriverManagerDataSource dataSourceManager){
        this.dataSourceManager = new DriverManagerDataSource();
        this.dataSourceManager.setDriverClassName("com.mysql.jdbc.Driver");
        this.dataSourceManager.setUrl("jdbc:mysql://localhost:3306");
        this.dataSourceManager.setUsername("root");
        this.dataSourceManager.setPassword("root");
    }
    
    public DataSource getDataSource() throws SQLException{
        return this.dataSourceManager;
    }
}