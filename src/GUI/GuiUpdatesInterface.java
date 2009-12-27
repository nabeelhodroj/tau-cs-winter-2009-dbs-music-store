package GUI;

import Tables.*;
import Queries.*;

/**
 * created by Ariel
 * 
 * GUI updater interface
 * =====================
 * all functions used by lower level to pass data and update the GUI tables accordingly
 */
public class GuiUpdatesInterface {

	////////////////
	// Search tab //
	////////////////
	
	// all update functions are taken from GUI.SearchFuncs
	
	/**
	 * updates the albums serach results table by the given AlbumsResultsTable
	 * @param albumResults
	 */
	public static void updateAlbumResultsTable(AlbumsResultsTable albumResults){
		//TODO
		// implement albums results table gui update
	}
	
	//////////////
	// Sale tab //
	//////////////
	
	/**
	 * clears the sale table (invoked after gui makes current sale) 
	 */
	public static void clearSaleTable(){
		//TODO
		// implement sale table gui clear
	}
	
	///////////////
	// Stock tab //
	///////////////
	
	//TODO continue
}
