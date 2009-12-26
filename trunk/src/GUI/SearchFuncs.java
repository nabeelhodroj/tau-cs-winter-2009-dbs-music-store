package GUI;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.ScrolledComposite;

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
						System.out.println("Search tab: by Album Selected"); //TODO
						// enable album ID and disable other parameters text boxes
						switchAlbumSearchBullet(true);
					}
				}
		);
		
		Main.getSearchBulletOtherParameters().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: by Other Parameters Selected!"); //TODO
						// disable album ID and enable other parameters text boxes
						switchAlbumSearchBullet(false);
					}
				}
		);
		
		Main.getSearchCheckBoxAlbumName().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: album name checkbox changed"); //TODO
						// change album name text box accordingly
						setSearchAlbumNameState();
					}
				}
		);
		
		Main.getSearchCheckBoxArtist().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: artist checkbox changed"); //TODO
						// change artist text box accordingly
						setSearchArtistState();
					}
				}
		);
		
		Main.getSearchCheckBoxYear().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: year checkbox changed"); //TODO
						// change artist text box accordingly
						setSearchYearState();
					}
				}
		);
		
		Main.getSearchCheckBoxSongNames().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: song names checkbox changed"); //TODO
						// change artist text box accordingly
						setSearchSongNamesState();
					}
				}
		);
		
		Main.getSearchCheckBoxGenres().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: genres checkbox changed"); //TODO
						// change artist text box accordingly
						setSearchGenresState();
					}
				}
		);
		
		Main.getSearchCheckBoxGenreOther().addSelectionListener(
				new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						System.out.println("Search tab: other genre checkbox changed"); //TODO
						// change artist text box accordingly
						setSearchGenreOtherState();
					}
				}
		);
	}
	
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
		Main.getSearchTextBoxGenreOther().setEnabled(isEnabled);
	}
	
	/**
	 * set other genre text box by its checkbox state
	 */
	protected static void setSearchGenreOtherState(){
		boolean isEnabled = Main.getSearchCheckBoxGenreOther().getSelection();
		Main.getSearchTextBoxGenreOther().setEnabled(isEnabled);
	}
}
