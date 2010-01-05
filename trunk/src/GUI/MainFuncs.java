package GUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
	
	// this flag is false if a DB action runs in background and false otherwise
	// DB actions are allowed one at a time
	public static boolean allowDBAction = false;
	public static MessageBox msgDBActionNotAllowed;
	
	/////////////////////////
	//	initialize program //
	/////////////////////////
	
	public static void initMsgDBActionNotAllowed(){
		msgDBActionNotAllowed = new MessageBox(Main.getMainShell(),SWT.ICON_WARNING);
		msgDBActionNotAllowed.setText("Cannot invoke action");
		msgDBActionNotAllowed.setMessage("Cannot invoke action, DB is busy.\nPlease try again later.");
	}
	
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
	
	//////////////
	//	Welcome	//
	//////////////
	
	public static void initWelcomeGroup(){
		Main.getMainLabelWelcomeText().setText(
				"Welcome to the SSDA Music Store Manager!\n"+
				"Select your choice:\n"+
				"Search for albums, place an order or add to sale, manage sales, manage stock (orders \n"+
				"and requests) and manage HR and database"
				);
	}
	
	/////////////////////
	//	animation path //
	/////////////////////
	
	public static String getAnimationURL(){
		// get class path
		String classPath = System.getProperty("java.class.path");
		StringTokenizer tokenizer = new StringTokenizer(classPath, ";");
		
		String projectPath = tokenizer.nextToken();  
		String str = projectPath.replaceAll(" ","%20");
		String url = "file:///"+str.replaceAll("\\\\","/")+"/../src/GUI/rubi_animation.gif";
		return url;
	}

	public static boolean isAllowDBAction() {
		return allowDBAction;
	}

	public static void setAllowDBAction(boolean allowDBAction) {
		MainFuncs.allowDBAction = allowDBAction;
	}

	public static MessageBox getMsgDBActionNotAllowed() {
		return msgDBActionNotAllowed;
	}

	public static void setMsgDBActionNotAllowed(MessageBox msgDBActionNotAllowed) {
		MainFuncs.msgDBActionNotAllowed = msgDBActionNotAllowed;
	}
}
