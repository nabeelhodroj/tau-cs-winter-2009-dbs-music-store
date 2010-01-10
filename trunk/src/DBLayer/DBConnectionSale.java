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



		@Override
		public void run() {
			Debug.log("DBConnectionSale.MakeSale thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			/*
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
			String toDateString = "'"+timeArr[0]+":"+timeArr[1]+" "+dateArr[0]+"/"+dateArr[1]+"/"+dateArr[2]+"','HH24:MM DD/MM/YYYY'";
			String insertQuery = "INSERT INTO sales(store_id, salesman_id, sale_time)" +
								" VALUES('"+StaticProgramTables.getThisStore().getStoreID()+"','"+sale.getSalesman().getEmployeeID()+"', TO_DATE("+toDateString+"))";
			Debug.log("DBConnectionSale.MakeSale [DEBUG NOTE]: Insert: "+insertQuery);
			queryList.add(insertQuery);
						
			
			// UPDATE TABLE "ALBUM_SALES"
			for (SaleTableItem saleTableItem : sale.getSaleItems().values()) {
				queryList.add( "INSERT INTO album_sales(sale_id, album_id, quantity) " +
						"VALUES(sales_seq.curval, "+saleTableItem.getAlbumID()+", "+saleTableItem.getQuantity()+")");

			}
			
			if (DBAccessLayer.executeCommandsAtomic(queryList) != 1){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.MAKE_SALE_FAILURE);
				Debug.log("DBConnectionSale.MakeSale thread failed to execute changes to DB");
				return;
				
			}
			Debug.log("DBConnectionSale.MakeSale: Done working with DB, calling GUI's initSaleTable");
			GuiUpdatesInterface.initSaleTable();
			*/
			// TODO remove:
			TablesExamples.makeSale(sale);
			
		}		
	}
	
	public class NameLess implements Runnable{
		private long albumID;
		private int storeID = StaticProgramTables.getThisStore().getStoreID();
		
		public NameLess(long albumID){
			this.albumID = albumID;
		}

		@Override
		public void run() {
			Debug.log("DBConnectionSale.NameLess thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			DBQueryResults dBQRes = DBAccessLayer.executeQuery("SELECT * FROM stock " +
															   "WHERE (album_id="+albumID+" ) AND (store_id="+storeID+")");
			if (dBQRes == null){
				Debug.log("DBConnectionSale.NameLess [ERROR]: Failed to access DataBase.");
				// TODO notify the GUI of the failure.
				return;
			}
			ResultSet rs = dBQRes.getResultSet();
			try {
				rs.next();
				AlbumsResultsTableItem retAlbum = new AlbumsResultsTableItem(albumID);
				retAlbum.setQuantity(rs.getInt("quantity"));
			} catch (SQLException e) {
				Debug.log("DBConnectionSale.NameLess [ERROR]: Failed to iterate over the ResultSet.");
				// TODO notify the GUI of the failure.
				dBQRes.close();
				return;
			}
			
			Debug.log("DBConnectionSale.NameLess Done working with DB calling GUI's NameLess.");
			// TODO return retAlbum to the GUI
			dBQRes.close();
		}
		
	}	

}
