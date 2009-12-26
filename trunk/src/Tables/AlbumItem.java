package Tables;

/**
 * created by Ariel
 * 
 * Album item data structure
 * =========================
 * holds all fields for a single album search result, to be inserted into an album search results table
 * contains the fields:
 * - album id
 * - album name
 * - artist
 * - year
 * - genre
 * - length (in seconds)
 * - songs list:
 * 	* song name
 * 	* artist
 * 	* length (in seconds)
 */
public class AlbumItem {
	private long albumID = -1;
	private String albumName = null;
	private String artist = null;
	private int year = -1;
	private String genre = null;
	private int length = -1;
	private SongsResultsTable songs = null;
	
	//////////////////////
	//	constructors	//
	//////////////////////
	
	/**
	 * constructor for an album result, to be entered to the album results table
	 */
	public AlbumItem(long albumID){
		this.albumID = albumID;
	}
	
	/**
	 * constructor with all album fields except songs list
	 * @param albumID
	 * @param albumName
	 * @param artist
	 * @param year
	 * @param genre
	 * @param length
	 */
	public AlbumItem(long albumID, String albumName, String artist, int year, String genre, int length){
		this.albumID = albumID;
		this.albumName = albumName;
		this.artist = artist;
		this.year = year;
		this.genre = genre;
		this.length = length;
	}
	
	/**
	 * constructor with all album fields
	 * @param albumID
	 * @param albumName
	 * @param artist
	 * @param year
	 * @param genre
	 * @param length
	 * @param songs
	 */
	public AlbumItem(long albumID, String albumName, String artist, int year, String genre, int length, SongsResultsTable songs){
		this.albumID = albumID;
		this.albumName = albumName;
		this.artist = artist;
		this.year = year;
		this.genre = genre;
		this.length = length;
		this.songs = songs;
	}

	/////////////////////////
	// getters and setters //
	/////////////////////////
	
	public long getAlbumID() {
		return albumID;
	}

	public void setAlbumID(long albumID) {
		this.albumID = albumID;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public SongsResultsTable getSongs() {
		return songs;
	}

	public void setSongs(SongsResultsTable songs) {
		this.songs = songs;
	}
}
