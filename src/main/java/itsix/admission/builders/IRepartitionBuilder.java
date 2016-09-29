package itsix.admission.builders;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IRepartition;
import itsix.admission.model.IStudent;

import java.util.List;

public interface IRepartitionBuilder {
	public IRepartition build(List<IStudent> students, List<IDepartment> departments, List<IStudent> assignedStudents);
}
