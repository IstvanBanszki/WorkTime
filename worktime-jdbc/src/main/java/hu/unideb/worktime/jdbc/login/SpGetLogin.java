package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SpGetLogin extends StoredProcedure implements ResultSetExtractor<LoginRecord> {

    private static final String SP_NAME = "get_login";
    private static final String SP_PARAMETER_1 = "login_name";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpGetLogin(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public LoginRecord getLoginRecord(String loginName) {
        return (LoginRecord) super.execute(loginName).get(SP_RESULT);
    }

    @Override
    public LoginRecord extractData(ResultSet rs) throws SQLException, DataAccessException {

        LoginRecord result = null;

        if (rs.next()) {
            result = new LoginRecord(rs.getInt("worker_id"), 
                               rs.getString("role_name"), 
                               rs.getString("password"));
        }
        return result;
    }
}
