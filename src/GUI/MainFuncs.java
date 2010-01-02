package GUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

import DBLayer.DBConnectionInterface;
import Debug.Debug;
import Debug.Debug.DebugOutput;
import Tables.StoresTableItem;

/**
 * created by Ariel
 * 
 * Main window handlers
 */
public class MainFuncs {
	
	/////////////////////////
	//	initialize program //
	/////////////////////////
	
	/**
	 * initialize initDialog listeners 
	 */
	public static void initiDialogBoxListeners(){
		InitialDialog.getInitDialogButtonStart().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Initial Dialog: Start button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						
						// get selected store id
						int storeID = InitialDialog.getInitDialogCombo().getSelectionIndex();
						// set current store to selected store
						StaticProgramTables.setThisStore(StaticProgramTables.stores.getStore(storeID));
						// update the stores details representation in main window is done in Main
						
						//TODO
						// update orders, requests and employees table
						DBConnectionInterface.getOrdersTable();
						DBConnectionInterface.getRequestsTable();
						DBConnectionInterface.getEmployeesTable(); // will initialize also employees combo box
						
						// close init dialog
						InitialDialog.closeInitDialog();
					}
				}
		);
		
		InitialDialog.getInitDialogButtonExit().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Initial Dialog: Exit button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						System.exit(-1);
					}
				}
		);
	}
	
	/**
	 * initialize initial dialog combo box
	 */
	public static void initDialogComboBoxItems(){
		for(StoresTableItem store : StaticProgramTables.stores.getStores().values()){
			InitialDialog.getInitDialogCombo().add(store.getStoreID()+": "+store.getCity());
		}
		// choose first by default
		InitialDialog.getInitDialogCombo().select(0);
	}
	
	/**
	 * initialize stores details view in main window
	 */
	public static void initStoreDetails(){
		Main.getMainLabelStoreDetailsStoreID().setText("Store: "+StaticProgramTables.getThisStore().getStoreID());
		Main.getMainLabelStoreDetailsStoreAddress().setText("Address: "+
				StaticProgramTables.thisStore.getAddress()+", "+StaticProgramTables.getThisStore().getCity());
		Main.getMainLabelStoreDetailsStorePhone().setText("Phone: "+StaticProgramTables.getThisStore().getPhone());
		Main.getMainLabelStoreDetailsStoreManager().setText("Manager: "+StaticProgramTables.getThisStore().getManagerID()); //TODO convert manager id to manager name	
	}
	
	/**
	 * tab switching
	 * @param tab
	 */
	public static void switchTab(int tab){
		Main.getMainTabFolder().setSelection(tab);
	}
	
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
	
	//////////////////
	//	Quick Tips	//
	//////////////////
	
	private static java.util.List<String> quickTips = new ArrayList<String>();
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
