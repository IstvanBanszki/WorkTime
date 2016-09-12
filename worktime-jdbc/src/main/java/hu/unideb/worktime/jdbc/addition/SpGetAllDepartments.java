package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Department;
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
public class SpGetAllDepartments extends StoredProcedure implements RowMapper<Department> {

    private static final String SP_NAME = "get_all_deparments";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetAllDepartments(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<Department> execute() {
        List<Department> spResult = (List<Department>) super.execute().get(SP_RESULT);
        if (spResult != null) {
            return spResult;
        }
        return new ArrayList();
    }
    
    @Override
    public Department mapRow(ResultSet rs, int i) throws SQLException {
        return new Department(rs.getString("name"), rs.getTimestamp("date_of_foundation").toLocalDateTime(),
                              rs.getInt("office_id"));
    }

}