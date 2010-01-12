package DBLayer;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import Tables.*;
import DiscsDB.DiscDBAlbumData;
import DiscsDB.DiscDBParser;
import DiscsDB.DiscDBTrackData;
import GUI.*;
import General.Debug;
import General.Debug.DebugOutput;

public class DBConnectionManage {
	
	public class GetEmployeesTable implements Runnable{
		@Override
		public void run() {
			Debug.log("DBConnectionManage.GetEmployeesTable thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			String employeesQuery =
				"SELECT *\n"+
				"FROM Employees\n"+
				"WHERE store_id = " + StaticProgramTables.thisStore.getStoreID();
			
			DBQueryResults queryResults = DBAccessLayer.executeQuery(employeesQuery);
			if (queryResults == null){
				Debug.log("DBConnectionInterface.GetEmployeesTable [ERROR]: Got NULL from query");
				
				GuiUpdatesInterface.initEmployeesTable(new EmployeesTable(StaticProgramTables.thisStore.getStoreID()));
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INIT_EMP_FAILURE);

				return;
			}
			ResultSet rs = queryResults.getResultSet();
			
			EmployeesTable employees = new EmployeesTable(StaticProgramTables.thisStore.getStoreID());
			
			try {
				while (rs.next()){
					employees.addEmployee(rs.getInt("Employee_id"), 
							rs.getString("First_name"),
							rs.getString("Last_name"),
							toGUIDate(rs.getDate("Hire_date")),
							toGUIDate(rs.getDate("Birth_date")),
							rs.getString("Address"),
							rs.getString("Phone_Number"), 
							rs.getString("Cell_phone_number"), 
							EmployeePositionsEnum.convertFromInt(rs.getInt("Position")));
				}
				queryResults.close();
			} catch (SQLException e) {
				Debug.log("DBConnectionInterface.GetEmployeesTable [ERROR]: Failed extracting employees from ResultSet");
				Debug.log(e.getStackTrace().toString());
				
				GuiUpdatesInterface.initEmployeesTable(new EmployeesTable(StaticProgramTables.thisStore.getStoreID()));
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INIT_EMP_FAILURE);
				
				queryResults.close();
				return;
			}
			
			GuiUpdatesInterface.initEmployeesTable(employees);
			
			// until implemented, use example
			//TablesExamples.getEmployeesTable();
		}
		
