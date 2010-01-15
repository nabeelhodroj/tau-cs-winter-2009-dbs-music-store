package DBLayer;

import java.sql.*;
import java.util.List;
import java.util.LinkedList;
import General.*;

public class DBAccessLayer {

	
	/**
	 * 	Init DB Connection Pool
	 * @param hostname
	 * @param portNum
	 * @param sid
	 * @param user
	 * @param pass
	 */
	public	static void	SetConnectionParams(String hostname, int portNum, 
			String sid, String user, String pass)
	{
		DBConnectionPool.SetConnectionParams(hostname, portNum, sid, user, pass);
	}
	
	
	/**
	 * Execute a query and return results
	 * @param sql
	 * @return DBQueryResults object which contains the results, null if error occurred
	 * NOTICE - caller must close Query Results object after using it
	 */
	public	static DBQueryResults	executeQuery(String sql)
	{
		// insert query to log
		Debug.query(sql);
		
		// get connection to DB, and verify it
		Connection conn = DBConnectionPool.getConnection();		
		if (conn == null)
		{
			Debug.log("DBAccessLayer::executeQuery: ERROR - failed getting connection to DB");			
			return null;
		}		
		
		ResultSet		rs;
		Statement	stmt = null;
		try
		{		
			stmt = conn.createStatement();
			
			// execute  statement
			rs = stmt.executeQuery(sql);
			DBQueryResults results = new DBQueryResults(conn, stmt, rs);
			return results;
		}
		catch	 (SQLException e)
		{
			Debug.log("DBAccessLayer::executeQuery: ERROR - exception occured during query:" + e.toString());				
			return null;
		}
		finally
		{
		//	closeStatementAndConnection(conn, stmt);
		}			
	}

	
	/**
	 * Run update (insert / update / delete) command
	 * @param sql
	 * @return - number of rows updated by command (0 if no updates), -1 for error
	 */
	public	static int	executeUpdate(String sql)
	{
		// insert query to log
		Debug.query(sql);
		
		// get connection to DB, and verify it
		Connection conn = DBConnectionPool.getConnection();
		if (conn == null)
		{
			Debug.log("DBAccessLayer::executeUpdate: ERROR - failed getting connection to DB");				
			return -1;
		}		
		
		Statement	stmt = null;
		int updated = 0;
		try
		{		
			stmt = conn.createStatement();
			
			// execute  statement
			updated = stmt.executeUpdate(sql);
			return updated;
		}
		catch	 (SQLException e)
		{
			Debug.log("DBAccessLayer::executeUpdate: ERROR - exception occured during update:" + e.toString());				
			return -1;
		}
		finally
		{
			closeStatementAndConnection(conn, stmt);
		}			
	}
	

