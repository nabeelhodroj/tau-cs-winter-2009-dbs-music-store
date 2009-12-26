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
	private boolean variousArtists;
	private Map<Integer,SongsResultsTableItem> songs = new HashMap<Integer,SongsResultsTableItem>();
	
	/**
	 * constructor for a songs results table
	 * @param albumID
	 */
	public SongsResultsTable(long albumID, boolean variousArtists){
		super("SongsResultsTable");
		this.albumID = albumID;
		this.variousArtists = variousArtists;
	}
	
	/**
	 * adder for song item
	 * @param track
	 * @param s
	 */
	public void addSong(SongsResultsTableItem s){
		songs.put(s.getTrack(), s);
	}
	
	/**
	 * detailed adder for song item, where song has an artist
	 * @param track
	 * @param name
	 * @param artist
	 * @param length
	 */
	public void addSong(int track, String name, String artist, int length){
		SongsResultsTableItem s = new SongsResultsTableItem(track,name,artist,length);
		songs.put(track, s);
	}
	
	/**
	 * detailed adder for song item, where song has no unique artist
	 * @param track
	 * @param name
	 * @param artist
	 * @param length
	 */
	public void addSong(int track, String name, int length){
		SongsResultsTableItem s = new SongsResultsTableItem(track,name,length);
		songs.put(track, s);
	}
	
	/**
	 * song getter by track number
	 * @param track
	 * @return
	 */
	public SongsResultsTableItem getSong(int track){
		return songs.get(track);
	}
	
	/**
	 * getter for the album ID to which this songs table belongs
	 * @return
	 */
	public long getAlbumID(){
		return this.albumID;
	}
	
	/**
	 * returns true if the album has various artists, and false otherwise
	 * @return
	 */
	public boolean hasVariousArtists(){
		return this.variousArtists;
	}
}
