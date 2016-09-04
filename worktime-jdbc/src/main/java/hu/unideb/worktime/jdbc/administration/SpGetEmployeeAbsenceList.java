package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.AbsenceType;
import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse.AdministrationAbsenceResponseBuilder;
import hu.unideb.worktime.api.model.administration.AdministrationRequest;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SpGetEmployeeAbsenceList extends StoredProcedure implements RowMapper<AdministrationAbsenceResponse>  {
    
    private static final String SP_NAME = "get_employee_absence_list";
    private static final String SP_PARAMETER_1 = "first_name";
    private static final String SP_PARAMETER_2 = "last_name";
    private static final String SP_PARAMETER_3 = "date_filter";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetEmployeeAbsenceList(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<AdministrationAbsenceResponse> execute(String firstName, String lastName, AdministrationRequest request) {

        List<AdministrationAbsenceResponse> spResult = (List<AdministrationAbsenceResponse>) super.execute(firstName, lastName, request.getDateFilter()).get(SP_RESULT);
        if(spResult != null) {
            return spResult;
        }
        return new ArrayList();
    }

    @Override
    public AdministrationAbsenceResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new AdministrationAbsenceResponseBuilder().setId(rs.getInt("id"))
                                                         .setNote(rs.getString("note"))
                                                         .setBeginDate(rs.getTimestamp("begin_date").toLocalDateTime())
                                                         .setEndDate(rs.getTimestamp("end_date").toLocalDateTime())
                                                         .setStatus(Status.valueOf(rs.getInt("status")))
                                                         .setAbsenceType(AbsenceType.valueOf(rs.getInt("absence_type_id")))
                                                         .build();
    }
}
