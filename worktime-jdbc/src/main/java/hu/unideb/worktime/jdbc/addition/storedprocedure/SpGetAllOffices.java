package hu.unideb.worktime.jdbc.addition.storedprocedure;

import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.jdbc.connection.WorkTimeConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public SpGetAllOffices(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<Office> getOffices() {
        return (List<Office>) super.execute().get(SP_RESULT);
    }
    
    @Override
    public Office mapRow(ResultSet rs, int i) throws SQLException {
        return new Office(rs.getInt("id"), rs.getString("name"), rs.getString("address"), 
                          rs.getDate("date_of_foundation").toLocalDate());
    }
    
}
