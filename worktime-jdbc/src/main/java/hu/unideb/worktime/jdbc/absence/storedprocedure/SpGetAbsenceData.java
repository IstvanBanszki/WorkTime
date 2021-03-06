package hu.unideb.worktime.jdbc.absence.storedprocedure;

import hu.unideb.worktime.api.model.absence.AbsenceDataResponse;
import hu.unideb.worktime.jdbc.connection.WorkTimeConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SpGetAbsenceData extends StoredProcedure implements RowMapper<AbsenceDataResponse> {
    
    private static final String SP_NAME = "get_absence_data";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetAbsenceData(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<AbsenceDataResponse> getAbsenceData(Integer key) {
        return (List<AbsenceDataResponse>) super.execute(key).get(SP_RESULT);
    }

    @Override
    public AbsenceDataResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new AbsenceDataResponse.Builder().setYear(rs.getInt("year"))
                  .setHolidayNumber(rs.getInt("holiday_number_total"))
                  .setAbsenceNumber(rs.getInt("absence_number"))
                  .setNotSetNumber(rs.getInt("not_set_absence_number"))
                  .setPayedNumber(rs.getInt("payed_absence_number"))
                  .setUnPayedNumber(rs.getInt("unpayed_absence_number"))
                  .setSickPayedNumber(rs.getInt("sickpayed_absence_number"))
                  .setVerifiedNumber(rs.getInt("verified_absence_number"))
                  .build();
        }
 
}
