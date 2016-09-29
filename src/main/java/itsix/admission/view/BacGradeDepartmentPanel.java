package itsix.admission.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BacGradeDepartmentPanel extends JPanel implements ISubjectsPanel {
	
	public BacGradeDepartmentPanel() {
		initialize();
	}

	private void initialize() {
		JLabel label = new JLabel("The admission in this department is based on the BAC grade only");		
	}
	
	
	
}
