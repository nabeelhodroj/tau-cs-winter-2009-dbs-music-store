package GUI;

import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import Tables.EmployeesTableItem;
import Tables.SaleTable;
import Tables.SaleTableItem;

/**
 * created by Ariel
 * 
 * Sale tab handlers
 */
public class SaleFuncs {
	
	/**
	 * initializes the sale tab view: enabled and disabled fields, default values etc.
	 * /// not needed since it's done by invoking initCurrentSale() from Main after employees
	 * /// table is initialized
	 */
	protected static void initSaleTabView(){
		// disable buttons
		Main.getSaleButtonRemoveItem().setEnabled(false);
		Main.getSaleButtonMakeSale().setEnabled(false);
	}

	/**
	 * initialize search tab listeners
	 */
	public static void initSaleListeners(){
		
	}
	
	////////////////////
	//	Sale handlers //
	////////////////////
	
	// sale table initialization
	////////////////////////////

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
		Main.getSaleButtonRemoveItem().setEnabled(false);
		Main.getSaleButtonMakeSale().setEnabled(false);
	}
	
	/**
	 * initialize current sale with current salesman, update date and time
	 */
	public static void initCurrentSale(){
		// initialize new sale table
		StaticProgramTables.sale = new SaleTable();
		// set salesman to current salesman
		StaticProgramTables.sale.setSalesman(getSelectedSalesman());

		// update view
		clearSaleTable();
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
	protected static int getSalesmanIDFromSelected(){
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
	
	/**
	 * returns selected salesman
	 * @return
	 */
	public static EmployeesTableItem getSelectedSalesman(){
		return StaticProgramTables.employees.getEmployee(getSalesmanIDFromSelected());
	}
	
	// adding and removing sale items
	/////////////////////////////////
	
	public static void updateSaleTableView(){
		int totalSalePrice = 0;
		// first remove all items
		Main.getSaleTableSaleItems().removeAll();
		
		// then insert all items again (updated)
		for(SaleTableItem saleItem: StaticProgramTables.sale.getSaleItems().values()){
			totalSalePrice += saleItem.getTotalPerItem();
			TableItem item = new TableItem(Main.getSaleTableSaleItems(),SWT.NONE);
			String[] entry = new String[]{
					Long.toString(saleItem.getAlbumID()),
					saleItem.getAlbumName(),
					Integer.toString(saleItem.getQuantity()),
					Integer.toString(saleItem.getPricePerItem()),
					Integer.toString(saleItem.getTotalPerItem())
			};
			item.setText(entry);
		}
		
		// update total sale price
		Main.getSaleLabelTotalPriceValue().setText(Integer.toString(totalSalePrice));
		
		// set buttons
		Main.getSaleButtonRemoveItem().setEnabled(false);
		if (StaticProgramTables.sale.getSaleItems().isEmpty()){ // if sale table is empty, disable make sale
			Main.getSaleButtonMakeSale().setEnabled(false);
		} else {
			Main.getSaleButtonMakeSale().setEnabled(true);
		}
	}
	
	/**
	 * adds new sale item to sale table
	 * or updates quantity if sale item already exists
	 * INVOKED ONLY IN  - SEARCH TAB \ ADD TO SALE -  BUTTON
	 * (that's where it gets all the details)
	 */
	public static void addItemToSale(long albumID, String albumName, int quantity, int price){
		// check if sale item already exists
		SaleTableItem saleItem = StaticProgramTables.sale.getSaleItem(albumID);
		
		if (saleItem == null){ // if sale item doesn't exist
			// create new sale item and add to current sale
			saleItem = new SaleTableItem(albumID,albumName,quantity,price);
			StaticProgramTables.sale.addSaleItem(saleItem);
		} else {
			// set new quantity, price and total price
			saleItem.setQuantity(saleItem.getQuantity()+quantity);
		}
		
		// update gui view
		updateSaleTableView();
	}
}
