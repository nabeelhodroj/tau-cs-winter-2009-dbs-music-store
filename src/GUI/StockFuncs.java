package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import DBLayer.DBConnectionInterface;
import Debug.Debug;
import Debug.Debug.DebugOutput;
import Queries.OrderAvailableStoresQuery;
import Queries.QueryErrorException;
import Tables.InvalidOrderException;
import Tables.OrderAvailableStoresTableItem;
import Tables.OrderStatusEnum;
import Tables.OrdersOrRequestsTable;
import Tables.OrdersOrRequestsTableItem;

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
								// clear order form (invoke clear button)
								clearFieldsButtonInvokation();
								
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

						//TODO
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
							
							//TODO
							
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
							
							//TODO
							
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

						//TODO
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
							
							//TODO
							
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
							
							//TODO
							
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
					Integer.toString(availableStore.getPrice())
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
		// add order to orders table
		StaticProgramTables.orders.addOrder(order);
		// update orders table view
		updateOrdersTableView();
		// flag DB as free
		MainFuncs.setAllowDBAction(true);
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
