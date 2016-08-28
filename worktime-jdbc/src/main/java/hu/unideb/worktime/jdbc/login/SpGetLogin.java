package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SpGetLogin extends StoredProcedure implements RowMapper<LoginRecord> {

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

    public LoginRecord execute(String loginName) {
        LoginRecord result = null;
        List<LoginRecord> spResult = (List<LoginRecord>) super.execute(loginName).get(SP_RESULT);
        if(spResult != null){
            result = spResult.get(0);
        }
        return result;
    }

    @Override
    public LoginRecord mapRow(ResultSet rs, int i) throws SQLException {
        return new LoginRecord(rs.getInt("worker_id"), 
                               rs.getString("role_name"), 
                               rs.getString("password"));
    }
}
