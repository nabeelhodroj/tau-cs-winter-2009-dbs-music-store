package Tables;

/**
 * created by Ariel
 * 
 * song item data structure for the SongResultsTable
 */
public class SongsResultsTableItem {
	private int track;
	private String songName;
	private String songArtist;
	private boolean hasArtist;
	private int songLength;
	
	/**
	 * constructor for a song item in the songs results table
	 * @param track
	 * @param name
	 * @param artist
	 * @param length
	 */
	public SongsResultsTableItem(int track, String name, String artist, int length){
		this.track = track;
		this.songName = name;
		this.songArtist = artist;
		this.hasArtist = true;
		this.songLength = length;
	}
	
	/**
	 * constructor for a song item in the songs results table
	 * with artist same as the album's artist
	 * @param track
	 * @param name
	 * @param length
	 */
	public SongsResultsTableItem(int track, String name, int length){
		this.track = track;
		this.songName = name;
		this.hasArtist = false;
		this.songLength = length;
	}
	
	public int getTrack() {
		return this.track;
	}
	
	public String getSongName() {
		return songName;
	}

	public String getSongArtist() {
		return songArtist;
	}

	public int getSongLength() {
		return songLength;
	}
	
	public boolean hasUniqueArtist(){
		return this.hasArtist;
	}
}
