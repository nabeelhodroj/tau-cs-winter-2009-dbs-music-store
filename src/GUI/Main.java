package GUI;
import java.util.ListIterator;
import java.util.Observable;
import org.eclipse.jface.viewers.*;
import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.ScrolledComposite;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Main extends org.eclipse.swt.widgets.Composite {

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}
	
	private Group mainHeaderContainer;
	private Menu mainMenuBar;
	private Label searchLabelSaleInfoQuantity;
	private Button searchButtonSaleInfoSale;
	private Text searchTextBoxSaleInfoQuantity;
	private Label searchLabelStockInfoPrice;
	private Button searchButtonStockInfoOrder;
	private Label searchLabelStockInfoLocation;
	private Label searchLabelStockInfoStoreStock;
	private Group searchGroupSaleInfo;
	private Button searchCheckBoxYear;
	private Label searchLabelStock;
	private Button searchCheckBoxGenreOther;
	private Button searchCheckBoxGenre10;
	private Button searchCheckBoxGenre09;
	private Button searchCheckBoxGenre08;
	private Button searchCheckBoxGenre07;
	private Button searchCheckBoxGenre06;
	private Button searchCheckBoxGenre05;
	private Button searchCheckBoxGenre04;
	private Button searchCheckBoxGenre03;
	private Button searchBulletInStockInStore;
	private Button searchBulletInStockInNetwork;
	private Button searchBulletInStockAll;
	private Button searchCheckBoxGenreJazz;
	private ProgressBar searchProgressBar;
	private Table searchTableAlbumResults;
	private Table searchTableSongResults;
	private Group searchGroupStockInfo;
	private Button searchButtonSearch;
	private Button searchButtonClear;
	private Text searchTextBoxGenreOther;
	private Button searchCheckBoxGenreRock;
	private Text searchTextBoxSongNames;
	private Text searchTextBoxYearTo;
	private Label searchLabelYearTo;
	private Text searchTextBoxYearFrom;
	private Text searchTextBoxArtist;
	private Text searchTextBoxAlbumName;
	private Button searchCheckBoxGenres;
	private Button searchCheckBoxSongNames;
	private Button searchCheckBoxArtist;
	private Button searchCheckBoxAlbumName;
	private Text searchTextBoxAlbumID;
	private Button searchBulletOtherParameters;
	private MenuItem mainMenuItemFile;
	private Button searchBulletByAlbum;
	private Group searchGroupResults;
	private Group searchGroupOptions;
	private Composite searchTabComposite;
	private TabItem managementTabItem;
	private TabItem stockTabItem;
	private TabItem searchTabItem;
	private TabItem saleTabItem;
	private TabFolder mainTabFolder;
	private Menu fileMenu;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
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
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			this.layout();
			pack();
			this.setSize(800, 600);
			{
				mainMenuBar = new Menu(getShell(), SWT.BAR);
				getShell().setMenuBar(mainMenuBar);
				{
					mainMenuItemFile = new MenuItem(mainMenuBar, SWT.CASCADE);
					mainMenuItemFile.setText("File");
					{
						fileMenu = new Menu(mainMenuItemFile);
						mainMenuItemFile.setMenu(fileMenu);
					}
				}
			}
			{
				mainHeaderContainer = new Group(this, SWT.NONE);
				GridLayout MainHeaderLayout = new GridLayout();
				MainHeaderLayout.makeColumnsEqualWidth = true;
				mainHeaderContainer.setLayout(MainHeaderLayout);
				GridData MainHeaderLData = new GridData();
				MainHeaderLData.widthHint = 785;
				MainHeaderLData.heightHint = 80;
				mainHeaderContainer.setLayoutData(MainHeaderLData);
				mainHeaderContainer.setText("Store Information");
			}
			{
				mainTabFolder = new TabFolder(this, SWT.NONE);
				GridData mainTabFolderLData = new GridData();
				mainTabFolderLData.widthHint = 785;
				mainTabFolderLData.heightHint = 465;
				mainTabFolder.setLayoutData(mainTabFolderLData);
				
				{
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
							searchGroupOptions.setBounds(5, 0, 355, 280);
							{
								searchBulletByAlbum = new Button(searchGroupOptions, SWT.RADIO | SWT.LEFT);
								searchBulletByAlbum.setText("Search by album ID:");
								searchBulletByAlbum.setBounds(12, 16, 118, 22);
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
								searchCheckBoxGenres.setBounds(12, 166, 82, 22);
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
								searchCheckBoxGenreRock.setBounds(12, 187, 60, 16);
							}
							{
								searchCheckBoxGenreJazz = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenreJazz.setText("Jazz");
								searchCheckBoxGenreJazz.setBounds(12, 207, 60, 16);
							}
							{
								searchBulletInStockAll = new Button(searchGroupOptions, SWT.RADIO | SWT.LEFT);
								searchBulletInStockAll.setText("All");
								searchBulletInStockAll.setBounds(154, 166, 33, 22);
							}
							{
								searchBulletInStockInNetwork = new Button(searchGroupOptions, SWT.RADIO | SWT.LEFT);
								searchBulletInStockInNetwork.setText("In network");
								searchBulletInStockInNetwork.setBounds(193, 166, 76, 22);
							}
							{
								searchBulletInStockInStore = new Button(searchGroupOptions, SWT.RADIO | SWT.LEFT);
								searchBulletInStockInStore.setText("In store");
								searchBulletInStockInStore.setBounds(269, 166, 60, 22);
							}
							{
								searchCheckBoxGenre03 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre03.setText("Genre03");
								searchCheckBoxGenre03.setBounds(76, 187, 60, 16);
							}
							{
								searchCheckBoxGenre04 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre04.setText("Genre04");
								searchCheckBoxGenre04.setBounds(76, 207, 60, 16);
							}
							{
								searchCheckBoxGenre05 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre05.setText("Genre05");
								searchCheckBoxGenre05.setBounds(141, 187, 60, 16);
							}
							{
								searchCheckBoxGenre06 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre06.setText("Genre06");
								searchCheckBoxGenre06.setBounds(141, 207, 60, 16);
							}
							{
								searchCheckBoxGenre07 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre07.setText("Genre07");
								searchCheckBoxGenre07.setBounds(205, 187, 60, 16);
							}
							{
								searchCheckBoxGenre08 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre08.setText("Genre08");
								searchCheckBoxGenre08.setBounds(205, 207, 60, 16);
							}
							{
								searchCheckBoxGenre09 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre09.setText("Genre09");
								searchCheckBoxGenre09.setBounds(270, 187, 60, 16);
							}
							{
								searchCheckBoxGenre10 = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenre10.setText("Genre10");
								searchCheckBoxGenre10.setBounds(270, 207, 60, 16);
							}
							{
								searchCheckBoxGenreOther = new Button(searchGroupOptions, SWT.CHECK | SWT.LEFT);
								searchCheckBoxGenreOther.setText("Other:");
								searchCheckBoxGenreOther.setBounds(12, 223, 54, 22);
							}
							{
								searchLabelStock = new Label(searchGroupOptions, SWT.NONE);
								searchLabelStock.setText("Stock:");
								searchLabelStock.setBounds(106, 169, 36, 16);
							}
							{
								searchTextBoxGenreOther = new Text(searchGroupOptions, SWT.BORDER);
								searchTextBoxGenreOther.setBounds(70, 223, 273, 22);
							}
							{
								searchButtonClear = new Button(searchGroupOptions, SWT.PUSH | SWT.CENTER);
								searchButtonClear.setText("Clear fields");
								searchButtonClear.setBounds(12, 251, 156, 22);
							}
							{
								searchButtonSearch = new Button(searchGroupOptions, SWT.PUSH | SWT.CENTER);
								searchButtonSearch.setText("Search");
								searchButtonSearch.setBounds(174, 251, 169, 22);
							}
						}
						{
							searchGroupResults = new Group(searchTabComposite, SWT.NONE);
							searchGroupResults.setLayout(null);
							searchGroupResults.setText("Seach Results");
							searchGroupResults.setBounds(366, 0, 419, 465);
							{
								searchTableAlbumResults = new Table(searchGroupResults, SWT.BORDER | SWT.MULTI
										| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
								searchTableAlbumResults.setBounds(12, 20, 395, 225);
								
								String[] albumResultTitles = {"Album ID","Album name","Artist","Year","Genre","Length"};
								int tableWidth = searchTableAlbumResults.getClientArea().width - getBorderWidth()*2;
								for (int i = 0; i < albumResultTitles.length; i++) {
									TableColumn column = new TableColumn(searchTableAlbumResults, SWT.NONE);
									column.setText(albumResultTitles[i]);
									column.setResizable(true);
									column.setMoveable(true);
									column.setWidth(tableWidth / albumResultTitles.length);
								}
								searchTableAlbumResults.setHeaderVisible(true);
								searchTableAlbumResults.setLinesVisible(true);
							}
							{
								searchProgressBar = new ProgressBar(searchGroupResults, SWT.NONE);
								searchProgressBar.setBounds(15, 251, 392, 22);
							}
							{
								searchTableSongResults = new Table(searchGroupResults, SWT.BORDER | SWT.MULTI
										| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
								searchTableSongResults.setBounds(12, 286, 395, 170);
								
								String[] albumResultTitles = {"Song name","Artist","Length"};
								int tableWidth = searchTableSongResults.getClientArea().width - getBorderWidth()*2;
								for (int i = 0; i < albumResultTitles.length; i++) {
									TableColumn column = new TableColumn(searchTableSongResults, SWT.NONE);
									column.setText(albumResultTitles[i]);
									column.setResizable(true);
									column.setMoveable(true);
									column.setWidth(tableWidth / albumResultTitles.length);
								}
								searchTableSongResults.setHeaderVisible(true);
								searchTableSongResults.setLinesVisible(true);
							}
							
						}
						{
							searchGroupStockInfo = new Group(searchTabComposite, SWT.NONE);
							searchGroupStockInfo.setLayout(null);
							searchGroupStockInfo.setText("Stock Information");
							searchGroupStockInfo.setBounds(5, 280, 171, 185);
							{
								searchLabelStockInfoStoreStock = new Label(searchGroupStockInfo, SWT.NONE);
								searchLabelStockInfoStoreStock.setText("Store stock:");
								searchLabelStockInfoStoreStock.setBounds(12, 22, 152, 22);
							}
							{
								searchLabelStockInfoLocation = new Label(searchGroupStockInfo, SWT.NONE);
								searchLabelStockInfoLocation.setText("Storage location: ");
								searchLabelStockInfoLocation.setBounds(12, 44, 152, 22);
							}
							{
								searchLabelStockInfoPrice = new Label(searchGroupStockInfo, SWT.NONE);
								searchLabelStockInfoPrice.setText("Price:");
								searchLabelStockInfoPrice.setBounds(12, 66, 152, 22);
							}
							{
								searchButtonStockInfoOrder = new Button(searchGroupStockInfo, SWT.PUSH | SWT.CENTER);
								searchButtonStockInfoOrder.setText("Place an order...");
								searchButtonStockInfoOrder.setBounds(12, 94, 147, 85);
							}
						}
						{
							searchGroupSaleInfo = new Group(searchTabComposite, SWT.NONE);
							searchGroupSaleInfo.setLayout(null);
							searchGroupSaleInfo.setText("Sale");
							searchGroupSaleInfo.setBounds(182, 280, 177, 185);
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
								searchButtonSaleInfoSale.setText("Add to sale...");
								searchButtonSaleInfoSale.setBounds(8, 55, 157, 124);
							}
						}
					}
				}
				{
					saleTabItem = new TabItem(mainTabFolder, SWT.NONE);
					saleTabItem.setText("Sale");
					saleTabItem.setToolTipText("Manage current sale");
				}
				{
					stockTabItem = new TabItem(mainTabFolder, SWT.NONE);
					stockTabItem.setText("Stock");
					stockTabItem.setToolTipText("View and manage orders and requests");
				}
				{
					managementTabItem = new TabItem(mainTabFolder, SWT.NONE);
					managementTabItem.setText("Management");
					managementTabItem.setToolTipText("View and manage HR");
				}
				mainTabFolder.setSelection(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Group getMainHeaderContainer() {
		return mainHeaderContainer;
	}

}
