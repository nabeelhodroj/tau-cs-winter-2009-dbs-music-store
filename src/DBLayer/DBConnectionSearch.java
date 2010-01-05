package DBLayer;

import Debug.Debug;
import Debug.Debug.DebugOutput;
import Queries.AlbumSearchQuery;
import Tables.TablesExamples;

public class DBConnectionSearch {
	
	public class GetAlbumsSearchResults implements Runnable{
		private AlbumSearchQuery albumSearchQuery;

		public GetAlbumsSearchResults(AlbumSearchQuery albumSearchQuery) {
			this.albumSearchQuery = albumSearchQuery;
		}

		@Override
		public void run() {
			Debug.log("DBConnectionSearch.GetAlbumsSearchResults thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			// TODO 
			// until implemented, use example:
			TablesExamples.getAlbumsSearchResults();
		}		
	}
}
