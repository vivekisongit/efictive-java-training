package in.conceptarchitect.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

@FunctionalInterface
public interface StatementExecutor<T> {
	
	T executeStatement(Statement statement) throws SQLException;

}
