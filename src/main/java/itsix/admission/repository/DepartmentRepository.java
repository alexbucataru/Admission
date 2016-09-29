package itsix.admission.repository;

import java.util.ArrayList;
import java.util.List;

import itsix.admission.model.AdmissionTypePublisher;
import itsix.admission.model.IAdmissionTypeInnerPublisher;
import itsix.admission.model.IDepartment;
import itsix.admission.model.IInnerPublisher;

public class DepartmentRepository implements IDepartmentRepository {
	private List<IDepartment> departments;

	public DepartmentRepository() {
		departments = new ArrayList<>();
	}
	
	@Override
	public void addDepartment(IDepartment department) {
		departments.add(department);
		
	}

	@Override
	public List<IDepartment> getDepartments() {
		return departments;
	}

	@Override
	public void setPublishers(List<AdmissionTypePublisher> publishers) {
		for (int i = 0; i < departments.size(); i++) {
			departments.get(i).setPublisher(publishers.get(i));
		}
		
	}


}


