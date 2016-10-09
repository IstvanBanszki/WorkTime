package hu.unideb.worktime.jdbc.addition.storedprocedure;

import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.jdbc.connection.WTConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository
public class SpGetAllSuperiorWorkers extends StoredProcedure implements RowMapper<Employee> {
    
    private static final String SP_NAME = "get_superiors";
    private static final String SP_RESULT = "result";

    @Autowired
    public SpGetAllSuperiorWorkers(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<Employee> getSuperiors() {
        return (List<Employee>) super.execute().get(SP_RESULT);
    }
    
    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        return new Employee(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
    }
    
}
