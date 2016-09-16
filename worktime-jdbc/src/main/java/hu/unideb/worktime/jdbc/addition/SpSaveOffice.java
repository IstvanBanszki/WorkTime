package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Office;
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
public class SpSaveOffice extends StoredProcedure implements ResultSetExtractor<Integer> {

    private static final String SP_NAME = "save_office";
    private static final String SP_PARAMETER_1 = "name";
    private static final String SP_PARAMETER_2 = "address";
    private static final String SP_PARAMETER_3 = "date_of_foundation";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpSaveOffice(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.DATE));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public Integer saveOffice(Office request) {
        return (Integer) super.execute(request.getName(), request.getAddress(), 
                request.getDateOfFoundtation()).get(SP_RESULT);
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
