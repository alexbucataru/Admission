package itsix.admission.custom;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import itsix.admission.model.IDepartment;

public class DepartmentComboBoxModel extends AbstractListModel<IDepartment> implements ComboBoxModel<IDepartment> {
	private List<IDepartment> departments;
	private IDepartment selectedDepartment;
	
	public DepartmentComboBoxModel(List<IDepartment> departments) {
		this.departments = departments;
	}

	@Override
	public IDepartment getElementAt(int index) {
		return departments.get(index);
	}

	@Override
	public int getSize() {
		return departments.size();
	}

	@Override
	public IDepartment getSelectedItem() {
		return selectedDepartment;
	}

	@Override
	public void setSelectedItem(Object selectedItem) {
		selectedDepartment = (IDepartment) selectedItem;		
	}
	
}
