package itsix.admission.model;

import java.util.ArrayList;
import java.util.List;

import itsix.admission.builders.IPickedDepartmentBuilder;

public class ExamAdmission implements IAdmissionType {
	private List<IWeightedSubject> availableSubjects;
	private List<IWeightedSubject> pickedSubjects;
	
	public ExamAdmission(List<IWeightedSubject> avaiableSubjects) {
		this.availableSubjects = avaiableSubjects;
		this.pickedSubjects = new ArrayList<>();
	}

	@Override
	public Double getAdmissionGrade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IWeightedSubject> getAvailableSubjects() {
		return availableSubjects;
	}

	@Override
	public List<IWeightedSubject> getPickedSubjects() {
		return pickedSubjects;
	}

	@Override
	public void publishItHasBeenSelected(IAdmissionTypeInnerPublisher publisher) {
		publisher.notifySubscribers(availableSubjects);
		
	}

	@Override
	public Integer getBacGradeWeight() {
		return 0;
	}

	@Override
	public IPickedDepartment buildPickDepartment(IPickedDepartmentBuilder pickedDepartmentBuilder,
			Department department) {
		return pickedDepartmentBuilder.buildPickedDepartmentExamAdmission(department);
	}
	
}
