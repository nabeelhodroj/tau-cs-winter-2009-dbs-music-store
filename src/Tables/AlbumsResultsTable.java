package Tables;

import java.util.*;

/**
 * created by Ariel
 * 
 * Albums search results table data structure
 * ==========================================
 * holds all AlbumItem tuples search results held by their album ID
 */
public class AlbumsResultsTable extends Table {
	private Map<Long,AlbumItem> albums = new HashMap<Long,AlbumItem>();
	
	/**
	 * constructor for the albums search results table
	 */
	public AlbumsResultsTable(){
		super("AlbumsResultsTable");
	}
	
	/**
	 * constructor for the albums search results table
	 * with the album results map as input
	 * @param albums
	 */
	public AlbumsResultsTable(HashMap<Long,AlbumItem> albums){
		super("AlbumsResultsTable");
		this.albums = albums;
	}
	
	//////////////////////////
	//	adders and getters	//
	//////////////////////////
	
	/**
	 * album adder to the albums search results map
	 * @param albumID
	 * @param album
	 */
	public void addAlbum(AlbumItem album){
		albums.put(album.getAlbumID(), album);
	}
	
	/**
	 * album adder to the albums search results map
	 * with explicit album details
	 * @param albumID
	 * @param albumName
	 * @param artist
	 * @param year
	 * @param genre
	 * @param length
	 * @param songs
	 */
	public void addAlbum(long albumID, String albumName, String artist, int year, String genre, int length, SongsResultsTable songs){
		AlbumItem album = new AlbumItem(albumID,albumName,artist,year,genre,length,songs);
		albums.put(albumID, album);
	}
	
	/**
	 * album item getter
	 * @param albumID
	 * @return
	 */
	public AlbumItem getAlbum(long albumID){
		return albums.get(albumID);
	}
}
