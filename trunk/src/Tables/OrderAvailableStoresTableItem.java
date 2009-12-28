package Tables;

/**
 * created by Ariel
 * 
 * order's available stores table item
 * ===================================
 * holds information about available store to order from:
 * - store id
 * - city
 * - quantity
 * - price
 */
public class OrderAvailableStoresTableItem {
	private int storeID;
	private String city;
	private int quantity;
	private int price;
	
	/**
	 * constructor for order's available store
	 * @param storeID
	 * @param city
	 * @param quantity
	 * @param price
	 */
	public OrderAvailableStoresTableItem(int storeID, String city, int quantity, int price){
		this.storeID = storeID;
		this.city = city;
		this.quantity = quantity;
		this.price = price;
	}

	public int getStoreID() {
		return storeID;
	}

	public String getCity() {
		return city;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}
}
