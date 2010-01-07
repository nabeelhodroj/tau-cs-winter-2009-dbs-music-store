package DBLayer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import Tables.EmployeePositionsEnum;
import Tables.EmployeesTable;
import Tables.EmployeesTableItem;
import Tables.TablesExamples;
import GUI.GuiUpdatesInterface;
import GUI.StaticProgramTables;
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
			ResultSet rs = null;//TODO: add a call with employeesQuery
			
			EmployeesTable employees = new EmployeesTable(StaticProgramTables.thisStore.getStoreID());
			
			//TODO: return to code
			/*try {
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
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			// TODO: return to code once works
			//GuiUpdatesInterface.initEmployeesTable(employees);
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
			ResultSet rs = null;//TODO: add a call with employeeExistsQuery
			
			boolean empExists = false;
			
			//TODO: return to code
			/*try {
				empExists = rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/ 

			// TODO: return to code once works
			//GuiUpdatesInterface.tryInsertNewEmployee(employeeID,empExists);
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

			String employeeExistsQuery = 
				"SELECT *\n"+
				"FROM Employees\n"+
				"WHERE employee_id = " + employee.getEmployeeID();
			
			ResultSet rs = null;//TODO: add a call with employeeExistsQuery
			
			//TODO: return to code
			/*try {
				if(rs.next()){
					String employeeRemove = 
						"DELETE FROM Employees\n"+
						"WHERE employee_id = " + employee.getEmployeeID();
					//TODO: add a DB call with employeeRemove
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
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
			
			//TODO: a DB insert call with employeeInsert
			
			// TODO: return to code:
			//GuiUpdatesInterface.insertUpdateEmployee(employee);
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
			
			String removeEmployee = "REMOVE FROM Employees\n" +
					"WHERE employee_id = "+employeeID;
			
			//TODO: add a DB call with removeEmployee
			//TODO: return to code:
			//GuiUpdatesInterface.removeEmployee(employeeID);
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
