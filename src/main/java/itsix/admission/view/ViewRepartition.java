package itsix.admission.view;
import itsix.admission.controller.IDepartmentController;
import itsix.admission.custom.AdmittedStudentsTableModel;
import itsix.admission.custom.DepartmentCellRenderer;
import itsix.admission.custom.DepartmentComboBoxModel;
import itsix.admission.model.IDepartment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;


public class ViewRepartition extends JFrame implements IViewRepartition {
	private JTable studentsTable;
	private JComboBox departmentsComboBox;
	private IDepartmentController departmentController;
	private AdmittedStudentsTableModel admittedStudentsTableModel;
	private DepartmentComboBoxModel departmentComboBoxModel;

	/**
	 * Create the application.
	 */
	public ViewRepartition(IDepartmentController departmentController) {
		this.departmentController = departmentController;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		
		JPanel departmentComboBoxPanel = new JPanel();
		getContentPane().add(departmentComboBoxPanel, BorderLayout.NORTH);
		
		departmentsComboBox = new JComboBox();
		departmentComboBoxModel = new DepartmentComboBoxModel(departmentController.getDepartments());
		departmentsComboBox.setModel(departmentComboBoxModel);
		departmentComboBoxModel.setSelectedItem(departmentController.getSelectedDepartment());
		departmentsComboBox.setRenderer(new DepartmentCellRenderer());
		departmentsComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentController.newSelectedDepartment();
				
			}
		});
		departmentComboBoxPanel.add(departmentsComboBox);
		
		JScrollPane studentsTableScrollPane = new JScrollPane();
		getContentPane().add(studentsTableScrollPane, BorderLayout.CENTER);
		
		studentsTable = new JTable();
		admittedStudentsTableModel = new AdmittedStudentsTableModel(departmentController.getSelectedDepartment());
		studentsTable.setModel(admittedStudentsTableModel);
		studentsTable.setRowSorter(new TableRowSorter<>(admittedStudentsTableModel));
		studentsTableScrollPane.setViewportView(studentsTable);
		
		
		JPanel assignedStudentsPanel = new JPanel();
		JButton assignedStudentsBtn = new JButton("View all admitted students");
		assignedStudentsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentController.goToAllAssignedStudents();
				
			}
		});
		assignedStudentsPanel.add(assignedStudentsBtn);
		add(assignedStudentsPanel, BorderLayout.SOUTH);
		
	}

	@Override
	public void display() {
		setVisible(true);
		
	}

	@Override
	public void tuck() {
		setVisible(false);
		
	}

	@Override
	public IDepartment getSelectedDepartment() {
		return departmentComboBoxModel.getSelectedItem();
	}

	@Override
	public void dataChanged(IDepartment selectedDepartment) {
		admittedStudentsTableModel.changeData(selectedDepartment);
		
	}

}
