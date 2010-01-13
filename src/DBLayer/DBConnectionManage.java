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
		
		private String toGUIDate(Date d){
			String[] dateArr = d.toString().split("-");
			return dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];
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
			private Thread parseThread;
			private boolean finishedSuccessfully = true;
			private Map<String,Integer> genres = new HashMap<String,Integer>();
			private Map<String,Integer> artistNames= new HashMap<String,Integer>();
			
			public BatchAddToDB(Thread parseThread) {
				this.parseThread = parseThread;
				Debug.log("DBConnectionManage.BatchAddToDB: access DB to get all current genres");
				DBQueryResults genresQueryResults = DBAccessLayer.executeQuery("SELECT genre_id, genre_name FROM genres");
				if (genresQueryResults == null) {
					Debug.log("DBConnectionManage.BatchAddToDB [ERROR]: failed while getting genres from DB");
					GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.UPDATE_DB_FAILURE);
					finishedSuccessfully = false;
					return;
				}
				
				ResultSet genresRS = genresQueryResults.getResultSet();
				try {
					while (genresRS.next()){
						genres.put(genresRS.getString("genre_name"), genresRS.getInt("genre_id"));
					}
				} catch (SQLException e) {
					Debug.log("DBConnectionManage.BatchAddToDB [ERROR]: RS failure while getting genres");
					GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.UPDATE_DB_FAILURE);
					finishedSuccessfully = false;
					genresQueryResults.close();
					return;
				}
				
				Debug.log("DBConnectionManage.BatchAddToDB: access DB to get all current artist names");
				DBQueryResults artistNamesQueryResults = DBAccessLayer.executeQuery("SELECT artist_id, artist_name FROM artists");
				if (artistNamesQueryResults == null) {
					Debug.log("DBConnectionManage.BatchAddToDB [ERROR]: failed while getting artists from DB");
					GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.UPDATE_DB_FAILURE);
					finishedSuccessfully = false;
					return;
				}
				
				ResultSet artistsRS = artistNamesQueryResults.getResultSet();
				try {
					while (artistsRS.next()){
						artistNames.put(artistsRS.getString("artist_name"), artistsRS.getInt("artist_id"));
					}
				} catch (SQLException e) {
					Debug.log("DBConnectionManage.BatchAddToDB [ERROR]: RS failure while getting artists");
					GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.UPDATE_DB_FAILURE);
					finishedSuccessfully = false;
					artistNamesQueryResults.close();
					return;
				}	
			}

			@Override
			public void run() {
				List<DiscDBAlbumData> parsedAlbums;
				while (parseThread.isAlive() || DiscDBParser.getCurrentAlbumListSize() > 0){
					List<String> insertBatch = new ArrayList<String>();
					String albumBuffer;
					String songBuffer;
					String artistBuffer;
					int artistID;
					String genreBuffer;
					int genreID;
					
					int numToRemove = Math.min(10000,DiscDBParser.getCurrentAlbumListSize());
					Debug.log("DBConnectionManage.BatchAddToDB read " + numToRemove + " albums");
					if (numToRemove == 0)
						try {
							Thread.sleep(1000);
							continue;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					parsedAlbums = DiscDBParser.removeAllbumsDataFromList(numToRemove); //TODO: set number
					
					for (DiscDBAlbumData albumData : parsedAlbums) {
						if (!genres.containsKey(albumData.getGenere())){
							genreID = genres.size();
							genres.put(albumData.getGenere(), genreID);
							genreBuffer = "INSERT INTO genres(genre_id,genre_name) VALUES(" + genreID + ",'" + albumData.getGenere() + "')";
							insertBatch.add(genreBuffer);
						} else {
							genreID = genres.get(albumData.getGenere());
						}
						
						if (!artistNames.containsKey(albumData.getArtist())){
							artistID = artistNames.size();
							artistNames.put(albumData.getArtist(), artistID);
							artistBuffer = "INSERT INTO artists(artist_id,artist_name) VALUES(" + artistID + ",'" + albumData.getArtist() + "')";
							insertBatch.add(artistBuffer);
						} else {
							artistID = artistNames.get(albumData.getArtist());
						}
						
						albumBuffer = "INSERT INTO Albums(album_name, artist_id, year, genre_id, length_sec,price) ";
						albumBuffer += "VALUES(" +
								"'"+albumData.getName() + "'," +
								artistID + "," +
								albumData.getYear()+ "," +
								genreID + "," +
								albumData.getLengthSec()+ "," +
								albumData.getPrice() +")\n";
						insertBatch.add(albumBuffer);
						
						for (DiscDBTrackData trackData : albumData.getTrackList()) {
							if (!artistNames.containsKey(trackData.getArtist())){
								artistID = artistNames.size();
								artistNames.put(trackData.getArtist(), artistID);
								artistBuffer = "INSERT INTO artists(artist_id,artist_name) VALUES(" + artistID + ",'" + trackData.getArtist() + "')";
								insertBatch.add(artistBuffer);
							} else {
								artistID = artistNames.get(trackData.getArtist());
							}
							
							songBuffer = "INSERT INTO Songs(album_id, track_num, song_name, artist_id, length_sec) ";
							songBuffer += "VALUES(" +
									"ALBUMS_SEQ.CURRVAL, " +
									trackData.getTrackNum() + ", " +
									"'" + trackData.getName() + "', " +
									artistID + "," +
									trackData.getLengthSdc() + ")";
							insertBatch.add(songBuffer);
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

			while (parseThread.isAlive() || updateThread.isAlive()){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (!fileParser.isFinishedSuccessfully() || !dbAdder.isFinishedSuccessfully()){ // Revert to previous DB state
				DBAccessLayer.executeUpdate("DELETE FROM Albums\n" +
						"WHERE album_id > " + lastAlbumID);
				DBAccessLayer.executeUpdate("DELETE FROM Songs\n" +
						"WHERE album_id > " + lastAlbumID);
			}
			GuiUpdatesInterface.notifyDataBaseUpdated(filename); 
			
			// until implemented, use example:
			// TablesExamples.updateDataBase(filename);
		}
	}
}
