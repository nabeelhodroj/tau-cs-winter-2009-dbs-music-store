package Tables;

import GUI.*;
import java.util.*;

/**
 * created by Ariel
 * 
 * holds examples of tables classes, for debugging
 */
public class TablesExamples {
	
	public static AlbumsResultsTable albumsResultsTableExample = new AlbumsResultsTable();
	public static StoresTable storesTableExample = new StoresTable();
	public static OrdersOrRequestsTable ordersTableExample = new OrdersOrRequestsTable(true);
	public static OrdersOrRequestsTable requestsTableExample = new OrdersOrRequestsTable(false);
	public static List<SaleTable> sales = new ArrayList<SaleTable>();
	public static OrderAvailableStoresTable orderAvailableStoresExample = new OrderAvailableStoresTable();
	public static EmployeesTable employees = new EmployeesTable(0);
	
	/**
	 * initialize tables examples
	 */
	public static void initTablesExamples(){
		
		// albums and songs results table example
		
		SongsResultsTable songs1 = new SongsResultsTable(1);
		songs1.addSong(1, "You are my Sima", "Shimon", 180);
		songs1.addSong(2,"Here in Ashdod", "Shimon", 200);
		
		AlbumsResultsTableItem albumsResultsItem1 = new AlbumsResultsTableItem(1, "Shimon Comes Live",
				"Shimon", 1998, "Rock", 1800, songs1, 60, 17, 30);
		
		SongsResultsTable songs2 = new SongsResultsTable(2);
		songs2.addSong(1, "You are my Sima", "Shimon", 180);
		songs2.addSong(2,"Who is your Sami", "Shimon feat. Sami", 200);
		songs2.addSong(3,"Who is your Susu", "Shimon feat. Susu", 150);
		
		AlbumsResultsTableItem albumsResultsItem2 = new AlbumsResultsTableItem(2, "Shimon and Friends",
				"Various", 1995, "Rock", 2000, songs2, 49, 19, 56);
		
		albumsResultsTableExample.addAlbum(albumsResultsItem1);
		albumsResultsTableExample.addAlbum(albumsResultsItem2);
		
		// stores table example
		
		StoresTableItem store1 = new StoresTableItem(0,"Tel-Aviv","Ben Yehuda 2","03-67890123",12345678);
		StoresTableItem store2 = new StoresTableItem(1,"Jerusalem","King George 4","02-56789012",87654321);
		StoresTableItem store3 = new StoresTableItem(2,"Haifa","Carmel 8","04-45678901",19283746);
		
		storesTableExample.addStore(store1);
		storesTableExample.addStore(store2);
		storesTableExample.addStore(store3);
		
		// order's available stores example
		
		OrderAvailableStoresTableItem availableStore1 = new OrderAvailableStoresTableItem(store2.getStoreID(),
				store2.getCity(),3, 55);
		OrderAvailableStoresTableItem availableStore2 = new OrderAvailableStoresTableItem(store3.getStoreID(),
				store3.getCity(),2, 59);
		
		orderAvailableStoresExample.addStore(availableStore1);
		orderAvailableStoresExample.addStore(availableStore2);
		
		// orders table example
		
		OrdersOrRequestsTableItem order1 = new OrdersOrRequestsTableItem(1,store1.getStoreID(),
				store2.getStoreID(),albumsResultsItem1.getAlbumID(),2,"12/04/08",OrderStatusEnum.WAITING);
		OrdersOrRequestsTableItem order2 = new OrdersOrRequestsTableItem(2,store1.getStoreID(),
				store3.getStoreID(),albumsResultsItem2.getAlbumID(),19,"15/08/09",OrderStatusEnum.WAITING);
		OrdersOrRequestsTableItem order3 = new OrdersOrRequestsTableItem(3,store1.getStoreID(),
				store2.getStoreID(),albumsResultsItem1.getAlbumID(),5,"30/01/09",OrderStatusEnum.COMPLETED);
		
		ordersTableExample.addOrder(order1);
		ordersTableExample.addOrder(order2);
		ordersTableExample.addOrder(order3);
		
		// requests table example
		
		OrdersOrRequestsTableItem request1 = new OrdersOrRequestsTableItem(4,store2.getStoreID(),
				store1.getStoreID(),albumsResultsItem1.getAlbumID(),4,"01/03/09",OrderStatusEnum.WAITING);
		OrdersOrRequestsTableItem request2 = new OrdersOrRequestsTableItem(5,store3.getStoreID(),
				store1.getStoreID(),albumsResultsItem2.getAlbumID(),9,"23/10/09",OrderStatusEnum.WAITING);
		OrdersOrRequestsTableItem request3 = new OrdersOrRequestsTableItem(6,store2.getStoreID(),
				store1.getStoreID(),albumsResultsItem2.getAlbumID(),2,"11/11/09",OrderStatusEnum.WAITING);
		
		requestsTableExample.addOrder(request1);
		requestsTableExample.addOrder(request2);
		requestsTableExample.addOrder(request3);
		
		// employees table example
		
		EmployeesTableItem employee1 = new EmployeesTableItem(123456789, "Ariel", "Stolerman",
				"01/01/2010", "14/08/1984", "123 Ben-Yehuda St., Givatayim", "03-1234567", "054-5551234", 0,
				EmployeePositionsEnum.NETWORK_MANAGER);
		EmployeesTableItem employee2 = new EmployeesTableItem(987654253, "Kalev", "Alpernas",
				"01/10/2009", "07/08/1986", "456 King George St., Ashdod", "08-9887263", "054-5556789", 0,
				EmployeePositionsEnum.MANAGER);
		EmployeesTableItem employee3 = new EmployeesTableItem(928347462, "Vadim", "Stotland",
				"03/01/2010", "04/09/1986", "789 Sami St., Petah-Tikva", "03-9181716", "054-5550099", 0,
				EmployeePositionsEnum.ASSIST_MANAGER);
		EmployeesTableItem employee4 = new EmployeesTableItem(199292919, "Rotem", "Druker",
				"01/01/2008", "13/07/1983", "91 Ben-Yehuda St., Tel-Aviv", "03-3456243", "054-5552626", 0,
				EmployeePositionsEnum.SALESMAN);
		
		employees.addEmployee(employee1);
		employees.addEmployee(employee2);
		employees.addEmployee(employee3);
		employees.addEmployee(employee4);
	}
	
