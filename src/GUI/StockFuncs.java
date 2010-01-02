package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import Tables.OrdersOrRequestsTable;
import Tables.OrdersOrRequestsTableItem;

/**
 * created by Ariel
 * 
 * Stock tab handlers
 */
public class StockFuncs {

	//////////////////////////
	//	Order form handlers //
	//////////////////////////
	
	public static void updateOrderAvailableStoresTable(){
		//TODO
		// implement order available stores gui update
	}
	
	//////////////////////
	//	Orders handlers //
	//////////////////////
	
	/**
	 * updates current orders table view according to current orders table
	 */
	public static void updateOrdersTableView(){
		// first remove all orders table items
		Main.getStockTableOrders().removeAll();
		
		// then insert all items
		for(OrdersOrRequestsTableItem order: StaticProgramTables.orders.getOrders().values()){
			TableItem item = new TableItem(Main.getStockTableOrders(), SWT.NONE);
			String[] entry = new String[]{
					Integer.toString(order.getOrderID()),
					Integer.toString(order.getSupplyingStoreID()),
					Long.toString(order.getAlbumID()),
					Integer.toString(order.getQuantity()),
					order.getDate(),
					order.getStatus().toString()
			};
			item.setText(entry);
		}
	}
	
	/**
	 * update current orders table to given one
	 * @param orders
	 */
	public static void setCurrentOrders(OrdersOrRequestsTable orders){
		StaticProgramTables.orders = orders;
	}
	
	////////////////////////
	//	Requests handlers //
	////////////////////////
	
	/**
	 * update requests table view according to current requests table
	 */
	public static void updateRequestsTableView(){
		// first remove all requests table items
		Main.getStockTableRequests().removeAll();
		
		// then insert all items
		for(OrdersOrRequestsTableItem request: StaticProgramTables.requests.getOrders().values()){
			TableItem item = new TableItem(Main.getStockTableRequests(), SWT.NONE);
			String[] entry = new String[]{
					Integer.toString(request.getOrderID()),
					Integer.toString(request.getOrderingStoreID()),
					Long.toString(request.getAlbumID()),
					Integer.toString(request.getQuantity()),
					request.getDate(),
					request.getStatus().toString()
			};
			item.setText(entry);
		}
	}
	
	/**
	 * update current requests table to given one
	 * @param requests
	 */
	public static void setCurrentRequests(OrdersOrRequestsTable requests){
		StaticProgramTables.requests = requests;
	}
}
