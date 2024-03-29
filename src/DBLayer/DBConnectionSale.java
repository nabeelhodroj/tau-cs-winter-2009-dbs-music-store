package DBLayer;

import java.lang.annotation.Retention;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import GUI.DBActionFailureEnum;
import GUI.GuiUpdatesInterface;
import GUI.StaticProgramTables;
import General.Debug;
import General.Debug.DebugOutput;
import Tables.AlbumsResultsTableItem;
import Tables.SaleTable;
import Tables.SaleTableItem;
import Tables.TablesExamples;

/**
 *	This class contains the Runnable classes for handling the GUI tab "SALE".
 * 	Each class corresponds to a single method in DBConnectionInterface class.
 */
public class DBConnectionSale { 
	
	/**
	 * Corresponds to DBConnectionInterface's "public static void makeSale(SaleTable sale);"
	 */
	public class MakeSale implements Runnable{
		private SaleTable sale;
		
		public MakeSale(SaleTable sale) {
			this.sale = sale;
		}



		
		public void run() {
			Debug.log("DBConnectionSale.MakeSale thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			List<String> queryList = new ArrayList<String>();
			
			// UPDATE TABLE "STOCK"
			for (SaleTableItem saleTableItem : sale.getSaleItems().values()) {
				queryList.add("UPDATE stock SET quantity=quantity-"+saleTableItem.getQuantity()+
			   			   " WHERE (album_id="+saleTableItem.getAlbumID()+") AND (store_id="+StaticProgramTables.getThisStore().getStoreID()+")");

			}
			
			// Delete quantity = 0
			queryList.add("DELETE FROM stock WHERE quantity=0"); 
			
			
			// UPDATE TABLE "SALES"
			String [] timeArr = sale.getTime().split(":");
			String [] dateArr = sale.getDate().split("/");
			String toDateString = "'"+timeArr[0]+":"+timeArr[1]+" "+dateArr[0]+"/"+dateArr[1]+"/"+dateArr[2]+"','HH24:MI DD/MM/YYYY'";
			String insertQuery = "INSERT INTO sales(store_id, salesman_id, sale_time)" +
								" VALUES("+StaticProgramTables.getThisStore().getStoreID()+","+sale.getSalesman().getEmployeeID()+", TO_DATE("+toDateString+"))";
			queryList.add(insertQuery);
						
			
			// UPDATE TABLE "ALBUM_SALES"
			for (SaleTableItem saleTableItem : sale.getSaleItems().values()) {
				queryList.add( "INSERT INTO album_sales(sale_id, album_id, quantity) " +
						"VALUES(sales_seq.currval, "+saleTableItem.getAlbumID()+", "+saleTableItem.getQuantity()+")");

			}
			
			if (DBAccessLayer.executeCommandsAtomic(queryList) != queryList.size()){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.MAKE_SALE_FAILURE);
				Debug.log("DBConnectionSale.MakeSale [ERROR]: Failed to execute changes to DB");
				return;
				
			}
			Debug.log("DBConnectionSale.MakeSale: Done working with DB, calling GUI's initSaleTable");
			GuiUpdatesInterface.initSaleTable();			
		}		
	}

}
