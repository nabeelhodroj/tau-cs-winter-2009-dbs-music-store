package GUI;

import java.util.*;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.ScrolledComposite;

import DBLayer.DBConnectionInterface;
import Debug.Debug;
import Debug.Debug.DebugOutput;
import Queries.*;
import Tables.*;

/**
 * created by Ariel
 * 
 * Search tab handlers
 */
public class SearchFuncs { 
	
	/**
	 * initializes the search tab view: enabled and disabled fields, default values etc.
	 */
	protected static void initSearchTabView(){
		// set search parameters group to be set on "by album id"
		switchAlbumSearchBullet(true);
		Main.getSearchButtonStockInfoOrder().setEnabled(false);
		Main.getSearchButtonSaleInfoSale().setEnabled(false);
	}

	protected static void initSearchListeners(){
		
		/////////////////////////////
		// Search parameters group //
		/////////////////////////////
		
		// search bullets listeners
		///////////////////////////
		
		Main.getSearchBulletByAlbum().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: by Album Selected",DebugOutput.FILE,DebugOutput.STDOUT);
						// enable album ID and disable other parameters text boxes
						switchAlbumSearchBullet(true);
					}
				}
		);
		
		Main.getSearchBulletOtherParameters().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: by Other Parameters Selected",DebugOutput.FILE,DebugOutput.STDOUT);
						// disable album ID and enable other parameters text boxes
						switchAlbumSearchBullet(false);
					}
				}
		);
		
		// search check boxes listeners
		///////////////////////////////
		
		Main.getSearchCheckBoxAlbumName().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: album name checkbox changed",DebugOutput.FILE,DebugOutput.STDOUT);
						// change album name text box accordingly
						setSearchAlbumNameState();
					}
				}
		);
		
		Main.getSearchCheckBoxArtist().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: artist checkbox changed",DebugOutput.FILE,DebugOutput.STDOUT);
						// change artist text box accordingly
						setSearchArtistState();
					}
				}
		);
		
		Main.getSearchCheckBoxYear().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: year checkbox changed",DebugOutput.FILE,DebugOutput.STDOUT);
						// change artist text box accordingly
						setSearchYearState();
					}
				}
		);
		
		Main.getSearchCheckBoxSongNames().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: song names checkbox changed",DebugOutput.FILE,DebugOutput.STDOUT);
						// change artist text box accordingly
						setSearchSongNamesState();
					}
				}
		);
		
		Main.getSearchCheckBoxGenres().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: genres checkbox changed",DebugOutput.FILE,DebugOutput.STDOUT);
						// change artist text box accordingly
						setSearchGenresState();
					}
				}
		);
		
		Main.getSearchCheckBoxGenreOther().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: other genre checkbox changed",DebugOutput.FILE,DebugOutput.STDOUT);
						// change artist text box accordingly
						setSearchGenreOtherState();
					}
				}
		);
		
		// clear / search buttons
		/////////////////////////
		
		// clear fields
		Main.getSearchButtonClear().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: clear fields button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						// clear all search fields
						clearSearchFields();
					}
				}
		);
		
		// search
		Main.getSearchButtonSearch().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: search button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						// create AlbumSearchQuery
						AlbumSearchQuery q = createAlbumSearchQuery();
						if (q != null)
						{
							// set gui environment
							setEnvSearchInvoked();
							// send query to DB
							DBConnectionInterface.getAlbumsSearchResults(q);
						}
					}
				}
		);
		
		//////////////////////////
		// Search results group //
		//////////////////////////
		
		// albums table listener
		Main.getSearchTableAlbumResults().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: album result selected",DebugOutput.FILE,DebugOutput.STDOUT);
						
						// get selected album table item
						Long albumID = Long.valueOf(Main.getSearchTableAlbumResults().getSelection()[0].getText());
						AlbumsResultsTableItem album = StaticProgramTables.results.getAlbum(albumID);
						// update songs table
						updateSongsResultsTable(album);
						// update stock information
						setLabelPrice(Integer.toString(album.getPrice()));
						setLabelStockLocation(Long.toString(album.getStorageLocation()));
						setLabelStoreStock(Integer.toString(album.getQuantity()));
						Main.getSearchButtonStockInfoOrder().setEnabled(true);
						// enable add to sale
						Main.getSearchButtonSaleInfoSale().setEnabled(true);
					}
				}
		);
		
		//////////////////////
		// Stock info group //
		//////////////////////
		
		// add to order button
		Main.getSearchButtonStockInfoOrder().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: add to order button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						//TODO clear order fields
						
						// set order album id to selected album
						Long albumID = Long.valueOf(Main.getSearchTableAlbumResults().getSelection()[0].getText());
						Main.getStockTextBoxAlbumIDInput().setText(Long.toString(albumID));
						// move to stock tab
						MainFuncs.switchTab(2);
					}
				}
		);
		
		///////////////////////
		// Add to sale group //
		///////////////////////
		
		// add to sale button listener
		Main.getSearchButtonSaleInfoSale().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						Debug.log("Search tab: add to sale button clicked",DebugOutput.FILE,DebugOutput.STDOUT);
						
						
					}
				}
		);
	}
	
	/*****************************************************
	 * 						HANDLERS
	 * 
	 * all methods used to handle events in the search tab
	 * 
	 * ***************************************************
	 */
	
	//////////////////////////////
	// handle search parameters //
	//////////////////////////////
	
	/**
	 * sets search tab \ search by fields according to enableByAlbumID:
	 * - if true: by album id enabled, by other parameters disabled
	 * - if false: by other parameters disabled, by album id enabled
	 * @param enableByAlbumID
	 */
	protected static void switchAlbumSearchBullet(boolean enableByAlbumID){
		// set other parameters search fields by enableByAlbumID
		Main.getSearchCheckBoxAlbumName().setEnabled(!enableByAlbumID);
		Main.getSearchTextBoxAlbumName().setEnabled(!enableByAlbumID);
		Main.getSearchCheckBoxArtist().setEnabled(!enableByAlbumID);
		Main.getSearchTextBoxArtist().setEnabled(!enableByAlbumID);
		Main.getSearchCheckBoxYear().setEnabled(!enableByAlbumID);
		Main.getSearchTextBoxYearFrom().setEnabled(!enableByAlbumID);
		Main.getSearchTextBoxYearTo().setEnabled(!enableByAlbumID);
		Main.getSearchCheckBoxSongNames().setEnabled(!enableByAlbumID);
		Main.getSearchTextBoxSongNames().setEnabled(!enableByAlbumID);
		Main.getSearchCheckBoxGenres().setEnabled(!enableByAlbumID);
		Button[] genres = Main.getSearchCheckBoxGenresArr();
		for (Button g: genres) g.setEnabled(!enableByAlbumID);
		Main.getSearchCheckBoxGenreOther().setEnabled(!enableByAlbumID);
		Main.getSearchTextBoxGenreOther().setEnabled(!enableByAlbumID);
		// set album id search fields by enableByAlbumID
		Main.getSearchTextBoxAlbumID().setEnabled(enableByAlbumID);
		
		// in case other parameters are enabled, set text boxes according to their checkboxes
		// otherwise it should be disabled anyway
		if (!enableByAlbumID){
			setSearchAlbumNameState();
			setSearchArtistState();
			setSearchYearState();
			setSearchSongNamesState();
			setSearchGenresState();
			setSearchGenreOtherState();
		}
	}
	
	/**
	 * set album name text box by its checkbox state
	 */
	protected static void setSearchAlbumNameState(){
		boolean isEnabled = Main.getSearchCheckBoxAlbumName().getSelection();
		Main.getSearchTextBoxAlbumName().setEnabled(isEnabled);
	}
	
	/**
	 * set artist text box by its checkbox state
	 */
	protected static void setSearchArtistState(){
		boolean isEnabled = Main.getSearchCheckBoxArtist().getSelection();
		Main.getSearchTextBoxArtist().setEnabled(isEnabled);
	}
	
	/**
	 * set year text boxes (from, to) by its checkbox state
	 */
	protected static void setSearchYearState(){
		boolean isEnabled = Main.getSearchCheckBoxYear().getSelection();
		Main.getSearchTextBoxYearFrom().setEnabled(isEnabled);
		Main.getSearchTextBoxYearTo().setEnabled(isEnabled);
	}
	
	/**
	 * set song names text box by its checkbox state
	 */
	protected static void setSearchSongNamesState(){
		boolean isEnabled = Main.getSearchCheckBoxSongNames().getSelection();
		Main.getSearchTextBoxSongNames().setEnabled(isEnabled);
	}
	
	/**
	 * set genres checkboxes by its the genres checkbox state
	 */
	protected static void setSearchGenresState(){
		boolean isEnabled = Main.getSearchCheckBoxGenres().getSelection();
		Button[] genres = Main.getSearchCheckBoxGenresArr();
		for (Button g: genres) g.setEnabled(isEnabled);
		Main.getSearchCheckBoxGenreOther().setEnabled(isEnabled);
		setSearchGenreOtherState();
	}
	
	/**
	 * set other genre text box by its checkbox state
	 */
	protected static void setSearchGenreOtherState(){
		boolean isEnabled = Main.getSearchCheckBoxGenreOther().getSelection() &&	// other genre check box is on
							Main.getSearchCheckBoxGenres().getSelection();			// and genres check box is on
		Main.getSearchTextBoxGenreOther().setEnabled(isEnabled);
	}
	
	////////////////////////////
	//	handle search buttons //
	////////////////////////////
	
	/**
	 * clear all search fields
	 */
	protected static void clearSearchFields(){
		// clear all text boxes
		Main.getSearchTextBoxAlbumID().setText("");
		Main.getSearchTextBoxAlbumName().setText("");
		Main.getSearchTextBoxArtist().setText("");
		Main.getSearchTextBoxGenreOther().setText("");
		Main.getSearchTextBoxSaleInfoQuantity().setText("");
		Main.getSearchTextBoxSongNames().setText("");
		Main.getSearchTextBoxYearFrom().setText("");
		Main.getSearchTextBoxYearTo().setText("");
		Main.getSearchTextBoxGenreOther().setText("");
		// clear all check boxes in genres
		for(Button g: Main.getSearchCheckBoxGenresArr()){
			g.setSelection(false);
		}
		Main.getSearchCheckBoxGenreOther().setSelection(false);
		Main.getSearchTextBoxGenreOther().setEnabled(false);
	}
	
	/**
	 * returns current search query or pops a warning if search query is illegal
	 * @return
	 */
	protected static AlbumSearchQuery createAlbumSearchQuery(){
		try{
			AlbumSearchQuery q = new AlbumSearchQuery();
			return q;
		}catch(QueryErrorException qee){
			MessageBox queryErrorMsg = new MessageBox(Main.getMainShell(),SWT.ICON_ERROR | SWT.OK);
			queryErrorMsg.setMessage(qee.getMessage());
			queryErrorMsg.setText("Query Error");
			queryErrorMsg.open();
			return null;
		}
	}
	
	/**
	 * sets the gui environment when search is invoked: disables search button, clears tables
	 * and disables stock and sale buttons from search tab
	 */
	public static void setEnvSearchInvoked(){
		// buttons:
		Main.getSearchButtonSearch().setEnabled(false);
		Main.getSearchButtonStockInfoOrder().setEnabled(false);
		Main.getSearchButtonSaleInfoSale().setEnabled(false);

		// clear all results if search is invoked
		Main.getSearchTableAlbumResults().removeAll();
		Main.getSearchTableSongResults().removeAll();
		clearStockInfo();
	}
	
	//////////////////////////////
	//	handle album results	//
	//////////////////////////////
	
	/**
	 * getter for the current search results data structure
	 */
	public static AlbumsResultsTable getCurrentSearchResults(){
		return StaticProgramTables.results;
	}
	
	/**
	 * setter for the current search results data structure
	 */
	public static void setCurrentSearchResults(AlbumsResultsTable results){
		StaticProgramTables.results = results;
	}
	
	/**
	 * update current results and the Album results table accordingly
	 * @param results
	 */
	public static void updateAlbumsResultsTable(AlbumsResultsTable results){
		// set current results to the new ones
		setCurrentSearchResults(results);
		
		// first clear all results tables
		Main.getSearchTableAlbumResults().removeAll();
		Main.getSearchTableSongResults().removeAll();
		// now enter new results
		for(AlbumsResultsTableItem album: results.getAlbums().values()){
			TableItem item = new TableItem(Main.getSearchTableAlbumResults(), SWT.NONE);
			Integer[] minsSecs = getMinutesSeconds(album.getLength());
			String[] entry = new String[]{
					Long.toString(album.getAlbumID()),
					album.getAlbumName(),
					album.getArtist(),
					Integer.toString(album.getYear()),
					album.getGenre(),
					minsSecs[0]+":"+minsSecs[1]
			};
			item.setText(entry);
		}
	}
	
	/**
	 * restores search tab environment after albums results came back:
	 * enables search button
	 */
	public static void setEnvSearchDone(){
		Main.getSearchButtonSearch().setEnabled(true);
	}
	
	/**
	 * update the Songs results table to show the given album's songs
	 * (taken from the current results data structure)
	 * @param albumID
	 */
	public static void updateSongsResultsTable(AlbumsResultsTableItem album){
		SongsResultsTable songs = album.getSongs();
		
		// first clear songs table
		Main.getSearchTableSongResults().removeAll();
		// now enter songs
		for(SongsResultsTableItem song: songs.getSongs().values()){
			TableItem item = new TableItem(Main.getSearchTableSongResults(), SWT.NONE);
			Integer[] minsSecs = getMinutesSeconds(song.getSongLength());
			String[] entry = new String[]{
					song.getSongName(),
					song.getSongArtist(),
					minsSecs[0]+":"+minsSecs[1]
			};
			item.setText(entry);
		}
	}
	
	/**
	 * returns the minutes-seconds representation for the given seconds integer
	 * @param secs
	 * @return
	 */
	public static Integer[] getMinutesSeconds(int secs){
		Integer[] a =  new Integer[]{secs/60,secs%60};
		return a;
	}
	
	///////////////////////
	// handle stock info //
	///////////////////////
	
	/**
	 * clears stock information
	 */
	public static void clearStockInfo(){
		Main.getSearchLabelStockInfoStoreStock().setText("Store stock: ");
		Main.getSearchLabelStockInfoLocation().setText("Storage location: ");
		Main.getSearchLabelStockInfoPrice().setText("Price: ");
	}
	
	/**
	 * set store stock label
	 * @param str
	 */
	protected static void setLabelStoreStock(String str){
		Main.getSearchLabelStockInfoStoreStock().setText("Store stock: "+str);
	}
	
	/**
	 * set stock location label
	 * @param str
	 */
	protected static void setLabelStockLocation(String str){
		Main.getSearchLabelStockInfoLocation().setText("Storage location: "+str);
	}
	
	/**
	 * set price label
	 * @param str
	 */
	protected static void setLabelPrice(String str){
		Main.getSearchLabelStockInfoPrice().setText("Price: "+str);
	}
}