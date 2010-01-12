package DiscsDB;

public class DiscDBTrackData {

	private String 	title;
	private String 	artist;
	private int		lengthSdc;
	private	String	name;
	private	int		trackNum;
	
	public static final String DB_NULL_VALUE = "NULL";
	
	// C'tor	
	public DiscDBTrackData(int trackNum, String trackTitle, String trackArtist, int trackLengthSec)
	{	
		this.trackNum = trackNum;
		this.title = trackTitle;
		this.artist = trackArtist;		
		this.lengthSdc = trackLengthSec;
								
		if (trackArtist != null)
		{
			this.artist = trackArtist;
			this.name = trackTitle;
		}
		else
		{
			parseTrackTitle();
		}
		
		this.artist = this.artist.replace("'", "''");
		this.title = this.title.replace("'", "''");
		this.name = this.name.replace("'", "''");
		
		
	}
	
	
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getLengthSdc() {
		return lengthSdc;
	}
	public void setLengthSdc(int lengthSdc) {
		this.lengthSdc = lengthSdc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTrackNum() {
		return trackNum;
	}
	public void setTrackNum(int trackNum) {
		this.trackNum = trackNum;
	}
	
	/** Try to get track's name and artist, if the title is in the format of:
	 *		artist / name
	 *		possible other titles:
	 *		artist1 / artist2 / ... / artistN / track name
	 */	
	public	void	parseTrackTitle()	
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
		return ((this.name.length()) > 0 && (this.artist.length() > 0));
	}
	
	public	String	toString()
	{
		String ret = this.trackNum + ". Title: " + this.title;

		if (this.name != null)
		{
			ret += "   Name: " + this.name;
		}
		else
		{
			ret += "   Name: " + "N/A";
		}

		if (this.artist != null)
		{
			ret += "   Artist: " + this.artist;
		}
		else
		{
			ret += "   Artist: " + "N/A";
		}

		ret += "   length: " + this.lengthSdc + " seconds\n";
		return ret;
	}
}
