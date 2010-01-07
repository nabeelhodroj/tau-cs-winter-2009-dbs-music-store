package DBLayer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import GUI.GuiUpdatesInterface;
import GUI.StaticProgramTables;
import General.Debug;
import General.Debug.DebugOutput;
import Tables.SaleTable;
import Tables.SaleTableItem;
import Tables.TablesExamples;

public class DBConnectionSale {
	public class MakeSale implements Runnable{
		private SaleTable sale;
		
		public MakeSale(SaleTable sale) {
			this.sale = sale;
		}



		@Override
		public void run() {
			Debug.log("DBConnectionSale.MakeSale thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			
			// UPDATE TABLE "STOCK"
			List<String> dbUpdatArg1List = new ArrayList<String>();
			List<String> dbUpdatArg2List = new ArrayList<String>();	
			
			// Update the quantity in all the stores
			String queryPattern = "UPDATE stock SET quantity=quantity-? " +
			   			   "WHERE (album_id=?) AND (store_id="+StaticProgramTables.getThisStore().getStoreID()+")";
			for (SaleTableItem saleTableItem : sale.getSaleItems().values()) {
				dbUpdatArg1List.add(saleTableItem.getQuantity()+"");
				dbUpdatArg2List.add(saleTableItem.getAlbumID()+"");

			}
			
			//TODO : execute the updates :: rotemExecuteBatch(queryPattern, dbUpdatArg1List, dbUpdatArg2List)

			String query="DELETE FROM stock WHERE quantity=0";
			// TODO : execute the deletion of all quantity=0 CDs :: rotemExecuteUpdate(query)  
			
			
			// UPDATE TABLE "SALES"
			String [] timeArr = sale.getTime().split(":");
			String [] dateArr = sale.getDate().split("/");
			String toDateString = "'"+timeArr[0]+":"+timeArr[1]+" "+dateArr[0]+"/"+dateArr[1]+"/"+dateArr[2]+"','HH24:MM DD/MM/YYYY'";
			query="INSERT INTO sales(store_id, salesman_id, sale_time)" +
					" VALUES('"+StaticProgramTables.getThisStore().getStoreID()+"','"+sale.getSalesman().getEmployeeID()+"', TO_DATE("+toDateString+"))";
			// TODO : execute the update :: rotemExecuteUpdateReturnID(query)
			Long saleID = new Long("0"); // this value returns from previous Rotem's function
			
			
			// UPDATE TABLE "ALBUM_SALES"
			queryPattern = "INSERT INTO album_sales(sale_id, album_id, quantity) " +
							"VALUES('"+saleID+"', ?, ?)";
				
			// TODO : execute the updates :: rotemExecuteBatch(queryPattern, dbUpdatArg2List, dbUpdatArg1List)
			
			Debug.log("DBConnectionSale.MakeSale: Done working with DB, calling GUI's initSaleTable");
			GuiUpdatesInterface.initSaleTable();
			
			// ARIEL'S : TablesExamples.initStoresTable();
			
		}		
	}
}
