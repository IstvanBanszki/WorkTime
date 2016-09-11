package hu.unideb.worktime.jdbc.absence;

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
public class SpEditAbsence extends StoredProcedure implements ResultSetExtractor<Integer> {
    
    private static final String SP_NAME = "edit_absence";
    private static final String SP_PARAMETER_1 = "absence_id";
    private static final String SP_PARAMETER_2 = "begin_date";
    private static final String SP_PARAMETER_3 = "end_date";
    private static final String SP_PARAMETER_4 = "absence_type_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpEditAbsence(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.DATE));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.DATE));
        declareParameter(new SqlParameter(SP_PARAMETER_4, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }
    
    public Integer execute(Integer id, AbsenceRequest values) {
        return (Integer) super.execute(id, values.getBeginDate(), 
                values.getEndDate(), values.getAbsenceType().getId()).get(SP_RESULT);
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
