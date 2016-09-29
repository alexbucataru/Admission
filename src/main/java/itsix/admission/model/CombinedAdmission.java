package itsix.admission.model;

import java.util.List;

import itsix.admission.builders.IPickedDepartmentBuilder;

public class CombinedAdmission implements IAdmissionType {
	
	private Integer bacGradeWeight;
	private ExamAdmission examAdmission;
	
	public CombinedAdmission(Integer bacGradeWeight, ExamAdmission examAdmission) {
		this.bacGradeWeight = bacGradeWeight;
		this.examAdmission = examAdmission;
	}

	@Override
	public Double getAdmissionGrade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IWeightedSubject> getAvailableSubjects() {
		return examAdmission.getAvailableSubjects();
	}

	@Override
	public List<IWeightedSubject> getPickedSubjects() {
		return examAdmission.getPickedSubjects();
	}

	@Override
	public void publishItHasBeenSelected(IAdmissionTypeInnerPublisher publisher) {
		publisher.notifySubscribers(examAdmission.getAvailableSubjects(), bacGradeWeight);
		
	}

	@Override
	public Integer getBacGradeWeight() {
		return bacGradeWeight;
	}

	@Override
	public IPickedDepartment buildPickDepartment(IPickedDepartmentBuilder pickedDepartmentBuilder,
			Department department) {
		return pickedDepartmentBuilder.buildPickedDepartmentCombinedAdmission(department, bacGradeWeight);
	}

}
