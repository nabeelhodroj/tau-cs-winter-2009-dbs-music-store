package GUI;

import Tables.*;
import Queries.*;

/**
 * created by Ariel
 * 
 * this class holds all the current (static) program's tables:
 * - stores table
 * - album search results table
 * - current sale table
 * - order available stores table
 * - orders table
 * - requests table
 * - employees table
 */
public class StaticProgramTables {
	// this store
	public static StoresTableItem thisStore = null;
	// stores table (for program initialization)
	public static StoresTable stores = null;
	// albums search results table
	public static AlbumsResultsTable results = null;
	// current sale table
	public static SaleTable sale = null;
	// order available stores table
	public static OrderAvailableStoresTable availableStores = new OrderAvailableStoresTable();
	// orders table
	public static OrdersOrRequestsTable orders = null;
	// requests table
	public static OrdersOrRequestsTable requests = null;
	// employees table
	public static EmployeesTable employees = null;
	
	// getters and setters
	
	public static StoresTableItem getThisStore() {
		return thisStore;
	}
	public static void setThisStore(StoresTableItem thisStore) {
		StaticProgramTables.thisStore = thisStore;
	}
}
