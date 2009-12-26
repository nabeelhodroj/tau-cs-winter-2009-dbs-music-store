package Tables;

import java.util.*;

/**
 * created by Ariel
 * 
 * Current sale table data structure
 * =================================
 * holds information on current sale:
 * - sale id
 * - salesman
 * - sale date
 * - sale time
 * - list of sale items (SaleTableItems)
 */
public class SaleTable extends Table {
	private int saleID;
	private String salesmanName;
	private String date;			//TODO format may be changed later on
	private Map<Integer,SaleTableItem> saleItems = new HashMap<Integer,SaleTableItem>();
	
	/**
	 * constructor for the sale table
	 * @param saleID
	 */
	public SaleTable(int saleID){
		super("SaleTable");
		this.saleID = saleID;
	}
	
	public SaleTable(int saleID, String salesmanName, String date, HashMap<Integer,SaleTableItem> saleItems){
		super("SaleTable");
		this.saleID = saleID;
		this.salesmanName = salesmanName;
		this.date = date;
		this.saleItems = saleItems;
	}

	public int getSaleID() {
		return saleID;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<Integer, SaleTableItem> getSaleItems() {
		return saleItems;
	}

	public void setSaleItems(Map<Integer, SaleTableItem> saleItems) {
		this.saleItems = saleItems;
	}
	
	/**
	 * returns the total price for the sale
	 * @return
	 */
	public int getTotalSalePrice(){
		int sum = 0;
		for(SaleTableItem sti: saleItems.values()){
			sum += sti.getTotalPerItem();
		}
		return sum;
	}
}
