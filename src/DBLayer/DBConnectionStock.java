package DBLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import GUI.GuiUpdatesInterface;
import GUI.OrdersRequestsActionsEnum;
import GUI.StaticProgramTables;
import General.Debug;
import General.Debug.DebugOutput;
import Queries.OrderAvailableStoresQuery;
import Tables.OrderAvailableStoresTable;
import Tables.OrderStatusEnum;
import Tables.OrdersOrRequestsTable;
import Tables.OrdersOrRequestsTableItem;
import Tables.TablesExamples;

public class DBConnectionStock {
	public class GetOrderAvailableStores implements Runnable{
		private OrderAvailableStoresQuery query;
		
 		public GetOrderAvailableStores(OrderAvailableStoresQuery query) {
			this.query = query;
		}

		@Override
		public void run() {
			Debug.log("DBConnectionStock.GetOrderAvailableStores thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			/* TODO un-comment
			OrderAvailableStoresTable availableStores = new OrderAvailableStoresTable();
			
			String queryStr = "SELECT DISTINCT store_id, city, quantity FROM stores, stock " +
							  "WHERE (stores.store_id=stock.store_id) AND (stock.album_id="+query.getAlbumID()+")";
			ResultSet rs = null ; //TODO : execute query ::  =rotemexecuteQuery(queryStr) 
			if (rs == null) {
				Debug.log("DBConnectionStock.GetOrderAvailableStores [ERROR]: DB access failed, NULL pointer returned.");
				GuiUpdatesInterface.updateOrderAvailableStores(availableStores);
				return;
			}
			else {try {
					while (rs.next()){
						availableStores.addStore(rs.getInt(1), rs.getString(2), rs.getInt(3));
					}
				} catch (SQLException e) {
					Debug.log("DBConnectionStock.GetOrderAvailableStores [ERROR] encountered an database access error:");
					Debug.log(e.getStackTrace().toString());
					GuiUpdatesInterface.updateOrderAvailableStores(availableStores);
					return;
				}
			}				
				
			Debug.log("DBConnectionStock.GetOrderAvailableStores done with DB, calling GUI's updateOrderAvailableStores");
			GuiUpdatesInterface.updateOrderAvailableStores(availableStores);
			 */
			// until implemented, use example:
			TablesExamples.getOrderAvailableStores(query);
		}
	}
	
	public class GetOrdersTable implements Runnable{
		@Override
		public void run() {
			Debug.log("DBConnectionStock.GetOrdersTable thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			/*
			OrdersOrRequestsTable ordersOrRequestsTable = new OrdersOrRequestsTable(true);
			int thisStoreID = StaticProgramTables.getThisStore().getStoreID();
			String queryStr = "SELECT order_id, supplying_store_id, album_id, quantity, order_date, status FROM stores " +
								"WHERE ordering_store_id="+thisStoreID;
			ResultSet rs = null ; //TODO : execute query ::  =rotemexecuteQuery(queryStr) 
			if (rs == null) {
				Debug.log("DBConnectionStock.GetOrdersTable [ERROR]: DB access failed, NULL pointer returned.");
				GuiUpdatesInterface.initOrdersTable(ordersOrRequestsTable);
				return;
			}
			else {try {
					while (rs.next()){
						String ordStat = rs.getString(6);
						ordersOrRequestsTable.addOrder(rs.getInt(1), thisStoreID, rs.getInt(2), rs.getLong(3), rs.getInt(4), rs.getDate(5).toString(), 
								(ordStat.compareToIgnoreCase("Waiting") == 0 ? OrderStatusEnum.WAITING : 
									(ordStat.compareToIgnoreCase("Completed") == 0 ? OrderStatusEnum.COMPLETED : 
										(ordStat.compareToIgnoreCase("Denied") == 0 ? OrderStatusEnum.DENIED : 
											OrderStatusEnum.CANCELED)))); 
					}
				} catch (SQLException e) {
					Debug.log("DBConnectionStock.GetOrdersTable [ERROR] encountered an database access error:");
					Debug.log(e.getStackTrace().toString());
					GuiUpdatesInterface.initOrdersTable(ordersOrRequestsTable);
					return;
				}
			}
			
			Debug.log("DBConnectionStock.GetOrdersTable done with DB, calling GUI's initOrdersTable");
			GuiUpdatesInterface.initOrdersTable(ordersOrRequestsTable);
			
			*/
			TablesExamples.getOrdersTable();
		}		
	}
	
	public class RemoveOrder implements Runnable{
		private int orderID;
			
