package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.administration.EditWorker;
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
public class SpEditWorkerData extends StoredProcedure implements ResultSetExtractor<Integer> {
    
    private static final String SP_NAME = "edit_worker_data";
    private static final String SP_PARAMETER_1 = "first_name";
    private static final String SP_PARAMETER_2 = "last_name";
    private static final String SP_PARAMETER_3 = "position";
    private static final String SP_PARAMETER_4 = "email_address";
    private static final String SP_PARAMETER_5 = "daily_work_hour_total";
    private static final String SP_PARAMETER_6 = "worker_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpEditWorkerData(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_4, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_5, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_6, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer editWorkerData(Integer key, EditWorker request) {
        return (Integer) super.execute(request.getFirstName(), request.getLastName(),
                request.getPosition(), request.getEmailAddress(), request.getDailyWorkHourTotal(),
                key).get(SP_RESULT);
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
