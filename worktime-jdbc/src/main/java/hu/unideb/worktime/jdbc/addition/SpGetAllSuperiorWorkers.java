package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.addition.Superior;
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
public class SpGetAllSuperiorWorkers extends StoredProcedure implements RowMapper<Superior> {
    
    private static final String SP_NAME = "get_superiors";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetAllSuperiorWorkers(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<Superior> getSuperiors() {
        List<Superior> spResult = (List<Superior>) super.execute().get(SP_RESULT);
        if (spResult != null) {
            return spResult;
        }
        return new ArrayList();
    }
    
    @Override
    public Superior mapRow(ResultSet rs, int i) throws SQLException {
        return new Superior(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
    }
    
}
