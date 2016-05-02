package hu.unideb.worktime.jdbc.login;

import java.sql.Types;

import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlOutParameter;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository("spLogin")
public class SPLogin extends StoredProcedure {

    private static final String SP_NAME = "get_login";

    public void install(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql(SP_NAME);
        this.declareParameter(new SqlParameter("login_name", Types.VARCHAR));
        this.declareParameter(new SqlParameter("password", Types.VARCHAR));
        this.declareParameter(new SqlOutParameter("status", Types.INTEGER));
        compile();
    }

    public int execute(String loginName, String password) {
        return (Integer) super.execute(loginName, password).get("status");
    }
}