		public RemoveOrder(int orderID) {
			super();
			this.orderID = orderID;
		}
		
		public void run() {
			Debug.log("DBConnectionStock.RemoveOrder thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			String queryStr = "DELETE FROM orders WHERE order_id="+orderID;
			// TODO : execute the query :: rotemExecuteUpdate(queryStr) 
			
			
			Debug.log("DBConnectionStock.RemoveOrder done with DB, calling GUI's removeOrder");
			//TODO un-comment: GuiUpdatesInterface.removeOrder(orderID);
			
			
			TablesExamples.removeOrder(orderID);
		}
	}
	
	public class PlaceOrder implements Runnable{
		private OrdersOrRequestsTableItem order;

		public PlaceOrder(OrdersOrRequestsTableItem order) {
			super();
			this.order = order;
		}

		@Override
		public void run() {
			Debug.log("DBConnectionStock.PlaceOrder thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			OrdersOrRequestsTableItem retOrder = new OrdersOrRequestsTableItem(-1, // Default
																			   StaticProgramTables.getThisStore().getStoreID(),
																			   order.getSupplyingStoreID(),
																			   order.getAlbumID(),
																			   order.getQuantity(),
																			   order.getDate(),
																			   OrderStatusEnum.WAITING);
			
			String [] dateArr = order.getDate().split("/");
			String toDateString = "'"+dateArr[0]+"/"+dateArr[1]+"/"+dateArr[2]+"','DD/MM/YYYY'";
			String query = "INSERT INTO orders(ordering_store_id, supplying_store_id, album_id, quantity, order_date, status) " +
							"VALUES('"+StaticProgramTables.getThisStore().getStoreID()+"','"+order.getSupplyingStoreID()+"','"+order.getAlbumID()+"'," +
									"'"+order.getQuantity()+"',TO_DATE("+toDateString+"),'Waiting')";
			
			int retOrderID = 0; // TODO : execute :: =rotemExecuteUpdateReturnID(String query)
			retOrder.setAlbumID(retOrderID);
			
			Debug.log("DBConnectionStock.PlaceOrder done with DB, calling GUI's addOrder");
			//TODO un-comment: GuiUpdatesInterface.addOrder(retOrder);			
			
			TablesExamples.placeOrder(order);
		}
	}
	
	public class UpdateOrderStatus implements Runnable{
		private int orderID;
		private OrderStatusEnum status;
		public UpdateOrderStatus(int orderID, OrderStatusEnum status) {
			super();
			this.orderID = orderID;
			this.status = status;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionStock.UpdateOrderStatus thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			/*
			String queryStr = "SELECT * FROM orders WHERE order_id="+orderID;
			ResultSet rs = null ; //TODO : execute :: =rotemexecuteQuery(queryStr)
			if (rs == null) {
				Debug.log("DBConnectionStock.UpdateOrderStatus [ERROR]: DB access failed, NULL pointer returned.");
				return;
			}
 
			try {
				switch (status){
				case CANCELED:
					if (!rs.next()){
						Debug.log("DBConnectionStock.UpdateOrderStatus [ERROR]: Tried to cancel non existing order");
						return;
					}
					if (rs.getString("status").compareToIgnoreCase("Waiting") != 0){
						GuiUpdatesInterface.denyOrdersOrRequestsTableAction(OrdersRequestsActionsEnum.CANCEL_ORDER,
								(rs.getString("status").compareToIgnoreCase("Completed") == 0 ? OrdersRequestsActionsEnum.APPROVE_REQUEST : OrdersRequestsActionsEnum.DENY_REQUEST), orderID);
					}
					queryStr = "UPDATE orders SET status=Canceled WHERE order_id="+orderID;
					// TODO execute : rotemExecuteUpdate(queryStr) & assert that EXACTLY ONE entry changed
					GuiUpdatesInterface.updateOrderStatus(orderID, status);
					break;
				case COMPLETED:
					if (!rs.next()){																								//SHOULD BE REMOVE_ORDER
						GuiUpdatesInterface.denyOrdersOrRequestsTableAction(OrdersRequestsActionsEnum.APPROVE_REQUEST, OrdersRequestsActionsEnum.CANCEL_ORDER, orderID);
					} else if (rs.getString("status").compareToIgnoreCase("Waiting") != 0){											
						GuiUpdatesInterface.denyOrdersOrRequestsTableAction(OrdersRequestsActionsEnum.APPROVE_REQUEST, OrdersRequestsActionsEnum.CANCEL_ORDER, orderID);
					} else { // Approving request, updating stocks.
						
						// Update the supplier
						queryStr = "UPDATE stock SET quantity=quantity-"+rs.getInt("quantity")+" " +
									"WHERE (store_id="+rs.getInt("supplying_store_id")+") AND (album_id="+rs.getLong("album_id")+")";
						// TODO execute :: rotemExecuteUpdate(queryStr)
						Debug.log("DBConnectionStock.UpdateOrderStatus updated the supplier");
						
						// Update the receiver
						queryStr = "UPDATE stock SET quantity=quantity+"+rs.getInt("quantity")+" " +
						"WHERE (store_id="+rs.getInt("ordering_store_id")+") AND (album_id="+rs.getLong("album_id")+")";
						// TODO execute :: rotemExecuteUpdate(queryStr) && assert that EXACTLY ONE entry changed
						// if NOT then need to add the album to the store (rather then just update the stock):  
						queryStr = "INSERT INTO stock(album_id, store_id, quantity, storage_location) " +
									"VALUES('"+rs.getInt("album_id")+"','"+rs.getInt("ordering_store_id")+"','"+rs.getInt("quantity")+"','"+(new Random()).nextLong() % 1000+"')";
						Debug.log("DBConnectionStock.UpdateOrderStatus updated the reciever");
						
						// Update the Orders table
						queryStr = "UPDATE orders SET status=Completed WHERE order_id="+orderID;
						//TODO execute :: rotemExecuteUpdate(queryStr) && assert that EXACTLY ONE entry changed
						
						
						// Update the Stock table
						queryStr = "DELETE FROM stock WHERE quantity=0";
						//TODO execute :: rotemExecuteUpdate(queryStr)
						
						Debug.log("DBConnectionStock.UpdateOrderStatus updated Orders and Stock tables");
						GuiUpdatesInterface.removeRequest(orderID);
					}
					break;
				case DENIED: 
					break;
			}		
			} catch (SQLException e) {
				Debug.log("DBConnectionStock.UpdateOrderStatus [ERROR]: DB access failed.");
				e.printStackTrace();
				return;
			}
			*/
			TablesExamples.updateOrderStatus(orderID, status);
		}
	}
	
