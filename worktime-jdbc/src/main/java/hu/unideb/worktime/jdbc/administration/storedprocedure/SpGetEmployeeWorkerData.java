package hu.unideb.worktime.jdbc.administration.storedprocedure;

import hu.unideb.worktime.api.model.administration.WorkerData;
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
public class SpGetEmployeeWorkerData extends StoredProcedure implements ResultSetExtractor<WorkerData>{

    private static final String SP_NAME = "get_worker_data";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetEmployeeWorkerData(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public WorkerData getWorkerData(Integer key) {
        return (WorkerData) super.execute(key).get(SP_RESULT);
    }

    @Override
    public WorkerData extractData(ResultSet rs) throws SQLException, DataAccessException {

        WorkerData.Builder builder = new WorkerData.Builder();

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
