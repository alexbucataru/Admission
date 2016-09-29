package itsix.admission.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JRadioButton;

import itsix.admission.builders.IPickedDepartmentBuilder;
import itsix.admission.view.ISubscriber;

public class Department implements IDepartment {
	private String name;
	private transient IAdmissionTypeInnerPublisher publisher;
	private IAdmissionType admissionType;
	private Integer numberOfPlaces;
	private List<IStudent> admittedStudents;

	public Department(String name, IAdmissionType admissionType, IAdmissionTypeInnerPublisher publisher, Integer size) {
		this.name = name;
		this.publisher = publisher;
		this.admissionType = admissionType;		
		this.numberOfPlaces = size;
		admittedStudents = new ArrayList<>();
	}
	
	

	@Override
	public String getName() {
		return name;
	}	

	@Override
	public void subscribe(ISubscriber subscriber) {
		publisher.subscribe(subscriber);
		
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		publisher.unsubscribe(subscriber);
		
	}

	@Override
	public void setPublisher(IAdmissionTypeInnerPublisher publisher) {
		this.publisher = publisher;
		
	}

	@Override
	public List<IWeightedSubject> getCopyOfPickedSubjectsList() {
		return new ArrayList<>(admissionType.getPickedSubjects());
	}


	@Override
	public void setAdmissionType(IAdmissionType admissionType) {
		this.admissionType = admissionType;
		
	}

	@Override
	public void publishItHasBeenSelected() {
		admissionType.publishItHasBeenSelected(publisher);
		
	}

	@Override
	public Class<? extends IAdmissionType> getAdmissionTypeClass() {
		return admissionType.getClass();
	}



	@Override
	public IAdmissionType getAdmissionType() {
		return admissionType;
	}



	@Override
	public List<IWeightedSubject> getAvailableSubjects() {
		return admissionType.getAvailableSubjects();
	}



	@Override
	public Integer getBacGradeWeight() {
		return admissionType.getBacGradeWeight();
	}



	@Override
	public IPickedDepartment buildPickDepartment(IPickedDepartmentBuilder pickedDepartmentBuilder) {
		return admissionType.buildPickDepartment(pickedDepartmentBuilder, this);
	}



	@Override
	public boolean notFull() {
		return admittedStudents.size() < numberOfPlaces;
	}



	@Override
	public void enrollStudent(IStudent student) {
		if (noAdmittedStudents()) {
			admittedStudents.add(student);
		} else {
			for (int i = 0; i < admittedStudents.size(); i++) {
				if (student.hasBiggerGrade(admittedStudents.get(i))) {
					admittedStudents.add(i, student);
					break;
				}
			}
		}
		if (overCapacity()) {
			IStudent replacedStudent = admittedStudents.get(admittedStudents.size() - 1);
			admittedStudents.remove(replacedStudent);
			replacedStudent.repartition();
		}
	}
	
	private boolean overCapacity() {
		return admittedStudents.size() > numberOfPlaces;
	}



	private boolean noAdmittedStudents() {
		return admittedStudents.isEmpty();
	}



	@Override
	public boolean canBeAccepted(Double admissionGrade) {
		return admittedStudents.get(admittedStudents.size() - 1).hasAdmissionGradeSmallerThan(admissionGrade);
	}



	@Override
	public List<IStudent> getAdmittedStudents() {
		return admittedStudents;
	}



	@Override
	public Integer getNumberOfPlaces() {
		return numberOfPlaces;
	}



	@Override
	public void setNumberOfPlaces(Integer numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
		
	}



	@Override
	public void addAssignedStudents(List<IStudent> assignedStudents) {
		assignedStudents.addAll(admittedStudents);
		
	}
}
