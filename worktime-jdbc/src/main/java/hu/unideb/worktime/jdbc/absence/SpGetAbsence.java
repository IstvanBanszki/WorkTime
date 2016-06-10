package hu.unideb.worktime.jdbc.absence;

import hu.unideb.worktime.api.model.AbsenceType;
import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.absence.GetAbsenceResponse;
import hu.unideb.worktime.api.model.absence.GetAbsenceResponse.GetAbsenceResponseBuilder;
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
public class SpGetAbsence extends StoredProcedure implements RowMapper<GetAbsenceResponse>{
    
    private static final String SP_NAME = "get_all_absence_by_worker";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpGetAbsence(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<GetAbsenceResponse> execute(Integer key) {
        List<GetAbsenceResponse> spResult = (List<GetAbsenceResponse>) super.execute(key).get(SP_RESULT);
        if(spResult != null){
            return spResult;
        }
        return new ArrayList();
    }    

    @Override
    public GetAbsenceResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new GetAbsenceResponseBuilder().setDescription(rs.getString("description"))
                                              .setBegin(rs.getTimestamp("begin").toLocalDateTime())
                                              .setEnd(rs.getTimestamp("end").toLocalDateTime())
                                              .setStatus(Status.valueOf(rs.getInt("status")))
                                              .setAbsenceType(AbsenceType.valueOf(rs.getInt("absence_type_id")))
                                              .build();
    }   
}