package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import Tables.EmployeesTable;
import Tables.EmployeesTableItem;
import Tables.OrdersOrRequestsTable;
import Tables.OrdersOrRequestsTableItem;

/**
 * created by Ariel
 * 
 * Management tab handlers
 */
public class ManageFuncs {

	/////////////////////////
	//	employees handlers //
	/////////////////////////
	
	/**
	 * update requests table view according to current requests table
	 */
	public static void updateEmployeesTableView(){
		// first remove all employees table items
		Main.getManageTableEmployees().removeAll();
		
		// then insert all items
		for(EmployeesTableItem employee: StaticProgramTables.employees.getEmployees().values()){
			TableItem item = new TableItem(Main.getManageTableEmployees(), SWT.NONE);
			String[] entry = new String[]{
					Integer.toString(employee.getEmployeeID()),
					employee.getFirstName(),
					employee.getLastName(),
					employee.getPosition().getStrRep()
			};
			item.setText(entry);
		}
	}
	
	/**
	 * update current employees table to given one
	 * @param employees
	 */
	public static void setCurrentEmployees(EmployeesTable employees){
		StaticProgramTables.employees = employees;
	}
}
