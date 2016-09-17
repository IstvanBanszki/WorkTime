package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.addition.FreeLogin;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SpGetFreeLogins extends StoredProcedure implements RowMapper<FreeLogin> {
    
    private static final String SP_NAME = "get_free_logins";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetFreeLogins(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<FreeLogin> getFreeLogins() {
        List<FreeLogin> spResult = (List<FreeLogin>) super.execute().get(SP_RESULT);
        if (spResult != null) {
            return spResult;
        }
        return new ArrayList();
    }
    
    @Override
    public FreeLogin mapRow(ResultSet rs, int i) throws SQLException {
        return new FreeLogin(rs.getInt("id"), rs.getString("name"));
    }

}
