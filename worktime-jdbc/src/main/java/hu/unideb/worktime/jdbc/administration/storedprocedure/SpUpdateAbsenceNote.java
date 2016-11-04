package hu.unideb.worktime.jdbc.administration.storedprocedure;

import hu.unideb.worktime.api.model.administration.Note;
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
public class SpUpdateAbsenceNote extends StoredProcedure implements ResultSetExtractor<Integer> {
    
    private static final String SP_NAME = "update_absence_note";
    private static final String SP_PARAMETER_1 = "absence_id";
    private static final String SP_PARAMETER_2 = "note";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpUpdateAbsenceNote(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer updateNote(Integer key, Note request) {
        return (Integer) super.execute(key, request.getNote()).get(SP_RESULT);
    }

    @Override
    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

        Integer result = null;
        if (rs.next()) {
            result = rs.getInt("status");
        }
        return result;
    }
    
}
