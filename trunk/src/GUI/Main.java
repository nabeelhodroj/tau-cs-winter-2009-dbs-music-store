package GUI;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.ScrolledComposite;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial use.
*/
public class Main extends org.eclipse.swt.widgets.Composite {

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	//////////////////////////////////
	//	Class Fields definitions	//
	//////////////////////////////////
	
	// Main Menu
	private static Menu mainMenuBar;
	private static MenuItem menuItemSeperator;
	
	private static MenuItem mainMenuItemFile;
	private static Menu fileMenu;
	private static MenuItem fileMenuItemExit;
	
	private static MenuItem mainMenuItemSearch;
	private static Menu searchMenu;
	private static MenuItem searchMenuItemAddToSale;
	private static MenuItem searchMenuItemPlaceOrder;
	private static MenuItem searchMenuItemSearch;
	private static MenuItem searchMenuItemClear;
	
	private static MenuItem mainMenuItemSale;
	private static Menu saleMenu;
	private static MenuItem saleMenuItemRemove;
	private static MenuItem saleMenuItemClear;
	private static MenuItem saleMenuItemMakeSale;
	
	private static MenuItem mainMenuItemStock;
	private static Menu stockMenu;
	private static MenuItem stockMenuItemCheckAvailability;
	private static MenuItem stockMenuItemClear;
	private static MenuItem stockMenuItemPlaceOrder;
	private static MenuItem stockMenuItemRemoveOrder;
	private static MenuItem stockMenuItemCancelOrder;
	private static MenuItem stockMenuItemDenyRequest;
	private static MenuItem stockMenuItemApproveRequest;
	
	private static MenuItem mainMenuItemManage;
	private static Menu manageMenu;
	private static MenuItem manageMenuItemBrowse;
	private static MenuItem manageMenuItemUpdateDBS;
	private static MenuItem manageMenuItemNew;
	private static MenuItem manageMenuItemInsert;
	private static MenuItem manageMenuItemEdit;
	private static MenuItem manageMenuItemSave;
	private static MenuItem manageMenuItemExitNoSave;
	private static MenuItem manageMenuItemRemoveEmployee;
	
	// Main Window Details
	//////////////////////
	// Store details
	private static Group mainGroupStoreDetails;
	private static Label mainLabelStoreDetailsStoreID;
	private static Label mainLabelStoreDetailsDateTime;
	private static Label mainLabelStoreDetailsStoreAddress;
	private static Label mainLabelStoreDetailsStorePhone;
	private static Label mainLabelStoreDetailsStoreManager;
	
	// Quick tips
	private static Group mainGroupQuickTips;
	private static Label mainLabelQuickTipsTip;
	
	// Tab folder
	private static TabFolder mainTabFolder;
	
	// Search tab
	/////////////
	private static TabItem searchTabItem;
	private static Composite searchTabComposite;
	
	// Search options group
	private static Group searchGroupOptions;
	private static Button searchBulletByAlbum;
	private static Text searchTextBoxAlbumID;
	private static Button searchBulletOtherParameters;
	private static Button searchCheckBoxAlbumName;
	private static Text searchTextBoxAlbumName;
	private static Button searchCheckBoxArtist;
	private static Text searchTextBoxArtist;
	private static Button searchCheckBoxYear;
	private static Text searchTextBoxYearFrom;
	private static Label searchLabelYearTo;
	private static Text searchTextBoxYearTo;
	private static Button searchCheckBoxSongNames;
	private static Text searchTextBoxSongNames;
	private static Composite searchCompositeStockField;
	private static Label searchLabelStock;
	private static Button searchBulletInStockAll;
	private static Button searchBulletInStockInStore;
	private static Button searchBulletInStockInNetwork;
	private static Button searchCheckBoxGenres;
	private static Button searchCheckBoxGenreJazz;
	private static Button searchCheckBoxGenreRock;
	private static Button searchCheckBoxGenre03;
	private static Button searchCheckBoxGenre04;
	private static Button searchCheckBoxGenre05;
	private static Button searchCheckBoxGenre06;
	private static Button searchCheckBoxGenre07;
	private static Button searchCheckBoxGenre08;
	private static Button searchCheckBoxGenre09;
	private static Button searchCheckBoxGenre10;
	private static Button searchCheckBoxGenreOther;
	private static Text searchTextBoxGenreOther;
	private static Button searchButtonClear;
	private static Button searchButtonSearch;
	
	// Search results group
	private static Group searchGroupResults;
	private static Table searchTableAlbumResults;
	private static TableColumn searchTableColumnAlbumID;
	private static TableColumn searchTableColumnAlbumName;
	private static TableColumn searchTableColumnAlbumArtist;
	private static TableColumn searchTableColumnAlbumYear;
	private static TableColumn searchTableColumnAlbumGenre;
	private static TableColumn searchTableColumnAlbumLength;
	private static ProgressBar searchProgressBar;
	private static Table searchTableSongResults;
	private static TableColumn searchTableColumnSongName;
	private static TableColumn searchTableColumnSongArtist;
	private static TableColumn searchTableColumnSongLength;
	
	// Stock information group
	private static Group searchGroupStockInfo;
	private static Label searchLabelStockInfoStoreStock;
	private static Label searchLabelStockInfoLocation;
	private static Label searchLabelStockInfoPrice;
	private static Button searchButtonStockInfoOrder;
	
	// Sale group
	private static Group searchGroupSaleInfo;	
	private static Label searchLabelSaleInfoQuantity;
	private static Text searchTextBoxSaleInfoQuantity;
	private static Button searchButtonSaleInfoSale;
	
	// Sale tab
	///////////
	private static TabItem saleTabItem;
	private static Composite saleCompositeMain;
	
	// Sale details group
	private static Group saleGroupSaleDetails;
	private static Label saleLabelSaleID;
	private static Label saleLabelSaleIDInput;
	private static Label saleLabelSaleDate;
	private static Label saleLabelDateInput;
	private static Label saleLabelSalesmanIDName;
	private static Combo saleComboSalesmanIDNameInput;
	private static Label saleLabelSaleTime;
	private static Label saleLabelTimeInput;
	
	// Sale table
	private static Table saleTableSaleItems;
	private static TableColumn saleTableColumnAlbumID;
	private static TableColumn saleTableColumnAlbumName;
	private static TableColumn saleTableColumnQuantity;
	private static TableColumn saleTableColumnPricePerItem;
	private static TableColumn saleTableColumnPriceTotal;
	private static Button saleButtonRemoveItem;
	private static Button saleButtonClearSale;
	private static Label saleLabelTotalPrice;
	private static Label saleLabelTotalPriceValue;
	private static Button saleButtonMakeSale;
	
	// Stock tab
	////////////
	private static TabItem stockTabItem;
	private static Composite stockTabComposite;
	
	// Order group
	private static Group stockGroupOrderForm;
	private static Label stockLabelOrderID;
	private static Label stockLabelOrderIDInput;
	private static Label stockLabelAlbumID;
	private static Text stockTextBoxAlbumIDInput;
	private static Label stockLabelDate;
	private static Label stockLabelOrderDateInput;
	private static Button stockButtonCheckAvailability;
	private static Label stockLabelOrderFromStore;
	private static Table stockTableOrderAvailableStores;
	private static TableColumn stockTableColumnStoreID;
	private static TableColumn stockTableColumnStoreCity;
	private static TableColumn stockTableColumnQuantity;
	private static TableColumn stockTableColumnPrice;
	private static Label stockLabelPrice;
	private static Label stockLabelStorePriceInput;
	private static Label stockLabelQuantityInStock;
	private static Label stockLabelQuantityInStockInput;
	private static Label stockLabelStorageLocation;
	private static Label stockLabelStorageLocationInput;
	private static Label stockLabelQuantityToOrder;
	private static Text stockTextBoxQuantityToOrder;
	private static Button stockButtonClearOrder;
	private static Button stockButtonPlaceOrder;
	
	// Orders table
	private static Label stockLabelOrders;
	private static Table stockTableOrders;
	private static TableColumn stockTableColumnOrdersOrderID;
	private static TableColumn stockTableColumnOrdersSupplierID;
	private static TableColumn stockTableColumnOrdersAlbumID;
	private static TableColumn stockTableColumnOrdersQuantity;
	private static TableColumn stockTableColumnOrdersDate;
	private static TableColumn stockTableColumnOrdersStatus;
	private static TableColumn stockTableColumnOrdersCompletionDate;
	private static Button stockButtonRemoveOrder;
	private static Button stockButtonCancelOrder;
	
	// Requests table
	private static Label stockLabelRequests;	
	private static Table stockTableRequests;
	private static TableColumn stockTableColumnRequestsOrderID;
	private static TableColumn stockTableColumnRequestsOrderingStoreID;
	private static TableColumn stockTableColumnRequestsAlbumID;
	private static TableColumn stockTableColumnRequestsQuantity;
	private static TableColumn stockTableColumnRequestsDate;
	private static TableColumn stockTableColumnRequestsStatus;
	private static TableColumn stockTableColumnRequestsCompletionDate;
	private static Button stockButtonDenyRequest;
	private static Button stockButtonApproveRequest;
	
	// Management tab
	/////////////////
	private static TabItem managementTabItem;
	private static Composite manageMainComposite;
	
	// Employees table
	private static Label manageLabelEmployees;
	private static Table manageTableEmployees;
	private static TableColumn manageTableColumnEmployeeID;
	private static TableColumn manageTableColumnEmployeePName;
	private static TableColumn manageTableColumnEmployeeLName;
	private static TableColumn manageTableColumnEmployeePosition;
	
	// Edit employee details group
	private static Group manageGroupEditEmployee;
	private static Label manageLabelEmployeeEmploymentDateInput;
	private static Label manageLabelEmployeeEmploymentDate;
	private static Label manageLabelEmployeeStoreID;
	private static Label manageLabelEmployeeEmployeeStoreIDInput;
	private static Label manageLabelEmployeeID;
	private static Text manageTextBoxEmployeeIDInput;
	private static Label manageLabelEmployeeBirth;
	private static Text manageTextBoxEmployeeBirthInput;
	private static Label manageLabelEmployeeFName;
	private static Text manageTextBoxEmployeeFNameInput;	
	private static Label manageLabelEmployeeLName;
	private static Text manageTextBoxEmployeeLNameInput;
	private static Label manageLabelEmployeeAddress;
	private static Text manageTextBoxEmployeeAddressInput;
	private static Label manageLabelEmployeePhone;
	private static Text manageTextBoxEmployeePhoneInput;
	private static Label manageLabelEmployeeCellPhone;
	private static Text manageTextBoxEmployeeCellPhoneInput;
	private static Label manageLabelEmployeePosition;
	private static Combo manageComboEmployeePositionInput;
	private static Button manageButtonEmployeeNew;
	private static Button manageButtonEmployeeInsert;
	private static Button manageButtonEmployeeNoSave;
	private static Button manageButtonEmployeeEdit;
	private static Button manageButtonEmployeeSave;
	private static Button manageButtonEmployeeRemove;
	
