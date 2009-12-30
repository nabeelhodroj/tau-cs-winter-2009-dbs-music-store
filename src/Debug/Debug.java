package Debug;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Debug {
	private static final String LOG_FILE = "DB_Music_Store_Log.txt";
	private static BufferedWriter outputFile = null;
	private static final boolean DEBUG_MODE = true; 
	
	public enum DebugOutput {
		STDOUT,
		STDERR,
		FILE
	}

	/**
	 * A method for logging.
	 * The usage is: Debug.log("This message goes to file..", DebugOutput.FILE);
	 * @param message: The massage explaining the current stage/problem.
	 * @param logger: The destination to write the message (STDOUT, STDERR, FILE) 
	 */
	public static void log(String message, DebugOutput logger) {
		switch (logger) {
			case STDOUT : {
				System.out.println(message);
				break;
			}
			case STDERR : {
				if (DEBUG_MODE) {
					System.err.println(message);
					break;
				}
			}
			case FILE : {
				try {
					if (outputFile == null) {
						outputFile = new BufferedWriter(new FileWriter(LOG_FILE));
					}
					outputFile.write(message + "\r\n");
					outputFile.flush();
				} catch (IOException e) {
					System.err.println(e);
				}
				break;
			}
		}
	}

}
