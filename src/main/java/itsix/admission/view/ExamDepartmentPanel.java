package itsix.admission.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExamDepartmentPanel extends JPanel implements ISubjectsPanel {
	private SubjectDualList subjectDualList;

	public ExamDepartmentPanel(SubjectDualList subjectDualList) {
		this.subjectDualList = subjectDualList;
		initialize();
	}

	private void initialize() {
		JLabel label = new JLabel();
		add(subjectDualList);		
	}
	
	
}
