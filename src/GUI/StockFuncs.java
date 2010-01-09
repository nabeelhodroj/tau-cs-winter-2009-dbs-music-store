package GUI;

import java.util.HashMap;

import oracle.net.aso.e;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import DBLayer.DBConnectionInterface;
import Queries.OrderAvailableStoresQuery;
import Queries.QueryErrorException;
import Tables.EmployeePositionsEnum;
import Tables.InvalidOrderException;
import Tables.OrderAvailableStoresTableItem;
import Tables.OrderStatusEnum;
import Tables.OrdersOrRequestsTable;
import Tables.OrdersOrRequestsTableItem;
import General.Debug;
import General.Debug.*;

/**
 * created by Ariel
 * 
 * Stock tab handlers
 */
public class StockFuncs {
	
	// order form fields
	private static long albumID;
	private static long storageLocation;
	private static int quantity;
	private static int price;
	
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
						
						try{
							// create query
							OrderAvailableStoresQuery query = new OrderAvailableStoresQuery();
							// send to DB
							
							// check if DB is not busy, else pop a message
							if (MainFuncs.isAllowDBAction()){
								// flag DB as busy
								MainFuncs.setAllowDBAction(false);
								
								DBConnectionInterface.getOrderAvailableStores(query);
								
							} else {
								MainFuncs.getMsgDBActionNotAllowed().open();
							}
							
						}catch(QueryErrorException qee){
							Debug.log("*** BUG: Stock tab \\ check availability query error", DebugOutput.FILE,DebugOutput.STDERR);
						}
					}
				}
		);
		
		// available stores table listener
		Main.getStockTableOrderAvailableStores().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: available stores table item selected",DebugOutput.FILE,DebugOutput.STDOUT);

						availableStoreSelected();
					}
				}
		);
		
		// clear fields button listener
		Main.getStockButtonClearOrder().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: clear fields button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						clearFieldsButtonInvokation();
					}
				}
		);
		
		// place order button listener
		Main.getStockButtonPlaceOrder().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: place order button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						try{							
							OrdersOrRequestsTableItem order = getOrder();
							// check if DB is not busy, else pop a message
							if (MainFuncs.isAllowDBAction()){
								// flag DB as busy
								MainFuncs.setAllowDBAction(false);
								
								DBConnectionInterface.placeOrder(order);
								
							} else {
								MainFuncs.getMsgDBActionNotAllowed().open();
							}
							
						}catch(InvalidOrderException ioe){
							ioe.getMsgBox().open();
						}
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

						ordersTableItemSelected();
					}
				}
		);

		// refresh orders button listener
		Main.getStockButtonRefreshOrders().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: refresh orders table button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						// check if DB is not busy, else pop a message
						if (MainFuncs.isAllowDBAction()){
							// flag DB as busy
							MainFuncs.setAllowDBAction(false);
							
							DBConnectionInterface.refreshOrdersTable();
							
						} else {
							MainFuncs.getMsgDBActionNotAllowed().open();
						}
					}
				}
		);

		// cancel order button listener
		Main.getStockButtonCancelOrder().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: cancel order button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						// check if DB is not busy, else pop a message
						if (MainFuncs.isAllowDBAction()){
							// flag DB as busy
							MainFuncs.setAllowDBAction(false);
							
							cancelOrderInvokation();
							
						} else {
							MainFuncs.getMsgDBActionNotAllowed().open();
						}
					}
				}
		);
		
		// remove order button listener
		Main.getStockButtonRemoveOrder().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: remove order button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						// check if DB is not busy, else pop a message
						if (MainFuncs.isAllowDBAction()){
							// flag DB as busy
							MainFuncs.setAllowDBAction(false);
							
							removeOrderInvokation();
							
						} else {
							MainFuncs.getMsgDBActionNotAllowed().open();
						}
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

						requestsTableItemSelected();
					}
				}
		);
		
		// refresh requests button listener
		Main.getStockButtonRefreshRequests().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: refresh requests table button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						// check if DB is not busy, else pop a message
						if (MainFuncs.isAllowDBAction()){
							// flag DB as busy
							MainFuncs.setAllowDBAction(false);
							
							DBConnectionInterface.refreshRequestsTable();
							
						} else {
							MainFuncs.getMsgDBActionNotAllowed().open();
						}
					}
				}
		);
		
		// deny request button listener
		Main.getStockButtonDenyRequest().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: deny request button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						// check if DB is not busy, else pop a message
						if (MainFuncs.isAllowDBAction()){
							// flag DB as busy
							MainFuncs.setAllowDBAction(false);
							
							denyRequestInvokation();
							
						} else {
							MainFuncs.getMsgDBActionNotAllowed().open();
						}
					}
				}
		);
		
		// approve request button listener
		Main.getStockButtonApproveRequest().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Stock tab: approve request button clicked",DebugOutput.FILE,DebugOutput.STDOUT);

						// check if DB is not busy, else pop a message
						if (MainFuncs.isAllowDBAction()){
							// flag DB as busy
							MainFuncs.setAllowDBAction(false);
							
							approveRequestInvokation();
							
						} else {
							MainFuncs.getMsgDBActionNotAllowed().open();
						}
					}
				}
		);
	}

	//////////////////////////
	//	Order form handlers //
	//////////////////////////
	
	/**
	 * updates order's available stores table view according to current table
	 * and releases DB
	 */
	public static void updateOrderAvailableStoresTable(){
		// update table results
		updateOrderAvailableStoresTableView();		
		// flag DB as free
		MainFuncs.setAllowDBAction(true);
	}
	
	/**
	 * updates order's available stores table view according to current table
	 */
	public static void updateOrderAvailableStoresTableView(){
		// first remove all orders table items
		Main.getStockTableOrderAvailableStores().removeAll();
		
		// then insert all items
		for(OrderAvailableStoresTableItem availableStore: StaticProgramTables.availableStores.getAvailableStores().values()){
			TableItem item = new TableItem(Main.getStockTableOrderAvailableStores(),SWT.NONE);
			String[] entry = new String[]{
					Integer.toString(availableStore.getStoreID()),
					availableStore.getCity(),
					Integer.toString(availableStore.getQuantity()),
			};
			item.setText(entry);
		}
	}
	
	// check availability button
	
	/*
	 * handled in listener
	 */
	
	// clear fields button
	
	/**
	 * clears order form fields and disables relevant buttons
	 */
	public static void clearFieldsButtonInvokation(){
		// clear table view
		Main.getStockTableOrderAvailableStores().removeAll();
		// disable buttons
		Main.getStockButtonCheckAvailability().setEnabled(false);
		Main.getStockButtonPlaceOrder().setEnabled(false);
		// clear fields
		setOrderFields("", "", "", "");
		// set quantity to default - 1
		Main.getStockTextBoxQuantityToOrder().setText("1");
	}
	
	/**
	 * set order form fields
	 * @param albumID
	 * @param storageLocation
	 * @param quantity
	 * @param price
	 */
	public static void setOrderFields(String albumID, String storageLocation, String quantity, String price){
		// album id
		Main.getStockLabelAlbumIDInput().setText(albumID);
		// storage location
		Main.getStockLabelStorageLocationInput().setText(storageLocation);
		// quantity
		Main.getStockLabelQuantityInStockInput().setText(quantity);
		// price
		Main.getStockLabelStorePriceInput().setText(price);
	}
	
	/**
	 * invoked by add to order button in search-tab
	 * set current order fields
	 * @param albumID
	 * @param storageLocation
	 * @param quantity
	 * @param price
	 */
	public static void setOrderFields(long albumID, long storageLocation, int quantity, int price){
		StockFuncs.albumID = albumID;
		StockFuncs.storageLocation = storageLocation;
		StockFuncs.quantity = quantity;
		StockFuncs.price = price;
		
		// set fields
		setOrderFields(
				Long.toString(albumID),
				Long.toString(storageLocation),
				Integer.toString(quantity),
				Integer.toString(price));
	}
	
	// order available stores table
	
	/**
	 * invoked when an available store is selected from the table
	 * sets place order button available
	 */
	public static void availableStoreSelected(){
		Main.getStockButtonPlaceOrder().setEnabled(true);
	}
	
	// place order button
	
	/**
	 * returns the current order from the order form
	 * or throws exception on invalid order
	 * @return
	 */
	public static OrdersOrRequestsTableItem getOrder() throws InvalidOrderException{
		try{
			int quantity = Integer.parseInt(Main.getStockTextBoxQuantityToOrder().getText());
			
			if(quantity <=0 )
				// quantity <= 0
				throw new InvalidOrderException("Quantity must be larger than 0");
			
			// get selected store's quantity
			int selectedStoreQuantity = Integer.parseInt(
					Main.getStockTableOrderAvailableStores().getSelection()[0].getText(2));
			if (quantity > selectedStoreQuantity)
				throw new InvalidOrderException("Selected store doesn't have enough in stock");
			else{
				// get selected store id
				int selectedStoreID = Integer.parseInt(
						Main.getStockTableOrderAvailableStores().getSelection()[0].getText(0));
				// get album id

				OrdersOrRequestsTableItem order = new OrdersOrRequestsTableItem(
						-1, // to be set by DB
						StaticProgramTables.thisStore.getStoreID(),
						selectedStoreID,
						albumID,
						quantity,
						MainFuncs.getDate(),
						OrderStatusEnum.WAITING);
				return order;
			}
			
		}catch(NumberFormatException nfe){
			// quantity is not a number
			throw new InvalidOrderException("Quantity must be an integer");
		}
	}
	
	/**
	 * add order to orders table in GUI
	 * @param order
	 */
	public static void addOrder(OrdersOrRequestsTableItem order){
		// clear order form (invoke clear button)
		clearFieldsButtonInvokation();
		// add order to orders table
		StaticProgramTables.orders.addOrder(order);
		// update orders table view
		updateOrdersTableView();
		// flag DB as free
		MainFuncs.setAllowDBAction(true);
	}
	
	///////////////////////////////////
	//	Orders and Requests handlers //
	///////////////////////////////////
	
	/**
	 * invoked by DB to notify that the requested orders/requests table action was denied
	 * and update orders and requests view
	 */
	public static void denyOrdersOrRequestsTableAction(OrdersRequestsActionsEnum actionTried,
			OrdersRequestsActionsEnum actionTaken, int orderID){
		// initialize message box
		String str1, str2;
		if(actionTried == OrdersRequestsActionsEnum.CANCEL_ORDER){
			str1 = "Order could not be canceled ";
		} else if(actionTried == OrdersRequestsActionsEnum.DENY_REQUEST){
			str1 = "Request could not be denied ";
		} else { // APPROVE_REQUEST
			str1 = "Request could not be approved ";
		}
		if(actionTaken == OrdersRequestsActionsEnum.CANCEL_ORDER){
			str2 = "since it was canceled by requester";
		} else if(actionTaken == OrdersRequestsActionsEnum.DENY_REQUEST){
			str2 = "since it was already denied by supplier";
		} else { // APPROVE_REQUEST
			str2 = "since it was already approved by supplier";
		}
		
		MessageBox errorMsg = new MessageBox(Main.getMainShell(),SWT.ICON_ERROR);
		errorMsg.setText("Action could not be invoked");
		errorMsg.setMessage(str1+str2);
		
		// pop error message
		errorMsg.open();
		
		// update view according to action taken
		// request was canceled by requester
		if (actionTaken == OrdersRequestsActionsEnum.CANCEL_ORDER){
			// remove request from requests table
			StaticProgramTables.requests.getOrders().remove(orderID);
			// update requests table view
			updateRequestsTableView();
		}
		// order was denied by supplier
		else if (actionTaken == OrdersRequestsActionsEnum.DENY_REQUEST){
			// set order as "denied"
			StaticProgramTables.orders.getOrder(orderID).setStatus(OrderStatusEnum.DENIED);
			// update orders table view
			updateOrdersTableView();
		}
		// else - order was approved by supplier
		else {
			// set order as "approved"
			StaticProgramTables.orders.getOrder(orderID).setStatus(OrderStatusEnum.COMPLETED);
			// update orders table view
			updateOrdersTableView();
		}
	}
	
	/**
	 * update the input order / request status and update gui view
	 * @param orderID
	 * @param status
	 */
	public static void updateOrderRequestStatus(int orderID, OrderStatusEnum status){
		// update order status
		StaticProgramTables.orders.getOrder(orderID).setStatus(status);
		
		// update gui view and disable orders buttons
		updateOrdersTableView();
		Main.getStockButtonRemoveOrder().setEnabled(false);
		Main.getStockButtonCancelOrder().setEnabled(false);	
		
		// flag DB as free
		MainFuncs.setAllowDBAction(true);
	}
	
	//////////////////////
	//	Orders handlers //
	//////////////////////
	
	/**
	 * set orders table's buttons when table item is selected
	 */
	public static void ordersTableItemSelected(){
		String selectedOrderStatus = Main.getStockTableOrders().getSelection()[0].getText(5);
		
		// if selected order is still waiting, allow only cancel order
		// otherwise allow only remove order from table
		if(selectedOrderStatus.equals(OrderStatusEnum.WAITING.getStrRep())){
			Main.getStockButtonRemoveOrder().setEnabled(false);
			Main.getStockButtonCancelOrder().setEnabled(true);
		}else{
			Main.getStockButtonRemoveOrder().setEnabled(true);
			Main.getStockButtonCancelOrder().setEnabled(false);
		}
	}
	
	/**
	 * invoked from DB after "Refresh" orders table button is invoked
	 * updates store's orders and updates view
	 * @param orders
	 */
	public static void refreshOrdersTable(OrdersOrRequestsTable orders){
		// update orders
		setCurrentOrders(orders);
		// update view
		updateOrdersTableView();
		
		// flag DB as free
		MainFuncs.setAllowDBAction(true);
	}
	
	/**
	 * invoked by "Cancel Order" button
	 * calls change of order status to canceled
	 */
	public static void cancelOrderInvokation(){
		// call DB action
		try{
			int selectedOrderID = Integer.parseInt(Main.getStockTableOrders().getSelection()[0].getText(0));
			DBConnectionInterface.updateOrderStatus(selectedOrderID,OrderStatusEnum.CANCELED);
		} catch (NumberFormatException nfe){
			Debug.log("*** BUG: StockFuncs.cancelOrderInvokation bug", DebugOutput.FILE, DebugOutput.STDERR);
		}
	}
	
	/**
	 * invoked by "Remove Order" button
	 * calls removal of selected order from DB
	 */
	public static void removeOrderInvokation(){
		// call DB action
		try{
			int selectedOrderID = Integer.parseInt(Main.getStockTableOrders().getSelection()[0].getText(0));
			DBConnectionInterface.removeOrder(selectedOrderID);
		} catch (NumberFormatException nfe){
			Debug.log("*** BUG: StockFuncs.removeOrderInvokation bug", DebugOutput.FILE, DebugOutput.STDERR);
		}
	}
	
	/**
	 * invoked from DB after "Remove Order" button is invoked
	 * removes order from orders table and updates view
	 * @param orderID
	 */
	public static void removeOrder(int orderID){
		// remove order from orders table
		StaticProgramTables.orders.getOrders().remove(orderID);
		
		// update view
		updateOrdersTableView();
		
		// flag DB as free
		MainFuncs.setAllowDBAction(true);
	}	
	
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
					order.getStatus().getStrRep()
			};
			item.setText(entry);
		}
		
		// disable order buttons
		Main.getStockButtonRemoveOrder().setEnabled(false);
		Main.getStockButtonCancelOrder().setEnabled(false);
	}
	
	/**
	 * update current orders table to given one
	 * @param orders
	 */
	public static void setCurrentOrders(OrdersOrRequestsTable orders){
		StaticProgramTables.orders = orders;
		// flag that orders initialization is done
		MainFuncs.setOrdersInitialized(true);
	}
	
	////////////////////////
	//	Requests handlers //
	////////////////////////
	
	/**
	 * set requests table's buttons when table item is selected
	 */
	public static void requestsTableItemSelected(){
		// enable both buttons
		Main.getStockButtonDenyRequest().setEnabled(true);
		Main.getStockButtonApproveRequest().setEnabled(true);
	}
	
	/**
	 * invoked from DB after "Refresh" requests table button is invoked
	 * updates store's requests and updates view
	 * @param requests
	 */
	public static void refreshRequestsTable(OrdersOrRequestsTable requests){
		// update requests
		setCurrentRequests(requests);
		// update view
		updateRequestsTableView();
		
		// flag DB as free
		MainFuncs.setAllowDBAction(true);
	}
	
	/**
	 * invoked by "Deny Request" button
	 * calls status change of order id to "denied"
	 * DB will call its removal from requests table
	 */
	public static void denyRequestInvokation(){
		// call DB action
		try{
			int selectedRequestID = Integer.parseInt(Main.getStockTableRequests().getSelection()[0].getText(0));
			DBConnectionInterface.updateOrderStatus(selectedRequestID, OrderStatusEnum.DENIED);
		} catch (NumberFormatException nfe){
			Debug.log("*** BUG: StockFuncs.denyRequestInvokation bug", DebugOutput.FILE, DebugOutput.STDERR);
		}
	}
	
	/**
	 * invoked by "Approve Request" button
	 * calls status change of order id to "approved"
	 * DB will call its removal from requests table
	 */
	public static void approveRequestInvokation(){
		// call DB action
		try{
			int selectedRequestID = Integer.parseInt(Main.getStockTableRequests().getSelection()[0].getText(0));
			DBConnectionInterface.updateOrderStatus(selectedRequestID, OrderStatusEnum.COMPLETED);
		} catch (NumberFormatException nfe){
			Debug.log("*** BUG: StockFuncs.approveRequestInvokation bug", DebugOutput.FILE, DebugOutput.STDERR);
		}
	}
	
	/**
	 * invoked from DB after a request was approved or denied
	 * removes request from requests table
	 * @param requestID
	 */
	public static void removeRequest(int requestID){
		// remove request from requests table
		StaticProgramTables.requests.getOrders().remove(requestID);
		System.out.println("999");
		// update view
		updateRequestsTableView();
		
		// flag DB as free
		MainFuncs.setAllowDBAction(true);
	}
	
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
					request.getStatus().getStrRep()
			};
			item.setText(entry);
		}
		
		// disable both buttons
		Main.getStockButtonDenyRequest().setEnabled(false);
		Main.getStockButtonApproveRequest().setEnabled(false);
	}
	
	/**
	 * update current requests table to given one
	 * @param requests
	 */
	public static void setCurrentRequests(OrdersOrRequestsTable requests){
		StaticProgramTables.requests = requests;
		// flag that requests initialization is done
		MainFuncs.setRequestsInitialized(true);
	}
	
	//////////////////////////
	//	DB failure handling	//
	//////////////////////////
	
	/**
	 * notifies the orders table could not be initialized
	 * and allows retry or exit program
	 */
	public static void notifyInitOrdersFailure(){		
		MessageBox errMsg = new MessageBox(Main.getMainShell(),SWT.ICON_ERROR | SWT.OK);
		errMsg.setText("DB Connection Error");
		errMsg.setMessage("Could not initialize orders table due to a connection error.\n"+
				"Please try again later.");
		// retry connection
		if (errMsg.open() == SWT.OK) System.exit(-1);
	}
	
	/**
	 * notifies the requests table could not be initialized
	 * and allows retry or exit program
	 */
	public static void notifyInitRequestsFailure(){
		MessageBox errMsg = new MessageBox(Main.getMainShell(),SWT.ICON_ERROR | SWT.OK);
		errMsg.setText("DB Connection Error");
		errMsg.setMessage("Could not initialize requests table due to a connection error.\n"+
				"Please try again later.");
		// retry connection
		if (errMsg.open() == SWT.OK) System.exit(-1);
	}
	
	/**
	 * notifies the order's available stores could not be fetched
	 * and restores gui
	 */
	public static void notifyOrderAvailableStoresFailure(){
		MessageBox errMsg = new MessageBox(Main.getMainShell(),SWT.ICON_ERROR | SWT.OK);
		errMsg.setText("DB Connection Error");
		errMsg.setMessage("Could not fetch order's available stores due to a connection error.\n"+
				"Please try again later.");
		// retry connection
		if (errMsg.open() == SWT.OK) {
			// restore gui
			MainFuncs.setAllowDBAction(true);
		}
	}
	
	/**
	 * notifies that could not place the order
	 * and restores gui
	 */
	public static void notifyPlaceOrderFailure(){
		MessageBox errMsg = new MessageBox(Main.getMainShell(),SWT.ICON_ERROR | SWT.OK);
		errMsg.setText("DB Connection Error");
		errMsg.setMessage("Could not place order due to a connection error.\n"+
				"Please try again later.");
		// retry connection
		if (errMsg.open() == SWT.OK) {
			// restore gui
			MainFuncs.setAllowDBAction(true);
		}
	}
	
	/**
	 * notifies the orders action (refresh, remove, cancel) could not be done
	 * and restores gui
	 */
	public static void notifyOrdersActionFailure(){
		MessageBox errMsg = new MessageBox(Main.getMainShell(),SWT.ICON_ERROR | SWT.OK);
		errMsg.setText("DB Connection Error");
		errMsg.setMessage("Could invoke action due to a connection error.\n"+
				"Please try again later.");
		// retry connection
		if (errMsg.open() == SWT.OK) {
			// restore gui
			MainFuncs.setAllowDBAction(true);
		}
	}
	
	/**
	 * notifies the requests action (deny, approve) could not be done
	 * and restores gui
	 */
	public static void notifyRequestsActionFailure(){
		MessageBox errMsg = new MessageBox(Main.getMainShell(),SWT.ICON_ERROR | SWT.OK);
		errMsg.setText("DB Connection Error");
		errMsg.setMessage("Could invoke action due to a connection error.\n"+
				"Please try again later.");
		// retry connection
		if (errMsg.open() == SWT.OK) {
			// restore gui
			MainFuncs.setAllowDBAction(true);
		}
	}
}
