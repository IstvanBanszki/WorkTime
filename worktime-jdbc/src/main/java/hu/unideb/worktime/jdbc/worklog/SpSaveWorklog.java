package hu.unideb.worktime.jdbc.worklog;

import hu.unideb.worktime.api.model.worklog.WorklogRequest;
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
public class SpSaveWorklog extends StoredProcedure implements RowMapper<Integer> {

    private static final String SP_NAME = "save_worklog";
    private static final String SP_PARAMETER_1 = "begin_date";
    private static final String SP_PARAMETER_2 = "work_hour";
    private static final String SP_PARAMETER_3 = "worker_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpSaveWorklog(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.TIMESTAMP));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer execute(Integer workerId, WorklogRequest values) {
        Integer result = null;
        List<Integer> spResult = (List<Integer>) super.execute(values.getBeginDate(), 
                values.getWorkHour(), workerId).get(SP_RESULT);
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
