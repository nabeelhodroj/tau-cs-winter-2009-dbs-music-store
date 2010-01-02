package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import Debug.Debug;
import Debug.Debug.DebugOutput;
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

	/**
	 * initializes the manage tab view: enabled and disabled fields, default values etc.
	 */
	protected static void initManageTabView(){
		// update employees table view
		updateEmployeesTableView();
		// disable employees details inputs
		switchEnableEmployeesDetails(false);
		// disable update data button
		Main.getManageButtonDBSUpdate().setEnabled(false);
		// initialize buttons
		setEnableEmployeeButtons(true, false, false, false, false, false);
	}

	/**
	 * initialize manage tab listeners
	 */
	protected static void initManageListeners(){
		// employees table listener
		Main.getManageTableEmployees().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: employee selected",DebugOutput.FILE,DebugOutput.STDOUT);
						
						// disable employee details
						switchEnableEmployeesDetails(false);
						// set buttons
						setEnableEmployeeButtons(true, false, false, true, false, true);
						// show employee details
						showSelectedEmployeeDetails();
						// enable edit and remove employee
					}
				}
		);
		
		// new button listener
		Main.getManageButtonEmployeeNew().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: New button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						// disable new, edit, remove employee buttons
						// enable insert, exit without saving, save buttons
						setEnableEmployeeButtons(false,true,true,false,true,false);
						// setup employee details fields
						newButtonInvokation();
						// disable new employee selection
						Main.getManageTableEmployees().setEnabled(false);
					}
				}
		);
		
		// insert button listener
		Main.getManageButtonEmployeeInsert().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: Insert button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						//TODO
					}
				}
		);
		
		// exit without saving button listener
		Main.getManageButtonEmployeeNoSave().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: Exit without saving button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						//TODO
					}
				}
		);
		
		// edit button listener
		Main.getManageButtonEmployeeEdit().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: Edit button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						//TODO
					}
				}
		);
		
		// save button listener
		Main.getManageButtonEmployeeSave().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: Save button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						//TODO
					}
				}
		);
		
		// remove employee button listener
		Main.getManageButtonEmployeeRemove().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: Remove employee button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						//TODO
					}
				}
		);
		
		// browse button listener
		Main.getManageButtonDBSBrowse().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: Browse button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						//TODO
					}
				}
		);
		
		// update database button listener
		Main.getManageButtonDBSUpdate().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Management tab: Update DBS button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						//TODO
					}
				}
		);
	}
	
	/////////////////////////
	//	employees handlers //
	/////////////////////////
	
	/**
	 * enables or disables employee details text boxes
	 * @param enable
	 */
	public static void switchEnableEmployeesDetails(boolean enable){
		Main.getManageTextBoxEmployeeIDInput().setEnabled(enable);
		Main.getManageTextBoxEmployeeBirthInput().setEnabled(enable);
		Main.getManageTextBoxEmployeeFNameInput().setEnabled(enable);
		Main.getManageTextBoxEmployeeLNameInput().setEnabled(enable);
		Main.getManageTextBoxEmployeeAddressInput().setEnabled(enable);
		Main.getManageTextBoxEmployeePhoneInput().setEnabled(enable);
		Main.getManageTextBoxEmployeeCellPhoneInput().setEnabled(enable);
		Main.getManageComboEmployeePositionInput().setEnabled(enable);
	}
	
	/**
	 * show selected employee details
	 */
	public static void showSelectedEmployeeDetails(){
		try{
			// get selected employee
			int employeeID = Integer.parseInt(Main.getManageTableEmployees().getSelection()[0].getText());
			EmployeesTableItem employee = StaticProgramTables.employees.getEmployee(employeeID);
			
			// update details view
			Main.getManageLabelEmployeeEmploymentDateInput().setText(employee.getEmploymentDate());
			Main.getManageLabelEmployeeEmployeeStoreIDInput().setText(Integer.toString(employee.getStoreID()));
			Main.getManageTextBoxEmployeeIDInput().setText(Integer.toString(employee.getEmployeeID()));
			Main.getManageTextBoxEmployeeBirthInput().setText(employee.getBirthDate());
			Main.getManageTextBoxEmployeeFNameInput().setText(employee.getFirstName());
			Main.getManageTextBoxEmployeeLNameInput().setText(employee.getLastName());
			Main.getManageTextBoxEmployeeAddressInput().setText(employee.getAddress());
			Main.getManageTextBoxEmployeePhoneInput().setText(employee.getPhone());
			Main.getManageTextBoxEmployeeCellPhoneInput().setText(employee.getCellPhone());
			Main.getManageComboEmployeePositionInput().setText(employee.getPosition().getStrRep());
		}catch(NumberFormatException nfe){
			System.out.println("*** BUG: ManageFuncs.showSelectedEmployeeDetails bug");
		}
	}
	
	/**
	 * set employees buttons enablement
	 * @param newEnable
	 * @param insertEnable
	 * @param noSaveEnable
	 * @param editEnable
	 * @param saveEnable
	 * @param removeEnable
	 */
	public static void setEnableEmployeeButtons(boolean newEnable, boolean insertEnable, boolean noSaveEnable,
			boolean editEnable, boolean saveEnable, boolean removeEnable){
		Main.getManageButtonEmployeeNew().setEnabled(newEnable);
		Main.getManageButtonEmployeeInsert().setEnabled(insertEnable);
		Main.getManageButtonEmployeeNoSave().setEnabled(noSaveEnable);
		Main.getManageButtonEmployeeEdit().setEnabled(editEnable);
		Main.getManageButtonEmployeeSave().setEnabled(saveEnable);
		Main.getManageButtonEmployeeRemove().setEnabled(removeEnable);
	}
	
	///////////////////////
	//	employee buttons //
	///////////////////////
	
	/**
	 * enable employee input fields and clear them
	 * initialize employment date and store id
	 */
	public static void newButtonInvokation(){
		// enable fields and clear them
		switchEnableEmployeesDetails(true);
		// clear fields
		Main.getManageTextBoxEmployeeIDInput().setText("");
		Main.getManageTextBoxEmployeeBirthInput().setText("");
		Main.getManageTextBoxEmployeeFNameInput().setText("");
		Main.getManageTextBoxEmployeeLNameInput().setText("");
		Main.getManageTextBoxEmployeeAddressInput().setText("");
		Main.getManageTextBoxEmployeePhoneInput().setText("");
		Main.getManageTextBoxEmployeeCellPhoneInput().setText("");
		Main.getManageComboEmployeePositionInput().setText("");
		// set employment date and store id
		Main.getManageLabelEmployeeEmploymentDateInput().setText(MainFuncs.getDate());
		Main.getManageLabelEmployeeEmployeeStoreIDInput().setText(
				Integer.toString(StaticProgramTables.thisStore.getStoreID()));
	}
	
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
