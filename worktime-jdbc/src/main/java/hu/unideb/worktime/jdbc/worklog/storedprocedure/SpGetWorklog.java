package hu.unideb.worktime.jdbc.worklog.storedprocedure;

import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import hu.unideb.worktime.jdbc.connection.WorkTimeConnection;
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
public class SpGetWorklog extends StoredProcedure implements RowMapper<WorklogResponse> {

    private static final String SP_NAME = "get_all_worklog_by_worker";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_PARAMETER_2 = "date_filter";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetWorklog(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<WorklogResponse> getWorklogs(Integer key, String request) {
        return (List<WorklogResponse>) super.execute(key, request).get(SP_RESULT);
    }

    @Override
    public WorklogResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new WorklogResponse(rs.getInt("id"), rs.getDate("begin_date").toLocalDate(), rs.getInt("work_hour"));
    }
}
