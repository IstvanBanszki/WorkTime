package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.User;
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
public class SpSaveUser extends StoredProcedure implements ResultSetExtractor<SaveResult> {
    
    private static final String SP_NAME = "save_user";
    private static final String SP_PARAMETER_1 = "login_name";
    private static final String SP_PARAMETER_2 = "password";
    private static final String SP_PARAMETER_3 = "role_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpSaveUser(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public SaveResult saveUser(User request, String password) {
        return (SaveResult) super.execute(request.getLoginName(), password, request.getRole().getId()).get(SP_RESULT);
    }

    @Override
    public SaveResult extractData(ResultSet rs) throws SQLException, DataAccessException {

        SaveResult result = null;
        if (rs.next()) {
            result = new SaveResult(rs.getInt("new_id"), rs.getInt("status"));
        }
        return result;
    }

}
