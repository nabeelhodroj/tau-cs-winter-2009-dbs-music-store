package GUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/*
 * Main window handlers
 */
public class MainFuncs {
	
	// Store details fields, initialized on startup
	protected static String storeID = "STR-ID-0000";
	protected static String storeCity = "Tel-Aviv";
	protected static String storeAddress = "Ben-Yehuda 12";
	protected static String storePhone = "03-67890123";
	protected static String storeManager = "John Smith";
	
	//////////////////////////////
	//	Date and Time getters	//
	//////////////////////////////
	
	 /**
	 * date getter
	 * @return string: current date in format dd/MM/yyyy
	 */
	public static String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
	
	/**
	 * date getter
	 * @return string: current day
	 */
	public static String getDay(){
        DateFormat dateFormat = new SimpleDateFormat("EEEE");
        Date date = new Date();
        return dateFormat.format(date);
    }
	
	/**
	 * date getter
	 * @return string: current time in format HH:mm
	 */
	public static String getTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
	
	//////////////////////////////////////////
	//	Store details getters and setters	//
	//////////////////////////////////////////
	
	/**
	 * getter for storeID
	 */
	public static String getStoreID(){
		return storeID;
	}
	
	/**
	 * setter for storeID
	 */
	public static void setStoreID(String storeID){
		MainFuncs.storeID = storeID;
	}

	/**
	 * getter for storeCity
	 */
	public static String getStoreCity(){
		return storeCity;
	}
	
	/**
	 * setter for storeCity
	 */
	public static void setStoreCity(String storeCity){
		MainFuncs.storeCity = storeCity;
	}

	/**
	 * getter for storeAddress
	 */
	public static String getStoreAddress(){
		return storeAddress;
	}
	
	/**
	 * setter for storeAddress
	 */
	public static void setStoreAddress(String storeAddress){
		MainFuncs.storeAddress = storeAddress;
	}

	/**
	 * getter for storePhone
	 */
	public static String getStorePhone(){
		return storePhone;
	}
	
	/**
	 * setter for storePhone
	 */
	public static void setStorePhone(String storePhone){
		MainFuncs.storePhone = storePhone;
	}
	
	/**
	 * getter for storeManager
	 */
	public static String getStoreManager(){
		return storeManager;
	}
	
	/**
	 * setter for storeManager
	 */
	public static void setStoreManager(String storeManager){
		MainFuncs.storeManager = storeManager;
	}
	
	//////////////////
	//	Quick Tips	//
	//////////////////
	
	private static List<String> quickTips = new ArrayList<String>();
	private static Iterator<String> quickTipsIter;
	public static void generateTips(){
		String tip;
		
		tip =	"For adding an album to current sale, search album by album ID or other parameters,\n"+
				"go to the \"Sale\" box on the bottom of the search tab, enter wanted quantity and\n"+
				"click the \"Add to sale\" button.";
		quickTips.add(tip);
		
		// initialize iterator
		quickTipsIter = quickTips.iterator();
	}
	
	/**
	 * cyclic getter for quick-tips
	 * @return
	 */
	public static String getTip(){
		if (quickTipsIter.hasNext()) return quickTipsIter.next();
		else {
			quickTipsIter = quickTips.iterator();
			return getTip();
		}
	}
	
}
