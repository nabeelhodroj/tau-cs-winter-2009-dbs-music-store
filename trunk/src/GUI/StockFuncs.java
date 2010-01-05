package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import Debug.Debug;
import Debug.Debug.DebugOutput;
import Tables.OrdersOrRequestsTable;
import Tables.OrdersOrRequestsTableItem;

/**
 * created by Ariel
 * 
 * Stock tab handlers
 */
public class StockFuncs {
	
	/**
	 * initialize stock tab view
	 */
	public static void initStockTabView(){
		// disable (almost) all buttons
		Main.getStockButtonCheckAvailability().setEnabled(false);
		Main.getStockButtonPlaceOrder().setEnabled(false);
		Main.getStockButtonRemoveOrder().setEnabled(false);
		Main.getStockButtonCancelOrder().setEnabled(false);
		Main.getStockButtonApproveRequest().setEnabled(false);
		Main.getStockButtonDenyRequest().setEnabled(false);
	}
	
	/**
	 * initialize stock tab listeners
	 */
	public static void initStockTabListeners(){
		// order form listeners
		///////////////////////
		
		// check availability button listener
		Main.getStockButtonCheckAvailability().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: check availability button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// available stores table listener
		Main.getStockTableOrderAvailableStores().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: available stores table item selected",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// clear fields button listener
		Main.getStockButtonClearOrder().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: clear fields button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// place order button listener
		Main.getStockButtonCheckAvailability().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: place order button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// orders
		/////////
		
		// orders table listener
		Main.getStockTableOrders().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: orders table item selected",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// cancel order button listener
		Main.getStockButtonCancelOrder().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: cancel order button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// remove order button listener
		Main.getStockButtonRemoveOrder().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: remove order button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// requests
		///////////
		
		// requests table listener
		Main.getStockTableRequests().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: requests table item selected",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// deny request button listener
		Main.getStockButtonDenyRequest().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: deny request button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
		
		// approve request button listener
		Main.getStockButtonApproveRequest().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: approve request button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						//TODO
					}
				}
		);
	}

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
