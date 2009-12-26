package Queries;

/**
 * created by Ariel
 * 
 * Exception class for queries that are not defined properly
 */
public class QueryErrorException extends Exception {
	private String queryType;
	
	public QueryErrorException(String message, String queryType){
		super(message);
		this.queryType = queryType;
	}
	
	/**
	 * returns the query type for the query generated this exception
	 * @return
	 */
	public String getQueryType(){
		return this.queryType;
	}
	
	@Override
	/**
	 * returns the string representation of the query error message
	 */
	public String toString(){
		return this.getMessage()+": "+this.getQueryType();
	}
}
