package DBLayer;

import Debug.Debug;
import Debug.Debug.DebugOutput;
import Tables.SaleTable;
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
			//TODO
			
			// until implemented, use example:
			TablesExamples.makeSale(sale);
		}		
	}
}
