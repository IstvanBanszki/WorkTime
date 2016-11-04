package hu.unideb.worktime.jdbc.administration.storedprocedure;

import hu.unideb.worktime.api.model.administration.Employee;
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
public class SpGetEmployeeList extends StoredProcedure implements RowMapper<Employee> {

    private static final String SP_NAME = "get_employee_list";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpGetEmployeeList(WorkTimeConnection connection) {
        super(connection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<Employee> getEmployees(Integer key) {

        return (List<Employee>) super.execute(key).get(SP_RESULT);
    }

    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        return new Employee(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
    }
}
