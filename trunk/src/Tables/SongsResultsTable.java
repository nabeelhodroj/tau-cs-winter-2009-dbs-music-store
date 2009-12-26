package Tables;

import java.util.*;

/**
 * created by Ariel
 * 
 * Songs table data structure
 * ==========================
 * holds all songs (by SongItem class) for the specified album id
 * for each song it holds:
 * - track (key)
 * - song name
 * - song artist
 * - length (in seconds)
 */
public class SongsResultsTable extends Table {
	private long albumID;
	private Map<Integer,SongItem> songs = new HashMap<Integer,SongItem>();
	
	/**
	 * constructor for a songs results table
	 * @param albumID
	 */
	public SongsResultsTable(long albumID){
		super("SongsResultsTable");
		this.albumID = albumID;
	}
	
	/**
	 * adder for song item
	 * @param track
	 * @param s
	 */
	public void addSong(int track, SongItem s){
		songs.put(track, s);
	}
	
	/**
	 * detailed adder for song item
	 * @param track
	 * @param name
	 * @param artist
	 * @param length
	 */
	public void addSong(int track, String name, String artist, int length){
		SongItem s = new SongItem(name,artist,length);
		songs.put(track, s);
	}
	
	/**
	 * song getter by track number
	 * @param track
	 * @return
	 */
	public SongItem getSong(int track){
		return songs.get(track);
	}
	
	/**
	 * getter for the album ID to which this songs table belongs
	 * @return
	 */
	public long getAlbumID(){
		return this.albumID;
	}
}