		@SuppressWarnings("deprecation")
		private String toGUIDate(Date d){
			return d.getDate()+"/"+d.getMonth()+"/"+d.getYear();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public class CheckIfEmployeeExists implements Runnable{
		private int employeeID;

		public CheckIfEmployeeExists(int employeeID) {
			super();
			this.employeeID = employeeID;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionManage.CheckIfEmployeeExists thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			String employeeExistsQuery =
				"SELECT *\n"+
				"FROM Employees\n"+
				"WHERE employee_id = " + employeeID;
			
			DBQueryResults queryResults = DBAccessLayer.executeQuery(employeeExistsQuery);
			if (queryResults == null){
				Debug.log("DBConnectionInterface.CheckIfEmployeeExists [ERROR]: Got NULL from query");
				
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				return;
			}
			ResultSet rs = queryResults.getResultSet();
			
			boolean empExists = false;
			
			try {
				empExists = rs.next();
			} catch (SQLException e) {
				Debug.log("DBConnectionInterface.InitDBConnection [ERROR]: Failed extracting employee from ResultSet");
				Debug.log(e.getStackTrace().toString());
				
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				queryResults.close();
				
				return;	
			}

			
			GuiUpdatesInterface.tryInsertNewEmployee(employeeID,empExists);
			// until implemented, use example:
			// TablesExamples.checkIfEmployeeExists(employeeID);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public class InsertUpdateEmployee implements Runnable{
		private EmployeesTableItem employee;

		public InsertUpdateEmployee(EmployeesTableItem employee) {
			super();
			this.employee = employee;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionManage.InsertUpdateEmployee thread is started",DebugOutput.FILE,DebugOutput.STDOUT);

			String employeeRemove = 
				"DELETE FROM Employees\n"+
				"WHERE employee_id = " + employee.getEmployeeID();

			String employeeInsert = 
				"INSERT INTO Employees(" +
				"employee_id, " +
				"first_name, " +
				"last_name, " +
				"Hire_Date, " +
				"Birth_Date, " +
				"address, " +
				"Phone_Number, " +
				"cell_phone_Number, " +
				"store_id, " +
				"position)\n" +
				
				"VALUES(" +
				employee.getEmployeeID()+"," +
				"'"+employee.getFirstName()+"'," +
				"'"+employee.getLastName()+"'," +
				"TO_DATE('"+employee.getEmploymentDate()+"','DD/MM/YYYY')"+","+
				"TO_DATE('"+employee.getBirthDate()+"','DD/MM/YYYY')"+","+
				"'"+employee.getAddress()+"',"+
				"'"+employee.getPhone()+"',"+
				"'"+employee.getCellPhone()+"',"+
				employee.getStoreID()+","+
				employee.getPosition().getIntRep()+")";
			
			List<String> sqlCommands = new ArrayList<String>();
			sqlCommands.add(employeeRemove);
			sqlCommands.add(employeeInsert);
			
			if (DBAccessLayer.executeCommandsAtomic(sqlCommands) != 2){
				Debug.log("DBConnectionInterface.InsertUpdateEmployee [ERROR]: Failed Removing and Updating Employees");

				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				return;
			}

			GuiUpdatesInterface.insertUpdateEmployee(employee);
			// until implemented, use example:
			// TablesExamples.insertUpdateEmployee(employee);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public class RemoveEmployee implements Runnable{
		private int employeeID;

		public RemoveEmployee(int employeeID) {
			super();
			this.employeeID = employeeID;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionManage.RemoveEmployee thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			String employeeRemove = "DELETE FROM Employees\n" +
					"WHERE employee_id = "+employeeID;

			if (DBAccessLayer.executeUpdate(employeeRemove) == -1){
				Debug.log("DBConnectionInterface.InitDBConnection [ERROR]: Failed Removing Employee");
				
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				return;
			}
			
			GuiUpdatesInterface.removeEmployee(employeeID);
			// until implemented, use example:
			// TablesExamples.removeEmployee(employeeID);
		}		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public class UpdateDatabase implements Runnable{
		
		private class ParseFile implements Runnable{
			private String filename;
			private boolean finishedSuccessfully = true;
			
			public ParseFile(String filename) {
				this.filename = filename;
			}

			@Override
			public void run() {
				try {
					DiscDBParser.parseFile(filename);
				} catch (IOException e) {
					GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.UPDATE_DB_FAILURE);
					finishedSuccessfully = false;
					return;	
				}
			}

			public boolean isFinishedSuccessfully() {
				return finishedSuccessfully;
			}
			
			
			
		}
		
		////////////////////////////////////////////////
		
		private class BatchAddToDB implements Runnable{
			Thread parseThread;
			private boolean finishedSuccessfully = true;
			
			public BatchAddToDB(Thread parseThread) {
				this.parseThread = parseThread;
			}

			@Override
			public void run() {
				List<DiscDBAlbumData> parsedAlbums;
				while (parseThread.isAlive()){
					List<String> insertBatch = new ArrayList<String>();
					String buffer;
					
					int numToRemove = Math.min(10000,DiscDBParser.getCurrentAlbumListSize());
					parsedAlbums = DiscDBParser.removeAllbumsDataFromList(numToRemove); //TODO: set number
					
					for (DiscDBAlbumData albumData : parsedAlbums) {
						buffer = "INSERT INTO Albums(album_name, artist_name, year, genre, length_sec) ";
						buffer += "VALUES(" +
								"'"+albumData.getName() + "'," +
								"'"+albumData.getArtist() + "'," +
								albumData.getYear()+ "," +
								"'"+albumData.getGenere() + "'," +
								albumData.getLengthSec()+ ")\n";
						insertBatch.add(buffer);
						
						for (DiscDBTrackData trackData : albumData.getTrackList()) {
							buffer = "INSERT INTO Songs(album_id, track_num, song_name, artist_name, length_sec) ";
							buffer += "VALUES(" +
									"ALBUMS_SEQ.CURRVAL, " +
									trackData.getTrackNum() + ", " +
									"'"+trackData.getName() + "', " +
									"'"+trackData.getArtist() + "'," +
									trackData.getLengthSdc() + ")";
							insertBatch.add(buffer);
						}
					}
					
					if (DBAccessLayer.executeBatch(insertBatch) != insertBatch.size()){
						GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.UPDATE_DB_FAILURE);
						finishedSuccessfully = false;
						return;
					}	
				}
			}

			public boolean isFinishedSuccessfully() {
				return finishedSuccessfully;
			}
			
		}
		
		////////////////////////////////////////////////
		
		private String filename;
		
		public UpdateDatabase(String filename) {
			super();
			this.filename = filename;
		}
		
		
		
		@Override
		public void run() {
			Debug.log("DBConnectionManage.UpdateDatabase thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			int lastAlbumID = 0;

			DBQueryResults maxIDQuery = DBAccessLayer.executeQuery("SELECT MAX(album_id) AS last_album_id\n" +
					"FROM Albums");
			if (maxIDQuery == null){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.UPDATE_DB_FAILURE);
				return;
			}
			ResultSet rs = maxIDQuery.getResultSet();
			try {
				rs.next();
				lastAlbumID = rs.getInt("last_album_id");
			} catch (SQLException e) {
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.UPDATE_DB_FAILURE);
				return;
			}
			
			ParseFile fileParser = new ParseFile(filename);
			Thread parseThread = new Thread(fileParser);
			parseThread.start();
			
			BatchAddToDB dbAdder = new BatchAddToDB(parseThread);
			Thread updateThread = new Thread(dbAdder);
			updateThread.start();

			if (!fileParser.isFinishedSuccessfully() || !dbAdder.isFinishedSuccessfully()){ // Revert to previous DB state
				DBAccessLayer.executeUpdate("DELETE FROM Albums\n" +
						"WHERE album_id > " + lastAlbumID);
				DBAccessLayer.executeUpdate("DELETE FROM Songs\n" +
						"WHERE album_id > " + lastAlbumID);
			}
			// until implemented, use example:
			// TablesExamples.updateDataBase(filename);
		}
	}
}