	/**
	 * Run insert command
	 * @param sql
	 * @param idFileldName - name of id field	
	 * @return - id of the new row, -1 if error occurred
	 */
	public	static int	insertAndGetID(String sql, String idFileldName)
	{
		// get connection to DB, and verify it
		Connection conn = DBConnectionPool.getConnection();		
		if (conn == null)
		{
			Debug.log("DBAccessLayer::insertAndGetID: ERROR - failed getting connection to DB");				
			return -1;
		}		
		
		ResultSet		rs;
		Statement	stmt = null;
		int updated = 0;
		try
		{		
			stmt = conn.createStatement();
			
			// execute  statement
			updated = stmt.executeUpdate(sql, new String[]{idFileldName});
			if (updated == 1)
			{
				rs		=	stmt.getGeneratedKeys();
				rs.next();
				int id		=	rs.getInt(1);				
				rs.close();
				Debug.log("DBAccessLayer::insertAndGetID: SUCCESS - insert completed, returing ID " + id);
				return id;
			}			
			Debug.log("DBAccessLayer::insertAndGetID: ERROR - insert didn't add 1 row as expected (added " + updated + " rows)");
			return -1;
		}
		catch	 (SQLException e)
		{
			Debug.log("DBAccessLayer::insertAndGetID: ERROR - exception occured during insert:" + e.toString());			
			return -1;
		}
		finally
		{
			closeStatementAndConnection(conn, stmt);
		}			
	}	
	
	
	/** Run the SQL pattern in a batch
	 * each statment's arguments are found in a different list (variable length)
	 * @return	the number of statements executed successfully
	 **/
/*	public static int	executePatternBatch(String sqlPattern, List<List<String>> argumentLists)
	{
		// insert query to log
		Debug.query(sqlPattern);
		
		// get connection to DB, and verify it
		Connection conn = DBConnectionPool.getConnection();
		if (conn == null)
		{
			Debug.log("DBAccessLayer::executePatternBatch: ERROR - failed getting connection to DB");			
			return 0;
		}
		
		PreparedStatement	pstmt = null;
		try
		{
			pstmt	=	conn.prepareStatement(sqlPattern);
			// run over the statements
			for (int statementIndex = 0; statementIndex < argumentLists.size(); statementIndex++)				
			{
				// parse each statement's arguments
				for (int argumentIndex = 0; argumentIndex < argumentLists.get(statementIndex).size(); argumentIndex++)
				{					
					// Handle null values 
					if (argumentLists.get(statementIndex).get(argumentIndex) == null)
					{
						pstmt.setNull(statementIndex+1, java.sql.Types.VARCHAR);
					}
					else
					{
						System.out.println(argumentLists.get(statementIndex).get(argumentIndex));
						pstmt.setString(argumentIndex+1, argumentLists.get(statementIndex).get(argumentIndex));
					}
				}
				
				// Add to batch
				pstmt.addBatch();
			}
		
			Debug.log("DBAccessLayer::executeBatch: INFO - exceuting batch with " + argumentLists.size() + " commands");
			return doBatch(conn, pstmt);
		}
			catch (SQLException e)
			{
				Debug.log("DBAccessLayer::executeBatch: ERROR - exception occured when adding command to batch");
				return 0;
			}				
		}	*/		
	
	
	public static int	executePatternBatch(String sqlPattern, List<List<Object>> argumentLists, List<FieldTypes> typesList)
	{
		// insert query to log
		Debug.query(sqlPattern);
		
		// get connection to DB, and verify it
		Connection conn = DBConnectionPool.getConnection();
		if (conn == null)
		{
			Debug.log("DBAccessLayer::executePatternBatch: ERROR - failed getting connection to DB");			
			return 0;
		}
		
		int statementsNum = argumentLists.get(0).size();
		int	argsInStatement = typesList.size();
		
		PreparedStatement	pstmt = null;
		try
		{
			pstmt	=	conn.prepareStatement(sqlPattern);
			// run over the statements
			for (int statementIndex = 0; statementIndex < statementsNum; statementIndex++)				
			{
				// parse each statement's arguments
				for (int argumentIndex = 0; argumentIndex < argsInStatement; argumentIndex++)
				{
					switch (typesList.get(argumentIndex))
					{						
						case FIELD_TYPE_INT:
						{
							if (argumentLists.get(argumentIndex).get(statementIndex) == null)
							{
								pstmt.setNull(argumentIndex+1, java.sql.Types.NUMERIC);
							}
							else
							{
								pstmt.setInt(argumentIndex+1, (Integer)argumentLists.get(argumentIndex).get(statementIndex));
							}
							break;
						}
						case FIELD_TYPE_LONG:
						{
							if (argumentLists.get(argumentIndex).get(statementIndex) == null)
							{
								pstmt.setNull(argumentIndex+1, java.sql.Types.NUMERIC);
							}
							else
							{							
								pstmt.setLong(argumentIndex+1, (Long)argumentLists.get(argumentIndex).get(statementIndex));
							}
							break;							
						}
						case FIELD_TYPE_STRING:
						{
							if (argumentLists.get(argumentIndex).get(statementIndex) == null)
							{
								pstmt.setNull(argumentIndex+1, java.sql.Types.VARCHAR);
							}
							else							
							{
								pstmt.setString(argumentIndex+1, argumentLists.get(argumentIndex).get(statementIndex).toString());
							}
							break;							
						}
						case FIELD_TYPE_DATE:
						{
							if (argumentLists.get(argumentIndex).get(statementIndex) == null)
							{
								pstmt.setNull(argumentIndex+1, java.sql.Types.DATE);
							}
							else							
							{												
								pstmt.setDate(argumentIndex+1, (Date)argumentLists.get(argumentIndex).get(statementIndex));
							}
							break;							
						}									
					}
				}				
				// Add to batch
				pstmt.addBatch();
			}
		
			Debug.log("DBAccessLayer::executeBatch: INFO - exceuting batch with " + statementsNum + " commands");
			return doBatch(conn, pstmt);
		}
			catch (SQLException e)
			{
				Debug.log("DBAccessLayer::executeBatch: ERROR - exception occured when adding command to batch");
				return 0;
			}				
		}	
	
	
	/** Run the SQL pattern in a batch
	 * supports 2 lists of arguments
	 * @pre:	the lists have the same length
	 * @return	the number of statements executed successfully
	 **/	
/*	public static int	executePatternBatch(String sqlPattern, List<String> firstArgumentList,
														List<String> secondArgumentList)
	{
		// insert query to log
		Debug.query(sqlPattern);
		
		List<List<String>> statementsArgs = new LinkedList<List<String>>();			
		for (int i = 0; i < firstArgumentList.size(); i++)
		{
			List<String> currStatementArgs = new   LinkedList<String>();				
			currStatementArgs.add(firstArgumentList.get(i));
			currStatementArgs.add(secondArgumentList.get(i));
			statementsArgs.add(currStatementArgs);
		}
		return executePatternBatch(sqlPattern, statementsArgs);
	}	*
	
	
	/** Run the SQL commands in a batch
	 * @return	the number of statements executed successfully
	 **/	
	public	static	int	executeBatch(List<String> sqlCommands)
	{
		// get connection to DB, and verify it
		Connection conn = DBConnectionPool.getConnection();
		if (conn == null)
		{
			Debug.log("DBAccessLayer::executeBatch: ERROR - failed getting connection to DB");			
			return 0;
		}
		
		Statement	stmt = null;
		try
		{
			stmt	=	conn.createStatement();
			for (String s : sqlCommands)
			{
				// Add to batch
				stmt.addBatch(s);
				
				// insert command to log
				Debug.query(s);				
			}
			
			Debug.log("DBAccessLayer::executeBatch: INFO - exceuting batch with " + sqlCommands.size() + " commands");
			return doBatch(conn, stmt);
		}
		catch (SQLException e)
		{
			Debug.log("DBAccessLayer::executeBatch: ERROR - exception occured when adding command to batch");
			return 0;
		}
	}

	
	/** 
	 * 
	 * @param sqlList	- list of commands
	 * @param minUpdatesPerCommand	- list of minimal rows to be updated by each command
	 * @return			- number of commands executed before transaction failed, 
	 * 						or sqlList.size() if transaction completed
	 */
	public	static	int	executeCommandsAtomic(List<String> sqlList, int[] minUpdatesPerCommand)
	{
		// get connection to DB, and verify it		
		Connection conn = DBConnectionPool.getConnection();
		if (conn == null)
		{
			Debug.log("DBAccessLayer::executeCommandsAtomic: ERROR - failed getting connection to DB");
			return 0;
		}
				
		Statement	stmt = null;
		int ret = 0;
		try
		{
			conn.setAutoCommit(false);			
			stmt = conn.createStatement();
			
			// execute each statement
			for (int i = 0; i < sqlList.size(); i++)
			{
				// insert command to log
				Debug.query(sqlList.get(i));
				
				int updated = stmt.executeUpdate(sqlList.get(i));
				// not enough rows updated - cancel transaction
				if (updated < minUpdatesPerCommand[i])
				{
					Debug.log("DBAccessLayer::executeCommandsAtomic: ERROR - error on command " + (i+1) + " only " + updated + " rows updated, (minimum is " + minUpdatesPerCommand[i] + " transaction is canceled");						
					conn.rollback();
					return ret;
				}
				else
				{
					ret++;
				}
			}				
			Debug.log("DBAccessLayer::executeCommandsAtomic: SUCCESS - transaction complete " + ret + " commands commited");				
			return ret;
		}
		catch	 (SQLException e)
		{
			Debug.log("DBAccessLayer::executeCommandsAtomic: ERROR - exception durring transaction - doing rollback: " + e.toString() );				
			try
			{
				conn.rollback();				
			}
			catch	 (SQLException ex)
			{
				Debug.log("DBAccessLayer::executeCommandsAtomic: ERROR - exception durring rollback: " + ex.toString());					
				return ret;
			}
			return ret;
		}
		finally
		{
			try
			{
				if (!conn.getAutoCommit())
				{		
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
			catch	 (SQLException ex)
			{
				Debug.log("DBAccessLayer::executeCommandsAtomic: ERROR - exception durring commit: " + ex.toString());					
			}
	
			closeStatementAndConnection(conn, stmt);
		}	
	}
	
	
	/** 
	 * 
	 * @param sqlList	- list of commands
	 * @return			- number of commands executed before transaction failed, 
	 * 						or sqlList.size() if transaction completed
	 */	
	public	static	int	executeCommandsAtomic(List<String> sqlList)
	{
		int[]	minUpdatesPerCommand = new int[sqlList.size()];
		int executed = executeCommandsAtomic(sqlList, minUpdatesPerCommand);
		if (executed != sqlList.size())
		{
			Debug.log("DBAccessLayer::executeCommandsAtomic: executed only " + executed + " out of " + sqlList.size() + " commands");
			//return 0;
		}
		Debug.log("DBAccessLayer::executeCommandsAtomic: SUCCEESS - all " + executed + " commands");
		return executed;		
	}

	
	/**
	 * Run the statement in batch
	 * @param conn
	 * @param stmt
	 * @return
	 */
	protected	static	int	doBatch(Connection conn, Statement stmt)
	{
		int[] res = null;
		try
		{
			conn.setAutoCommit(false);
			long startTime = System.nanoTime();		// for performance measuring
			// Execute the statement
			 Debug.log("DBAccessLayer::doBatch: INFO - Starting batch...");
			res = stmt.executeBatch();
			 // calculate how much time the process took
			 long estimatedTime = System.nanoTime() - startTime;
			 estimatedTime /= 1000000000;	// convert to seconds		
			 Debug.log("DBAccessLayer::doBatch: INFO - batch done in " + estimatedTime + " seconds");
			
			// Check how many commands where executed
			for (int i = 0; i < res.length; i++)
			{
				if (res[i] == Statement.EXECUTE_FAILED)
				{
					Debug.log("DBAccessLayer::doBatch: ERROR - error on command " + (i+1));					
					return i;
				}
			}
			conn.commit();
			return res.length;
		}
		catch (SQLException e)
		{
			if (res == null)
			{
				Debug.log("DBAccessLayer::doBatch: ERROR - exception occured during update, no commands done:" + e.toString());					
				return 0;
			}
			else
			{
				Debug.log("DBAccessLayer::doBatch: ERROR - exception occured during update, " + res.length + " commands done: " + e.toString());					
				return res.length;
			}
		}
		// release connection and statement - no matter what
		finally
		{
			try
			{
				conn.setAutoCommit(true);
			}
			catch (SQLException e)
			{
				
			}
			closeStatementAndConnection(conn, stmt);
		}			
	}	
	
	
	/**	Handles release of connection and statement 
	 **/
	protected static void	closeStatementAndConnection(Connection conn, Statement pstmt)
	{
		try
		{
			if (pstmt != null)
			{
				pstmt.close();
				//Debug.log("DBAccessLayer::closeStatementAndConnection - closed statement");
			}
		}
		catch (SQLException e)
		{
			Debug.log("DBAccessLayer::closeStatementAndConnection - ERROR - Exception while trying to close statement:" + e.toString());
		}
		finally
		{
			DBConnectionPool.endConnection(conn);
			//Debug.log("DBAccessLayer::closeStatementAndConnection -  ended connection");
		}
	}
}