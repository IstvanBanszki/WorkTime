package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SpGetAllOffices extends StoredProcedure implements RowMapper<Office> {
    
    private static final String SP_NAME = "get_all_offices";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetAllOffices(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<Office> getOffices() {
        List<Office> spResult = (List<Office>) super.execute().get(SP_RESULT);
        if (spResult != null) {
            return spResult;
        }
        return new ArrayList();
    }
    
    @Override
    public Office mapRow(ResultSet rs, int i) throws SQLException {
        return new Office(rs.getInt("id"), rs.getString("name"), rs.getString("address"), 
                          rs.getDate("date_of_foundation").toLocalDate());
    }
    
}
