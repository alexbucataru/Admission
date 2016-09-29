package itsix.admission.model;

import java.util.ArrayList;
import java.util.List;

import itsix.admission.view.ISubscriber;

public class InnerPickedDepartment implements IInnerPickedDepartment {
	private IDepartment department;
	
	public InnerPickedDepartment(IDepartment department) {
		this.department = department;
	}

	@Override
	public String getName() {
		return department.getName();
	}

	@Override
	public IDepartment getDepartment() {
		return department;
	}

	@Override
	public void hasBeenSelected() {
		department.publishItHasBeenSelected();
		
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		department.subscribe(subscriber);
		
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		department.unsubscribe(subscriber);
		
	}

	@Override
	public boolean notFull() {
		return department.notFull();
	}

	@Override
	public void enrollStudent(Student student) {
		department.enrollStudent(student);
		
	}

	@Override
	public boolean canBeAccepted(Double admissionGrade) {
		return department.canBeAccepted(admissionGrade);
	}
}
