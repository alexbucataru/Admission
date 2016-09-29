package itsix.admission.custom;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import itsix.admission.model.IStudent;
import itsix.admission.model.ISubject;
import itsix.admission.validators.IValidator;

public class StudentTableModel extends AbstractTableModel {
	private static final String[] columnNames = {"Name" ,
					"SSN", "Grade"};
	
	private List<IStudent> students;
	
	private ISubject subject;
	
	private IValidator validator;
	
	public StudentTableModel(ISubject subject, IValidator validator) {
		setData(subject);
		this.validator = validator;
	}

	private void setData(ISubject subject) {
		this.students = subject.getStudents();
		this.subject = subject;
	}
	
	public void changeData(ISubject subject) {
		setData(subject);
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
			return students.get(row).getGrade(subject).toString();
		default:
			return null;
		}
	}	
	
	@Override 
	public void setValueAt(Object value, int row, int column) {
		String validationResult = "";
		try {
			Double grade = Double.valueOf((String) value);
			validationResult = validator.validate("Grade", grade, 1, 10);
			if (validationResult.equals("")) {
				students.get(row).setGrade(subject, grade);
			} else {
				JOptionPane.showMessageDialog(null, validationResult);
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Grade must be a number between 1 and 10");
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 2;
	}
		
}
