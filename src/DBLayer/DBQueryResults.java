package DBLayer;
import java.sql.*;
import General.*;

/**
 * This class is created in order to return query results
 * in order to iterate ResultSet object, the connection and statement must stay open 
 * @author ROTEMD
 *
 */
public class DBQueryResults {
	
	private	ResultSet	resultSet;
	private Connection connection;
	private Statement statement;
	
	public DBQueryResults(Connection conn, Statement stmt, ResultSet rs)
	{
		this.connection = conn;
		this.statement = stmt;
		this.resultSet = rs;
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}	
	
	
	public void	close()
	{
		try
		{
			resultSet.close();
			Debug.log("DBQueryResults::close - closed result set");				
		}
		catch (SQLException e)
		{
			Debug.log("DBQueryResults::close - ERROR - Exception while trying to close result set");			
		}
		finally
		{
			DBAccessLayer.closeStatementAndConnection(connection, statement);
		}
	}
	
}
