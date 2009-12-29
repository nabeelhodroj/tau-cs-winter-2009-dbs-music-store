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

import Queries.*;
import Tables.*;

/**
 * created by Ariel
 * 
 * Search tab handlers
 * *** System out prints are for debugging ***
 */
public class SearchFuncs { 
	
	/**
	 * initializes the search tab view: enabled and disabled fields, default values etc.
	 */
	protected static void initSearchTabView(){
		// set search parameters group to be set on "by album id"
		switchAlbumSearchBullet(true);
		// set all search parameters text boxes disabled except for album id
		Main.getSearchTextBoxAlbumName().setEnabled(false);
	}

	protected static void initSearchListeners(){
		
		// Search parameters group
		//////////////////////////
		
		// search bullets listeners
		Main.getSearchBulletByAlbum().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: by Album Selected");
						// enable album ID and disable other parameters text boxes
						switchAlbumSearchBullet(true);
					}
				}
		);
		
		Main.getSearchBulletOtherParameters().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: by Other Parameters Selected!");
						// disable album ID and enable other parameters text boxes
						switchAlbumSearchBullet(false);
					}
				}
		);
		
		// search check boxes listeners
		
		Main.getSearchCheckBoxAlbumName().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: album name checkbox changed");
						// change album name text box accordingly
						setSearchAlbumNameState();
					}
				}
		);
		
		Main.getSearchCheckBoxArtist().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: artist checkbox changed");
						// change artist text box accordingly
						setSearchArtistState();
					}
				}
		);
		
		Main.getSearchCheckBoxYear().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: year checkbox changed");
						// change artist text box accordingly
						setSearchYearState();
					}
				}
		);
		
		Main.getSearchCheckBoxSongNames().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: song names checkbox changed");
						// change artist text box accordingly
						setSearchSongNamesState();
					}
				}
		);
		
		Main.getSearchCheckBoxGenres().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: genres checkbox changed");
						// change artist text box accordingly
						setSearchGenresState();
					}
				}
		);
		
		Main.getSearchCheckBoxGenreOther().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: other genre checkbox changed");
						// change artist text box accordingly
						setSearchGenreOtherState();
					}
				}
		);
		
		// buttons
		
		Main.getSearchButtonClear().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: clear fields button clicked");
						// clear all search fields
						clearSearchFields();
					}
				}
		);
	}
	
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
		//TODO
		// should implement update of the Albums results table according to the
		// new results
	}
	
	/**
	 * update the Songs results table to show the given album's songs
	 * (taken from the current results data structure)
	 * @param albumID
	 */
	public static void updateSongsResultsTable(long albumID){
		//TODO
		// should get the songs list for the given album id from the current search results
	}
	
	/**
	 * returns the minutes-seconds representation for the given seconds integer
	 * @param secs
	 * @return
	 */
	public static List<Integer> getMinutesSeconds(int secs){
		List<Integer> l =  new ArrayList<Integer>();
		l.add(secs/60);
		l.add(secs%60);
		return l;
	}
}
