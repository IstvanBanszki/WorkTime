package hu.unideb.worktime.jdbc.administration;

import hu.unideb.worktime.api.model.administration.Employee;
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
public class SpGetEmployeeList extends StoredProcedure implements RowMapper<Employee> {

    private static final String SP_NAME = "get_employee_list";
    private static final String SP_PARAMETER_1 = "worker_id";
    private static final String SP_RESULT = "result";
    
    @Autowired
    public SpGetEmployeeList(WTConnection wtConnection) {
        super(wtConnection.getDataSource(), SP_NAME);
        declareParameter(new SqlParameter(SP_PARAMETER_1, Types.INTEGER));
        declareParameter(new SqlReturnResultSet(SP_RESULT, this));
        setFunction(false);
        compile();
    }

    public List<Employee> getEmployees(Integer key) {

        List<Employee> spResult = (List<Employee>) super.execute(key).get(SP_RESULT);
        if(spResult != null){
            return spResult;
        }
        return new ArrayList();
    }

    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        return new Employee(rs.getString("first_name"), rs.getString("last_name"));
    }
}
