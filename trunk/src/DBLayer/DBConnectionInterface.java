package DBLayer;

/**
 * created by Ariel
 * *** will be updated by Kalev and Vadim ***
 * 
 * DB Connection Interface
 * =======================
 * all functions used by gui to pass queries / transactions to the DB layer
 * ///////////////////////////////////////////////
 * //											//
 * //	to be implemented by Kalev and Vadim	//
 * //											//
 * ///////////////////////////////////////////////
 */
public class DBConnectionInterface{
	
	//////////
	// Main //
	//////////
	
	/**
	 * invoked automatically when program starts
	 * invoke query to get initial stores table, for selection of current store
	 */
	public static void getStoresTable(){}
	
	////////////////
	// Search tab //
	////////////////
	
	/**
	 * invoked in Main \ search-tab \ "Search" button
	 * invoke albums search query
	 */
	public static void getAlbumsSearchResults(){}
	
	//////////////
	// Sale tab //
	//////////////
	

	///////////////
	// Stock tab //
	///////////////
	

	////////////////////
	// Management tab //
	////////////////////
}