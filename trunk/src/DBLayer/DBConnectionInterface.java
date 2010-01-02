package DBLayer;

import Tables.*;
import Queries.*;
import Debug.*;
import Debug.Debug.DebugOutput;

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
	public static void initStoresTable(){
		Debug.log("DBConnectionInterface.getStoresTable is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.initStoresTable();
	}
	
	////////////////
	// Search tab //
	////////////////
	
	/**
	 * invoked in Main \ search-tab \ "Search" button
	 * invokes albums search query
	 * corresponding method in GUI.GuiUpdatesInterface: updateAlbumResultsTable
	 */
	public static void getAlbumsSearchResults(AlbumSearchQuery albumSearchQuery){
		Debug.log("DBConnectionInterface.getAlbumsSearchResults is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO

		// until implemented, use example:
		TablesExamples.getAlbumsSearchResults();
	}
	
	//////////////
	// Sale tab //
	//////////////
	
	/**
	 * invoked in Main \ sale-tab \ "Make Sale" button
	 * invokes sale creation and adds to sales table
	 * corresponding method in GUI.GuiUpdatesInterface: clearSaleTable
	 */
	public static void makeSale(SaleTable sale){
		Debug.log("DBConnectionInterface.makeSale is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.makeSale(sale);
	}
	
	/**
	 * invoked in Main \ search-tab \ "Add to Sale" button
	 * add item to current sale table and updates quantity in DB
	 * corresponding method in GUI.GuiUpdatesInterface: addItemToSale
	 * @param quantity
	 * @param albumID
	 */
	public static void addItemToSale(SaleTableItem saleItem){
		Debug.log("DBConnectionInterface.addItemToSale is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.addItemToSale(saleItem);
	}
	
	/**
	 * invoked in Main \ sale-tab \ "Remove Item" button
	 * removes item from current sale table and updates quantity in DB
	 * corresponding method in GUI.GuiUpdatesInterface: removeItemFromSale
	 * @param saleItem
	 */
	public static void removeItemFromSale(SaleTableItem saleItem){
		Debug.log("DBConnectionInterface.removeItemFromSale is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.removeItemFromSale(saleItem);
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
		Debug.log("DBConnectionInterface.getOrderAvailableStores is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.getOrderAvailableStores(albumID);
	}
	
	// orders table
	
	/**
	 * invoked in InitialDialog \ "Start" - when program starts, after choosing the
	 * current store-id, "Start" is selected to initialize data view
	 * invokes query to get initial orders table for selected store
	 * corresponding method in GUI.GuiUpdatesInterface: initOrdersTable
	 */
	public static void getOrdersTable(){
		Debug.log("DBConnectionInterface.getOrdersTable is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.getOrdersTable();
	}
	
	/**
	 * invoked in Main \ stock-tab \ "Remove Order" button
	 * removes order with given order-id from orders table
	 * corresponding method in GUI.GuiUpdatesInterface: removeOrder
	 * @param orderID
	 */
	public static void removeOrder(int orderID){
		Debug.log("DBConnectionInterface.removeOrder is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.removeOrder(orderID);
	}
	
	/**
	 * invoked in Main \ stock-tab \ "Place Order" button
	 * invokes add order to orders table
	 * corresponding method in GUI.GuiUpdatesInterface: addOrder
	 */
	public static void placeOrder(OrdersOrRequestsTableItem order){
		Debug.log("DBConnectionInterface.placeOrder is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.placeOrder(order);
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
		Debug.log("DBConnectionInterface.updateOrderStatus is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.updateOrderStatus(orderID, status);
	}
	
	// requests table
	
	/**
	 * invoked in InitialDialog \ "Start" - when program starts, after choosing the
	 * current store-id, "Start" is selected to initialize data view
	 * invokes query to get initial requests table for selected store
	 * corresponding method in GUI.GuiUpdatesInterface: initRequestsTable
	 */
	public static void getRequestsTable(){
		Debug.log("DBConnectionInterface.getRequestsTable is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.getRequestsTable();
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
		Debug.log("DBConnectionInterface.removeRequest is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.removeRequest(orderID);
	}
	
	/**
	 * invoked in Main \ stock-tab \ "Place Order" button by requesting store
	 * adds request to requests table
	 * corresponding method in GUI.GuiUpdatesInterface: addRequest
	 * @param requestID
	 */
	public static void addRequest(OrdersOrRequestsTableItem request){
		Debug.log("DBConnectionInterface.addRequest is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.addRequest(request);
	}

	////////////////////
	// Management tab //
	////////////////////
	
	// employees table
	
	/**
	 * invoked in InitialDialog \ "Start" - when program starts, after choosing the
	 * current store-id, "Start" is selected to initialize data view
	 * invokes query to get initial employees table for selected store
	 * corresponding method in GUI.GuiUpdatesInterface: initEmployeesTable
	 */
	public static void getEmployeesTable(){
		Debug.log("DBConnectionInterface.getEmployeesTable is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.getEmployeesTable();
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
		Debug.log("DBConnectionInterface.insertUpdateEmployee is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.insertUpdateEmployee(employee);
	}
	
	/**
	 * invoked in Main \ management-tab \ "Remove Employee" button
	 * removes employee with given employee-id from employees table
	 * corresponding method in GUI.GuiUpdatesInterface: removeEmployee 
	 * @param employeeID
	 */
	public static void removeEmployee(int employeeID){
		Debug.log("DBConnectionInterface.removeEmployee is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.removeEmployee(employeeID);
	}
	
	// database update
	
	/**
	 * invoked in Main \ management-tab \ "Update Database" button
	 * invokes database update from given filename
	 * corresponding method in GUI.GuiUpdatesInterface: notifyDataBaseUpdated
	 * @param filename
	 */
	public static void updateDataBase(String filename){
		Debug.log("DBConnectionInterface.updateDataBase is called",DebugOutput.FILE,DebugOutput.STDOUT);
		//TODO
		
		// until implemented, use example:
		TablesExamples.updateDataBase(filename);
	}
}