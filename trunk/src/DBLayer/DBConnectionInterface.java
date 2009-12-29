package DBLayer;

import Tables.*;
import Queries.*;

/**
 * created by Ariel
 * *** will be updated by Kalev and Vadim ***
 * 
 * DB Connection Interface
 * =======================
 * all functions used by gui to pass queries / transactions to the DB layer
 * for each method, the corresponding method in GuiUpdatesInterface, the one that
 * should be called when finishing, is mentioned in the javadoc
 * 
 * *** System out prints are for debugging ***
 * 
 * ///////////////////////////////////////////////
 * //											//
 * //	to be implemented by Kalev and Vadim	//
 * //											//
 * ///////////////////////////////////////////////
 * 
 */
public class DBConnectionInterface{
	
	//////////
	// Main //
	//////////
	
	/**
	 * invoked automatically when program starts
	 * invokes query to get initial stores table, for selection of current store
	 * corresponding method in GUI.GuiUpdatesInterface: initStoresTable
	 */
	public static void getStoresTable(){
		System.out.println("DBConnectionInterface.getStoresTable is called");
		//TODO
	}
	
	////////////////
	// Search tab //
	////////////////
	
	/**
	 * invoked in Main \ search-tab \ "Search" button
	 * invokes albums search query
	 * corresponding method in GUI.GuiUpdatesInterface: updateAlbumResultsTable
	 */
	public static void getAlbumsSearchResults(){
		System.out.println("DBConnectionInterface.getAlbumsSearchResults is called");
		//TODO
	}
	
	//////////////
	// Sale tab //
	//////////////
	
	/**
	 * invoked in Main \ sale-tab \ "Make Sale" button
	 * invokes sale creation and adds to sales table
	 * corresponding method in GUI.GuiUpdatesInterface: clearSaleTable
	 */
	public static void makeSale(){
		System.out.println("DBConnectionInterface.makeSale is called");
		//TODO
	}

	///////////////
	// Stock tab //
	///////////////
	
	// order's available stores
	
	/**
	 * invoked in Main \ stock-tab \ "Check Availability" button
	 * invokes order's available stores query
	 * corresponding method in GUI.GuiUpdatesInterface: updateOrderAvailableStores
	 * @param albumID
	 */
	public static void getOrderAvailableStores(long albumID){
		System.out.println("DBConnectionInterface.getOrderAvailableStores is called");
		//TODO
	}
	
	// orders table
	
	/**
	 * invoked in Startup \ "OK"	// yet to be implemented - when program starts, after choosing the
	 * 								// current store-id, "OK" is selected to initialize data view
	 * invokes query to get initial orders table for selected store
	 * corresponding method in GUI.GuiUpdatesInterface: initOrdersTable
	 */
	public static void getOrdersTable(){
		System.out.println("DBConnectionInterface.getOrdersTable is called");
		//TODO
	}
	
	/**
	 * invoked in Main \ stock-tab \ "Remove Order" button
	 * removes order with given order-id from orders table
	 * corresponding method in GUI.GuiUpdatesInterface: removeOrder
	 * @param orderID
	 */
	public static void removeOrder(int orderID){
		System.out.println("DBConnectionInterface.removeOrder is called");
		//TODO
	}
	
	/**
	 * invoked in Main \ stock-tab \ "Place Order" button
	 * invokes add order to orders table
	 * corresponding method in GUI.GuiUpdatesInterface: addOrder
	 */
	public static void placeOrder(){
		System.out.println("DBConnectionInterface.placeOrder is called");
		//TODO
	}
	
	/**
	 * invoked in Main \ stock-tab \
	 * 		- "Place Order" button by current store -> waiting
	 *  	- "Cancel Order" button by current store -> canceled
	 * 		- "Deny Request" button by requested store -> denied
	 * 		- "Approve Request" button by requested store -> completed
	 * update order in orders table to given status. order in orders table will always appear:
	 * - waiting
	 * - denied
	 * - completed
	 * - canceled (by requester)
	 * corresponding method in GUI.GuiUpdatesInterface: updateOrderStatus
	 * @param orderID
	 * @param status
	 */
	public static void updateOrderStatus(int orderID, OrderStatusEnum status){
		System.out.println("DBConnectionInterface.updateOrderStatus is called");
		//TODO
	}
	
	// requests table
	
	/**
	 * invoked in Startup \ "OK"	// yet to be implemented - when program starts, after choosing the
	 * 								// current store-id, "OK" is selected to initialize data view
	 * invokes query to get initial requests table for selected store
	 * corresponding method in GUI.GuiUpdatesInterface: initRequestsTable
	 */
	public static void getRequestsTable(){
		System.out.println("DBConnectionInterface.getRequestsTable is called");
		//TODO
	}
	
	/**
	 * invoked in Main \ stock-tab \
	 * 		- "Deny Request" button by current store
	 * 		- "Approve Request" button by current store
	 * 		- "Cancel Order" button by requesting store
	 * removes request with given order-id from requests table
	 * corresponding method in GUI.GuiUpdatesInterface: removeRequest
	 * @param orderID
	 */
	public static void removeRequest(int orderID){
		System.out.println("DBConnectionInterface.removeRequest is called");
		//TODO
	}
	
	/**
	 * invoked in Main \ stock-tab \ "Place Order" button by requesting store
	 * adds request to requests table
	 * corresponding method in GUI.GuiUpdatesInterface: addRequest
	 * @param requestID
	 */
	public static void addRequest(OrdersOrRequestsTableItem request){
		System.out.println("DBConnectionInterface.addRequest is called");
		//TODO
	}

	////////////////////
	// Management tab //
	////////////////////
	
	// employees table
	
	/**
	 * invoked in Startup \ "OK"	// yet to be implemented - when program starts, after choosing the
	 * 								// current store-id, "OK" is selected to initialize data view
	 * invokes query to get initial employees table for selected store
	 * corresponding method in GUI.GuiUpdatesInterface: initEmployeesTable
	 */
	public static void getEmployeesTable(){
		System.out.println("DBConnectionInterface.getEmployeesTable is called");
		//TODO
	}
	
	/**
	 * invoked in Main \ management-tab \
	 * 		- "Insert" button
	 * 		- "Save" button
	 * adds employee to employees table if not a member, or updates employee details if already exists
	 * corresponding method in GUI.GuiUpdatesInterface: insertUpdateEmployee
	 * @param employee
	 */
	public static void insertUpdateEmployee(EmployeesTableItem employee){
		System.out.println("DBConnectionInterface.insertUpdateEmployee is called");
		//TODO
	}
	
	/**
	 * invoked in Main \ management-tab \ "Remove Employee" button
	 * removes employee with given employee-id from employees table
	 * corresponding method in GUI.GuiUpdatesInterface: removeEmployee 
	 * @param employeeID
	 */
	public static void removeEmployee(int employeeID){
		System.out.println("DBConnectionInterface.removeEmployee is called");
		//TODO
	}
	
	// database update
	
	/**
	 * invoked in Main \ management-tab \ "Update Database" button
	 * invokes database update from given filename
	 * corresponding method in GUI.GuiUpdatesInterface: notifyDataBaseUpdated
	 * @param filename
	 */
	public static void updateDataBase(String filename){
		System.out.println("DBConnectionInterface.updateDataBase is called");
		//TODO
	}
}