	public class GetRequestTable implements Runnable{

		@Override
		public void run() {
			Debug.log("DBConnectionStock.GetRequestTable thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			/*
			OrdersOrRequestsTable ordersOrRequestsTable = new OrdersOrRequestsTable(false);
			int thisStoreID = StaticProgramTables.getThisStore().getStoreID();
			String queryStr = "SELECT order_id, ordering_store_id, supplying_store_id, album_id, quantity, order_date, status FROM stores " +
								"WHERE supplying_store_id="+thisStoreID;
			ResultSet rs = null ; //TODO : execute query ::  =rotemexecuteQuery(queryStr) 
			if (rs == null) {
				Debug.log("DBConnectionStock.GetRequestTable [ERROR]: DB access failed, NULL pointer returned.");
				GuiUpdatesInterface.initOrdersTable(ordersOrRequestsTable);
				return;
			}
			else try {
					while (rs.next()){
						String ordStat = rs.getString(6);
						ordersOrRequestsTable.addOrder(rs.getInt(1), rs.getInt(2), thisStoreID ,rs.getLong(3), rs.getInt(4), rs.getDate(5).toString(), 
								(ordStat.compareToIgnoreCase("Waiting") == 0 ? OrderStatusEnum.WAITING : 
									(ordStat.compareToIgnoreCase("Completed") == 0 ? OrderStatusEnum.COMPLETED : 
										(ordStat.compareToIgnoreCase("Denied") == 0 ? OrderStatusEnum.DENIED : 
											OrderStatusEnum.CANCELED)))); 
					}
				} catch (SQLException e) {
					Debug.log("DBConnectionStock.GetRequestTable [ERROR] encountered an database access error:");
					Debug.log(e.getStackTrace().toString());
					GuiUpdatesInterface.initOrdersTable(ordersOrRequestsTable);
					return;
				}
			
			Debug.log("DBConnectionStock.GetRequestTable done with DB, calling GUI's initOrdersTable");
			GuiUpdatesInterface.initOrdersTable(ordersOrRequestsTable);
			*/
			TablesExamples.getRequestsTable();
		}
	}
	
	public class RefreshOrdersTable implements Runnable{

		@Override
		public void run() {
			TablesExamples.refreshOrdersTable();
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class RefreshRequestsTable implements Runnable{

		@Override
		public void run() {
			TablesExamples.refreshRequestsTable();
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