	// Update database group
	private static Group manageGroupDBSManage;
	private static Label manageLabelDBSUpdate;
	private static Text manageTextBoxDBSUpdateFileInput;
	private static Button manageButtonDBSBrowse;
	private static Button manageButtonDBSUpdate;
	private static ProgressBar manageProgressBarDBSUpdate;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		// generate Quick Tips
		MainFuncs.generateTips();
		// Start GUI
		showGUI();
	}
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.TITLE | SWT.CLOSE | SWT.MIN);
		shell.setText("SSDA Music Store Manager");
		Main inst = new Main(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	public Main(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	/**
	 * Main Program window
	 * holds tabs for functionality:
	 * - Search
	 * - Sales
	 * - Stock
	 * - Management
	 */
	private void initGUI() {
		try {
			this.setLayout(null);
			this.layout();
			pack();
			this.setSize(800, 590);
			{
				/**
				 * Main menu
				 */
				mainMenuBar = new Menu(getShell(), SWT.BAR);
				getShell().setMenuBar(mainMenuBar);
				{
					mainMenuItemFile = new MenuItem(mainMenuBar, SWT.CASCADE);
					mainMenuItemFile.setText("&File");
					{
						fileMenu = new Menu(mainMenuItemFile);
						mainMenuItemFile.setMenu(fileMenu);
						{
							fileMenuItemExit = new MenuItem(fileMenu, SWT.PUSH);
							fileMenuItemExit.setText("E&xit");
						}
					}
					
					mainMenuItemSearch = new MenuItem(mainMenuBar, SWT.CASCADE);
					mainMenuItemSearch.setText("&Search");
					{
						searchMenu = new Menu(mainMenuItemSearch);
						mainMenuItemSearch.setMenu(searchMenu);
						{
							searchMenuItemClear = new MenuItem(searchMenu, SWT.PUSH);
							searchMenuItemClear.setText("&Clear Fields");
						}
						{
							searchMenuItemSearch = new MenuItem(searchMenu, SWT.PUSH);
							searchMenuItemSearch.setText("&Search");
						}
						{
							menuItemSeperator = new MenuItem(searchMenu, SWT.SEPARATOR);
						}
						{
							searchMenuItemPlaceOrder = new MenuItem(searchMenu, SWT.PUSH);
							searchMenuItemPlaceOrder.setText("&Place an Order...");
						}
						{
							menuItemSeperator = new MenuItem(searchMenu, SWT.SEPARATOR);
						}
						{
							searchMenuItemAddToSale = new MenuItem(searchMenu, SWT.PUSH);
							searchMenuItemAddToSale.setText("&Add to Sale...");
						}
					}
					
					mainMenuItemSale = new MenuItem(mainMenuBar, SWT.CASCADE);
					mainMenuItemSale.setText("Sa&le");
					{
						saleMenu = new Menu(mainMenuItemSale);
						mainMenuItemSale.setMenu(saleMenu);
						{
							saleMenuItemRemove = new MenuItem(saleMenu, SWT.PUSH);
							saleMenuItemRemove.setText("&Remove Item");
						}
						{
							saleMenuItemClear = new MenuItem(saleMenu, SWT.PUSH);
							saleMenuItemClear.setText("&Clear Sale");
						}
						{
							saleMenuItemMakeSale = new MenuItem(saleMenu, SWT.PUSH);
							saleMenuItemMakeSale.setText("&Make Sale");
						}						
					}
					
					mainMenuItemStock = new MenuItem(mainMenuBar, SWT.CASCADE);
					mainMenuItemStock.setText("S&tock");
					{
						stockMenu = new Menu(mainMenuItemStock);
						mainMenuItemStock.setMenu(stockMenu);
						{
							stockMenuItemCheckAvailability = new MenuItem(stockMenu, SWT.PUSH);
							stockMenuItemCheckAvailability.setText("&Check Availability");
						}
						{
							stockMenuItemClear = new MenuItem(stockMenu, SWT.PUSH);
							stockMenuItemClear.setText("Clear &Fields");
						}
						{
							stockMenuItemPlaceOrder = new MenuItem(stockMenu, SWT.PUSH);
							stockMenuItemPlaceOrder.setText("&Place Order");
						}
						{
							menuItemSeperator = new MenuItem(stockMenu, SWT.SEPARATOR);
						}
						{
							stockMenuItemRemoveOrder = new MenuItem(stockMenu, SWT.PUSH);
							stockMenuItemRemoveOrder.setText("&Remove Order");
						}
						{
							stockMenuItemCancelOrder = new MenuItem(stockMenu, SWT.PUSH);
							stockMenuItemCancelOrder.setText("Cancel &Order");
						}
						{
							menuItemSeperator = new MenuItem(stockMenu, SWT.SEPARATOR);
						}
						{
							stockMenuItemDenyRequest = new MenuItem(stockMenu, SWT.PUSH);
							stockMenuItemDenyRequest.setText("&Deny Request");
						}
						{
							stockMenuItemApproveRequest = new MenuItem(stockMenu, SWT.PUSH);
							stockMenuItemApproveRequest.setText("&Approve Request");
						}
					}
					
					mainMenuItemManage = new MenuItem(mainMenuBar, SWT.CASCADE);
					mainMenuItemManage.setText("&Management");
					{
						manageMenu = new Menu(mainMenuItemManage);
						mainMenuItemManage.setMenu(manageMenu);
						{
							manageMenuItemBrowse = new MenuItem(manageMenu, SWT.PUSH);
							manageMenuItemBrowse.setText("&Browse...");
						}
						{
							manageMenuItemUpdateDBS = new MenuItem(manageMenu, SWT.PUSH);
							manageMenuItemUpdateDBS.setText("&Update Database");
						}
						{
							menuItemSeperator = new MenuItem(manageMenu, SWT.SEPARATOR);
						}
						{
							manageMenuItemNew = new MenuItem(manageMenu, SWT.PUSH);
							manageMenuItemNew.setText("&New");
						}
						{
							manageMenuItemInsert = new MenuItem(manageMenu, SWT.PUSH);
							manageMenuItemInsert.setText("&Insert");
						}
						{
							manageMenuItemEdit = new MenuItem(manageMenu, SWT.PUSH);
							manageMenuItemEdit.setText("&Edit");
						}
						{
							manageMenuItemSave = new MenuItem(manageMenu, SWT.PUSH);
							manageMenuItemSave.setText("&Save");
						}
						{
							manageMenuItemExitNoSave = new MenuItem(manageMenu, SWT.PUSH);
							manageMenuItemExitNoSave.setText("Exit &Without Saving");
						}
						{
							manageMenuItemRemoveEmployee = new MenuItem(manageMenu, SWT.PUSH);
							manageMenuItemRemoveEmployee.setText("&Remove Employee");
						}
					}
				}
			}
			
			{
				/**
				 * Main window details: store details and quick tips
				 */
				mainGroupStoreDetails = new Group(this, SWT.NONE);
				mainGroupStoreDetails.setLayout(null);
				mainGroupStoreDetails.setText("Store Details");
				mainGroupStoreDetails.setBounds(7, 0, 324, 86);
				{
					mainLabelStoreDetailsStoreID = new Label(mainGroupStoreDetails, SWT.NONE);
					mainLabelStoreDetailsStoreID.setText("Store: "+MainFuncs.getStoreID());
					mainLabelStoreDetailsStoreID.setBounds(5, 19, 101, 20);
				}
				{
					mainLabelStoreDetailsStoreAddress = new Label(mainGroupStoreDetails, SWT.NONE);
					mainLabelStoreDetailsStoreAddress.setText("Address: "+MainFuncs.getStoreAddress()+", "+MainFuncs.getStoreCity());
					mainLabelStoreDetailsStoreAddress.setBounds(5, 41, 245, 20);
				}
				{
					mainLabelStoreDetailsStorePhone = new Label(mainGroupStoreDetails, SWT.NONE);
					mainLabelStoreDetailsStorePhone.setText("Phone: "+MainFuncs.getStorePhone());
					mainLabelStoreDetailsStorePhone.setBounds(5, 63, 133, 20);
				}
				{
					mainLabelStoreDetailsStoreManager = new Label(mainGroupStoreDetails, SWT.NONE);
					mainLabelStoreDetailsStoreManager.setText("Manager: "+MainFuncs.getStoreManager());
					mainLabelStoreDetailsStoreManager.setBounds(150, 62, 162, 22);
				}
				{
					mainLabelStoreDetailsDateTime = new Label(mainGroupStoreDetails, SWT.NONE);
					mainLabelStoreDetailsDateTime.setBounds(150, 19, 167, 20);
					mainLabelStoreDetailsDateTime.setText(MainFuncs.getDay() + ", " + MainFuncs.getDate()+ ", "+ MainFuncs.getTime());
				}
			}
			{
				mainGroupQuickTips = new Group(this, SWT.NONE);
				mainGroupQuickTips.setLayout(null);
				mainGroupQuickTips.setBounds(337, 0, 460, 86);
				mainGroupQuickTips.setText("Quick Tips");
				{
					mainLabelQuickTipsTip = new Label(mainGroupQuickTips, SWT.NONE);
					mainLabelQuickTipsTip.setBounds(8, 19, 445, 61);
					mainLabelQuickTipsTip.setText(MainFuncs.getTip());
				}
			}
			{
				mainTabFolder = new TabFolder(this, SWT.NONE);
				mainTabFolder.setSelection(0);
				mainTabFolder.setBounds(7, 92, 793, 491);

				{
					/**
					 * Search Tab
					 * ==========
					 * Contains search fields, results and stock and sale actions
					 */
					searchTabItem = new TabItem(mainTabFolder, SWT.NONE);
					searchTabItem.setText("Search");
					searchTabItem.setToolTipText("Search for Albums");
					{
						searchTabComposite = new Composite(mainTabFolder, SWT.NONE);
						searchTabComposite.setLayout(null);
						searchTabItem.setControl(searchTabComposite);
						{
							searchGroupOptions = new Group(searchTabComposite, SWT.NONE);
							searchGroupOptions.setLayout(null);
							searchGroupOptions.setText("Search by");
							searchGroupOptions.setBounds(5, 0, 355, 313);
							{
								searchBulletByAlbum = new Button(searchGroupOptions, SWT.RADIO | SWT.LEFT);
								searchBulletByAlbum.setText("Search by album ID:");
								searchBulletByAlbum.setBounds(12, 16, 118, 22);
								searchBulletByAlbum.setSelection(true);
							}
							{
								searchBulletOtherParameters = new Button(searchGroupOptions, SWT.RADIO | SWT.LEFT);
								searchBulletOtherParameters.setText("Search by other parameters:");
								searchBulletOtherParameters.setBounds(12, 42, 173, 22);
							}
							{
								searchTextBoxAlbumID = new Text(searchGroupOptions, SWT.BORDER);
								searchTextBoxAlbumID.setBounds(138, 16, 205, 22);
								searchTextBoxAlbumID.setToolTipText("Enter album ID");
							}
							{
								searchCheckBoxAlbumName = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxAlbumName.setText("Album name:");
								searchCheckBoxAlbumName.setBounds(12, 69, 82, 22);
							}
							{
								searchCheckBoxArtist = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxArtist.setText("Artist:");
								searchCheckBoxArtist.setBounds(12, 93, 82, 21);
							}
							{
								searchCheckBoxYear = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxYear.setText("Year from:");
								searchCheckBoxYear.setBounds(12, 117, 82, 23);
							}
							{
								searchCheckBoxSongNames = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxSongNames.setText("Song name(s):");
								searchCheckBoxSongNames.setBounds(12, 141, 88, 22);
							}
							{
								searchCheckBoxGenres = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenres.setText("Genre(s):");
								searchCheckBoxGenres.setBounds(12, 186, 82, 22);
							}
							{
								searchTextBoxAlbumName = new Text(searchGroupOptions, SWT.BORDER);
								searchTextBoxAlbumName.setBounds(106, 69, 237, 22);
							}
							{
								searchTextBoxArtist = new Text(searchGroupOptions, SWT.BORDER);
								searchTextBoxArtist.setBounds(106, 93, 237, 22);
							}
							{
								searchTextBoxYearFrom = new Text(searchGroupOptions, SWT.BORDER);
								searchTextBoxYearFrom.setBounds(106, 117, 54, 22);
							}
							{
								searchLabelYearTo = new Label(searchGroupOptions, SWT.NONE);
								searchLabelYearTo.setText("To:");
								searchLabelYearTo.setBounds(168, 120, 20, 16);
							}
							{
								searchTextBoxYearTo = new Text(searchGroupOptions, SWT.BORDER);
								searchTextBoxYearTo.setBounds(188, 117, 60, 22);
							}
							{
								searchTextBoxSongNames = new Text(searchGroupOptions, SWT.BORDER);
								searchTextBoxSongNames.setBounds(106, 141, 237, 22);
							}
							{
								searchCheckBoxGenreRock = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenreRock.setText("Rock");
								searchCheckBoxGenreRock.setBounds(12, 207, 60, 16);
							}
							{
								searchCheckBoxGenreJazz = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenreJazz.setText("Jazz");
								searchCheckBoxGenreJazz.setBounds(12, 227, 60, 16);
							}
							{
								searchCheckBoxGenre03 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre03.setText("Genre03");
								searchCheckBoxGenre03.setBounds(76, 207, 60, 16);
							}
							{
								searchCheckBoxGenre04 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre04.setText("Genre04");
								searchCheckBoxGenre04.setBounds(76, 227, 60, 16);
							}
							{
								searchCheckBoxGenre05 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre05.setText("Genre05");
								searchCheckBoxGenre05.setBounds(141, 207, 60, 16);
							}
							{
								searchCheckBoxGenre06 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre06.setText("Genre06");
								searchCheckBoxGenre06.setBounds(141, 227, 60, 16);
							}
							{
								searchCheckBoxGenre07 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre07.setText("Genre07");
								searchCheckBoxGenre07.setBounds(205, 207, 60, 16);
							}
							{
								searchCheckBoxGenre08 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre08.setText("Genre08");
								searchCheckBoxGenre08.setBounds(205, 227, 60, 16);
							}
							{
								searchCheckBoxGenre09 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre09.setText("Genre09");
								searchCheckBoxGenre09.setBounds(270, 207, 60, 16);
							}
							{
								searchCheckBoxGenre10 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre10.setText("Genre10");
								searchCheckBoxGenre10.setBounds(270, 227, 60, 16);
							}
							{
								searchCheckBoxGenreOther = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenreOther.setText("Other:");
								searchCheckBoxGenreOther.setBounds(12, 243, 54, 22);
							}
							{
								searchTextBoxGenreOther = new Text(searchGroupOptions, SWT.BORDER);
								searchTextBoxGenreOther.setBounds(74, 243, 273, 22);
							}
							{
								searchButtonClear = new Button(searchGroupOptions, SWT.PUSH | SWT.CENTER);
								searchButtonClear.setText("Clear Fields");
								searchButtonClear.setBounds(12, 273, 156, 33);
							}
							{
								searchButtonSearch = new Button(searchGroupOptions, SWT.PUSH | SWT.CENTER);
								searchButtonSearch.setText("Search");
								searchButtonSearch.setBounds(174, 273, 169, 33);
							}
							{
								searchCompositeStockField = new Composite(searchGroupOptions, SWT.NONE);
								searchCompositeStockField.setLayout(null);
								searchCompositeStockField.setBounds(12, 166, 331, 20);
								{
									searchLabelStock = new Label(searchCompositeStockField, SWT.NONE);
									searchLabelStock.setText("Stock:");
									searchLabelStock.setBounds(0, 2, 36, 16);
								}
								{
									searchBulletInStockInStore = new Button(searchCompositeStockField, SWT.RADIO | SWT.LEFT);
									searchBulletInStockInStore.setText("In store");
									searchBulletInStockInStore.setBounds(223, -1, 60, 22);
								}
								{
									searchBulletInStockInNetwork = new Button(searchCompositeStockField, SWT.RADIO | SWT.LEFT);
									searchBulletInStockInNetwork.setText("In network");
									searchBulletInStockInNetwork.setBounds(147, -1, 76, 22);
								}
								{
									searchBulletInStockAll = new Button(searchCompositeStockField, SWT.RADIO | SWT.LEFT);
									searchBulletInStockAll.setText("All");
									searchBulletInStockAll.setBounds(96, -1, 33, 22);
									searchBulletInStockAll.setSelection(true);
								}
							}
						}
						{
							searchGroupResults = new Group(searchTabComposite, SWT.NONE);
							searchGroupResults.setLayout(null);
							searchGroupResults.setText("Search Results");
							searchGroupResults.setBounds(366, 0, 419, 465);
							{
								searchTableAlbumResults = new Table(searchGroupResults, SWT.BORDER | SWT.MULTI
										| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
								searchTableAlbumResults.setBounds(12, 20, 395, 225);
								searchTableAlbumResults.setHeaderVisible(true);
								searchTableAlbumResults.setLinesVisible(true);
								int numOfColumns = 6;
								int tableWidth = searchTableAlbumResults.getClientArea().width - getBorderWidth()*2;
								
								searchTableColumnAlbumID = new TableColumn(searchTableAlbumResults, SWT.NONE);
								searchTableColumnAlbumID.setText("Album ID");
								searchTableColumnAlbumID.setResizable(true);
								searchTableColumnAlbumID.setMoveable(true);
								searchTableColumnAlbumID.setWidth(tableWidth / numOfColumns);
								
								searchTableColumnAlbumName = new TableColumn(searchTableAlbumResults, SWT.NONE);
								searchTableColumnAlbumName.setText("Album Name");
								searchTableColumnAlbumName.setResizable(true);
								searchTableColumnAlbumName.setMoveable(true);
								searchTableColumnAlbumName.setWidth(tableWidth / numOfColumns);
								
								searchTableColumnAlbumArtist = new TableColumn(searchTableAlbumResults, SWT.NONE);
								searchTableColumnAlbumArtist.setText("Artist");
								searchTableColumnAlbumArtist.setResizable(true);
								searchTableColumnAlbumArtist.setMoveable(true);
								searchTableColumnAlbumArtist.setWidth(tableWidth / numOfColumns);
								
								searchTableColumnAlbumYear = new TableColumn(searchTableAlbumResults, SWT.NONE);
								searchTableColumnAlbumYear.setText("Year");
								searchTableColumnAlbumYear.setResizable(true);
								searchTableColumnAlbumYear.setMoveable(true);
								searchTableColumnAlbumYear.setWidth(tableWidth / numOfColumns);
								
								searchTableColumnAlbumGenre = new TableColumn(searchTableAlbumResults, SWT.NONE);
								searchTableColumnAlbumGenre.setText("Genre");
								searchTableColumnAlbumGenre.setResizable(true);
								searchTableColumnAlbumGenre.setMoveable(true);
								searchTableColumnAlbumGenre.setWidth(tableWidth / numOfColumns);
								
								searchTableColumnAlbumLength = new TableColumn(searchTableAlbumResults, SWT.NONE);
								searchTableColumnAlbumLength.setText("Length");
								searchTableColumnAlbumLength.setResizable(true);
								searchTableColumnAlbumLength.setMoveable(true);
								searchTableColumnAlbumLength.setWidth(tableWidth / numOfColumns);
							}
							{
								searchProgressBar = new ProgressBar(searchGroupResults, SWT.NONE);
								searchProgressBar.setBounds(15, 251, 392, 22);
							}
							{
								searchTableSongResults = new Table(searchGroupResults, SWT.BORDER | SWT.MULTI
										| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
								searchTableSongResults.setBounds(12, 286, 395, 170);
								searchTableSongResults.setHeaderVisible(true);
								searchTableSongResults.setLinesVisible(true);
								
								int tableWidth = searchTableSongResults.getClientArea().width - getBorderWidth()*2;
								int numOfColumns = 3;
								
								searchTableColumnSongName = new TableColumn(searchTableSongResults, SWT.NONE);
								searchTableColumnSongName.setText("Song name");
								searchTableColumnSongName.setResizable(true);
								searchTableColumnSongName.setMoveable(true);
								searchTableColumnSongName.setWidth(tableWidth / numOfColumns);
								
								searchTableColumnSongArtist = new TableColumn(searchTableSongResults, SWT.NONE);
								searchTableColumnSongArtist.setText("Artist");
								searchTableColumnSongArtist.setResizable(true);
								searchTableColumnSongArtist.setMoveable(true);
								searchTableColumnSongArtist.setWidth(tableWidth / numOfColumns);
								
								searchTableColumnSongLength = new TableColumn(searchTableSongResults, SWT.NONE);
								searchTableColumnSongLength.setText("Length");
								searchTableColumnSongLength.setResizable(true);
								searchTableColumnSongLength.setMoveable(true);
								searchTableColumnSongLength.setWidth(tableWidth / numOfColumns);
							}
							
						}
						{
							searchGroupStockInfo = new Group(searchTabComposite, SWT.NONE);
							searchGroupStockInfo.setLayout(null);
							searchGroupStockInfo.setText("Stock Information");
							searchGroupStockInfo.setBounds(5, 319, 171, 146);
							{
								searchLabelStockInfoStoreStock = new Label(searchGroupStockInfo, SWT.NONE);
								searchLabelStockInfoStoreStock.setText("Store stock:");
								searchLabelStockInfoStoreStock.setBounds(12, 22, 152, 19);
							}
							{
								searchLabelStockInfoLocation = new Label(searchGroupStockInfo, SWT.NONE);
								searchLabelStockInfoLocation.setText("Storage location: ");
								searchLabelStockInfoLocation.setBounds(12, 44, 152, 19);
							}
							{
								searchLabelStockInfoPrice = new Label(searchGroupStockInfo, SWT.NONE);
								searchLabelStockInfoPrice.setText("Price:");
								searchLabelStockInfoPrice.setBounds(12, 66, 152, 19);
							}
							{
								searchButtonStockInfoOrder = new Button(searchGroupStockInfo, SWT.PUSH | SWT.CENTER);
								searchButtonStockInfoOrder.setText("Place an Order...");
								searchButtonStockInfoOrder.setBounds(12, 91, 147, 45);
							}
						}
						{
							searchGroupSaleInfo = new Group(searchTabComposite, SWT.NONE);
							searchGroupSaleInfo.setLayout(null);
							searchGroupSaleInfo.setText("Sale");
							searchGroupSaleInfo.setBounds(182, 319, 177, 146);
							{
								searchLabelSaleInfoQuantity = new Label(searchGroupSaleInfo, SWT.NONE);
								searchLabelSaleInfoQuantity.setText("Add to sale with quantity:");
								searchLabelSaleInfoQuantity.setBounds(8, 22, 127, 22);
							}
							{
								searchTextBoxSaleInfoQuantity = new Text(searchGroupSaleInfo, SWT.BORDER);
								searchTextBoxSaleInfoQuantity.setText("1");
								searchTextBoxSaleInfoQuantity.setBounds(137, 19, 28, 22);
							}
							{
								searchButtonSaleInfoSale = new Button(searchGroupSaleInfo, SWT.PUSH | SWT.CENTER);
								searchButtonSaleInfoSale.setText("Add to Sale...");
								searchButtonSaleInfoSale.setBounds(8, 91, 157, 45);
							}
						}
					}
					
					// initialize search tab view
					SearchFuncs.initSearchTabView();
					// initialize search listeners
					SearchFuncs.initSearchListeners();
				}
				{
					/**
					 * Sale tab
					 * ========
					 * Manage current sale
					 * 
					 */
					saleTabItem = new TabItem(mainTabFolder, SWT.NONE);
					saleTabItem.setText("Sale");
					saleTabItem.setToolTipText("Manage current sale");
					{
						saleCompositeMain = new Composite(mainTabFolder, SWT.NONE);
						saleCompositeMain.setLayout(null);
						saleTabItem.setControl(saleCompositeMain);
						{
							saleGroupSaleDetails = new Group(saleCompositeMain, SWT.NONE);
							saleGroupSaleDetails.setLayout(null);
							saleGroupSaleDetails.setText("Manage Current Sale");
							saleGroupSaleDetails.setBounds(0, 0, 423, 90);
							{
								saleLabelSaleID = new Label(saleGroupSaleDetails, SWT.NONE);
								saleLabelSaleID.setText("Sale ID:");
								saleLabelSaleID.setBounds(7, 24, 47, 21);
								saleLabelSaleID.setSize(47, 18);
							}
							{
								saleLabelSaleIDInput = new Label(saleGroupSaleDetails, SWT.BORDER);
								saleLabelSaleIDInput.setText("STR0000-000000-0000");
								saleLabelSaleIDInput.setBounds(66, 24, 126, 18);
							}
							{
								saleLabelSalesmanIDName = new Label(saleGroupSaleDetails, SWT.NONE);
								saleLabelSalesmanIDName.setText("Salesman:");
								saleLabelSalesmanIDName.setBounds(7, 54, 47, 18);
							}
							{
								saleComboSalesmanIDNameInput = new Combo(saleGroupSaleDetails, SWT.NONE);
								saleComboSalesmanIDNameInput.setBounds(66, 53, 191, 21);
							}
							{
								saleLabelSaleDate = new Label(saleGroupSaleDetails, SWT.NONE);
								saleLabelSaleDate.setText("Date of sale:");
								saleLabelSaleDate.setBounds(269, 24, 73, 18);
							}
							{
								saleLabelSaleTime = new Label(saleGroupSaleDetails, SWT.NONE);
								saleLabelSaleTime.setText("Time of sale:");
								saleLabelSaleTime.setBounds(269, 54, 73, 18);
							}
							{
								saleLabelDateInput = new Label(saleGroupSaleDetails, SWT.BORDER);
								saleLabelDateInput.setBounds(343, 24, 69, 18);
								saleLabelDateInput.setText(MainFuncs.getDate());
							}
							{
								saleLabelTimeInput = new Label(saleGroupSaleDetails, SWT.BORDER);
								saleLabelTimeInput.setBounds(343, 54, 69, 18);
								saleLabelTimeInput.setText(MainFuncs.getTime());
							}
						}
						{
							saleTableSaleItems = new Table(saleCompositeMain, SWT.BORDER | SWT.MULTI
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
							saleTableSaleItems.setBounds(16, 108, 759, 267);
							saleTableSaleItems.setHeaderVisible(true);
							saleTableSaleItems.setLinesVisible(true);
							int tableWidth = saleTableSaleItems.getClientArea().width - getBorderWidth()*2;
							int numOfColumns = 5;
							
							// adding columns
							saleTableColumnAlbumID = new TableColumn(saleTableSaleItems, SWT.NONE);
							saleTableColumnAlbumID.setText("Album ID");
							saleTableColumnAlbumID.setResizable(true);
							saleTableColumnAlbumID.setMoveable(true);
							saleTableColumnAlbumID.setWidth(tableWidth / numOfColumns);
							
							saleTableColumnAlbumName = new TableColumn(saleTableSaleItems, SWT.NONE);
							saleTableColumnAlbumName.setText("Album name");
							saleTableColumnAlbumName.setResizable(true);
							saleTableColumnAlbumName.setMoveable(true);
							saleTableColumnAlbumName.setWidth(111);
							saleTableColumnAlbumName.setWidth(tableWidth / numOfColumns);
							
							saleTableColumnQuantity = new TableColumn(saleTableSaleItems, SWT.NONE);
							saleTableColumnQuantity.setText("Quantity");
							saleTableColumnQuantity.setResizable(true);
							saleTableColumnQuantity.setMoveable(true);
							saleTableColumnQuantity.setWidth(tableWidth / numOfColumns);
							
							saleTableColumnPricePerItem = new TableColumn(saleTableSaleItems, SWT.NONE);
							saleTableColumnPricePerItem.setText("Price per item");
							saleTableColumnPricePerItem.setResizable(true);
							saleTableColumnPricePerItem.setMoveable(true);
							saleTableColumnPricePerItem.setWidth(tableWidth / numOfColumns);
							
							saleTableColumnPriceTotal = new TableColumn(saleTableSaleItems, SWT.NONE);
							saleTableColumnPriceTotal.setText("Total price");
							saleTableColumnPriceTotal.setResizable(true);
							saleTableColumnPriceTotal.setMoveable(true);
							saleTableColumnPriceTotal.setWidth(tableWidth / numOfColumns);
						}
						{
							saleLabelTotalPriceValue = new Label(saleCompositeMain, SWT.BORDER);
							saleLabelTotalPriceValue.setBounds(679, 383, 94, 22);
						}
						{
							saleLabelTotalPrice = new Label(saleCompositeMain, SWT.NONE);
							saleLabelTotalPrice.setText("Total price:");
							saleLabelTotalPrice.setBounds(598, 384, 76, 22);
							saleLabelTotalPrice.setAlignment(SWT.RIGHT);
						}
						{
							saleButtonRemoveItem = new Button(saleCompositeMain, SWT.PUSH | SWT.CENTER);
							saleButtonRemoveItem.setText("Remove Item");
							saleButtonRemoveItem.setBounds(18, 384, 110, 30);
						}
						{
							saleButtonClearSale = new Button(saleCompositeMain, SWT.PUSH | SWT.CENTER);
							saleButtonClearSale.setText("Clear Sale");
							saleButtonClearSale.setBounds(134, 384, 110, 30);
						}
						{
							saleButtonMakeSale = new Button(saleCompositeMain, SWT.PUSH | SWT.CENTER);
							saleButtonMakeSale.setText("Make Sale");
							saleButtonMakeSale.setBounds(680, 412, 93, 41);
						}
					}
				}
				{
					/**
					 * Stock tab
					 * =========
					 * Manage Stock, orders and requests
					 * 
					 */
					stockTabItem = new TabItem(mainTabFolder, SWT.NONE);
					stockTabItem.setText("Stock");
					stockTabItem.setToolTipText("View and manage orders and requests");
					{
						stockTabComposite = new Composite(mainTabFolder, SWT.NONE);
						stockTabComposite.setLayout(null);
						stockTabItem.setControl(stockTabComposite);
						{
							stockGroupOrderForm = new Group(stockTabComposite, SWT.NONE);
							stockGroupOrderForm.setLayout(null);
							stockGroupOrderForm.setText("Order Albums");
							stockGroupOrderForm.setBounds(2, 0, 779, 147);
							{
								stockLabelOrderID = new Label(stockGroupOrderForm, SWT.NONE);
								stockLabelOrderID.setText("Order ID:");
								stockLabelOrderID.setBounds(8, 19, 74, 22);
							}
							{
								stockLabelOrderIDInput = new Label(stockGroupOrderForm, SWT.BORDER);
								stockLabelOrderIDInput.setBounds(88, 18, 153, 22);
								stockLabelOrderIDInput.setText("ORD-0000-0000-000000-000");
							}
							{
								stockLabelAlbumID = new Label(stockGroupOrderForm, SWT.NONE);
								stockLabelAlbumID.setText("Album ID:");
								stockLabelAlbumID.setBounds(8, 49, 74, 22);
							}
							{
								stockTextBoxAlbumIDInput = new Text(stockGroupOrderForm, SWT.BORDER);
								stockTextBoxAlbumIDInput.setBounds(87, 47, 154, 22);
							}
							{
								stockLabelDate = new Label(stockGroupOrderForm, SWT.NONE);
								stockLabelDate.setText("Order date:");
								stockLabelDate.setBounds(8, 79, 74, 22);
							}
							{
								stockLabelOrderDateInput = new Label(stockGroupOrderForm, SWT.BORDER);
								stockLabelOrderDateInput.setBounds(86, 79, 70, 22);
								stockLabelOrderDateInput.setText(MainFuncs.getDate());
							}
							{
								stockButtonCheckAvailability = new Button(stockGroupOrderForm, SWT.PUSH | SWT.CENTER);
								stockButtonCheckAvailability.setText("Check Availability");
								stockButtonCheckAvailability.setBounds(8, 108, 235, 30);
							}
							{
								stockLabelOrderFromStore = new Label(stockGroupOrderForm, SWT.NONE);
								stockLabelOrderFromStore.setText("Order from:");
								stockLabelOrderFromStore.setBounds(256, 18, 79, 24);
							}
							{
								stockTableOrderAvailableStores = new Table(stockGroupOrderForm, SWT.BORDER | SWT.MULTI
										| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
								stockTableOrderAvailableStores.setBounds(254, 42, 284, 94);
								stockTableOrderAvailableStores.setHeaderVisible(true);
								stockTableOrderAvailableStores.setLinesVisible(true);
								int numOfColumns = 3;
								int tableWidth = stockTableOrderAvailableStores.getClientArea().width - getBorderWidth()*2;
								
								stockTableColumnStoreID = new TableColumn(stockTableOrderAvailableStores, SWT.NONE);
								stockTableColumnStoreID.setText("Store ID");
								stockTableColumnStoreID.setResizable(true);
								stockTableColumnStoreID.setMoveable(true);
								stockTableColumnStoreID.setWidth(tableWidth / numOfColumns);
								
								stockTableColumnStoreCity = new TableColumn(stockTableOrderAvailableStores, SWT.NONE);
								stockTableColumnStoreCity.setText("City");
								stockTableColumnStoreCity.setResizable(true);
								stockTableColumnStoreCity.setMoveable(true);
								stockTableColumnStoreCity.setWidth(tableWidth / numOfColumns);
								
								stockTableColumnQuantity = new TableColumn(stockTableOrderAvailableStores, SWT.NONE);
								stockTableColumnQuantity.setText("Quantity");
								stockTableColumnQuantity.setResizable(true);
								stockTableColumnQuantity.setMoveable(true);
								stockTableColumnQuantity.setWidth(tableWidth / numOfColumns);
								
								stockTableColumnPrice = new TableColumn(stockTableOrderAvailableStores, SWT.NONE);
								stockTableColumnPrice.setText("Price");
								stockTableColumnPrice.setResizable(true);
								stockTableColumnPrice.setMoveable(true);
								stockTableColumnPrice.setWidth(tableWidth / numOfColumns);
							}
							{
								stockLabelQuantityInStock = new Label(stockGroupOrderForm, SWT.NONE);
								stockLabelQuantityInStock.setText("Quantity:");
								stockLabelQuantityInStock.setBounds(656, 19, 52, 22);
							}
							{
								stockLabelQuantityInStockInput = new Label(stockGroupOrderForm, SWT.BORDER);
								stockLabelQuantityInStockInput.setBounds(709, 18, 57, 22);
							}
							{
								stockLabelPrice = new Label(stockGroupOrderForm, SWT.NONE);
								stockLabelPrice.setText("Price:");
								stockLabelPrice.setBounds(552, 20, 52, 22);
							}
							{
								stockLabelStorePriceInput = new Label(stockGroupOrderForm, SWT.BORDER);
								stockLabelStorePriceInput.setBounds(604, 18, 46, 22);
							}
							{
								stockLabelStorageLocation = new Label(stockGroupOrderForm, SWT.NONE);
								stockLabelStorageLocation.setText("Location:");
								stockLabelStorageLocation.setBounds(551, 50, 52, 22);
							}
							{
								stockLabelStorageLocationInput = new Label(stockGroupOrderForm, SWT.BORDER);
								stockLabelStorageLocationInput.setBounds(605, 49, 161, 22);
							}
							{
								stockButtonPlaceOrder = new Button(stockGroupOrderForm, SWT.PUSH | SWT.CENTER);
								stockButtonPlaceOrder.setText("Place Order");
								stockButtonPlaceOrder.setBounds(665, 109, 102, 30);
							}
							{
								stockButtonClearOrder = new Button(stockGroupOrderForm, SWT.PUSH | SWT.CENTER);
								stockButtonClearOrder.setText("Clear Fields");
								stockButtonClearOrder.setBounds(551, 109, 108, 30);
							}
							{
								stockLabelQuantityToOrder = new Label(stockGroupOrderForm, SWT.NONE);
								stockLabelQuantityToOrder.setText("Quantity to order:");
								stockLabelQuantityToOrder.setBounds(551, 83, 108, 22);
							}
							{
								stockTextBoxQuantityToOrder = new Text(stockGroupOrderForm, SWT.BORDER);
								stockTextBoxQuantityToOrder.setText("1");
								stockTextBoxQuantityToOrder.setBounds(665, 82, 100, 22);
							}
						}
						{
							stockTableOrders = new Table(stockTabComposite, SWT.BORDER | SWT.MULTI
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
							stockTableOrders.setBounds(5, 174, 770, 110);
							stockTableOrders.setHeaderVisible(true);
							stockTableOrders.setLinesVisible(true);
							int numOfColumns = 7;
							int tableWidth = stockTableOrders.getClientArea().width - getBorderWidth()*2;
							
							stockTableColumnOrdersOrderID = new TableColumn(stockTableOrders, SWT.NONE);
							stockTableColumnOrdersOrderID.setText("Order ID");
							stockTableColumnOrdersOrderID.setResizable(true);
							stockTableColumnOrdersOrderID.setMoveable(true);
							stockTableColumnOrdersOrderID.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnOrdersSupplierID = new TableColumn(stockTableOrders, SWT.NONE);
							stockTableColumnOrdersSupplierID.setText("Supplier ID");
							stockTableColumnOrdersSupplierID.setResizable(true);
							stockTableColumnOrdersSupplierID.setMoveable(true);
							stockTableColumnOrdersSupplierID.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnOrdersAlbumID = new TableColumn(stockTableOrders, SWT.NONE);
							stockTableColumnOrdersAlbumID.setText("Album ID");
							stockTableColumnOrdersAlbumID.setResizable(true);
							stockTableColumnOrdersAlbumID.setMoveable(true);
							stockTableColumnOrdersAlbumID.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnOrdersQuantity = new TableColumn(stockTableOrders, SWT.NONE);
							stockTableColumnOrdersQuantity.setText("Quantity");
							stockTableColumnOrdersQuantity.setResizable(true);
							stockTableColumnOrdersQuantity.setMoveable(true);
							stockTableColumnOrdersQuantity.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnOrdersDate = new TableColumn(stockTableOrders, SWT.NONE);
							stockTableColumnOrdersDate.setText("Date");
							stockTableColumnOrdersDate.setResizable(true);
							stockTableColumnOrdersDate.setMoveable(true);
							stockTableColumnOrdersDate.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnOrdersStatus = new TableColumn(stockTableOrders, SWT.NONE);
							stockTableColumnOrdersStatus.setText("Status");
							stockTableColumnOrdersStatus.setResizable(true);
							stockTableColumnOrdersStatus.setMoveable(true);
							stockTableColumnOrdersStatus.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnOrdersCompletionDate = new TableColumn(stockTableOrders, SWT.NONE);
							stockTableColumnOrdersCompletionDate.setText("Completion Date");
							stockTableColumnOrdersCompletionDate.setResizable(true);
							stockTableColumnOrdersCompletionDate.setMoveable(true);
							stockTableColumnOrdersCompletionDate.setWidth(tableWidth / numOfColumns);
						}
						{
							stockTableRequests = new Table(stockTabComposite, SWT.BORDER | SWT.MULTI // TODO
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
							stockTableRequests.setBounds(4, 317, 770, 104);
							stockTableRequests.setHeaderVisible(true);
							stockTableRequests.setLinesVisible(true);
							int numOfColumns = 7;
							int tableWidth = stockTableRequests.getClientArea().width - getBorderWidth()*2;
							
							stockTableColumnRequestsOrderID = new TableColumn(stockTableRequests, SWT.NONE);
							stockTableColumnRequestsOrderID.setText("Order ID");
							stockTableColumnRequestsOrderID.setResizable(true);
							stockTableColumnRequestsOrderID.setMoveable(true);
							stockTableColumnRequestsOrderID.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnRequestsOrderingStoreID = new TableColumn(stockTableRequests, SWT.NONE);
							stockTableColumnRequestsOrderingStoreID.setText("Requesting Store ID");
							stockTableColumnRequestsOrderingStoreID.setResizable(true);
							stockTableColumnRequestsOrderingStoreID.setMoveable(true);
							stockTableColumnRequestsOrderingStoreID.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnRequestsAlbumID = new TableColumn(stockTableRequests, SWT.NONE);
							stockTableColumnRequestsAlbumID.setText("Album ID");
							stockTableColumnRequestsAlbumID.setResizable(true);
							stockTableColumnRequestsAlbumID.setMoveable(true);
							stockTableColumnRequestsAlbumID.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnRequestsQuantity = new TableColumn(stockTableRequests, SWT.NONE);
							stockTableColumnRequestsQuantity.setText("Quantity");
							stockTableColumnRequestsQuantity.setResizable(true);
							stockTableColumnRequestsQuantity.setMoveable(true);
							stockTableColumnRequestsQuantity.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnRequestsDate = new TableColumn(stockTableRequests, SWT.NONE);
							stockTableColumnRequestsDate.setText("Date");
							stockTableColumnRequestsDate.setResizable(true);
							stockTableColumnRequestsDate.setMoveable(true);
							stockTableColumnRequestsDate.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnRequestsStatus = new TableColumn(stockTableRequests, SWT.NONE);
							stockTableColumnRequestsStatus.setText("Status");
							stockTableColumnRequestsStatus.setResizable(true);
							stockTableColumnRequestsStatus.setMoveable(true);
							stockTableColumnRequestsStatus.setWidth(tableWidth / numOfColumns);
							
							stockTableColumnRequestsCompletionDate = new TableColumn(stockTableRequests, SWT.NONE);
							stockTableColumnRequestsCompletionDate.setText("Completion Date");
							stockTableColumnRequestsCompletionDate.setResizable(true);
							stockTableColumnRequestsCompletionDate.setMoveable(true);
							stockTableColumnRequestsCompletionDate.setWidth(tableWidth / numOfColumns);
						}
						{
							stockLabelRequests = new Label(stockTabComposite, SWT.NONE);
							stockLabelRequests.setText("Requests:");
							stockLabelRequests.setBounds(6, 295, 64, 20);
						}
						{
							stockButtonApproveRequest = new Button(stockTabComposite, SWT.PUSH | SWT.CENTER);
							stockButtonApproveRequest.setText("Approve Request");
							stockButtonApproveRequest.setBounds(674, 429, 101, 24);
						}
						{
							stockButtonDenyRequest = new Button(stockTabComposite, SWT.PUSH | SWT.CENTER);
							stockButtonDenyRequest.setText("Deny Request");
							stockButtonDenyRequest.setBounds(567, 429, 101, 24);
						}
						{
							stockLabelOrders = new Label(stockTabComposite, SWT.NONE);
							stockLabelOrders.setText("Orders:");
							stockLabelOrders.setBounds(7, 153, 60, 20);
						}
						{
							stockButtonCancelOrder = new Button(stockTabComposite, SWT.PUSH | SWT.CENTER);
							stockButtonCancelOrder.setText("Cancel Order");
							stockButtonCancelOrder.setBounds(672, 286, 101, 24);
						}
						{
							stockButtonRemoveOrder = new Button(stockTabComposite, SWT.PUSH | SWT.CENTER);
							stockButtonRemoveOrder.setText("Remove Order");
							stockButtonRemoveOrder.setBounds(565, 286, 101, 24);
						}
					}
				}
				{
					/**
					 * Management tab
					 * ==============
					 * Manage Employees and database
					 * 
					 */
					managementTabItem = new TabItem(mainTabFolder, SWT.NONE);
					managementTabItem.setText("Management");
					managementTabItem.setToolTipText("View and manage employees, update database");
					{
						manageMainComposite = new Composite(mainTabFolder, SWT.NONE);
						manageMainComposite.setLayout(null);
						managementTabItem.setControl(manageMainComposite);
						{
							manageLabelEmployees = new Label(manageMainComposite, SWT.NONE);
							manageLabelEmployees.setText("Employees:");
							manageLabelEmployees.setBounds(12, 12, 60, 22);
						}
						{
							manageTableEmployees = new Table(manageMainComposite, SWT.BORDER | SWT.MULTI // TODO
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
							manageTableEmployees.setBounds(13, 35, 423, 250);
							manageTableEmployees.setHeaderVisible(true);
							manageTableEmployees.setLinesVisible(true);
							int numOfColumns = 4;
							int tableWidth = manageTableEmployees.getClientArea().width - getBorderWidth()*2;
							
							manageTableColumnEmployeeID = new TableColumn(manageTableEmployees, SWT.NONE);
							manageTableColumnEmployeeID.setText("Employee ID");
							manageTableColumnEmployeeID.setResizable(true);
							manageTableColumnEmployeeID.setMoveable(true);
							manageTableColumnEmployeeID.setWidth(tableWidth / numOfColumns);
							
							manageTableColumnEmployeePName = new TableColumn(manageTableEmployees, SWT.NONE);
							manageTableColumnEmployeePName.setText("First name");
							manageTableColumnEmployeePName.setResizable(true);
							manageTableColumnEmployeePName.setMoveable(true);
							manageTableColumnEmployeePName.setWidth(tableWidth / numOfColumns);
							
							manageTableColumnEmployeeLName = new TableColumn(manageTableEmployees, SWT.NONE);
							manageTableColumnEmployeeLName.setText("Last name");
							manageTableColumnEmployeeLName.setResizable(true);
							manageTableColumnEmployeeLName.setMoveable(true);
							manageTableColumnEmployeeLName.setWidth(tableWidth / numOfColumns);
							
							manageTableColumnEmployeePosition = new TableColumn(manageTableEmployees, SWT.NONE);
							manageTableColumnEmployeePosition.setText("Position");
							manageTableColumnEmployeePosition.setResizable(true);
							manageTableColumnEmployeePosition.setMoveable(true);
							manageTableColumnEmployeePosition.setWidth(tableWidth / numOfColumns);
						}
						{
							manageGroupEditEmployee = new Group(manageMainComposite, SWT.NONE);
							manageGroupEditEmployee.setLayout(null);
							manageGroupEditEmployee.setText("Edit Employee Details");
							manageGroupEditEmployee.setBounds(449, 12, 324, 441);
							{
								manageLabelEmployeeID = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeeID.setText("ID:");
								manageLabelEmployeeID.setBounds(12, 79, 25, 22);
							}
							{
								manageTextBoxEmployeeIDInput = new Text(manageGroupEditEmployee, SWT.BORDER);
								manageTextBoxEmployeeIDInput.setBounds(12, 101, 129, 22);
							}
							{
								manageLabelEmployeeFName = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeeFName.setText("First name:");
								manageLabelEmployeeFName.setBounds(12, 131, 62, 22);
							}
							{
								manageTextBoxEmployeeFNameInput = new Text(manageGroupEditEmployee, SWT.BORDER);
								manageTextBoxEmployeeFNameInput.setBounds(12, 153, 129, 22);
							}
							{
								manageLabelEmployeeLName = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeeLName.setText("Last name:");
								manageLabelEmployeeLName.setBounds(177, 131, 60, 22);
							}
							{
								manageTextBoxEmployeeLNameInput = new Text(manageGroupEditEmployee, SWT.BORDER);
								manageTextBoxEmployeeLNameInput.setBounds(178, 153, 129, 22);
							}
							{
								manageLabelEmployeeEmploymentDate = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeeEmploymentDate.setText("Date of employment:");
								manageLabelEmployeeEmploymentDate.setBounds(12, 24, 112, 22);
							}
							{
								manageLabelEmployeeBirth = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeeBirth.setText("Date of birth:");
								manageLabelEmployeeBirth.setBounds(177, 79, 78, 22);
							}
							{
								manageTextBoxEmployeeBirthInput = new Text(manageGroupEditEmployee, SWT.BORDER);
								manageTextBoxEmployeeBirthInput.setBounds(177, 101, 129, 22);
							}
							{
								manageLabelEmployeeAddress = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeeAddress.setText("Address:");
								manageLabelEmployeeAddress.setBounds(12, 183, 51, 22);
							}
							{
								manageTextBoxEmployeeAddressInput = new Text(manageGroupEditEmployee, SWT.BORDER);
								manageTextBoxEmployeeAddressInput.setBounds(12, 205, 296, 22);
							}
							{
								manageLabelEmployeeStoreID = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeeStoreID.setText("Employing store ID:");
								manageLabelEmployeeStoreID.setBounds(176, 24, 106, 22);
							}
							{
								manageLabelEmployeeEmployeeStoreIDInput = new Label(manageGroupEditEmployee, SWT.BORDER);
								manageLabelEmployeeEmployeeStoreIDInput.setBounds(176, 49, 130, 22);
							}
							{
								manageLabelEmployeeEmploymentDateInput = new Label(manageGroupEditEmployee, SWT.BORDER);
								manageLabelEmployeeEmploymentDateInput.setBounds(12, 50, 130, 22);
							}
							{
								manageLabelEmployeePhone = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeePhone.setText("Phone:");
								manageLabelEmployeePhone.setBounds(12, 235, 60, 22);
							}
							{
								manageLabelEmployeeCellPhone = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeeCellPhone.setText("Cellular Phone:");
								manageLabelEmployeeCellPhone.setBounds(180, 235, 80, 22);
							}
							{
								manageTextBoxEmployeePhoneInput = new Text(manageGroupEditEmployee, SWT.BORDER);
								manageTextBoxEmployeePhoneInput.setBounds(12, 257, 131, 22);
							}
							{
								manageTextBoxEmployeeCellPhoneInput = new Text(manageGroupEditEmployee, SWT.BORDER);
								manageTextBoxEmployeeCellPhoneInput.setBounds(177, 257, 131, 22);
							}
							{
								manageLabelEmployeePosition = new Label(manageGroupEditEmployee, SWT.NONE);
								manageLabelEmployeePosition.setText("Position:");
								manageLabelEmployeePosition.setBounds(12, 291, 60, 22);
							}
							{
								manageComboEmployeePositionInput = new Combo(manageGroupEditEmployee, SWT.NONE);
								manageComboEmployeePositionInput.setBounds(78, 291, 229, 21);
							}
							{
								manageButtonEmployeeNew = new Button(manageGroupEditEmployee, SWT.PUSH | SWT.CENTER);
								manageButtonEmployeeNew.setText("New");
								manageButtonEmployeeNew.setBounds(12, 363, 60, 30);
							}
							{
								manageButtonEmployeeInsert = new Button(manageGroupEditEmployee, SWT.PUSH | SWT.CENTER);
								manageButtonEmployeeInsert.setText("Insert");
								manageButtonEmployeeInsert.setBounds(81, 363, 60, 30);
							}
							{
								manageButtonEmployeeEdit = new Button(manageGroupEditEmployee, SWT.PUSH | SWT.CENTER);
								manageButtonEmployeeEdit.setText("Edit");
								manageButtonEmployeeEdit.setBounds(12, 399, 60, 30);
							}
							{
								manageButtonEmployeeSave = new Button(manageGroupEditEmployee, SWT.PUSH | SWT.CENTER);
								manageButtonEmployeeSave.setText("Save");
								manageButtonEmployeeSave.setBounds(81, 399, 60, 30);
							}
							{
								manageButtonEmployeeRemove = new Button(manageGroupEditEmployee, SWT.PUSH | SWT.CENTER);
								manageButtonEmployeeRemove.setText("Remove Employee");
								manageButtonEmployeeRemove.setBounds(150, 399, 157, 30);
							}
							{
								manageButtonEmployeeNoSave = new Button(manageGroupEditEmployee, SWT.PUSH | SWT.CENTER);
								manageButtonEmployeeNoSave.setText("Exit Without Saving");
								manageButtonEmployeeNoSave.setBounds(150, 363, 157, 30);
							}
						}
						{
							manageGroupDBSManage = new Group(manageMainComposite, SWT.NONE);
							manageGroupDBSManage.setLayout(null);
							manageGroupDBSManage.setText("Manage Database");
							manageGroupDBSManage.setBounds(12, 293, 425, 160);
							{
								manageLabelDBSUpdate = new Label(manageGroupDBSManage, SWT.NONE);
								manageLabelDBSUpdate.setText("Select update file:");
								manageLabelDBSUpdate.setBounds(12, 26, 96, 18);
							}
							{
								manageTextBoxDBSUpdateFileInput = new Text(manageGroupDBSManage, SWT.BORDER);
								manageTextBoxDBSUpdateFileInput.setBounds(13, 49, 393, 22);
							}
							{
								manageButtonDBSBrowse = new Button(manageGroupDBSManage, SWT.PUSH | SWT.CENTER);
								manageButtonDBSBrowse.setText("Browse...");
								manageButtonDBSBrowse.setBounds(214, 82, 74, 30);
							}
							{
								manageButtonDBSUpdate = new Button(manageGroupDBSManage, SWT.PUSH | SWT.CENTER);
								manageButtonDBSUpdate.setText("Update Database");
								manageButtonDBSUpdate.setBounds(294, 82, 112, 30);
							}
							{
								manageProgressBarDBSUpdate = new ProgressBar(manageGroupDBSManage, SWT.NONE);
								manageProgressBarDBSUpdate.setBounds(13, 123, 393, 25);
							}
						}
					}

				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//////////////////////////
	//	Getters and Setters	//
	//////////////////////////

	protected static Menu getMainMenuBar() {
		return mainMenuBar;
	}

	protected static MenuItem getMenuItemSeperator() {
		return menuItemSeperator;
	}

	protected static MenuItem getMainMenuItemFile() {
		return mainMenuItemFile;
	}

	protected static Menu getFileMenu() {
		return fileMenu;
	}

	protected static MenuItem getFileMenuItemExit() {
		return fileMenuItemExit;
	}

	protected static MenuItem getMainMenuItemSearch() {
		return mainMenuItemSearch;
	}

	protected static Menu getSearchMenu() {
		return searchMenu;
	}

	protected static MenuItem getSearchMenuItemAddToSale() {
		return searchMenuItemAddToSale;
	}

	protected static MenuItem getSearchMenuItemPlaceOrder() {
		return searchMenuItemPlaceOrder;
	}

	protected static MenuItem getSearchMenuItemSearch() {
		return searchMenuItemSearch;
	}

	protected static MenuItem getSearchMenuItemClear() {
		return searchMenuItemClear;
	}

	protected static MenuItem getMainMenuItemSale() {
		return mainMenuItemSale;
	}

	protected static Menu getSaleMenu() {
		return saleMenu;
	}

	protected static MenuItem getSaleMenuItemRemove() {
		return saleMenuItemRemove;
	}

	protected static MenuItem getSaleMenuItemClear() {
		return saleMenuItemClear;
	}

	protected static MenuItem getSaleMenuItemMakeSale() {
		return saleMenuItemMakeSale;
	}

	protected static MenuItem getMainMenuItemStock() {
		return mainMenuItemStock;
	}

	protected static Menu getStockMenu() {
		return stockMenu;
	}

	protected static MenuItem getStockMenuItemCheckAvailability() {
		return stockMenuItemCheckAvailability;
	}

	protected static MenuItem getStockMenuItemClear() {
		return stockMenuItemClear;
	}

	protected static MenuItem getStockMenuItemPlaceOrder() {
		return stockMenuItemPlaceOrder;
	}

	protected static MenuItem getStockMenuItemRemoveOrder() {
		return stockMenuItemRemoveOrder;
	}

	protected static MenuItem getStockMenuItemCancelOrder() {
		return stockMenuItemCancelOrder;
	}

	protected static MenuItem getStockMenuItemDenyRequest() {
		return stockMenuItemDenyRequest;
	}

	protected static MenuItem getStockMenuItemApproveRequest() {
		return stockMenuItemApproveRequest;
	}

	protected static MenuItem getMainMenuItemManage() {
		return mainMenuItemManage;
	}

	protected static Menu getManageMenu() {
		return manageMenu;
	}

	protected static MenuItem getManageMenuItemBrowse() {
		return manageMenuItemBrowse;
	}

	protected static MenuItem getManageMenuItemUpdateDBS() {
		return manageMenuItemUpdateDBS;
	}

	protected static MenuItem getManageMenuItemNew() {
		return manageMenuItemNew;
	}

	protected static MenuItem getManageMenuItemInsert() {
		return manageMenuItemInsert;
	}

	protected static MenuItem getManageMenuItemEdit() {
		return manageMenuItemEdit;
	}

	protected static MenuItem getManageMenuItemSave() {
		return manageMenuItemSave;
	}

	protected static MenuItem getManageMenuItemExitNoSave() {
		return manageMenuItemExitNoSave;
	}

	protected static MenuItem getManageMenuItemRemoveEmployee() {
		return manageMenuItemRemoveEmployee;
	}

	protected static Group getMainGroupStoreDetails() {
		return mainGroupStoreDetails;
	}

	protected static Label getMainLabelStoreDetailsStoreID() {
		return mainLabelStoreDetailsStoreID;
	}

	protected static Label getMainLabelStoreDetailsDateTime() {
		return mainLabelStoreDetailsDateTime;
	}

	protected static Label getMainLabelStoreDetailsStoreAddress() {
		return mainLabelStoreDetailsStoreAddress;
	}

	protected static Label getMainLabelStoreDetailsStorePhone() {
		return mainLabelStoreDetailsStorePhone;
	}

	protected static Label getMainLabelStoreDetailsStoreManager() {
		return mainLabelStoreDetailsStoreManager;
	}

	protected static Group getMainGroupQuickTips() {
		return mainGroupQuickTips;
	}

	protected static Label getMainLabelQuickTipsTip() {
		return mainLabelQuickTipsTip;
	}

	protected static TabFolder getMainTabFolder() {
		return mainTabFolder;
	}

	protected static TabItem getSearchTabItem() {
		return searchTabItem;
	}

	protected static Composite getSearchTabComposite() {
		return searchTabComposite;
	}

	protected static Group getSearchGroupOptions() {
		return searchGroupOptions;
	}

	protected static Button getSearchBulletByAlbum() {
		return searchBulletByAlbum;
	}

	protected static Text getSearchTextBoxAlbumID() {
		return searchTextBoxAlbumID;
	}

	protected static Button getSearchBulletOtherParameters() {
		return searchBulletOtherParameters;
	}

	protected static Button getSearchCheckBoxAlbumName() {
		return searchCheckBoxAlbumName;
	}

	protected static Text getSearchTextBoxAlbumName() {
		return searchTextBoxAlbumName;
	}

	protected static Button getSearchCheckBoxArtist() {
		return searchCheckBoxArtist;
	}

	protected static Text getSearchTextBoxArtist() {
		return searchTextBoxArtist;
	}

	protected static Button getSearchCheckBoxYear() {
		return searchCheckBoxYear;
	}

	protected static Text getSearchTextBoxYearFrom() {
		return searchTextBoxYearFrom;
	}

	protected static Label getSearchLabelYearTo() {
		return searchLabelYearTo;
	}

	protected static Text getSearchTextBoxYearTo() {
		return searchTextBoxYearTo;
	}

	protected static Button getSearchCheckBoxSongNames() {
		return searchCheckBoxSongNames;
	}

	protected static Text getSearchTextBoxSongNames() {
		return searchTextBoxSongNames;
	}

	protected static Composite getSearchCompositeStockField() {
		return searchCompositeStockField;
	}

	protected static Label getSearchLabelStock() {
		return searchLabelStock;
	}

	protected static Button getSearchBulletInStockAll() {
		return searchBulletInStockAll;
	}

	protected static Button getSearchBulletInStockInStore() {
		return searchBulletInStockInStore;
	}

	protected static Button getSearchBulletInStockInNetwork() {
		return searchBulletInStockInNetwork;
	}

	protected static Button getSearchCheckBoxGenres() {
		return searchCheckBoxGenres;
	}

	protected static Button getSearchCheckBoxGenreJazz() {
		return searchCheckBoxGenreJazz;
	}

	protected static Button getSearchCheckBoxGenreRock() {
		return searchCheckBoxGenreRock;
	}

	protected static Button getSearchCheckBoxGenre03() {
		return searchCheckBoxGenre03;
	}

	protected static Button getSearchCheckBoxGenre04() {
		return searchCheckBoxGenre04;
	}

	protected static Button getSearchCheckBoxGenre05() {
		return searchCheckBoxGenre05;
	}

	protected static Button getSearchCheckBoxGenre06() {
		return searchCheckBoxGenre06;
	}

	protected static Button getSearchCheckBoxGenre07() {
		return searchCheckBoxGenre07;
	}

	protected static Button getSearchCheckBoxGenre08() {
		return searchCheckBoxGenre08;
	}

	protected static Button getSearchCheckBoxGenre09() {
		return searchCheckBoxGenre09;
	}

	protected static Button getSearchCheckBoxGenre10() {
		return searchCheckBoxGenre10;
	}

	protected static Button getSearchCheckBoxGenreOther() {
		return searchCheckBoxGenreOther;
	}

	protected static Text getSearchTextBoxGenreOther() {
		return searchTextBoxGenreOther;
	}

	protected static Button getSearchButtonClear() {
		return searchButtonClear;
	}

	protected static Button getSearchButtonSearch() {
		return searchButtonSearch;
	}

	protected static Group getSearchGroupResults() {
		return searchGroupResults;
	}

	protected static Table getSearchTableAlbumResults() {
		return searchTableAlbumResults;
	}

	protected static TableColumn getSearchTableColumnAlbumID() {
		return searchTableColumnAlbumID;
	}

	protected static TableColumn getSearchTableColumnAlbumName() {
		return searchTableColumnAlbumName;
	}

	protected static TableColumn getSearchTableColumnAlbumArtist() {
		return searchTableColumnAlbumArtist;
	}

	protected static TableColumn getSearchTableColumnAlbumYear() {
		return searchTableColumnAlbumYear;
	}

	protected static TableColumn getSearchTableColumnAlbumGenre() {
		return searchTableColumnAlbumGenre;
	}

	protected static TableColumn getSearchTableColumnAlbumLength() {
		return searchTableColumnAlbumLength;
	}

	protected static ProgressBar getSearchProgressBar() {
		return searchProgressBar;
	}

	protected static Table getSearchTableSongResults() {
		return searchTableSongResults;
	}

	protected static TableColumn getSearchTableColumnSongName() {
		return searchTableColumnSongName;
	}

	protected static TableColumn getSearchTableColumnSongArtist() {
		return searchTableColumnSongArtist;
	}

	protected static TableColumn getSearchTableColumnSongLength() {
		return searchTableColumnSongLength;
	}

	protected static Group getSearchGroupStockInfo() {
		return searchGroupStockInfo;
	}

	protected static Label getSearchLabelStockInfoStoreStock() {
		return searchLabelStockInfoStoreStock;
	}

	protected static Label getSearchLabelStockInfoLocation() {
		return searchLabelStockInfoLocation;
	}

	protected static Label getSearchLabelStockInfoPrice() {
		return searchLabelStockInfoPrice;
	}

	protected static Button getSearchButtonStockInfoOrder() {
		return searchButtonStockInfoOrder;
	}

	protected static Group getSearchGroupSaleInfo() {
		return searchGroupSaleInfo;
	}

	protected static Label getSearchLabelSaleInfoQuantity() {
		return searchLabelSaleInfoQuantity;
	}

	protected static Text getSearchTextBoxSaleInfoQuantity() {
		return searchTextBoxSaleInfoQuantity;
	}

	protected static Button getSearchButtonSaleInfoSale() {
		return searchButtonSaleInfoSale;
	}

	protected static TabItem getSaleTabItem() {
		return saleTabItem;
	}

	protected static Composite getSaleCompositeMain() {
		return saleCompositeMain;
	}

	protected static Group getSaleGroupSaleDetails() {
		return saleGroupSaleDetails;
	}

	protected static Label getSaleLabelSaleID() {
		return saleLabelSaleID;
	}

	protected static Label getSaleLabelSaleIDInput() {
		return saleLabelSaleIDInput;
	}

	protected static Label getSaleLabelSaleDate() {
		return saleLabelSaleDate;
	}

	protected static Label getSaleLabelDateInput() {
		return saleLabelDateInput;
	}

	protected static Label getSaleLabelSalesmanIDName() {
		return saleLabelSalesmanIDName;
	}

	protected static Combo getSaleComboSalesmanIDNameInput() {
		return saleComboSalesmanIDNameInput;
	}

	protected static Label getSaleLabelSaleTime() {
		return saleLabelSaleTime;
	}

	protected static Label getSaleLabelTimeInput() {
		return saleLabelTimeInput;
	}

	protected static Table getSaleTableSaleItems() {
		return saleTableSaleItems;
	}

	protected static TableColumn getSaleTableColumnAlbumID() {
		return saleTableColumnAlbumID;
	}

	protected static TableColumn getSaleTableColumnAlbumName() {
		return saleTableColumnAlbumName;
	}

	protected static TableColumn getSaleTableColumnQuantity() {
		return saleTableColumnQuantity;
	}

	protected static TableColumn getSaleTableColumnPricePerItem() {
		return saleTableColumnPricePerItem;
	}

	protected static TableColumn getSaleTableColumnPriceTotal() {
		return saleTableColumnPriceTotal;
	}

	protected static Button getSaleButtonRemoveItem() {
		return saleButtonRemoveItem;
	}

	protected static Button getSaleButtonClearSale() {
		return saleButtonClearSale;
	}

	protected static Label getSaleLabelTotalPrice() {
		return saleLabelTotalPrice;
	}

	protected static Label getSaleLabelTotalPriceValue() {
		return saleLabelTotalPriceValue;
	}

	protected static Button getSaleButtonMakeSale() {
		return saleButtonMakeSale;
	}

	protected static TabItem getStockTabItem() {
		return stockTabItem;
	}

	protected static Composite getStockTabComposite() {
		return stockTabComposite;
	}

	protected static Group getStockGroupOrderForm() {
		return stockGroupOrderForm;
	}

	protected static Label getStockLabelOrderID() {
		return stockLabelOrderID;
	}

	protected static Label getStockLabelOrderIDInput() {
		return stockLabelOrderIDInput;
	}

	protected static Label getStockLabelAlbumID() {
		return stockLabelAlbumID;
	}

	protected static Text getStockTextBoxAlbumIDInput() {
		return stockTextBoxAlbumIDInput;
	}

	protected static Label getStockLabelDate() {
		return stockLabelDate;
	}

	protected static Label getStockLabelOrderDateInput() {
		return stockLabelOrderDateInput;
	}

	protected static Button getStockButtonCheckAvailability() {
		return stockButtonCheckAvailability;
	}

	protected static Label getStockLabelOrderFromStore() {
		return stockLabelOrderFromStore;
	}

	protected static Table getStockTableOrderAvailableStores() {
		return stockTableOrderAvailableStores;
	}

	protected static TableColumn getStockTableColumnStoreID() {
		return stockTableColumnStoreID;
	}

	protected static TableColumn getStockTableColumnStoreCity() {
		return stockTableColumnStoreCity;
	}

	protected static TableColumn getStockTableColumnQuantity() {
		return stockTableColumnQuantity;
	}

	protected static TableColumn getStockTableColumnPrice() {
		return stockTableColumnPrice;
	}

	protected static Label getStockLabelPrice() {
		return stockLabelPrice;
	}

	protected static Label getStockLabelStorePriceInput() {
		return stockLabelStorePriceInput;
	}

	protected static Label getStockLabelQuantityInStock() {
		return stockLabelQuantityInStock;
	}

	protected static Label getStockLabelQuantityInStockInput() {
		return stockLabelQuantityInStockInput;
	}

	protected static Label getStockLabelStorageLocation() {
		return stockLabelStorageLocation;
	}

	protected static Label getStockLabelStorageLocationInput() {
		return stockLabelStorageLocationInput;
	}

	protected static Label getStockLabelQuantityToOrder() {
		return stockLabelQuantityToOrder;
	}

	protected static Text getStockTextBoxQuantityToOrder() {
		return stockTextBoxQuantityToOrder;
	}

	protected static Button getStockButtonClearOrder() {
		return stockButtonClearOrder;
	}

	protected static Button getStockButtonPlaceOrder() {
		return stockButtonPlaceOrder;
	}

	protected static Label getStockLabelOrders() {
		return stockLabelOrders;
	}

	protected static Table getStockTableOrders() {
		return stockTableOrders;
	}

	protected static TableColumn getStockTableColumnOrdersOrderID() {
		return stockTableColumnOrdersOrderID;
	}

	protected static TableColumn getStockTableColumnOrdersSupplierID() {
		return stockTableColumnOrdersSupplierID;
	}

	protected static TableColumn getStockTableColumnOrdersAlbumID() {
		return stockTableColumnOrdersAlbumID;
	}

	protected static TableColumn getStockTableColumnOrdersQuantity() {
		return stockTableColumnOrdersQuantity;
	}

	protected static TableColumn getStockTableColumnOrdersDate() {
		return stockTableColumnOrdersDate;
	}

	protected static TableColumn getStockTableColumnOrdersStatus() {
		return stockTableColumnOrdersStatus;
	}

	protected static TableColumn getStockTableColumnOrdersCompletionDate() {
		return stockTableColumnOrdersCompletionDate;
	}

	protected static Button getStockButtonRemoveOrder() {
		return stockButtonRemoveOrder;
	}

	protected static Button getStockButtonCancelOrder() {
		return stockButtonCancelOrder;
	}

	protected static Label getStockLabelRequests() {
		return stockLabelRequests;
	}

	protected static Table getStockTableRequests() {
		return stockTableRequests;
	}

	protected static TableColumn getStockTableColumnRequestsOrderID() {
		return stockTableColumnRequestsOrderID;
	}

	protected static TableColumn getStockTableColumnRequestsOrderingStoreID() {
		return stockTableColumnRequestsOrderingStoreID;
	}

	protected static TableColumn getStockTableColumnRequestsAlbumID() {
		return stockTableColumnRequestsAlbumID;
	}

	protected static TableColumn getStockTableColumnRequestsQuantity() {
		return stockTableColumnRequestsQuantity;
	}

	protected static TableColumn getStockTableColumnRequestsDate() {
		return stockTableColumnRequestsDate;
	}

	protected static TableColumn getStockTableColumnRequestsStatus() {
		return stockTableColumnRequestsStatus;
	}

	protected static TableColumn getStockTableColumnRequestsCompletionDate() {
		return stockTableColumnRequestsCompletionDate;
	}

	protected static Button getStockButtonDenyRequest() {
		return stockButtonDenyRequest;
	}

	protected static Button getStockButtonApproveRequest() {
		return stockButtonApproveRequest;
	}

	protected static TabItem getManagementTabItem() {
		return managementTabItem;
	}

	protected static Composite getManageMainComposite() {
		return manageMainComposite;
	}

	protected static Label getManageLabelEmployees() {
		return manageLabelEmployees;
	}

	protected static Table getManageTableEmployees() {
		return manageTableEmployees;
	}

	protected static TableColumn getManageTableColumnEmployeeID() {
		return manageTableColumnEmployeeID;
	}

	protected static TableColumn getManageTableColumnEmployeePName() {
		return manageTableColumnEmployeePName;
	}

	protected static TableColumn getManageTableColumnEmployeeLName() {
		return manageTableColumnEmployeeLName;
	}

	protected static TableColumn getManageTableColumnEmployeePosition() {
		return manageTableColumnEmployeePosition;
	}

	protected static Group getManageGroupEditEmployee() {
		return manageGroupEditEmployee;
	}

	protected static Label getManageLabelEmployeeEmploymentDateInput() {
		return manageLabelEmployeeEmploymentDateInput;
	}

	protected static Label getManageLabelEmployeeEmploymentDate() {
		return manageLabelEmployeeEmploymentDate;
	}

	protected static Label getManageLabelEmployeeStoreID() {
		return manageLabelEmployeeStoreID;
	}

	protected static Label getManageLabelEmployeeEmployeeStoreIDInput() {
		return manageLabelEmployeeEmployeeStoreIDInput;
	}

	protected static Label getManageLabelEmployeeID() {
		return manageLabelEmployeeID;
	}

	protected static Text getManageTextBoxEmployeeIDInput() {
		return manageTextBoxEmployeeIDInput;
	}

	protected static Label getManageLabelEmployeeBirth() {
		return manageLabelEmployeeBirth;
	}

	protected static Text getManageTextBoxEmployeeBirthInput() {
		return manageTextBoxEmployeeBirthInput;
	}

	protected static Label getManageLabelEmployeeFName() {
		return manageLabelEmployeeFName;
	}

	protected static Text getManageTextBoxEmployeeFNameInput() {
		return manageTextBoxEmployeeFNameInput;
	}

	protected static Label getManageLabelEmployeeLName() {
		return manageLabelEmployeeLName;
	}

	protected static Text getManageTextBoxEmployeeLNameInput() {
		return manageTextBoxEmployeeLNameInput;
	}

	protected static Label getManageLabelEmployeeAddress() {
		return manageLabelEmployeeAddress;
	}

	protected static Text getManageTextBoxEmployeeAddressInput() {
		return manageTextBoxEmployeeAddressInput;
	}

	protected static Label getManageLabelEmployeePhone() {
		return manageLabelEmployeePhone;
	}

	protected static Text getManageTextBoxEmployeePhoneInput() {
		return manageTextBoxEmployeePhoneInput;
	}

	protected static Label getManageLabelEmployeeCellPhone() {
		return manageLabelEmployeeCellPhone;
	}

	protected static Text getManageTextBoxEmployeeCellPhoneInput() {
		return manageTextBoxEmployeeCellPhoneInput;
	}

	protected static Label getManageLabelEmployeePosition() {
		return manageLabelEmployeePosition;
	}

	protected static Combo getManageComboEmployeePositionInput() {
		return manageComboEmployeePositionInput;
	}

	protected static Button getManageButtonEmployeeNew() {
		return manageButtonEmployeeNew;
	}

	protected static Button getManageButtonEmployeeInsert() {
		return manageButtonEmployeeInsert;
	}

	protected static Button getManageButtonEmployeeNoSave() {
		return manageButtonEmployeeNoSave;
	}

	protected static Button getManageButtonEmployeeEdit() {
		return manageButtonEmployeeEdit;
	}

	protected static Button getManageButtonEmployeeSave() {
		return manageButtonEmployeeSave;
	}

	protected static Button getManageButtonEmployeeRemove() {
		return manageButtonEmployeeRemove;
	}

	protected static Group getManageGroupDBSManage() {
		return manageGroupDBSManage;
	}

	protected static Label getManageLabelDBSUpdate() {
		return manageLabelDBSUpdate;
	}

	protected static Text getManageTextBoxDBSUpdateFileInput() {
		return manageTextBoxDBSUpdateFileInput;
	}

	protected static Button getManageButtonDBSBrowse() {
		return manageButtonDBSBrowse;
	}

	protected static Button getManageButtonDBSUpdate() {
		return manageButtonDBSUpdate;
	}

	protected static ProgressBar getManageProgressBarDBSUpdate() {
		return manageProgressBarDBSUpdate;
	}
}
