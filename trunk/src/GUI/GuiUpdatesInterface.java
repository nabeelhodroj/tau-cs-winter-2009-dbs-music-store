package GUI;

import Tables.*;
import Queries.*;

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
		//TODO
		// implement stores table gui update - user store selection window (combo box)
	}

	////////////////
	// Search tab //
	////////////////
	
	// all update functions are taken from GUI.SearchFuncs
	
	/**
	 * updates the albums search results table by the given AlbumsResultsTable
	 * @param albumResults
	 */
	public static void updateAlbumResultsTable(AlbumsResultsTable albumResults){
		//TODO
		// implement albums results table gui update
	}
	
	//////////////
	// Sale tab //
	//////////////
	
	/**
	 * clears the sale table (invoked after gui makes current sale) 
	 */
	public static void clearSaleTable(){
		//TODO
		// implement sale table gui clear
	}
	
	///////////////
	// Stock tab //
	///////////////
	
	// order's available stores
	
	/**
	 * updates the order's available stores table by the given availableStores
	 * @param availableStores
	 */
	public static void updateOrderAvailableStores(OrderAvailableStoresTable availableStores){
		//TODO
		// implement order available stores gui update
	}
	
	// orders table
	
	/**
	 * initialize the store's orders table
	 * @param orders
	 */
	public static void initOrdersTable(OrdersOrRequestsTable orders){
		//TODO
		// implement orders table gui initialization
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
		//TODO
		// implement requests table gui initialization
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
	
	/**
	 * update request in requests table to given status. request in requests table will always appear:
	 * - waiting
	 * @param orderID
	 * @param status
	 */
	public static void updateRequestStatus(int requestID, OrderStatusEnum status){
		//TODO
		// implement request status update and gui update
	}
	
	/////////////////
	//	management //
	/////////////////
	
	/**
	 * initialize employees table and employees list on sale tab
	 * @param employees
	 */
	public static void initEmployeesTable(EmployeesTable employees){
		//TODO
		// implement initialize employees table and available employees list on sale tab
	}
	
	/**
	 * adds employee to employee table if not a member, or updates employee details if already exists
	 * @param employee
	 */
	public static void addUpdateEmployee(EmployeesTableItem employee){
		//TODO
		// implement update employee details in employee table and update gui
	}
	
	/**
	 * removes employee with given employee-id from employees table
	 * @param employeeID
	 */
	public static void removeEmployee(int employeeID){
		//TODO
		// implement remove employee from  employees table
	}
}
