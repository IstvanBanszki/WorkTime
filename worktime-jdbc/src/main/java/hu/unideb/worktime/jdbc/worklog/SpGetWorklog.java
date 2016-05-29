package hu.unideb.worktime.jdbc.worklog;

import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.worklog.GetWorklogResponse;
import hu.unideb.worktime.api.model.worklog.GetWorklogResponse.GetWorklogResponseBuilder;
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
public class SpGetWorklog extends StoredProcedure implements RowMapper<GetWorklogResponse>{
    
    private static final String SP_NAME = "get_all_worklog_by_worker";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpGetWorklog(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<GetWorklogResponse> execute(Integer key) {
        List<GetWorklogResponse> spResult = (List<GetWorklogResponse>) super.execute(key).get(SP_RESULT);
        if(spResult != null){
            return spResult;
        }
        return new ArrayList();
    }

    @Override
    public GetWorklogResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new GetWorklogResponseBuilder().setDescription(rs.getString("description"))
                                              .setBegin(rs.getTimestamp("begin").toLocalDateTime())
                                              .setEnd(rs.getTimestamp("end").toLocalDateTime())
                                              .setStatus(Status.valueOf(rs.getInt("status")))
                                              .build();
    }
    
}
