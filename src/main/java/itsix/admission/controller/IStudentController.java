package itsix.admission.controller;

import java.util.List;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IPickedDepartment;
import itsix.admission.model.IStudent;
import itsix.admission.model.IWeightedSubject;
import itsix.admission.view.EnrollStudentView;
import itsix.admission.view.IEnrollStudentView;

public interface IStudentController {
	public void enroll();
	
	public void setEnrollStudentView(IEnrollStudentView enrollStudentView);
	
	public void pickDepartment();
	
	public void unpickDepartment();

	List<IDepartment> getSourceDepartmentsList();
	
	public List<IDepartment> getDestinationDepartmentsList();

	public void pickAllDepartments();
	
	public void unpickAllDepartments();
	
	public void setSelectedDepartment(IPickedDepartment pickedDepartment);

	public List<IWeightedSubject> getSourceSubjects();

	public List<IWeightedSubject> getDestinationSubjects();

	public void pickSubject();

	public void unpickSubject();

	public List<IPickedDepartment> getPickedDepartments();

	public void pickedDepartmentHasBeenSelected();

	public void setSubjectLists(List<IWeightedSubject> weightedSubjects);

	public List<IWeightedSubject> getAvailableSubjects();

	public List<IWeightedSubject> getPickedSubjects();

	public void departmentHasBeenSelected();

	public void emptySubjectLists();

	public void setSubjectListsExamAdmission(List<IWeightedSubject> weightedSubjects);

	public void setSubjectListsCombinedAdmission(List<IWeightedSubject> weightedSubjects);

	public void reinitializeDepartmentLists();

	public void reinitializePickedSubjects();

	public void noPickedDepartmentSelected();

	public List<IStudent> getAssignedStudents();


	
}
