package itsix.admission.custom;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IStudent;
import itsix.admission.model.ISubject;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AdmittedStudentsTableModel extends AbstractTableModel {
	private static final String[] columnNames = {"Name" ,
		"SSN", "Grade"};
	
	private List<IStudent> students;
	
	private IDepartment department;
	
	public AdmittedStudentsTableModel(IDepartment department) {
		setData(department);
	}

	private void setData(IDepartment department) {
		this.department = department;
		students = this.department.getAdmittedStudents();
	}
	
	public void changeData(IDepartment department) {
		setData(department);
		fireTableDataChanged();
	}	

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return students.get(row).getName();
		case 1:
			return students.get(row).getSSN();
		case 2:
			return students.get(row).getAdmissionGrade().toString();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
		
	}
}
