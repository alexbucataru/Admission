package itsix.admission.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import itsix.admission.controller.IDepartmentController;
import itsix.admission.controller.ISubjectController;
import itsix.admission.custom.DepartmentCellRenderer;
import itsix.admission.custom.DepartmentComboBoxModel;
import itsix.admission.custom.IntegerJTextField;
import itsix.admission.custom.SubjectCellRenderer;
import itsix.admission.custom.SubjectComboBoxModel;
import itsix.admission.custom.SubjectTableModel;

public class AdmissionRulesView extends JFrame implements IAdmissionRulesView {
	private IntegerJTextField numberOfPlacesTextField;
	private JTextField newSubjectTextField;
	private JTable subjectsTable;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private IDepartmentController departmentController;
	private ISubjectController subjectController;
	private DepartmentComboBoxModel departmentComboBoxModel;
	private SubjectComboBoxModel subjectComboBoxModel;
	private JPanel subjectsPanel;
	private JButton saveBtn;
	private JPanel bacGradePanel;
	private JPanel saveBtnPanel;
	private SubjectTableModel subjectTableModel;
	private JRadioButton withExamRdBtn;
	private JRadioButton combinedRdBtn;
	private JRadioButton withBacGradeRdBtn;
	private IntegerJTextField bacGradeTextField;
	
