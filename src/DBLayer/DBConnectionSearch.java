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
					"Albums.artist_name, " +
					"Albums.year, " +
					"Albums.genre, " +
					"Albums.length_sec, " +
					"Albums.price\n";
			String fromPart = "FROM Albums";
			String wherePart = "WHERE "; 
				
			if (albumSearchQuery.isByAlbumID()){
				wherePart += "Albums.album_id = " + albumSearchQuery.getAlbumID();
			} else {
				if (albumSearchQuery.hasAlbumName()){
					wherePart += "Albums.album_name LIKE '%" + albumSearchQuery.getAlbumName()+"%' AND\n";
				}
				if (albumSearchQuery.hasArtist()) {
					wherePart += "Albums.artist_name LIKE '%" + albumSearchQuery.getArtist()+"%' AND\n";
				}
				if (albumSearchQuery.hasGenres()) {
					for (int i = 0;i < albumSearchQuery.getGenresArr().length;i++){
						if (albumSearchQuery.getGenresArr()[i]){
							wherePart += "Albums.genre LIKE '%" + AlbumSearchQuery.getGenreNames()[i] + "%' AND\n";
						}
					}
				}
				if (albumSearchQuery.hasOtherGenre()){
					wherePart += "Albums.genre LIKE '%" + albumSearchQuery.getOtherGenre()+"%' AND\n";
				}
				if (albumSearchQuery.hasYear()){
					wherePart += "Albums.year <= " + albumSearchQuery.getYearTo() + " AND\n";
					wherePart += "Albums.year >= " + albumSearchQuery.getYearFrom() + "AND \n";
				}
				if (albumSearchQuery.hasSongNames()){
					fromPart += ", Songs";
					wherePart += "Albums.Album_id = Songs.album_id AND\n";
					for (String songName : albumSearchQuery.getSongNames().split(";")) {
						wherePart += "Songs.song_name LIKE '%" + songName + "%' AND\n";
					}
				}
				
				if (albumSearchQuery.getStockOption() == AlbumSearchStockOptionEnum.STORE){
					fromPart += ", Stock";
					wherePart += "Albums.album_id = Stock.album_id AND\n";
					wherePart += "Stock.store_id = " + StaticProgramTables.thisStore.getStoreID() + "AND\n";
				} else if (albumSearchQuery.getStockOption() == AlbumSearchStockOptionEnum.NETWORK){
					fromPart += ", Stock";
					wherePart += "Albums.album_id = Stock.album_id AND\n";
				}
			}
			
			if (wherePart.endsWith("AND\n")) wherePart = wherePart.substring(0, wherePart.length()-4);
			
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
			
			String songsQuery = "SELECT track_num, song_name, artist_name, length_sec\n";
			songsQuery += "FROM Songs\n";
			songsQuery += "WHERE album_id = " + albumID + "\n";
			
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
