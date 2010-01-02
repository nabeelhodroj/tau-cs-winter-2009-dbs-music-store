package GUI;

/**
 * created by Ariel
 * 
 * Sale tab handlers
 */
public class SaleFuncs {

	public static void clearSaleTable(){
		// clear fields
		Main.getSaleTableSaleItems().removeAll();
		Main.getSaleLabelTotalPriceValue().setText("");
		Main.getSaleLabelDateInput().setText(MainFuncs.getDate());
		Main.getSaleLabelTimeInput().setText(MainFuncs.getTime());
		// set buttons
		Main.getSaleButtonMakeSale().setEnabled(false);
		Main.getSaleButtonRemoveItem().setEnabled(false);
	}
}
