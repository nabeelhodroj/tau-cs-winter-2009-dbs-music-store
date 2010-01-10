package DBLayer;

import java.sql.*;
import Tables.*;
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
			/* TODO: return to code
			DBQueryResults queryResults = DBAccessLayer.executeQuery(employeesQuery);
			if (queryResults == null){
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
							rs.getDate("Hire_date").toString(),
							rs.getDate("Birth_date").toString(),
							rs.getString("Adress"),
							rs.getString("Phone_Number"), 
							rs.getString("Cell_phone_number"), 
							EmployeePositionsEnum.convertFromInt(rs.getInt("Position")));
				}
				queryResults.close();
			} catch (SQLException e) {
				GuiUpdatesInterface.initEmployeesTable(new EmployeesTable(StaticProgramTables.thisStore.getStoreID()));
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INIT_EMP_FAILURE);
				
				queryResults.close();
				return;
			}
			
			GuiUpdatesInterface.initEmployeesTable(employees); */
			// until implemented, use example
			TablesExamples.getEmployeesTable();
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
			
			/* TODO: return to code
			DBQueryResults queryResults = DBAccessLayer.executeQuery(employeeExistsQuery);
			if (queryResults == null){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				return;
			}
			ResultSet rs = queryResults.getResultSet();
			
			boolean empExists = false;
			
			try {
				empExists = rs.next();
			} catch (SQLException e) {
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				queryResults.close();
				
				return;	
			}

			
			GuiUpdatesInterface.tryInsertNewEmployee(employeeID,empExists);*/
			// until implemented, use example:
			TablesExamples.checkIfEmployeeExists(employeeID);
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
			/* TODO: return to code
			if (DBAccessLayer.executeUpdate(employeeRemove) == -1){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				return;
			}

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
				employee.getFirstName()+"," +
				employee.getLastName()+"," +
				"TO_DATE('"+employee.getEmploymentDate()+"','DD/MM/YYYY')"+","+
				"TO_DATE('"+employee.getBirthDate()+"','DD/MM/YYYY')"+","+
				employee.getAddress()+","+
				employee.getPhone()+","+
				employee.getCellPhone()+","+
				employee.getStoreID()+","+
				employee.getPosition().getIntRep()+")";
			
			if (DBAccessLayer.executeUpdate(employeeInsert) == -1){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				return;
			}
			
			GuiUpdatesInterface.insertUpdateEmployee(employee);*/
			// until implemented, use example:
			TablesExamples.insertUpdateEmployee(employee);
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
			
			String employeeRemove = "REMOVE FROM Employees\n" +
					"WHERE employee_id = "+employeeID;
			/* TODO: return to code
			if (DBAccessLayer.executeUpdate(employeeRemove) == -1){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.INSERT_SAVE_EMP_FAILURE);
				return;
			}
			
			GuiUpdatesInterface.removeEmployee(employeeID); */
			// until implemented, use example:
			TablesExamples.removeEmployee(employeeID);
		}		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public class UpdateDatabase implements Runnable{
		private String filename;

		public UpdateDatabase(String filename) {
			super();
			this.filename = filename;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionManage.UpdateDatabase thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			//TODO
			
			// until implemented, use example:
			TablesExamples.updateDataBase(filename);
		}
	}
}