	/**
	 * invoke GUI albums search results update with example
	 */
	public static void getAlbumsSearchResults(){
		try {
			// simulate DB action
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// sleep didn't work
			System.err.println("*** BUG: Sleep didn't work...");
		}
		GuiUpdatesInterface.updateAlbumResultsTable(albumsResultsTableExample);
	}
	
	/**
	 * invoke GUI stores table initialize with example
	 */
	public static void initStoresTable(){
		GuiUpdatesInterface.initStoresTable(storesTableExample);
	}
	
	/**
	 * invoke make sale as example
	 * real method should insert given sale to data base
	 * @param sale
	 */
	public static void makeSale(SaleTable sale){
		sales.add(sale);
		GuiUpdatesInterface.initSaleTable();
	}
	
	/**
	 * invoke GUI order's available stores update with example
	 * @param albumID
	 */
	public static void getOrderAvailableStores(long albumID){
		GuiUpdatesInterface.updateOrderAvailableStores(orderAvailableStoresExample);
	}
	
	/**
	 * invoke orders table initialize with example
	 */
	public static void getOrdersTable(){
		GuiUpdatesInterface.initOrdersTable(ordersTableExample);
	}
	
	/**
	 * invoke remove order from orders table in gui
	 * @param orderID
	 */
	public static void removeOrder(int orderID){
		GuiUpdatesInterface.removeOrder(orderID);
	}
	
	/**
	 * invoke add order to orders table
	 * @param order
	 */
	public static void placeOrder(OrdersOrRequestsTableItem order){
		GuiUpdatesInterface.addOrder(order);
	}
	
	public static void updateOrderStatus(int orderID, OrderStatusEnum status){
		// TODO
	}
	
	/**
	 * invoke requests table initialize with example
	 */
	public static void getRequestsTable(){
		GuiUpdatesInterface.initRequestsTable(requestsTableExample);
	}
	
	public static void removeRequest(int orderID){
		//TODO
	}
	
	public static void addRequest(OrdersOrRequestsTableItem request){
		//TODO
	}
	
	// Management tab
	
	/**
	 * invoke employees table initialize with example
	 */
	public static void getEmployeesTable(){
		GuiUpdatesInterface.initEmployeesTable(employees);
	}

	/**
	 * check if employee exists in network
	 * this example returns "false" always, as if the employee doesn't exist in DB
	 * @param employeeID
	 */
	public static void checkIfEmployeeExists(int employeeID){
		GuiUpdatesInterface.tryInsertNewEmployee(employeeID, false);
	}
	
	/**
	 * inserts employee to "DB" if new, or updates details if employee exists
	 * @param employee
	 */
	public static void insertUpdateEmployee(EmployeesTableItem employee){
		if (employees.getEmployee(employee.getEmployeeID()) != null) // employee exists, remove it
			employees.getEmployees().remove(employee.getEmployeeID());
		// now insert new one
		employees.addEmployee(employee);
		
		// update store's employees list
		GuiUpdatesInterface.insertUpdateEmployee(employee);
	}
	
	/**
	 * remove employee from gui
	 * @param employeeID
	 */
	public static void removeEmployee(int employeeID){
		GuiUpdatesInterface.removeEmployee(employeeID);
	}
	
	/**
	 * notify gui that update is complete
	 * @param filename
	 */
	public static void updateDataBase(String filename){
		try {
			// simulate DB action
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// sleep didn't work
			System.err.println("*** BUG: Sleep didn't work...");
		}
		GuiUpdatesInterface.notifyDataBaseUpdated(filename);
	}
}
