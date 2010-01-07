package DBLayer;

import Tables.EmployeesTableItem;
import Tables.TablesExamples;
import General.Debug;
import General.Debug.DebugOutput;

public class DBConnectionManage {
	public class GetEmployeesTable implements Runnable{
		@Override
		public void run() {
			Debug.log("DBConnectionManage.GetEmployeesTable thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			//TODO
			
			// until implemented, use example:
			TablesExamples.getEmployeesTable();

		}
	}
	
	public class CheckIfEmployeeExists implements Runnable{
		private int employeeID;

		public CheckIfEmployeeExists(int employeeID) {
			super();
			this.employeeID = employeeID;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionManage.CheckIfEmployeeExists thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			//TODO
			
			// until implemented, use example:
			TablesExamples.checkIfEmployeeExists(employeeID);
		}
	}
	
	public class InsertUpdateEmployee implements Runnable{
		private EmployeesTableItem employee;

		public InsertUpdateEmployee(EmployeesTableItem employee) {
			super();
			this.employee = employee;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionManage.InsertUpdateEmployee thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			//TODO
			
			// until implemented, use example:
			TablesExamples.insertUpdateEmployee(employee);
		}
	}
	
	public class RemoveEmployee implements Runnable{
		private int employeeID;

		public RemoveEmployee(int employeeID) {
			super();
			this.employeeID = employeeID;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionManage.RemoveEmployee thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			//TODO
			
			// until implemented, use example:
			TablesExamples.removeEmployee(employeeID);
		}		
	}
	
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