	public AdmissionRulesView(IDepartmentController departmentController, ISubjectController subjectController) {
		this.departmentController = departmentController;
		this.subjectController = subjectController;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				tuck();
			}
		});
		
		setBounds(100, 100, 500, 490);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		subjectsPanel = new JPanel();
		
		JPanel departmentComboBoxPanel = new JPanel();
		getContentPane().add(departmentComboBoxPanel);
		
		JComboBox departmentsComboBox = new JComboBox();
		departmentComboBoxModel = new DepartmentComboBoxModel(departmentController.getDepartments());
		departmentsComboBox.setModel(departmentComboBoxModel);
		departmentsComboBox.setRenderer(new DepartmentCellRenderer());
		departmentsComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonGroup.clearSelection();
				hidePanels();
				departmentController.newDepartmentSelected(departmentComboBoxModel.getSelectedItem());
			}
		});
		departmentComboBoxPanel.add(departmentsComboBox);
		
		JPanel numberOfPlacesPanel = new JPanel();
		JLabel numberOfPlaceslbl = new JLabel("Available spots: ");
		numberOfPlacesPanel.add(numberOfPlaceslbl);
		
		numberOfPlacesTextField = new IntegerJTextField();
	    numberOfPlacesTextField.setText(departmentController.getNumberOfPlaces());
		numberOfPlacesPanel.add(numberOfPlacesTextField);
		numberOfPlacesTextField.setColumns(10);
		
		add(numberOfPlacesPanel);
		
		JPanel admissionTypePanel = new JPanel();
		getContentPane().add(admissionTypePanel);
		admissionTypePanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		withBacGradeRdBtn = new JRadioButton("With BAC Grade");
		withBacGradeRdBtn.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				bacGradeAdmissionPicked();
				
			}
		});
		
		
		buttonGroup.add(withBacGradeRdBtn);
		admissionTypePanel.add(withBacGradeRdBtn);
		
		withExamRdBtn = new JRadioButton("With exam");
		withExamRdBtn.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				examAdmissionPicked();
				
			}
		});
		buttonGroup.add(withExamRdBtn);
		admissionTypePanel.add(withExamRdBtn);
		
		combinedRdBtn = new JRadioButton("With BAC Grade and exam");
		combinedRdBtn.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				combinedAdmissionPicked();
				
			}
		});
		
		buttonGroup.add(combinedRdBtn);
		admissionTypePanel.add(combinedRdBtn);
		
		bacGradePanel = new JPanel();
		getContentPane().add(bacGradePanel);
		
		JLabel bacgradelbl = new JLabel("BAC Grade Weight: ");
		bacGradePanel.add(bacgradelbl);
		
		bacGradeTextField = new IntegerJTextField();
		bacGradePanel.add(bacGradeTextField);
		bacGradeTextField.setColumns(10);
		
		JPanel newSubjectPanel = new JPanel();
		subjectsPanel.add(newSubjectPanel);
		
		JLabel newSubjectNameLbl = new JLabel("New Subject Name: ");
		newSubjectPanel.add(newSubjectNameLbl);
		
		newSubjectTextField = new JTextField();
		newSubjectPanel.add(newSubjectTextField);
		newSubjectTextField.setColumns(10);
		
		JButton createNewSubjectBtn = new JButton("Create New Subject");
		createNewSubjectBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subjectController.addSubject();
				subjectComboBoxModel.update();				
			}
		});
		newSubjectPanel.add(createNewSubjectBtn);
		
		JPanel existingSubjectsPanel = new JPanel();
		subjectsPanel.add(existingSubjectsPanel);
		
		JLabel existingSubjectsLbl = new JLabel("Existing Subjects:");
		existingSubjectsPanel.add(existingSubjectsLbl);
		
		JComboBox existingSubjectsComboBox = new JComboBox();
		subjectComboBoxModel = new SubjectComboBoxModel(subjectController.getSubjects());		
		existingSubjectsComboBox.setModel(subjectComboBoxModel);
		existingSubjectsComboBox.setRenderer(new SubjectCellRenderer());
		existingSubjectsComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subjectController.setSelectedSubject(subjectComboBoxModel.getSelectedItem());
				
			}
		});
		existingSubjectsPanel.add(existingSubjectsComboBox);
		
		JButton addSubjectBtn = new JButton("Add Subject");
		addSubjectBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentController.addSubject(subjectController.getSelectedSubject());
				
			}
		});
		existingSubjectsPanel.add(addSubjectBtn);
		
		JScrollPane subjectsTableScrollPane = new JScrollPane();
		subjectsPanel.add(subjectsTableScrollPane);
		
		subjectsTable = new JTable();
		subjectTableModel = new SubjectTableModel(departmentController.getPickedSubjects());
		subjectsTable.setModel(subjectTableModel);
		subjectsTableScrollPane.setViewportView(subjectsTable);
		
		add(subjectsPanel);
		
		saveBtnPanel = new JPanel();
		getContentPane().add(saveBtnPanel);
		
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		saveBtnPanel.add(saveBtn);
		
		hidePanels();
	}

	protected void combinedAdmissionPicked() {
		bacGradePanel.setVisible(true);
		subjectsPanel.setVisible(true);
		saveBtnPanel.setVisible(true);
		saveBtn.removeActionListener(saveBtn.getActionListeners()[0]);
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentController.saveCombinedAdmission();
				
			}
		});
		departmentController.updateAvailableSubjects();
		subjectTableModel.setData(departmentController.getPickedSubjects());
	}

	protected void examAdmissionPicked() {
		bacGradePanel.setVisible(false);
		subjectsPanel.setVisible(true);
		saveBtnPanel.setVisible(true);
		saveBtn.removeActionListener(saveBtn.getActionListeners()[0]);
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentController.saveExamAdmission();
				
			}
		});
		departmentController.updateAvailableSubjects();
		subjectTableModel.setData(departmentController.getPickedSubjects());
	}

	private void hidePanels() {
		bacGradePanel.setVisible(false);
		subjectsPanel.setVisible(false);
		saveBtnPanel.setVisible(false);
	}

	protected void bacGradeAdmissionPicked() {
		bacGradePanel.setVisible(false);
		subjectsPanel.setVisible(false);
		saveBtnPanel.setVisible(true);
		departmentController.bacGradeAdmissionPicked();
		saveBtn.removeActionListener(saveBtn.getActionListeners()[0]);
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentController.saveBacBasedAdmission();
				
			}
		});
	}

	@Override
	public void display() {
		subjectComboBoxModel.setSelectedItem(subjectController.getSelectedSubject());
		departmentComboBoxModel.setSelectedItem(departmentController.getSelectedDepartment());
		departmentController.newDepartmentSelected(departmentComboBoxModel.getSelectedItem());
		setVisible(true);
		
	}

	@Override
	public void tuck() {
		setVisible(false);
		
	}

	@Override
	public void newDepartmentSelected() {
		numberOfPlacesTextField.setText(departmentController.getNumberOfPlaces());
		subjectTableModel.setData(departmentController.getPickedSubjects());
		
	}

	@Override
	public void newSubjectAdded() {
		subjectTableModel.updateData();
		
	}

	@Override
	public String getNewSubjectName() {
		return newSubjectTextField.getText();
	}

	@Override
	public String getBacGradeWeight() {
		return bacGradeTextField.getText();
	}

	@Override
	public JRadioButton getBacGradeRdBtn() {
		return withBacGradeRdBtn;
	}

	@Override
	public JRadioButton getExamAdmissionRdBtn() {
		return withExamRdBtn;
	}

	@Override
	public JRadioButton getCombinedAdmissionRdBtn() {
		return combinedRdBtn;
	}

	@Override
	public void setSelectedRadioButton(JRadioButton jRadioButton) {
		jRadioButton.setSelected(true);
		
	}

	@Override
	public void setBacGradeWeight(Integer bacGradeWeight) {
		bacGradeTextField.setText(bacGradeWeight.toString());
		
	}

	@Override
	public String getNumberOfPlaces() {
		return numberOfPlacesTextField.getText();
	}
	

}
