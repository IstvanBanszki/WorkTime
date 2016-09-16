package hu.unideb.worktime.jdbc.administration;

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
public class SpApproveEmployeeAbsence extends StoredProcedure implements ResultSetExtractor<Integer> {
    
    private static final String SP_NAME = "approve_employee_absence";
    private static final String SP_PARAMETER_1 = "absence_id";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpApproveEmployeeAbsence(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer approve(Integer key) {
        return (Integer) super.execute(key).get(SP_RESULT);
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
