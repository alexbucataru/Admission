package itsix.admission.model;

import itsix.admission.view.ISubscriber;

public class PickedDepartmentBacAdmission implements IPickedDepartment {
	private IInnerPickedDepartment pickedDepartment;
	private GradeCalculator gradeCalculator;

	public PickedDepartmentBacAdmission(IInnerPickedDepartment pickedDepartment, GradeCalculator gradeCalculator) {
		this.pickedDepartment = pickedDepartment;
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

	@Override
	public boolean notFull() {
		return pickedDepartment.notFull();
	}

	@Override
	public Double computeAdmissionGrade(IStudent student) {
		return gradeCalculator.bacGrade(student);
		
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
		return true;
	}

}
