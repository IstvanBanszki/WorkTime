package hu.unideb.worktime.jdbc.worklog.storedprocedure;

import hu.unideb.worktime.api.model.worklog.MontlyStatRequest;
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
public class SpGetMonthlyWorklogs extends StoredProcedure implements RowMapper<WorklogResponse> {
    
    private static final String SP_NAME = "get_monthly_worklogs_by_worker";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_PARAMETER_2 = "years";
    private static final String SP_PARAMETER_3 = "months";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetMonthlyWorklogs(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<WorklogResponse> getMonthlyWorklogs(MontlyStatRequest key) {
        return (List<WorklogResponse>) super.execute(key.getWorkerId(), key.getYear(), key.getMonth()).get(SP_RESULT);
    }

    @Override
    public WorklogResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new WorklogResponse(rs.getInt("id"), rs.getDate("begin_date").toLocalDate(), rs.getInt("work_hour"));
    }

}
