package itsix.admission.controller;

import itsix.admission.model.IDepartment;
import itsix.admission.model.ISubject;
import itsix.admission.model.IWeightedSubject;
import itsix.admission.view.IAdmissionRulesView;
import itsix.admission.view.IAssignedStudentsView;
import itsix.admission.view.IViewRepartition;

import java.util.List;

public interface IDepartmentController {
	void setAdmissionRulesView(IAdmissionRulesView admissionRulesView);

	List<IDepartment> getDepartments();

	IDepartment getSelectedDepartment();

	void saveBacBasedAdmission();

	void saveExamAdmission();

	void saveCombinedAdmission();

	List<IWeightedSubject> getPickedSubjects();

	void newDepartmentSelected(IDepartment selectedItem);

	void addSubject(ISubject selectedSubject);

	void setPickedSubjects(List<IWeightedSubject> weightedSubjects);

	void bacGradeAdmissionPicked();

	void examAdmissionPicked();

	void newSelectedDepartment();
	
	void setViewRepartition(IViewRepartition viewRepartition);

	String getNumberOfPlaces();
	
	void setAssignedStudentView(IAssignedStudentsView assignedStudentsView);

	void goToAllAssignedStudents();

	void updateAvailableSubjects();
}
