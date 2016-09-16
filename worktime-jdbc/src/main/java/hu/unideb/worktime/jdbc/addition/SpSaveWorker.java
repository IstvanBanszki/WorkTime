package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Worker;
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
public class SpSaveWorker extends StoredProcedure implements ResultSetExtractor<Integer> {
            
    private static final String SP_NAME = "save_worker";
    private static final String SP_PARAMETER_1 = "first_name";
    private static final String SP_PARAMETER_2 = "last_name";
    private static final String SP_PARAMETER_3 = "gender";
    private static final String SP_PARAMETER_4 = "date_of_birth";
    private static final String SP_PARAMETER_5 = "nationality";
    private static final String SP_PARAMETER_6 = "position";
    private static final String SP_PARAMETER_7 = "email_address";
    private static final String SP_PARAMETER_8 = "daily_work_hour_total";
    private static final String SP_PARAMETER_9 = "superior_worker_id";
    private static final String SP_PARAMETER_10 = "department_id";
    private static final String SP_PARAMETER_11 = "user_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpSaveWorker(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_4, Types.DATE));
        declareParameter(new SqlParameter(SP_PARAMETER_5, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_6, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_7, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_8, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_9, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_10, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_11, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer saveWorker(Worker request) {
        return (Integer) super.execute(request.getFirstName(), request.getLastName(),
                request.getGender(), request.getDateOfBirth(), request.getNationality(),
                request.getPosition(), request.getEmailAddres(), request.getDailyWorkHourTotal(),
                request.getDepartmentId(), request.getSuperiorId(), request.getUserId()).get(SP_RESULT);
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
