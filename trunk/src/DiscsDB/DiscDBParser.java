package DiscsDB;
import org.apache.commons.compress.compressors.bzip2.*;
import org.apache.commons.compress.archivers.tar.*;	
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;
import General.*;

public class DiscDBParser {
	
	private static String 	BZIP2_SUFFIX = ".bz2";
	private static String 	BZIP2_TAR_SUFFIX = ".tar.tar";
	private static String 	TAR_SUFFIX = ".tar";
	private static String 	TEMP_TAR_ARCHIVE = "archive.tar";
	private	static	int		bufferSize = 1000000;
	private	static	int		TRACK_FRAMES_IN_SEC = 75;
	private static 	int		MAX_ALBUMS_IN_LIST = 10000;
	private	static	int		MIN_ALBUM_PRICE = 30;
	private	static	int		MAX_ALBUM_PRICE = 120;
	
	private	static	boolean	Parsing = false;
	
	
	/** this list holds the albums read from file
	 * multiple threads can add and remove records from it safely 
	 * **/
	private static List<DiscDBAlbumData> generalAlbumList = new LinkedList<DiscDBAlbumData>();
	protected static final ReentrantLock lock = new ReentrantLock();
	
	
	/** adds a list of albums to the general albums list **/
	public static void addAlbumDataToList(List<DiscDBAlbumData> listToAdd)
	{
		lock.lock();
		generalAlbumList.addAll(listToAdd);
		lock.unlock();
		Debug.log("DiscDBParser::addAlbumDataToList: Added " + listToAdd.size() + " albums to outer list", Debug.DebugOutput.STDOUT);
	}
	
	
	/** returns (and removes) a list of albums from the general albums list **/
	public static List<DiscDBAlbumData> removeAllbumsDataFromList(int size)	
	{
		List<DiscDBAlbumData> albumList = null;
		try
		{
			lock.lock();
			if (generalAlbumList.size() >= size)
			{
				albumList = new LinkedList<DiscDBAlbumData>();
				for (int i = 0; i< size; i++)
				{
					albumList.add(i, generalAlbumList.remove(0));
				}
				Debug.log("DiscDBParser::removeAllbumsDataFromList: - retrived " + size + "albums from list", Debug.DebugOutput.STDOUT);				
			}
			else
			{
				Debug.log("DiscDBParser::removeAllbumsDataFromList: ERROR - can't retrive " + size + "albums, because list contains only " + generalAlbumList.size(), Debug.DebugOutput.STDOUT);
				Debug.log("DiscDBParser::removeAllbumsDataFromList: ERROR - can't retrive " + size + "albums, because list contains only " + generalAlbumList.size());
			}
		}
		finally
		{
			lock.unlock();
		}
		return albumList;
	}

	
	private static void parseTarFile(String tarFileName)	 throws IOException
	{
		List<DiscDBAlbumData> albumList = new LinkedList<DiscDBAlbumData>();
		List<String>	genereList = new LinkedList<String>();
		/// Read tar file
		FileInputStream tarIn = new FileInputStream(tarFileName);
		TarArchiveInputStream tarInput = new TarArchiveInputStream(tarIn);		 
		TarArchiveEntry entry;
		int index = 0;
		int legalsDiscs = 0;
		
		long startTime = System.nanoTime();		// for performance measuring
		
		try
		{
			setParsing(true);
			 while ((entry = tarInput.getNextTarEntry()) != null)
			 {
				 // read entry
				int offset = 0;
				if (entry.getSize() > 0)
				{
					 byte[] content = new byte[(int)entry.getSize()];
					 while ((offset >= 0) && (offset < entry.getSize())) 
					 {
						 offset += tarInput.read(content, offset, content.length - offset);
					 }
					 
					 // create dummy file for entry
					 String entryFileName = "entry" + index +".txt";
					 index++;
					 
					 FileOutputStream entryFile = new FileOutputStream(entryFileName);
					 entryFile.write(content, 0, offset);
					 entryFile.close();
					 
					 // parse file
					 File file = new File(entryFileName);
					 Parser par = new Parser(file);
					 Random random = new Random();
					 
					 try
					 {
						if (par.isCddbFormat()) 
						{
							// Run the parser for the file
							if (par.run()) 
							{
								// Reset variables
								String sGenere = "unknown";
								// Get name of genre
								if (par.getDgenre().trim().length() > 0) 
								{
									sGenere = par.getDgenre().toLowerCase();
								}			
								
								// Generate (random) price for albums
								int price = MIN_ALBUM_PRICE + random.nextInt(MAX_ALBUM_PRICE - MIN_ALBUM_PRICE + 1);								
								
								DiscDBAlbumData discData = new DiscDBAlbumData(par.getDtitle(), sGenere, par.getDyear(), par.getDiscLength(), price); 								
								Pattern _notAsciiPattern = Pattern.compile("[^\\p{ASCII}]"); 
								boolean ascii = !(_notAsciiPattern.matcher(par.getDtitle()).find());
								for (int i = 0; i < par.getTrackOffsets().size() && ascii ; i++)
								{
									/* Get track length - in seconds */
									int trackLnegthSec;
									if (i < par.getTrackOffsets().size()-1)
									{
										trackLnegthSec = getTrackLengthInSec(par.getTrackOffsets().get(i), par.getTrackOffsets().get(i+1));										
									}
									else
									{
										trackLnegthSec = getLastTrackLengthInSec(par.getTrackOffsets().get(i), discData.getLengthSec());
									}									
									
									// does album has an artist ? if so - add it to all the tracks
									String albumArtist = null;
									if (!discData.isVariousArtists())
									{
										albumArtist = discData.getArtist(); 
									}
									DiscDBTrackData trackData = new DiscDBTrackData(i+1, par.getTtitle()[i], albumArtist, trackLnegthSec);
									discData.addTrackToList(trackData);
									ascii = !(_notAsciiPattern.matcher(par.getTtitle()[i]).find());								
								}
								
								/* Disc and tracks titles are all in ASCII format */
					//			System.out.println(discData.toString());
								if (ascii)
								{
									legalsDiscs++;
									// Add genere to list
									if (!genereList.contains(sGenere))
									{
										genereList.add(sGenere);
									}
									
									// move albums to "main" list, so someone else may read them
									if (albumList.size() > MAX_ALBUMS_IN_LIST)
									{
										addAlbumDataToList(albumList);
										albumList.clear();											
									}
									albumList.add(discData);
								}
								else
								{
						//			System.out.println("Disc was not ASCII");
								}
							}								
						}
						else 
						{
							Debug.log("DiscDBParser::parseTarFile: ERROR - Cannot parse file " + file + " which is not a CDDB format CD text data file");								
						}
					 }
					 catch	(Exception ex)
					 {
						Debug.log("DiscDBParser::parseTarFile: ERROR - Exception occurred during parsing of file: " + file + " (" + ex.toString() + ")");						 

					 }
					 finally
					 {
						 par.close();
						 file.delete();
					 }
				 }
			 }
			 // calculate how much time the process took
			 long estimatedTime = System.nanoTime() - startTime;
			 estimatedTime /= 1000000000;	// convert to seconds
			 Debug.log("DiscDBParser::parseTarFile: SUCCESS - Parsed " + index + " albums (" + legalsDiscs + " valid), " + genereList.size() + " generes. Parse time: " + estimatedTime + " seconds");			 
		}
		finally
		{
			setParsing(false);
			tarIn.close();
			tarInput.close();			 
		}		
	}
	
	
	/* Extract file in Bzip2 format, into tar file */
	private static void parseZipFile(String fileName)	 throws IOException
	{
		String tarFileName = TEMP_TAR_ARCHIVE;
		
		FileInputStream in = new FileInputStream(fileName);
		FileOutputStream out = new FileOutputStream(tarFileName);
		BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
		final byte[] buffer = new byte[bufferSize];
		int n = 0;
		try
		{
			while (-1 != (n = bzIn.read(buffer))) {
				out.write(buffer, 0, n);
			}
		}
		finally
		{
			out.close();
			bzIn.close();
		}		
	}
	
	
	public static void parseFile(String fileName) throws IOException 
	{
		// read bz file (which can come in form of *.tar.tar) into tar file
		if (fileName.endsWith(BZIP2_SUFFIX) || fileName.endsWith(BZIP2_TAR_SUFFIX))
		{
			parseZipFile(fileName);
		}
		
		parseTarFile(TEMP_TAR_ARCHIVE);	
	}
	
	
	/** Calculate track length in seconds, according to its' and the successor offsets **?
	 * 
	 * @param offset
	 * @param nextTackOffset
	 * @return
	 */
	public static int	getTrackLengthInSec(int offset, int nextTackOffset)
	{
		int diff = nextTackOffset - offset;
		int length = diff / TRACK_FRAMES_IN_SEC;
		return length;
	}
	

	/** Calculate Disc's last track length in seconds, according to:
	 *  - its' offset (in frames) 
	 *  - disc's length (in seconds) **?
	 * 
	 * @param offset
	 * @param nextTackOffset
	 * @return
	 */	
	private static int	getLastTrackLengthInSec(int offset, int discLenghtSec)
	{
		int nextOffset = discLenghtSec*TRACK_FRAMES_IN_SEC;
		return getTrackLengthInSec(offset, nextOffset);		
	}


	public static boolean isParsing() {
		return Parsing;
	}


	public static void setParsing(boolean parsing) {
		Parsing = parsing;
	}
	
	
	public static int	getCurrentAlbumListSize()
	{
		return generalAlbumList.size();
	}
}