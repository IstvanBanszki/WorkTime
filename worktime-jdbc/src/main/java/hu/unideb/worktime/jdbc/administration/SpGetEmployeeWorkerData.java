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
public class SpGetEmployeeWorkerData extends StoredProcedure implements ResultSetExtractor<EditWorker>{

    private static final String SP_NAME = "get_worker_data";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetEmployeeWorkerData(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public EditWorker getWorkerData(Integer key) {
        return (EditWorker) super.execute(key).get(SP_RESULT);
    }

    @Override
    public EditWorker extractData(ResultSet rs) throws SQLException, DataAccessException {

        EditWorker.Builder builder = new EditWorker.Builder();

        if (rs.next()) {
            builder.setFirstName(rs.getString("first_name"));
            builder.setLastName(rs.getString("last_name"));
            builder.setPosition(rs.getString("position"));
            builder.setEmailAddress(rs.getString("email_address"));
            builder.setDailyWorkHourTotal(rs.getInt("daily_work_hour_total"));
        }
        return builder.build();
    }
    
}
