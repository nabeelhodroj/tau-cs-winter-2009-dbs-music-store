package GUI;

import java.util.StringTokenizer;

import Tables.EmployeesTableItem;

/**
 * created by Ariel
 * 
 * Sale tab handlers
 */
public class SaleFuncs {
	
	// initialize sale tab listeners
	
	public static void initSaleTabListeners(){
		
	}
	
	////////////////////
	//	Sale handlers //
	////////////////////

	/**
	 * clear sale table when sale is done to enable new sale
	 */
	public static void clearSaleTable(){
		// clear fields
		Main.getSaleTableSaleItems().removeAll();
		Main.getSaleLabelTotalPriceValue().setText("");
		Main.getSaleLabelDateInput().setText(MainFuncs.getDate());
		Main.getSaleLabelTimeInput().setText(MainFuncs.getTime());
		// set buttons
		Main.getSaleButtonMakeSale().setEnabled(false);
		Main.getSaleButtonRemoveItem().setEnabled(false);
	}
	
	/**
	 * updates salesmen list according to current employees table
	 */
	public static void updateSalesmenList(){
		// first clear all salesmen in the list
		Main.getSaleComboSalesmanIDNameInput().removeAll();
		
		// then insert all salesmen (all employees available in the store)
		for(EmployeesTableItem employee: StaticProgramTables.employees.getEmployees().values()){
			Main.getSaleComboSalesmanIDNameInput().add(Integer.toString(employee.getEmployeeID())+
					": "+employee.getFirstName()+" "+employee.getLastName());
		}
	}
	
	/**
	 * returns the integer value of selected salesman ID
	 * (or -1 if encountered a parsing error)
	 * @return
	 */
	public static int getSalesmanIDFromSelected(){
		String employeeDetails = Main.getSaleComboSalesmanIDNameInput().getText();
		StringTokenizer tokenizer = new StringTokenizer(employeeDetails,":");
		try{
			int id = Integer.parseInt(tokenizer.nextToken());
			return id;
		}catch(NumberFormatException nfe){
			System.out.println("*** BUG: SaleFuncs.getSalesmanIDFromSelected - salesman id is not an integer");
			return -1;
		}
	}
}
