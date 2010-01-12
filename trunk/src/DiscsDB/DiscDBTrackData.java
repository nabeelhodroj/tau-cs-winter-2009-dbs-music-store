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
	
	public	void	parseTrackTitle()	
	{
		this.name = this.title;
		this.artist = DB_NULL_VALUE;
		if (this.title.indexOf("/") != -1)
		{		
			String[] artistAndName = 	this.title.split("/");
			if (artistAndName.length == 2)
			{
				this.artist = artistAndName[0].trim();
				this.name = artistAndName[1].trim();
			}
		}		
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
