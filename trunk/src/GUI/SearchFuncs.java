package GUI;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.ScrolledComposite;

/*
 * Search tab handlers
 */
public class SearchFuncs {

	public static void initSearchListeners(){
		
		// Search parameters group
		//////////////////////////
		
		// search bullets listeners
		Main.getSearchBulletByAlbum().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						System.out.println("By Album Selected!");
						
						// disable other parameters text boxes
						Main.getSearchCheckBoxAlbumName().setEnabled(false);
						Main.getSearchTextBoxAlbumName().setEnabled(false);
						Main.getSearchCheckBoxArtist().setEnabled(false);
						Main.getSearchTextBoxArtist().setEnabled(false);
						Main.getSearchCheckBoxYear().setEnabled(false);
						Main.getSearchTextBoxYearFrom().setEnabled(false);
						Main.getSearchTextBoxYearTo().setEnabled(false);
						Main.getSearchCheckBoxSongNames().setEnabled(false);
						Main.getSearchTextBoxSongNames().setEnabled(false);
						Main.getSearchBulletInStockAll().setEnabled(false);
						Main.getSearchBulletInStockInNetwork().setEnabled(false);
						Main.getSearchBulletInStockInStore().setEnabled(false);
						Main.getSearchCheckBoxGenres().setEnabled(false);
						Main.getSearchCheckBoxGenreJazz().setEnabled(false);
						Main.getSearchCheckBoxGenreRock().setEnabled(false);
						Main.getSearchCheckBoxGenreJazz().setEnabled(false);
						Main.getSearchCheckBoxGenre03().setEnabled(false);
						Main.getSearchCheckBoxGenre04().setEnabled(false);
						Main.getSearchCheckBoxGenre05().setEnabled(false);
						Main.getSearchCheckBoxGenre06().setEnabled(false);
						Main.getSearchCheckBoxGenre07().setEnabled(false);
						Main.getSearchCheckBoxGenre08().setEnabled(false);
						Main.getSearchCheckBoxGenre09().setEnabled(false);
						Main.getSearchCheckBoxGenre10().setEnabled(false);
						Main.getSearchCheckBoxGenreOther().setEnabled(false);
						Main.getSearchTextBoxGenreOther().setEnabled(false);
						// enable album ID
						Main.getSearchTextBoxAlbumID().setEnabled(true);
					}
				}
		);
		
		Main.getSearchBulletOtherParameters().addSelectionListener(
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e){
						System.out.println("By Other Parameters Selected!");
						
						// disable album ID
						Main.getSearchTextBoxAlbumID().setEnabled(false);
						// enable other parameters text boxes
						Main.getSearchCheckBoxAlbumName().setEnabled(true);
						Main.getSearchTextBoxAlbumName().setEnabled(true);
						Main.getSearchCheckBoxArtist().setEnabled(true);
						Main.getSearchTextBoxArtist().setEnabled(true);
						Main.getSearchCheckBoxYear().setEnabled(true);
						Main.getSearchTextBoxYearFrom().setEnabled(true);
						Main.getSearchTextBoxYearTo().setEnabled(true);
						Main.getSearchCheckBoxSongNames().setEnabled(true);
						Main.getSearchTextBoxSongNames().setEnabled(true);
						Main.getSearchBulletInStockAll().setEnabled(true);
						Main.getSearchBulletInStockInNetwork().setEnabled(true);
						Main.getSearchBulletInStockInStore().setEnabled(true);
						Main.getSearchCheckBoxGenres().setEnabled(true);
						Main.getSearchCheckBoxGenreJazz().setEnabled(true);
						Main.getSearchCheckBoxGenreRock().setEnabled(true);
						Main.getSearchCheckBoxGenreJazz().setEnabled(true);
						Main.getSearchCheckBoxGenre03().setEnabled(true);
						Main.getSearchCheckBoxGenre04().setEnabled(true);
						Main.getSearchCheckBoxGenre05().setEnabled(true);
						Main.getSearchCheckBoxGenre06().setEnabled(true);
						Main.getSearchCheckBoxGenre07().setEnabled(true);
						Main.getSearchCheckBoxGenre08().setEnabled(true);
						Main.getSearchCheckBoxGenre09().setEnabled(true);
						Main.getSearchCheckBoxGenre10().setEnabled(true);
						Main.getSearchCheckBoxGenreOther().setEnabled(true);
						Main.getSearchTextBoxGenreOther().setEnabled(true);						
					}
				}
		);
	}
}
