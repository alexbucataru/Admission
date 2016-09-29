package itsix.admission.builders;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IRepartition;
import itsix.admission.model.IStudent;
import itsix.admission.model.Repartition;

import java.util.List;

public class RepartitionBuilder implements IRepartitionBuilder {

	@Override
	public IRepartition build(List<IStudent> students,
			List<IDepartment> departments, List<IStudent> assignedStudents) {
		return new Repartition(students, departments, assignedStudents);
	}

	
	
}
