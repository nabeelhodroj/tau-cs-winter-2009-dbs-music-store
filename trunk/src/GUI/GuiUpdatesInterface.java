package GUI;

import Tables.*;
import Debug.*;
import Debug.Debug.DebugOutput;

/**
 * created by Ariel
 * 
 * GUI updater interface
 * =====================
 * all functions used by lower level to pass data and update the GUI tables accordingly
 */
public class GuiUpdatesInterface {
	
	//////////
	// Main	//
	//////////
	
	/**
	 * initialize stores table (for choosing a store when program starts)
	 * @param stores
	 */
	public static void initStoresTable(StoresTable stores){
		Debug.log("GuiUpdatesInterface: initStoresTable is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		// update the stores list
		StaticProgramTables.stores = stores;
		// add stores to choice combobox in initial dialog is done when opening the initial dialog
		// in InitialDialog.open()
	}

	////////////////
	// Search tab //
	////////////////
	
	// all update functions are taken from GUI.SearchFuncs
	
	/**
	 * updates the albums search results table by the given AlbumsResultsTable
	 * and sets the search tab gui environment (enables search button)
	 * @param albumResults
	 */
	public static void updateAlbumResultsTable(AlbumsResultsTable albumResults){
		Debug.log("GuiUpdatesInterface: updateAlbumResultsTable is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		// update albums results table
		SearchFuncs.updateAlbumsResultsTable(albumResults);
		// set gui environment
		SearchFuncs.setEnvSearchDone();
	}
	
	//////////////
	// Sale tab //
	//////////////
	
	// all update functions are taken from GUI.SaleFuncs
	
	/**
	 * clears the sale table (invoked after gui makes current sale) 
	 */
	public static void initSaleTable(){
		Debug.log("GuiUpdatesInterface: clearSaleTable is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		// initialize new sale table and clear gui view
		SaleFuncs.initCurrentSale();
	}
	
	///////////////
	// Stock tab //
	///////////////
	
	// all update functions are taken from GUI.StockFuncs
	
	// order's available stores
	
	/**
	 * updates the order's available stores table by the given availableStores
	 * @param availableStores
	 */
	public static void updateOrderAvailableStores(OrderAvailableStoresTable availableStores){
		Debug.log("GuiUpdatesInterface: updateOrderAvailableStores is invoked", DebugOutput.FILE, DebugOutput.STDOUT);

		StaticProgramTables.availableStores = availableStores;
		StockFuncs.updateOrderAvailableStoresTable();
	}
	
	// orders table
	
	/**
	 * initialize the store's orders table
	 * @param orders
	 */
	public static void initOrdersTable(OrdersOrRequestsTable orders){
		Debug.log("GuiUpdatesInterface: initOrdersTable is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		StockFuncs.setCurrentOrders(orders);
		// gui initialization will be done in Main
	}
	
	/**
	 * removes order with given order-id from orders table
	 * @param orderID
	 */
	public static void removeOrder(int orderID){
		//TODO
		// implement removal of given order from orders table and update gui
	}
	
	/**
	 * adds order to orders table
	 * @param orderID
	 */
	public static void addOrder(OrdersOrRequestsTableItem order){
		//TODO
		// implement adding an order to the orders table and update gui
	}
	
	/**
	 * update order in orders table to given status. order in orders table will always appear:
	 * - waiting
	 * - denied
	 * - completed
	 * - canceled
	 * @param orderID
	 * @param status
	 */
	public static void updateOrderStatus(int orderID, OrderStatusEnum status){
		//TODO
		// implement order status update and gui update
	}
	
	// requests table
	
	/**
	 * initialize the store's requests table
	 * @param requests
	 */
	public static void initRequestsTable(OrdersOrRequestsTable requests){
		Debug.log("GuiUpdatesInterface: initRequestsTable is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		StockFuncs.setCurrentRequests(requests);
		// gui initialization will be done in Main
	}
	
	/**
	 * removes request with given request-id (order-id in database) from requests table
	 * @param orderID
	 */
	public static void removeRequest(int requestID){
		//TODO
		// implement removal of given request from requests table and update gui
	}
	
	/**
	 * adds request to requests table
	 * @param requestID
	 */
	public static void addRequest(OrdersOrRequestsTableItem request){
		//TODO
		// implement adding a request to the requests table and update gui
	}
	
	/*
	 * no need for request status update method, since status in requests table
	 * always appears "waiting", otherwise it will not appear in the table.
	 * deny and approve request will update the order status in the DB orders table,
	 * and will update the status in the requesting store's orders table.
	 */
	
	/////////////////
	//	management //
	/////////////////
	
	// all update functions are taken from GUI.ManageFuncs
	
	// employees table
	
	/**
	 * initialize employees table and employees list on sale tab
	 * @param employees
	 */
	public static void initEmployeesTable(EmployeesTable employees){
		Debug.log("GuiUpdatesInterface: initEmployeesTable is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		ManageFuncs.setCurrentEmployees(employees);
		// gui initialization will be done in Main:
		// - employees table
		// - employees list in sale tab
	}
	
	/**
	 * receives true iff employee exists in DB, and if so pops an error
	 * otherwise continues to new employee insertion 
	 * @param exists
	 */
	public static void tryInsertNewEmployee(int employeeID, boolean exists){
		Debug.log("GuiUpdatesInterface: getDoesEmployeeExist is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		// try to insert employee
		ManageFuncs.tryInsert(exists, employeeID);
	}
	
	/**
	 * adds employee to employees table if not a member, or updates employee details if already exists
	 * @param employee
	 */
	public static void insertUpdateEmployee(EmployeesTableItem employee){
		Debug.log("GuiUpdatesInterface: insertUpdateEmployee is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		// inserts employee / update its details and update view
		ManageFuncs.insertUpdateEmployee(employee);
	}
	
	/**
	 * removes employee with given employee-id from employees table
	 * @param employeeID
	 */
	public static void removeEmployee(int employeeID){
		Debug.log("GuiUpdatesInterface: removeEmployee is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		// remove employee from  employees table in gui and update view
		ManageFuncs.removeEmployee(employeeID);
	}
	
	// database update
	
	/**
	 * notifies the gui that the given filename was added to the database
	 * @param filename
	 */
	public static void notifyDataBaseUpdated(String filename){
		Debug.log("GuiUpdatesInterface: notifyDataBaseUpdated is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		ManageFuncs.updateComplete(filename);
	}
}
