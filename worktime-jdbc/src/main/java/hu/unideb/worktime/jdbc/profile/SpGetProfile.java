package hu.unideb.worktime.jdbc.profile;

import hu.unideb.worktime.api.model.Gender;
import hu.unideb.worktime.api.model.profile.ProfileRecord;
import hu.unideb.worktime.api.model.profile.ProfileRecord.ProfileRecordBuilder;
import hu.unideb.worktime.jdbc.connection.WTConnection;
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
public class SpGetProfile extends StoredProcedure implements RowMapper<ProfileRecord>{

    private static final String SP_NAME = "get_profile_data";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpGetProfile(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public ProfileRecord execute(int workerId) {
        ProfileRecord result = null;
        List<ProfileRecord> spResult = (List<ProfileRecord>) super.execute(workerId).get(SP_RESULT);
        if(spResult != null){
            result = spResult.get(0);
        }
        return result;
    }

    @Override
    public ProfileRecord mapRow(ResultSet rs, int i) throws SQLException {
        return new ProfileRecordBuilder().setDateOfRegistration(rs.getTimestamp("date_of_registration").toLocalDateTime())
                                         .setFirstName(rs.getString("first_name"))
                                         .setLastName(rs.getString("last_name"))
                                         .setGender(Gender.valueOf(rs.getInt("gender")))
                                         .setDateOfBirth(rs.getTimestamp("date_of_birth").toLocalDateTime())
                                         .setNationality(rs.getString("nationality"))
                                         .setPosition(rs.getString("position"))
                                         .setEmailAddress(rs.getString("email_address"))
                                         .setDailyWorkHourTotal(rs.getInt("daily_work_hour_total"))
                                         .setDepartmentName(rs.getString("department_name"))
                                         .setOfficeName(rs.getString("office_name"))
                                         .build();
    }
    
}
