package DBLayer;

import java.sql.*;

import GUI.*;
import General.*;
import General.Debug.*;
import Queries.*;
import Tables.*;

public class DBConnectionSearch {
	
	public class GetAlbumsSearchResults implements Runnable{
		private AlbumSearchQuery albumSearchQuery;

		public GetAlbumsSearchResults(AlbumSearchQuery albumSearchQuery) {
			this.albumSearchQuery = albumSearchQuery;
		}
		
		// TODO: Deal with unique in case of Songs search
		
		@Override
		public void run() {
			Debug.log("DBConnectionSearch.GetAlbumsSearchResults thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			String selectPart = "SELECT Albums.album_id, " +
					"Albums.album_name, " +
					"artists.artist_name, " +
					"Albums.year, " +
					"genres.genre_name, " +
					"Albums.length_sec, " +
					"Albums.price\n";
			String fromPart = "FROM Albums, artists, genres";
			String wherePart = "WHERE Albums.artist_id = artists.artist_id AND\n" +
					"Albums.genre_id = genres.genre_name"; 
				
			if (albumSearchQuery.isByAlbumID()){
				wherePart += " AND\n" +
						"Albums.album_id = " + albumSearchQuery.getAlbumID();
			} else {
				if (albumSearchQuery.hasAlbumName()){
					wherePart += " AND\n" +
							"Albums.album_name LIKE '%" + albumSearchQuery.getAlbumName()+"%'";
					
				}
				if (albumSearchQuery.hasArtist()) {
					wherePart += " AND\n" +
							"artists.artist_name LIKE '%" + albumSearchQuery.getArtist()+"%'";
				}
				if (albumSearchQuery.hasGenres()) {
					for (int i = 0;i < albumSearchQuery.getGenresArr().length;i++){
						if (albumSearchQuery.getGenresArr()[i]){
							wherePart += " AND\n" +
									"genres.genre_name LIKE '%" + AlbumSearchQuery.getGenreNames()[i] + "%'";
						}
					}
				}
				if (albumSearchQuery.hasOtherGenre()){
					wherePart += " AND\n" +
							"genres.genre_name LIKE '%" + albumSearchQuery.getOtherGenre()+"%'";
				}
				if (albumSearchQuery.hasYear()){
					wherePart += " AND\n" +
							"Albums.year <= " + albumSearchQuery.getYearTo() + " AND\n" +
							"Albums.year >= " + albumSearchQuery.getYearFrom();
				}
				if (albumSearchQuery.hasSongNames()){
					fromPart += ", Songs";
					wherePart += " AND\n" +
							"Albums.Album_id = Songs.album_id";
					for (String songName : albumSearchQuery.getSongNames().split(";")) {
						wherePart += " AND\n" +
								"Songs.song_name LIKE '%" + songName + "%'";
					}
				}
				
				if (albumSearchQuery.getStockOption() == AlbumSearchStockOptionEnum.STORE){
					fromPart += ", Stock";
					wherePart += " AND\n" +
							"Albums.album_id = Stock.album_id AND\n" +
							"Stock.store_id = " + StaticProgramTables.thisStore.getStoreID();
				} else if (albumSearchQuery.getStockOption() == AlbumSearchStockOptionEnum.NETWORK){
					fromPart += ", Stock";
					wherePart += " AND\n" +
							"Albums.album_id = Stock.album_id";
				}
			}
			
			fromPart += "\n";
			wherePart += "\n";
			
			DBQueryResults searchQueryResults = DBAccessLayer.executeQuery(selectPart+fromPart+wherePart);
			if (searchQueryResults == null){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.SEARCH_FAILURE);
				return;
			}
			ResultSet rs = searchQueryResults.getResultSet();
			
			AlbumsResultsTable resultsTable = new AlbumsResultsTable();
			
			try {
				while (rs.next()){
					resultsTable.addAlbum(rs.getInt("album_id"), 
							rs.getString("album_name"), 
							rs.getString("artist_name"),
							rs.getInt("year"),
							rs.getString("genre"), 
							rs.getInt("length_sec"),
							new SongsResultsTable(rs.getInt("album_id")),
							rs.getInt("price"),
							-1,
							-1);
				}
			} catch (SQLException e) {
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.SEARCH_FAILURE);
				searchQueryResults.close();
				return;
			}
			
			GuiUpdatesInterface.updateAlbumResultsTable(resultsTable);
			// until implemented, use example:
			// TablesExamples.getAlbumsSearchResults();
		}		
	}
	
	public class GetSongsResults implements Runnable{
		long albumID;
		public GetSongsResults(long albumID) {
			this.albumID = albumID;
		}
		@Override
		public void run() {
			Debug.log("DBConnectionSearch.GetSongsResults thread is started",DebugOutput.FILE,DebugOutput.STDOUT);
			
			String songsQuery = "SELECT Songs.track_num, Songs.song_name, artists.artist_name, Songs.length_sec\n";
			songsQuery += "FROM Songs, Artists\n";
			songsQuery += "WHERE Songs.album_id = " + albumID + " AND\n" +
					"Songs.artist_id = artists.artist_id";
			
			DBQueryResults songsQueryResults = DBAccessLayer.executeQuery(songsQuery);
			if (songsQueryResults == null){
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.SEARCH_FAILURE);
				return;
			}
			ResultSet rs = songsQueryResults.getResultSet();

			SongsResultsTable resultsTable = new SongsResultsTable(albumID);
			
			try {
				while (rs.next()){
					resultsTable.addSong(rs.getInt("track_num"),
							rs.getString("song_name"),
							rs.getString("artist_name"),
							rs.getInt("length_sec"));
				}
			} catch (SQLException e) {
				GuiUpdatesInterface.notifyDBFailure(DBActionFailureEnum.SEARCH_FAILURE);
				songsQueryResults.close();
				return;
			}

			GuiUpdatesInterface.updateSongsResultsTable(albumID, resultsTable);
			// until implemented, use example:
			// TablesExamples.getSongsResults(albumID);
		}
	}
}
