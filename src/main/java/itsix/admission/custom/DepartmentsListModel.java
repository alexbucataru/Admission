package itsix.admission.custom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import itsix.admission.model.*;

public class DepartmentsListModel extends AbstractListModel<IDepartment> {
	
	private List<IDepartment> departments;
	
	public DepartmentsListModel(List<IDepartment> departments) {
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
	
	public void update() {
		fireContentsChanged(this, 0, getSize());
	}

	public void setData(List<IDepartment> departments) {
		this.departments = departments;
		fireContentsChanged(this, 0, getSize());
		
	}
}
