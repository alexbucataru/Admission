package itsix.admission.builders;

import itsix.admission.model.IDepartment;

import java.util.List;

import itsix.admission.model.*;

public interface IDepartmentBuilder {
	public IDepartment build(String name, IAdmissionType admissionType, IAdmissionTypeInnerPublisher publisher, Integer size);
	
}
