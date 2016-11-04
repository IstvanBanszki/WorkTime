package hu.unideb.worktime.jdbc.addition.storedprocedure;

import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.jdbc.connection.WorkTimeConnection;
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
public class SpEditOffice extends StoredProcedure implements ResultSetExtractor<Integer> {
    
    private static final String SP_NAME = "edit_office";
    private static final String SP_PARAMETER_1 = "name";
    private static final String SP_PARAMETER_2 = "address";
    private static final String SP_PARAMETER_3 = "date_of_foundation";
    private static final String SP_PARAMETER_4 = "office_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpEditOffice(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.DATE));
        declareParameter(new SqlParameter(SP_PARAMETER_4, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer editOffice(Integer id, Office request) {
        return (Integer) super.execute(request.getName(), request.getAddress(), 
                request.getDateOfFoundtation(), id).get(SP_RESULT);
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
