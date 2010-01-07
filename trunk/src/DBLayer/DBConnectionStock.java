package DBLayer;

import General.Debug;
import General.Debug.DebugOutput;
import Queries.OrderAvailableStoresQuery;
import Tables.OrderStatusEnum;
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
			//TODO
			
			// until implemented, use example:
			TablesExamples.getOrderAvailableStores(query);
		}
	}
	
	public class GetOrdersTable implements Runnable{
		@Override
		public void run() {
			Debug.log("DBConnectionStock.GetOrdersTable thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			// TODO
			
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
			// TODO

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
			// TODO			
			
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
			// TODO			
			
			TablesExamples.updateOrderStatus(orderID, status);
		}
	}
	
	public class GetRequestTable implements Runnable{

		@Override
		public void run() {
			Debug.log("DBConnectionStock.GetRequestTable thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			// TODO			
			
			TablesExamples.getRequestsTable();
		}
	}
	
	public class RemoveRequest implements Runnable{
		private int orderID;

		public RemoveRequest(int orderID) {
			super();
			this.orderID = orderID;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionStock.RemoveRequest thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			// TODO			

			TablesExamples.removeRequest(orderID);
		}
	}
	
	public class AddRequest implements Runnable{
		private OrdersOrRequestsTableItem request;

		public AddRequest(OrdersOrRequestsTableItem request) {
			super();
			this.request = request;
		}
		
		@Override
		public void run() {
			Debug.log("DBConnectionStock.AddRequest thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			// TODO
			
			TablesExamples.addRequest(request);
		}		
	}
	
	
	
}
