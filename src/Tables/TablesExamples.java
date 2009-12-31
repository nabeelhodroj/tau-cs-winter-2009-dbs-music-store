package Tables;

import GUI.*;

/**
 * created by Ariel
 * 
 * holds examples of tables classes, for debugging
 */
public class TablesExamples {
	
	public static AlbumsResultsTable albumsResultsTableExample = new AlbumsResultsTable();
	
	/**
	 * initialize tables examples
	 */
	public static void initTablesExamples(){
		SongsResultsTable songs1 = new SongsResultsTable(1);
		songs1.addSong(1, "You are my Sima", "Shimon", 180);
		songs1.addSong(2,"Here in Ashdod", "Shimon", 200);
		
		AlbumsResultsTableItem albumsResultsItem1 = new AlbumsResultsTableItem(1, "Shimon Comes Live",
				"Shimon", 1998, "Rock", 1800, songs1, 60, 17, 30);
		
		SongsResultsTable songs2 = new SongsResultsTable(2);
		songs2.addSong(1, "You are my Sima", "Shimon", 180);
		songs2.addSong(2,"Who is your Sami", "Shimon feat. Sami", 200);
		songs2.addSong(3,"Who is your Susu", "Shimon feat. Susu", 150);
		
		AlbumsResultsTableItem albumsResultsItem2 = new AlbumsResultsTableItem(2, "Shimon and Friends",
				"Various", 1995, "Rock", 2000, songs2, 49, 19, 56);
		
		albumsResultsTableExample.addAlbum(albumsResultsItem1);
		albumsResultsTableExample.addAlbum(albumsResultsItem2);
	}
	
	/**
	 * invoke GUI albums search results update with an example
	 */
	public static void getAlbumsSearchResults(){
		GuiUpdatesInterface.updateAlbumResultsTable(albumsResultsTableExample);
	}
}
