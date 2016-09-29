package itsix.admission.model;

import java.io.Serializable;
import java.util.List;

import itsix.admission.builders.IPickedDepartmentBuilder;

public interface IDepartment extends Serializable, IPublisher {
	public String getName();
	public void setPublisher(IAdmissionTypeInnerPublisher publisher);
	public List<IWeightedSubject> getCopyOfPickedSubjectsList();
	public void setAdmissionType(IAdmissionType admissionType);
	public void publishItHasBeenSelected();
	public Class<? extends IAdmissionType> getAdmissionTypeClass();
	public IAdmissionType getAdmissionType();
	public List<IWeightedSubject> getAvailableSubjects();
	public Integer getBacGradeWeight();
	public IPickedDepartment buildPickDepartment(IPickedDepartmentBuilder pickedDepartmentBuilder);
	public boolean notFull();
	public void enrollStudent(IStudent student);
	public boolean canBeAccepted(Double admissionGrade);
	public List<IStudent> getAdmittedStudents();
	public Integer getNumberOfPlaces();
	public void setNumberOfPlaces(Integer valueOf);
	public void addAssignedStudents(List<IStudent> assignedStudents);
}
