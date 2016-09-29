package itsix.admission.view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import itsix.admission.controller.IHomeController;
import itsix.admission.controller.ISubjectController;
import itsix.admission.custom.DoubleJTextField;
import itsix.admission.custom.StudentTableModel;
import itsix.admission.custom.SubjectCellRenderer;
import itsix.admission.custom.SubjectComboBoxModel;
import itsix.admission.model.ISubject;
import itsix.admission.validators.IValidator;

public class AdmissionExamGradesView extends JFrame implements IAdmissionExamGradesView {
	private JTable gradesTable;
	private SubjectComboBoxModel subjectComboBoxModel;
	private ISubjectController subjectController;
	private StudentTableModel studentTableModel;
	private IHomeController homeController;
	private IValidator validator;

	public AdmissionExamGradesView(ISubjectController subjectController, IHomeController homeController, IValidator validator) {
		this.subjectController = subjectController;
		this.homeController = homeController;
		this.validator = validator;
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
		
		setBounds(100, 100, 400, 390);
		getContentPane().setLayout(new BorderLayout(0, 20));
		
		JPanel subjectsPanel = new JPanel();
		getContentPane().add(subjectsPanel, BorderLayout.NORTH);
		
		studentTableModel = new StudentTableModel(subjectController.getSelectedSubject(), validator);
		
		JComboBox subjectComboBox = new JComboBox();
		subjectComboBoxModel = new SubjectComboBoxModel(subjectController.getSubjects());
		subjectComboBoxModel.setSelectedItem(subjectController.getSelectedSubject());
		subjectComboBox.setModel(subjectComboBoxModel);
		subjectComboBox.setRenderer(new SubjectCellRenderer());
		subjectComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				subjectController.setSelectedSubject(subjectComboBoxModel.getSelectedItem());
				subjectController.newSubjectSelected();
			}
		});
		subjectsPanel.add(subjectComboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		
		gradesTable = new JTable();		
		
		gradesTable.setModel(studentTableModel);
		gradesTable.setRowSorter(new TableRowSorter<>(studentTableModel));
		
		scrollPane.setViewportView(gradesTable);
		
		JPanel buttonsPanel = new JPanel();
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		
		JPanel submitGradesBtnPanel = new JPanel();
		//buttonsPanel.add(submitGradesBtnPanel);
		
		JButton submitGradesBtn = new JButton("Submit Grades");
		submitGradesBtnPanel.add(submitGradesBtn);
		
		JPanel computeRepartitionBtnPanel = new JPanel();
		buttonsPanel.add(computeRepartitionBtnPanel);
		
		JButton computeRepartitionBtn = new JButton("Compute Repartition");
		computeRepartitionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subjectController.doRepartition();
				homeController.goToRepartition();
				
			}
		});
		computeRepartitionBtnPanel.add(computeRepartitionBtn);
	}

	@Override
	public void display() {
		subjectController.newSubjectSelected();
		setVisible(true);
		
	}

	@Override
	public void tuck() {
		setVisible(false);
		
	}
	
	@Override
	public void dataChanged(ISubject selectedSubject) {
		studentTableModel.changeData(subjectController.getSelectedSubject());
		
	}



}
