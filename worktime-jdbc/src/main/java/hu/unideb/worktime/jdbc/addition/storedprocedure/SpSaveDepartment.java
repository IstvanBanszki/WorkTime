package hu.unideb.worktime.jdbc.addition.storedprocedure;

import hu.unideb.worktime.api.model.Department;
import hu.unideb.worktime.api.model.SaveResult;
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
public class SpSaveDepartment extends StoredProcedure implements ResultSetExtractor<SaveResult> {
    
    private static final String SP_NAME = "save_department";
    private static final String SP_PARAMETER_1 = "name";
    private static final String SP_PARAMETER_2 = "date_of_foundation";
    private static final String SP_PARAMETER_3 = "office_id";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpSaveDepartment(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.VARCHAR));
        declareParameter(new SqlParameter(SP_PARAMETER_2, Types.DATE));
        declareParameter(new SqlParameter(SP_PARAMETER_3, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public SaveResult saveDepartment(Department request) {
        return (SaveResult) super.execute(request.getName(), request.getDateOfFoundtation(),
                                    request.getOfficeId()).get(SP_RESULT);
    }

    @Override
    public SaveResult extractData(ResultSet rs) throws SQLException, DataAccessException {

        SaveResult result = null;
        if (rs.next()) {
            result = new SaveResult(rs.getInt("new_id"), rs.getInt("status"));
        }
        return result;
    }

}
