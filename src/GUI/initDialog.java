package GUI;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class initDialog extends org.eclipse.swt.widgets.Dialog {
	private static initDialog inst;
	private static Shell shell;
	private static Shell dialogShell;
	private static Button initDialogButtonExit;
	private static Button initDialogButtonStart;
	private static Group initDialogGroup;
	private static Combo initDialogCombo;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Dialog inside a new Shell.
	*/
	public static void openInitDialog() {
		try {
			Display display = Display.getDefault();
			shell = new Shell(display);
			inst = new initDialog(shell, SWT.NULL);
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeInitDialog(){
		shell.close();
	}

	public initDialog(Shell parent, int style) {
		super(parent, style);
	}

	public void open() {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			dialogShell.setText("SSDA Music Store Manager");

			dialogShell.setLayout(null);
			{
				initDialogGroup = new Group(dialogShell, SWT.NONE);
				GridLayout initDialogGroupLayout = new GridLayout();
				initDialogGroup.setLayout(null);
				initDialogGroup.setText("Select store");
				initDialogGroup.setBounds(0, 4, 306, 113);
				{
					initDialogCombo = new Combo(initDialogGroup, SWT.NONE);
					initDialogCombo.setBounds(12, 29, 276, 21);
				}
				{
					initDialogButtonStart = new Button(initDialogGroup, SWT.PUSH | SWT.CENTER);
					initDialogButtonStart.setText("Start");
					initDialogButtonStart.setBounds(12, 62, 135, 33);
				}
				{
					initDialogButtonExit = new Button(initDialogGroup, SWT.PUSH | SWT.CENTER);
					initDialogButtonExit.setText("Exit");
					initDialogButtonExit.setBounds(153, 62, 135, 33);
				}
			}
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setLocation(getParent().toDisplay(100, 100));
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			
			// initialize combo box and listeners
			MainFuncs.initDialogComboBoxItems();
			MainFuncs.initiDialogBoxListeners();
			
			while (!dialogShell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Shell getDialogShell() {
		return dialogShell;
	}

	public static void setDialogShell(Shell dialogShell) {
		initDialog.dialogShell = dialogShell;
	}

	public static Button getInitDialogButtonExit() {
		return initDialogButtonExit;
	}

	public static void setInitDialogButtonExit(Button initDialogButtonExit) {
		initDialog.initDialogButtonExit = initDialogButtonExit;
	}

	public static Button getInitDialogButtonStart() {
		return initDialogButtonStart;
	}

	public static void setInitDialogButtonStart(Button initDialogButtonStart) {
		initDialog.initDialogButtonStart = initDialogButtonStart;
	}

	public static Group getInitDialogGroup() {
		return initDialogGroup;
	}

	public static void setInitDialogGroup(Group initDialogGroup) {
		initDialog.initDialogGroup = initDialogGroup;
	}

	public static Combo getInitDialogCombo() {
		return initDialogCombo;
	}

	public static void setInitDialogCombo(Combo initDialogCombo) {
		initDialog.initDialogCombo = initDialogCombo;
	}	
}
