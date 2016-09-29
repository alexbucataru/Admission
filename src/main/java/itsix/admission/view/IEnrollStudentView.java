package itsix.admission.view;

import javax.swing.JRadioButton;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IPickedDepartment;
import itsix.admission.model.IWeightedSubject;

public interface IEnrollStudentView extends IView {

	String getStudentSSN();

	String getStudentBACGrade();

	IDepartment getSelectedSourceDepartment();

	IPickedDepartment getSelectedDestinationDepartment();

	void updateDepartmentChoices();

	IWeightedSubject getSelectedDestinationSubject();

	IWeightedSubject getSelectedSourceSubject();

	String getStudentName();

	void updateSubjectChoices();

	public void subscribe(IPickedDepartment pickedDepartment);

	void noPickedDepartmentSelected();


}
