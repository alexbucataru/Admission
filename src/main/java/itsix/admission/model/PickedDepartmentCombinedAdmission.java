package itsix.admission.model;

import java.util.ArrayList;
import java.util.List;

import itsix.admission.view.ISubscriber;

public class PickedDepartmentCombinedAdmission implements IPickedDepartment {
	private IInnerPickedDepartment pickedDepartment;
	private List<IWeightedSubject> pickedSubjects;
	private Integer bacGradeWeight;
	private GradeCalculator gradeCalculator;
	

	public PickedDepartmentCombinedAdmission(IInnerPickedDepartment pickedDepartment, Integer bacGradeWeight, GradeCalculator gradeCalculator) {
		this.pickedDepartment = pickedDepartment;
		pickedSubjects = new ArrayList<>();
		this.bacGradeWeight = bacGradeWeight;
		this.gradeCalculator = gradeCalculator;
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		pickedDepartment.subscribe(subscriber);
		
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		pickedDepartment.unsubscribe(subscriber);
		
	}

	@Override
	public String getName() {
		return pickedDepartment.getName();
	}

	@Override
	public IDepartment getDepartment() {
		return pickedDepartment.getDepartment();
	}

	@Override
	public void hasBeenSelected() {
		pickedDepartment.hasBeenSelected();
		
	}

	public List<IWeightedSubject> getSubjects() {
		return pickedSubjects;
	}

	@Override
	public boolean notFull() {
		return pickedDepartment.notFull();
	}

	@Override
	public Double computeAdmissionGrade(IStudent student) {
		return gradeCalculator.combinedGrade(student, bacGradeWeight, pickedSubjects);
	}

	@Override
	public void enrollStudent(Student student) {
		pickedDepartment.enrollStudent(student);
		
	}

	@Override
	public boolean canBeAccepted(Double admissionGrade) {
		return pickedDepartment.canBeAccepted(admissionGrade);
	}

	@Override
	public boolean weightsAreOk() {
		Integer sum = 0;
		for (IWeightedSubject weightedSubject : pickedSubjects) {
			sum += weightedSubject.getWeight();
		}
		
		return (sum + bacGradeWeight) == 100; 
	}
	
	
}
