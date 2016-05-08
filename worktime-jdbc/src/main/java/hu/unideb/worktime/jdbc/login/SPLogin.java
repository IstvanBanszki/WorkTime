package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.api.model.login.LoginResponse;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.Types;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SPLogin extends StoredProcedure {

    private static final String SP_NAME = "get_login";

    @Autowired
    public SPLogin(WTConnection wtConnection) {
        super.setDataSource(wtConnection.getDataSource());
        setSql(SP_NAME);
        declareParameter(new SqlParameter("login_name", Types.VARCHAR));
        declareParameter(new SqlParameter("password", Types.VARCHAR));
        declareParameter(new SqlOutParameter("role_name", Types.VARCHAR));
        declareParameter(new SqlOutParameter("worker_id", Types.INTEGER));
        compile();
    }

    public LoginResponse execute(String loginName, String password) {
        Map<String, Object> result = super.execute(loginName, password);
        return new LoginResponse((int) result.get("worker_id"), (String) result.get("role_name"));
    }
}
