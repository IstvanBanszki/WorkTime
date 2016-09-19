package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.AbsenceType;
import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceRequest;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse;
import hu.unideb.worktime.api.model.administration.AdministrationAbsenceResponse.Builder;
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
public class SpGetEmployeeAbsenceList extends StoredProcedure implements RowMapper<AdministrationAbsenceResponse>  {
    
    private static final String SP_NAME = "get_employee_absence_list";
    private static final String SP_PARAMETER_1 = "employee_id";
    private static final String SP_PARAMETER_2 = "date_filter";
    private static final String SP_PARAMETER_3 = "show_not_approved";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetEmployeeAbsenceList(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<AdministrationAbsenceResponse> getAbsenceListForEmployee(Integer id, AdministrationAbsenceRequest request) {

        return (List<AdministrationAbsenceResponse>) super.execute(id, request.getDateFilter(), request.isNotApprove()).get(SP_RESULT);
    }

    @Override
    public AdministrationAbsenceResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new Builder().setId(rs.getInt("id"))
                                     .setDateOfRegistration(rs.getTimestamp("date_of_registration").toLocalDateTime())
                                     .setDateOfModification(rs.getTimestamp("date_of_modification").toLocalDateTime())
                                     .setNote(rs.getString("note"))
                                     .setBeginDate(rs.getDate("begin_date").toLocalDate())
                                     .setEndDate(rs.getDate("end_date").toLocalDate())
                                     .setStatus(Status.valueOf(rs.getInt("status")))
                                     .setAbsenceType(AbsenceType.valueOf(rs.getInt("absence_type_id")))
                                     .build();
    }
}
