package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.api.model.login.LoginKey;
import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.Types;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SPLogin extends StoredProcedure{

    private static final String SP_NAME = "get_login";

    @Autowired
    public SPLogin(WTConnection wtConnection) {
        super.setDataSource(wtConnection.getDataSource());
        setSql(SP_NAME);
        declareParameter(new SqlParameter("login_name", Types.VARCHAR));
        declareParameter(new SqlParameter("password", Types.VARCHAR));
        compile();
    }

    public LoginRecord execute(LoginKey key) {
        Map<String, Object> result = super.execute(key.getLoginName(), key.getPassword());
        return new LoginRecord((int) result.get("worker_id"), (String) result.get("role_name"));
    }
}
