package GUI;

import Tables.*;
import General.Debug;
import General.Debug.DebugOutput;
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
	public static void updateAlbumResultsTable(final AlbumsResultsTable albumResults){
		Debug.log("GuiUpdatesInterface: updateAlbumResultsTable is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				// update albums results table
				SearchFuncs.updateAlbumsResultsTable(albumResults);
				// set gui environment
				SearchFuncs.setEnvSearchDone();
				
				}
			});
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
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				// initialize new sale table and clear gui view
				SaleFuncs.initCurrentSale();
				
				}
			});
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
	public static void updateOrderAvailableStores(final OrderAvailableStoresTable availableStores){
		Debug.log("GuiUpdatesInterface: updateOrderAvailableStores is invoked", DebugOutput.FILE, DebugOutput.STDOUT);

		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				StaticProgramTables.availableStores = availableStores;
				StockFuncs.updateOrderAvailableStoresTable();
				
				}
			});
	}
	
	// joint actions for orders table and requests table
	
	/**
	 * in case of a collision in the DB when trying to process an order / request
	 * but its status was changed by other store, deny action and update view
	 * @param actionTried: action that the user tried to invoke
	 * @param actionTaken: action that the other store already did
	 * @param orderID: the relevant order id
	 * 
	 * possible collisions:
	 * - this store: cancel order		- succeeded other store: deny request
	 * - this store: cancel order		- succeeded other store: approve request
	 * - this store: deny request		- succeeded other store: cancel order
	 * - this store: approve request	- succeeded other store: cancel order
	 */
	public static void denyOrdersOrRequestsTableAction(final OrdersRequestsActionsEnum actionTried,
			final OrdersRequestsActionsEnum actionTaken, final int orderID){
		Debug.log("GuiUpdatesInterface: denyOrdersOrRequestsTableAction is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				StockFuncs.denyOrdersOrRequestsTableAction(actionTried,actionTaken,orderID);
				
				}
			});
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
	public static void removeOrder(final int orderID){
		Debug.log("GuiUpdatesInterface: removeOrder is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				//TODO
				// implement removal of given order from orders table and update gui
				
				}
			});
	}
	
	/**
	 * adds order to orders table
	 * @param orderID
	 */
	public static void addOrder(final OrdersOrRequestsTableItem order){
		Debug.log("GuiUpdatesInterface: addOrder is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				StockFuncs.addOrder(order);
				
				}
			});
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
	public static void updateOrderStatus(final int orderID, final OrderStatusEnum status){
		Debug.log("GuiUpdatesInterface: updateOrderStatus is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				StockFuncs.updateOrderRequestStatus(orderID,status);
				
				}
			});
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
	public static void removeRequest(final int requestID){
		Debug.log("GuiUpdatesInterface: removeRequest is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				//TODO
				// implement removal of given request from requests table and update gui
				
				}
			});
	}
	
	/**
	 * adds request to requests table
	 * @param requestID
	 */
	public static void addRequest(final OrdersOrRequestsTableItem request){
		Debug.log("GuiUpdatesInterface: addRequest is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				//TODO
				// implement adding a request to the requests table and update gui
				
				}
			});
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
	public static void tryInsertNewEmployee(final int employeeID, final boolean exists){
		Debug.log("GuiUpdatesInterface: getDoesEmployeeExist is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				// try to insert employee
				ManageFuncs.tryInsert(exists, employeeID);
				
				}
			});
	}
	
	/**
	 * adds employee to employees table if not a member, or updates employee details if already exists
	 * @param employee
	 */
	public static void insertUpdateEmployee(final EmployeesTableItem employee){
		Debug.log("GuiUpdatesInterface: insertUpdateEmployee is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				// inserts employee / update its details and update view
				ManageFuncs.insertUpdateEmployee(employee);
				
				}
			});
	}
	
	/**
	 * removes employee with given employee-id from employees table
	 * @param employeeID
	 */
	public static void removeEmployee(final int employeeID){
		Debug.log("GuiUpdatesInterface: removeEmployee is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				// remove employee from  employees table in gui and update view
				ManageFuncs.removeEmployee(employeeID);
				
				}
			});
	}
	
	// database update
	
	/**
	 * notifies the gui that the given filename was added to the database
	 * @param filename
	 */
	public static void notifyDataBaseUpdated(final String filename){
		Debug.log("GuiUpdatesInterface: notifyDataBaseUpdated is invoked", DebugOutput.FILE, DebugOutput.STDOUT);
		
		Main.getMainDisplay().asyncExec(new Runnable() {
			public void run() {
				
				ManageFuncs.updateComplete(filename);
				
				}
			});
	}
}
