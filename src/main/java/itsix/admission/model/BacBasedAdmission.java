package itsix.admission.model;

import java.util.ArrayList;
import java.util.List;

import itsix.admission.builders.IPickedDepartmentBuilder;

public class BacBasedAdmission implements IAdmissionType {

	@Override
	public Double getAdmissionGrade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IWeightedSubject> getAvailableSubjects() {
		return new ArrayList<>();
	}

	@Override
	public List<IWeightedSubject> getPickedSubjects() {
		return new ArrayList<>();
	}

	@Override
	public void publishItHasBeenSelected(IAdmissionTypeInnerPublisher publisher) {
		publisher.notifySubscribers();
		
	}

	@Override
	public Integer getBacGradeWeight() {
		return 100;
	}


	@Override
	public IPickedDepartment buildPickDepartment(IPickedDepartmentBuilder pickedDepartmentBuilder,
			Department department) {
		return pickedDepartmentBuilder.buildPickedDepartmentBacAdmission(department);
	}


}
