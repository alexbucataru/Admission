package itsix.admission.view;

import itsix.admission.controller.IStudentController;
import itsix.admission.custom.AssignedStudentsTableModel;
import itsix.admission.model.IStudent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class AssignedStudentsView extends JFrame implements IAssignedStudentsView {
	private JTable studentsTable;
	private AssignedStudentsTableModel assignedStudentsTableModel;
	private IStudentController studentController;

	public AssignedStudentsView(IStudentController studentController) {
		this.studentController = studentController;
		initialize();
	}

	private void initialize() {		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				tuck();
			}
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 20));
		
		JScrollPane studentsTableScrollPane = new JScrollPane();
		add(studentsTableScrollPane);
		
		studentsTable = new JTable();		
		assignedStudentsTableModel = new AssignedStudentsTableModel(studentController.getAssignedStudents());
		studentsTable.setModel(assignedStudentsTableModel);
		studentsTable.setRowSorter(new TableRowSorter<>(assignedStudentsTableModel));
		studentsTableScrollPane.setViewportView(studentsTable);
	}

	@Override
	public void display() {
		assignedStudentsTableModel.dataChanged();	
		setVisible(true);	
		
	}

	@Override
	public void tuck() {
		setVisible(false);
		
	}

}
