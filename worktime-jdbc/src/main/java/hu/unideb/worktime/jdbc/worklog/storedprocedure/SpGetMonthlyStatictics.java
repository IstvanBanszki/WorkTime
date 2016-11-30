package hu.unideb.worktime.jdbc.worklog.storedprocedure;

import hu.unideb.worktime.api.model.worklog.MontlyStatRequest;
import hu.unideb.worktime.api.model.worklog.MontlyStatResponse;
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
public class SpGetMonthlyStatictics extends StoredProcedure implements ResultSetExtractor<MontlyStatResponse> {
    
    private static final String SP_NAME = "get_monthly_statictics_by_worker";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_PARAMETER_2 = "years";
    private static final String SP_PARAMETER_3 = "months";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetMonthlyStatictics(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public MontlyStatResponse getMonthlyStatictics(MontlyStatRequest key) {
        return (MontlyStatResponse) super.execute(key.getWorkerId(), key.getYear(), key.getMonth()).get(SP_RESULT);
    }

    @Override
    public MontlyStatResponse extractData(ResultSet rs) throws SQLException, DataAccessException {
        
        MontlyStatResponse result = null;
        if (rs.next()) {
            result = new MontlyStatResponse(rs.getInt("sum"), rs.getInt("count"), rs.getInt("avarage"));
        }
        return result;
    }
    
    
}
