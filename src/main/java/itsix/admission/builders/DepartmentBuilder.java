package itsix.admission.builders;

import itsix.admission.model.Department;
import itsix.admission.model.IAdmissionType;
import itsix.admission.model.IAdmissionTypeInnerPublisher;
import itsix.admission.model.IDepartment;
import itsix.admission.model.IInnerPublisher;

public class DepartmentBuilder implements IDepartmentBuilder {

	@Override
	public IDepartment build(String name, IAdmissionType admissionType, IAdmissionTypeInnerPublisher publisher, Integer size) {
		return new Department(name, admissionType, publisher, size);
	}


}