package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SpGetEmployeeWorklogList extends StoredProcedure implements RowMapper<WorklogResponse> {

    private static final String SP_NAME = "get_employee_worklog_list";
    private static final String SP_PARAMETER_1 = "first_name";
    private static final String SP_PARAMETER_2 = "last_name";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetEmployeeWorklogList(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<WorklogResponse> execute(String firstName, String lastName) {

        List<WorklogResponse> spResult = (List<WorklogResponse>) super.execute(firstName, lastName).get(SP_RESULT);
        if(spResult != null){
            return spResult;
        }
        return new ArrayList();
    }

    @Override
    public WorklogResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new WorklogResponse(rs.getTimestamp("begin").toLocalDateTime(), rs.getInt("work_hour"));
    }
    
}
