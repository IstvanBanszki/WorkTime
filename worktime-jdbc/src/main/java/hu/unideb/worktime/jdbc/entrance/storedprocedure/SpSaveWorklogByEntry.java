package hu.unideb.worktime.jdbc.entrance.storedprocedure;

import hu.unideb.worktime.api.model.SaveResult;
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
public class SpSaveWorklogByEntry extends StoredProcedure implements ResultSetExtractor<SaveResult> {
    
    private static final String SP_NAME = "save_worklog_by_entry";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpSaveWorklogByEntry(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }
    
    public SaveResult saveEntryByWorker(Integer id) {
        return (SaveResult) super.execute(id).get(SP_RESULT);
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
