package itsix.admission.repository;

import java.io.Serializable;
import java.util.List;

import itsix.admission.model.AdmissionTypePublisher;
import itsix.admission.model.IAdmissionTypeInnerPublisher;
import itsix.admission.model.IDepartment;
import itsix.admission.model.IInnerPublisher;

public interface IDepartmentRepository extends Serializable {
	public void addDepartment(IDepartment department);

	public List<IDepartment> getDepartments();

	public void setPublishers(List<AdmissionTypePublisher> publishers);
}
