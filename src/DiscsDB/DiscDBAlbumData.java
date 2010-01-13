package DiscsDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import General.Debug;


public class DiscDBAlbumData {	
	
	// field length constraints
	private static	int		MAX_GENRE_LENGTH = 50;
	private static	int		MAX_ARTIST_NAME_LENGTH = 150;
	private static	int		MAX_ALBUM_NAME_LENGTH = 150;

	private List<DiscDBTrackData> trackList = new LinkedList<DiscDBTrackData>();
	private	String	title;	/* disc title, may include artist name */
	private	String	name;
	private String 	genere;
	private	String 	year;
	private String 	artist;
	private	int		lengthSec;
	private int		Price;
	private boolean isVariousArtists ;
	
	public static final String DB_NULL_VALUE = "NULL";

	
	public DiscDBAlbumData(String title, String genere, int year, int lengthSec)	
	{	
		this(title, genere, year, lengthSec, 0);
	}
	
	
	public DiscDBAlbumData(String title, String genere, int year, int lengthSec, int price)
	{
		this.title = title.trim();
		this.genere = genere.trim();
		this.lengthSec = lengthSec;
		this.Price = price;
		if (year > 0)
		{
			this.year = Integer.toString(year);
		}
		else
		{
			this.year = DB_NULL_VALUE;
		}
		
		parseDiscTitle();
		checkVariousArtist();
		
		// support "'" in strings - so it won't fuck up the DB commands....		
		this.title = this.title.replace("'", "''");
		this.name = this.name.replace("'", "''");
		this.artist = this.artist.replace("'", "''");
		this.genere = this.genere.replace("'", "''");
		
	}
	
	/** Add track to track list, according to its' number (1 based - must be at least 1 !!!)  
	 * 
	 * @param track
	 */
	public	void addTrackToList(DiscDBTrackData track)
	{
		trackList.add(track.getTrackNum()-1, track);		
	}
	
	/** Get Track according to its' number (1 based - must be at least 1 !!!) 
	 * 
	 * @param trackNum
	 * @return
	 */
	public	DiscDBTrackData getTrack(int trackNum)
	{
		return trackList.get(trackNum-1);
	}
	
	
	public List<DiscDBTrackData> getTrackList() {
		return trackList;
	}
	public void setTrackList(List<DiscDBTrackData> trackList) {
		this.trackList = trackList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getLengthSec() {
		return lengthSec;
	}
	public void setLengthSec(int lengthSec) {
		this.lengthSec = lengthSec;
	}
	public boolean isVariousArtists() {
		return isVariousArtists;
	}
	public void setVariousArtists(boolean isVariousArtists) {
		this.isVariousArtists = isVariousArtists;
	}
		
	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}	
	
	public boolean checkVariousArtist()
	{
		if (this.artist.toLowerCase().contains("various") ||
			this.artist.toLowerCase().contains("va."))
		{
			setVariousArtists(true);			
		}
		else
		{
			setVariousArtists(false);			
		}
		return isVariousArtists();
	}

	
	/** Try to get disc's name and artist, if the title is in the format of:
	 *		artist / name
	 *		possible other titles:
	 *		artist1 / artist2 / ... / artistN / album name
	 *		artist1 / artist2 / ... / artistN / album name (CD 1/2)
	 */
	public	void	parseDiscTitle()	
	{
		this.name = "";
		this.artist = "";
		if (this.title.indexOf(" / ") != -1)
		{		
			String[] artistAndName = 	this.title.split(" / ");		
			for (int i = 0; i < artistAndName.length-1; i++)
			{
				if (this.artist.length() > 0)
				{
					this.artist += ", ";
				}	
				this.artist += artistAndName[i].trim();				
			}
			this.name = artistAndName[artistAndName.length-1].trim();
			if (artistAndName.length > 2)
			{
		//		Debug.log("DiscDBParser::parseTarFile: INFO - multiple slash title: " + this.title +
			//			"\n artist: " + this.artist + "\n name: " + this.name);				
			}
		}
		else
		{
			this.name = this.title;
			this.artist = DB_NULL_VALUE;			
//			Debug.log("DiscDBParser::parseTarFile: INFO - no slash title: " + this.title);								
		}
	}
	
	
	public	boolean isValid()
	{
		return  ((this.name.length() > 0) && (this.artist.length() > 0) 
				&& (this.name.length() <= MAX_ALBUM_NAME_LENGTH) &&
				(this.artist.length() <= MAX_ARTIST_NAME_LENGTH) &&
				(this.genere.length() <= MAX_GENRE_LENGTH) );
	}
	
	
	public	String	toString()	
	{
		String ret = "Disc Title: " + this.title;
		if (isVariousArtists())
		{		this.artist = this.artist.replace("'", "''");
			ret += "(various Artists)";
		}
		ret += "\n";
		if (this.name != null)
		{
			ret += "Name: " + this.name + "\n";
		}
		else
		{
			ret += "Name: " + "N/A" + "\n";
		}

		if (this.artist != null)
		{
			ret += "Artist: " + this.artist + "\n";
		}
		else
		{
			ret += "Artist: " + "N/A" + "\n";
		}

		if (this.year != null)
		{
			ret += "Year: " + this.year + "\n";
		}
		else
		{
			ret += "Year: " + "N/A" + "\n";
		}
		
		ret += "Genere: " + this.genere + "\n";
		ret += "length: " + this.lengthSec + " seconds\n";
		
		for (DiscDBTrackData track : trackList)
		{
			ret += "   " + track.toString();
		}
		return ret;
	}
}
