package GUI;

/**
 * created by Ariel
 * 
 * enum for actions on orders or requests tables
 * used by DB to inform that action could not be taken
 */
public enum OrdersRequestsActionsEnum {

	CANCEL_ORDER,
	APPROVE_REQUEST,
	DENY_REQUEST;
	
}
