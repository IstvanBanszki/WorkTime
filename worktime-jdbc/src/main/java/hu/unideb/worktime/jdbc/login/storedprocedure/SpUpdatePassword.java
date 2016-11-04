package hu.unideb.worktime.jdbc.login.storedprocedure;

import hu.unideb.worktime.api.model.login.UpdatePasswordRecord;
import hu.unideb.worktime.jdbc.connection.WorkTimeConnection;
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
public class SpUpdatePassword extends StoredProcedure implements ResultSetExtractor<Integer>{

    private static final String SP_NAME = "update_password";
    private static final String SP_PARAMETER_1 = "login_name";
    private static final String SP_PARAMETER_2 = "old_password";
    private static final String SP_PARAMETER_3 = "new_password";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpUpdatePassword(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer update(UpdatePasswordRecord key) {
        return (Integer) super.execute(key.getLoginName(),
                key.getOldPassword(), key.getNewPassword()).get(SP_RESULT);
    }

    @Override
    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

        Integer result = null;

        if (rs.next()) {
            result = rs.getInt("status");
        }
        return result;
    }

}
