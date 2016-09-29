package itsix.admission.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import itsix.admission.controller.IStudentController;
import itsix.admission.custom.DoubleJTextField;
import itsix.admission.custom.IntegerJTextField;
import itsix.admission.model.IDepartment;
import itsix.admission.model.IPickedDepartment;
import itsix.admission.model.IWeightedSubject;

public class EnrollStudentView extends JFrame implements IEnrollStudentView {
	private IntegerJTextField ssnTextField;
	private JTextField nameTextField;
	private DoubleJTextField bacGradeTextField;
	private DepartmentDualList departmentDualList;
	private IStudentController studentController;
	private SubjectDualList subjectDualList;
	
	
	public EnrollStudentView(IStudentController enrollStudentController) {
		this.studentController = enrollStudentController;
		departmentDualList = new DepartmentDualList(studentController);
		initialize();
	}
	private void initialize() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				tuck();
			}
		});
		
		setBounds(100, 100, 500, 690);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel studentInfoPanel = new JPanel();
		getContentPane().add(studentInfoPanel);
		studentInfoPanel.setLayout(new BoxLayout(studentInfoPanel, BoxLayout.Y_AXIS));
		
		JPanel ssnPanel = new JPanel();
		FlowLayout fl_ssnPanel = (FlowLayout) ssnPanel.getLayout();
		fl_ssnPanel.setHgap(36);
		studentInfoPanel.add(ssnPanel);
		
		JLabel ssnLabel = new JLabel("SSN");
		ssnPanel.add(ssnLabel);
		ssnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		ssnTextField = new IntegerJTextField();
		ssnPanel.add(ssnTextField);
		ssnTextField.setColumns(13);
		
		JPanel namePanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) namePanel.getLayout();
		flowLayout_1.setHgap(29);
		studentInfoPanel.add(namePanel);
		
		JLabel nameLabel = new JLabel("Name");
		namePanel.add(nameLabel);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		nameTextField = new JTextField();
		namePanel.add(nameTextField);
		nameTextField.setColumns(13);
		
		JPanel bacGradePanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) bacGradePanel.getLayout();
		studentInfoPanel.add(bacGradePanel);
		
		JLabel bacGradeLabel = new JLabel("BAC grade");
		bacGradePanel.add(bacGradeLabel);
		bacGradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		bacGradeTextField = new DoubleJTextField();
		bacGradePanel.add(bacGradeTextField);
		bacGradeTextField.setColumns(13);
		
		JPanel deparmentListPanel = new JPanel();
		getContentPane().add(deparmentListPanel);
		deparmentListPanel.setLayout(new BoxLayout(deparmentListPanel, BoxLayout.X_AXIS));
		
		
		add(departmentDualList);
		
		//JPanel subjectLabelPanel = new JPanel();
		//getContentPane().add(subjectLabelPanel);
		
		subjectDualList = new SubjectDualList(studentController);
		add(subjectDualList);
		
		JPanel enrollButtonPanel = new JPanel();
		getContentPane().add(enrollButtonPanel);
		enrollButtonPanel.setLayout(new BoxLayout(enrollButtonPanel, BoxLayout.X_AXIS));
		
		JButton enrollBtn = new JButton("Enroll");
		enrollBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				studentController.enroll();
				
			}
			
		});
		enrollBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		enrollButtonPanel.add(enrollBtn);
	}
	
	@Override
	public String getStudentSSN() {
		return ssnTextField.getText();
	}
	@Override
	public String getStudentName() {
		return nameTextField.getText();
	}
	@Override
	public String getStudentBACGrade() {
		return bacGradeTextField.getText();
	}
	@Override
	public void display() {
		setVisible(true);
		studentController.reinitializePickedSubjects();
		subjectDualList.reinitialize();
		departmentDualList.reinitialize();
		subjectDualList.hideButtonsPanel();
	}
	
	@Override
	public void tuck() {
		ssnTextField.setText("");
		nameTextField.setText("");
		bacGradeTextField.setText("");
		updateDepartmentChoices();
		setVisible(false);		
	}
	
	
	public void updateDepartmentChoices() {
		departmentDualList.dataChanged();
	}
	
	@Override
	public IDepartment getSelectedSourceDepartment() {
		return departmentDualList.getSelectedSourceDepartment();
	}
	
	@Override
	public IPickedDepartment getSelectedDestinationDepartment() {
		return departmentDualList.getSelectedDestinationDepartment();
	}
	
	
	@Override
	public IWeightedSubject getSelectedSourceSubject() {
		return subjectDualList.getSelectedSourceSubject();
	}
	
	@Override
	public IWeightedSubject getSelectedDestinationSubject() {
		return subjectDualList.getSelectedDestinationSubject();
	}

	@Override
	public void updateSubjectChoices() {
		subjectDualList.dataChanged();
		
	}
	
	@Override
	public void subscribe(IPickedDepartment pickedDepartment) {
		subjectDualList.subscribe(pickedDepartment);
	}
	@Override
	public void noPickedDepartmentSelected() {
		subjectDualList.reinitialize();
		
	}
}
