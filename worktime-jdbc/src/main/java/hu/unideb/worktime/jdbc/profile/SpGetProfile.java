package hu.unideb.worktime.jdbc.profile;

import hu.unideb.worktime.api.model.Gender;
import hu.unideb.worktime.api.model.profile.ProfileRecord;
import hu.unideb.worktime.api.model.profile.ProfileRecord.Builder;
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
public class SpGetProfile extends StoredProcedure implements ResultSetExtractor<ProfileRecord>{

    private static final String SP_NAME = "get_profile_data";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpGetProfile(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public ProfileRecord getProfileRecord(int workerId) {
        return (ProfileRecord) super.execute(workerId).get(SP_RESULT);
    }

    @Override
    public ProfileRecord extractData(ResultSet rs) throws SQLException, DataAccessException {

        Builder result = new Builder();

        if (rs.next()) {
            result.setDateOfRegistration(rs.getTimestamp("date_of_registration").toLocalDateTime())
                  .setFirstName(rs.getString("first_name"))
                  .setLastName(rs.getString("last_name"))
                  .setGender(Gender.valueOf(rs.getInt("gender")))
                  .setDateOfBirth(rs.getDate("date_of_birth").toLocalDate())
                  .setNationality(rs.getString("nationality"))
                  .setPosition(rs.getString("position"))
                  .setEmailAddress(rs.getString("email_address"))
                  .setDailyWorkHourTotal(rs.getInt("daily_work_hour_total"))
                  .setDepartmentName(rs.getString("department_name"))
                  .setOfficeName(rs.getString("office_name"));
        }
        return result.build();
    }
    
}
