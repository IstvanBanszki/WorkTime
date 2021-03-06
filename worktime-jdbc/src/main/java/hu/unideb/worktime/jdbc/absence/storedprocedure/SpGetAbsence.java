package hu.unideb.worktime.jdbc.absence.storedprocedure;

import hu.unideb.worktime.api.model.AbsenceType;
import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.absence.AbsenceResponse;
import hu.unideb.worktime.api.model.absence.AbsenceResponse.Builder;
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
public class SpGetAbsence extends StoredProcedure implements RowMapper<AbsenceResponse>{
    
    private static final String SP_NAME = "get_all_absence_by_worker";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_PARAMETER_2 = "date_filter";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpGetAbsence(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<AbsenceResponse> getAbsences(Integer key, String request) {
        return (List<AbsenceResponse>) super.execute(key, request).get(SP_RESULT);
    }

    @Override
    public AbsenceResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new Builder().setId(rs.getInt("id"))
                                              .setBeginDate(rs.getDate("begin_date").toLocalDate())
                                              .setEndDate(rs.getDate("end_date").toLocalDate())
                                              .setStatus(Status.valueOf(rs.getInt("status")))
                                              .setAbsenceType(AbsenceType.valueOf(rs.getInt("absence_type_id")))
                                              .build();
    }
}
