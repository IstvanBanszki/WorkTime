
package hu.unideb.worktime.jdbc.absence;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.absence.AbsenceRequest;
import hu.unideb.worktime.jdbc.connection.WTConnection;
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
public class SpSaveAbsence extends StoredProcedure implements ResultSetExtractor<SaveResult> {
    
    private static final String SP_NAME = "save_absence";
    private static final String SP_PARAMETER_1 = "begin_date";
    private static final String SP_PARAMETER_2 = "end_date";
    private static final String SP_PARAMETER_3 = "worker_id";
    private static final String SP_PARAMETER_4 = "absence_type_id";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpSaveAbsence(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.DATE));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.DATE));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_4, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public SaveResult saveAbsence(Integer workerId, AbsenceRequest values) {
        return (SaveResult) super.execute(values.getBeginDate(),  values.getEndDate(),
                workerId, values.getAbsenceType().getId()).get(SP_RESULT);
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
