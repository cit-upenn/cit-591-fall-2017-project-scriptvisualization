package moviegui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Event;

public class MainWindow {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(197, 81, 105, 49);
		
		Button btnSearch = new Button(shell, SWT.NONE);
		
		//btnSearch.addListener(eventType, listener);
		
		btnSearch.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg) {
				System.out.println("click!"); 
				
			}
		 });

		
		
		
		
		btnSearch.setBounds(207, 159, 95, 28);
		btnSearch.setText("Search");

	}
}
