package hu.unideb.worktime.jdbc.login;

import hu.unideb.worktime.api.model.login.UpdatePasswordRecord;
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
public class SpUpdatePassword extends StoredProcedure implements RowMapper<Integer>{

    private static final String SP_NAME = "change_password";
    private static final String SP_PARAMETER_1 = "login_name";
    private static final String SP_PARAMETER_2 = "old_password";
    private static final String SP_PARAMETER_3 = "new_password";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpUpdatePassword(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer execute(UpdatePasswordRecord key) {
        Integer result = null;
        List<Integer> spResult = (List<Integer>) super.execute(key.getLoginName(),
                key.getOldPassword(), key.getNewPassword()).get(SP_RESULT);
        if (spResult != null) {
            result = spResult.get(0);
        }
        return result;
    }

    @Override
    public Integer mapRow(ResultSet rs, int i) throws SQLException {
        return rs.getInt("status");
    }
}
