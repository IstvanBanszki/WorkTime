package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.administration.AdministrationWorklogRequest;
import hu.unideb.worktime.api.model.administration.AdministrationWorklogResponse;
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
public class SpGetEmployeeWorklogList extends StoredProcedure implements RowMapper<AdministrationWorklogResponse> {

    private static final String SP_NAME = "get_employee_worklog_list";
    private static final String SP_PARAMETER_1 = "first_name";
    private static final String SP_PARAMETER_2 = "last_name";
    private static final String SP_PARAMETER_3 = "date_filter";
    private static final String SP_PARAMETER_4 = "list_daily_work_hour";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetEmployeeWorklogList(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_4, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<AdministrationWorklogResponse> getWorklogListForEmployee(String firstName, String lastName, AdministrationWorklogRequest request) {

        List<AdministrationWorklogResponse> spResult = (List<AdministrationWorklogResponse>) 
                super.execute(firstName, lastName, request.getDateFilter(), request.isShowDailyWorkhours()).get(SP_RESULT);
        if(spResult != null) {
            return spResult;
        }
        return new ArrayList();
    }

    @Override
    public AdministrationWorklogResponse mapRow(ResultSet rs, int i) throws SQLException {
        return new AdministrationWorklogResponse(rs.getString("note"), rs.getInt("id"), 
                rs.getDate("begin_date").toLocalDate(), rs.getInt("work_hour"));
    }
    
}
