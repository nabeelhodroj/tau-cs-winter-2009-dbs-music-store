package Tables;

/**
 * created by Ariel
 * 
 * song item data structure for the SongResultsTable
 */
public class SongItem {
	private String songName;
	private String songArtist;
	private int songLength;
	
	/**
	 * constructor for a song item in the songs results table
	 * @param name
	 * @param artist
	 * @param length
	 */
	public SongItem(String name, String artist, int length){
		this.songName = name;
		this.songArtist = artist;
		this.songLength = length;
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
}
