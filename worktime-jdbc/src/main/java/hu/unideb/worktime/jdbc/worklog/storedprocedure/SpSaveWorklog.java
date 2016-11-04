package hu.unideb.worktime.jdbc.worklog.storedprocedure;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.worklog.WorklogRequest;
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
public class SpSaveWorklog extends StoredProcedure implements ResultSetExtractor<SaveResult> {

    private static final String SP_NAME = "save_worklog";
    private static final String SP_PARAMETER_1 = "begin_date";
    private static final String SP_PARAMETER_2 = "work_hour";
    private static final String SP_PARAMETER_3 = "worker_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpSaveWorklog(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.DATE));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public SaveResult saveWorklog(Integer workerId, WorklogRequest values) {
        return (SaveResult) super.execute(values.getBeginDate(), 
                values.getWorkHour(), workerId).get(SP_RESULT);
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
