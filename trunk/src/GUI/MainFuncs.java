package GUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import DBLayer.DBConnectionInterface;
import Tables.StoresTableItem;
import General.*;
import General.Debug.*;

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
	
	// flags for tables initialization
	public static boolean isOrdersInitialized = false;
	public static boolean isRequestsInitialized = false;
	public static boolean isEmployeesInitialized = false;
	
	/////////////////////////
	//	initialize program //
	/////////////////////////
	
	/**
	 * invoked automatically on program startup
	 * - creates initial connection with DB
	 * - initializes stores list
	 */
	public static void initDBConnection(){
		// create connection
		String classPath = System.getProperty("java.class.path").split(";")[0];
		ConfigurationManager confMan = new ConfigurationManager(classPath+"\\..\\src\\General\\Store.props");
		
		DBConnectionInterface.initDBConnection(confMan);
	}
	
	/**
	 * invoked when main window opens
	 * waits until all tables are updated from DB:
	 * - orders
	 * - requests
	 * - employees
	 * then updates gui fields
	 */
	public static void initializeTablesAndFields(){
		// wait until all tables are initialized
		while (!isOrdersInitialized || !isRequestsInitialized || !isEmployeesInitialized){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("*** BUG: sleep failed");
			}
		}
		// initialize all fields:
		/////////////////////////
		
		// initialize store details view
		MainFuncs.initStoreDetails();
		// initialize welcome group
		MainFuncs.initWelcomeGroup();
		
		// initialize search tab view
		SearchFuncs.initSearchTabView();
		// initialize search listeners
		SearchFuncs.initSearchListeners();
		
		// initialize sale tab view
		SaleFuncs.initSaleTabView();
		// initialize sale listeners
		SaleFuncs.initSaleListeners();
		
		// initialize orders table values
		StockFuncs.updateOrdersTableView();
		// initialize requests table values
		StockFuncs.updateRequestsTableView();
		// initialize stock tab view
		StockFuncs.initStockTabView();
		// initialize stock tab listeners
		StockFuncs.initStockTabListeners();
		
		// initialize employees table
		ManageFuncs.initManageTabView();
		// initialize sale salesman list
		SaleFuncs.updateSalesmenList();
		Main.getSaleComboSalesmanIDNameInput().select(0);
		// initialize employee tab listeners
		ManageFuncs.initManageListeners();
		// initialize current sale
		// initialized only here, after employees are initialized
		SaleFuncs.initCurrentSale();
	}
	
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
	
	//////////////////////////
	//	DB error handling	//
	//////////////////////////
	
	/**
	 * notifies the user a DB error has occurred and:
	 * - if the action was an initial action, allows him to retry or quit program
	 * - if the action wasn't crucial, notifies and restores gui
	 */
	public static void notifyDBFailure(DBActionFailureEnum failure){
		//TODO
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
        DateFormat dateFormat = new SimpleDateFormat("kk:mm");
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

	public static boolean isOrdersInitialized() {
		return isOrdersInitialized;
	}

	public static void setOrdersInitialized(boolean isOrdersInitialized) {
		MainFuncs.isOrdersInitialized = isOrdersInitialized;
	}

	public static boolean isRequestsInitialized() {
		return isRequestsInitialized;
	}

	public static void setRequestsInitialized(boolean isRequestsInitialized) {
		MainFuncs.isRequestsInitialized = isRequestsInitialized;
	}

	public static boolean isEmployeesInitialized() {
		return isEmployeesInitialized;
	}

	public static void setEmployeesInitialized(boolean isEmployeesInitialized) {
		MainFuncs.isEmployeesInitialized = isEmployeesInitialized;
	}
}
