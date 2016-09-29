package itsix.admission.builders;

import java.util.List;

import itsix.admission.model.IPickedDepartment;
import itsix.admission.model.IStudent;

public interface IStudentBuilder {
	public IStudent build(Integer ssn, String name, Double bacGrade, List<IPickedDepartment> departments, IGradedSubjectBuilder gradedSubjectBuilder);
}
