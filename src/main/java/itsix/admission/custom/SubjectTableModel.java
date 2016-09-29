package itsix.admission.custom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IWeightedSubject;

public class SubjectTableModel extends AbstractTableModel {
	private List<IWeightedSubject> weightedSubjects;
	private static final String[] columnNames = {"Subject", "Weight"};
 
	public SubjectTableModel(List<IWeightedSubject> weightedSubjects) {
		this.weightedSubjects = weightedSubjects;
	}
	
	public SubjectTableModel() {
		weightedSubjects = new ArrayList<>();
	}
	
	public void removeData(){
		weightedSubjects.clear();
		fireTableDataChanged();
	}
	
	public void addElement(IWeightedSubject subject) {
		weightedSubjects.add(subject);
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return weightedSubjects.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return weightedSubjects.get(row).getName();
		case 1:
			return weightedSubjects.get(row).getWeight().toString();	
		default:
			return null;
		}
	}
	
	@Override
	public void setValueAt(Object value, int row, int column) {
		String weight = (String) value;
		boolean isOkToSetWeight = true;
		try {
			Integer weightInt = Integer.valueOf(weight);
			for (IWeightedSubject weightedSubject : weightedSubjects) {
				if (weightedSubject.isSameSubject(weightedSubjects.get(row))) {
					if (weightedSubject.hasSameWeight(weightInt)) {
						JOptionPane.showMessageDialog(null, "Subject with that weight already exists");
						isOkToSetWeight = false;
						break;
					}
				}
			}
			if (isOkToSetWeight) {
				weightedSubjects.get(row).setWeight(weightInt);
			}

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Weight must be a number");
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 1;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
		
	}

	public void setData(List<IWeightedSubject> pickedSubjects) {
		this.weightedSubjects = pickedSubjects;
		fireTableDataChanged();
		
	}

	public void updateData() {
		fireTableDataChanged();
		
	}

}
