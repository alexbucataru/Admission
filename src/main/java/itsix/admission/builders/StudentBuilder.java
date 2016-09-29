package itsix.admission.builders;

import java.util.List;

import itsix.admission.model.*;

public class StudentBuilder implements IStudentBuilder {

	@Override
	public IStudent build(Integer ssn, String name, Double bacGrade, 
			List<IPickedDepartment> departments, IGradedSubjectBuilder gradedSubjectBuilder) {
		return new Student(ssn, name, bacGrade, departments, gradedSubjectBuilder);
	}

}
