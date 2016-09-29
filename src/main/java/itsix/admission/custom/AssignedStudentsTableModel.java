package itsix.admission.custom;

import itsix.admission.model.IStudent;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AssignedStudentsTableModel extends AbstractTableModel {
	private static final String[] columnNames = {"Name" ,
		"SSN", "Department"};
	
	private List<IStudent> students;
	
	public AssignedStudentsTableModel(List<IStudent> students) {
		this.students = students;
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
			return students.get(row).getDepartmentName();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
		
	}
	
	public void dataChanged() {
		fireTableDataChanged();
	}
}
