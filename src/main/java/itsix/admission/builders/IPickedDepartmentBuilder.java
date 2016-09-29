package itsix.admission.builders;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IInnerPickedDepartment;
import itsix.admission.model.IPickedDepartment;

public interface IPickedDepartmentBuilder {
	public IInnerPickedDepartment build(IDepartment department);
	public IPickedDepartment buildPickedDepartmentBacAdmission(IDepartment department);
	public IPickedDepartment buildPickedDepartmentCombinedAdmission(IDepartment department, Integer bacGradeWeight);
	public IPickedDepartment buildPickedDepartmentExamAdmission(IDepartment department);
}
