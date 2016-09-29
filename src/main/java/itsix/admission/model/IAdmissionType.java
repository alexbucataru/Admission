package itsix.admission.model;

import java.io.Serializable;
import java.util.List;

import itsix.admission.builders.IPickedDepartmentBuilder;

public interface IAdmissionType extends Serializable {
	public Double getAdmissionGrade();

	public List<IWeightedSubject> getAvailableSubjects();

	public List<IWeightedSubject> getPickedSubjects();

	public void publishItHasBeenSelected(IAdmissionTypeInnerPublisher publisher);

	public Integer getBacGradeWeight();


	public IPickedDepartment buildPickDepartment(IPickedDepartmentBuilder pickedDepartmentBuilder,
			Department department);
}
