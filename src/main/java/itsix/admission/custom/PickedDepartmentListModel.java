package itsix.admission.custom;

import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IPickedDepartment;

public class PickedDepartmentListModel extends AbstractListModel<IPickedDepartment> {
private List<IPickedDepartment> departments;
	
	public PickedDepartmentListModel(List<IPickedDepartment> departments) {
		this.departments = departments;
	}
	
	@Override
	public IPickedDepartment getElementAt(int index) {
		return departments.get(index);
	}

	@Override
	public int getSize() {
		return departments.size();
	}	
	
	public void update() {
		fireContentsChanged(this, 0, getSize());
	}

	public void setData(List<IPickedDepartment> departments) {
		this.departments = departments;
		update();
	}
	
	public Integer moveUp(Integer index) {
		if (index > 0) {
			Collections.swap(departments, index, index - 1);
			update();
			return index - 1;
		}
		return index;
	}
	
	public Integer moveDown(Integer index) {
		if (index < departments.size() - 1) {
			Collections.swap(departments, index, index + 1);
			update();
			return index + 1;
		}
		return index;
	}
}
