package itsix.admission.controller;

import itsix.admission.view.IAdmissionExamGradesView;
import itsix.admission.view.IHomeView;

public interface IHomeController {
	public void goToEnrollStudent();
	public void goToAdmissionExamGrades();
	public void goToAddmissionRules();
	public void goToRepartition();
	public void serializeData();
	public void setAdmissionExamGradesView(IAdmissionExamGradesView admissionExamGradesView);
}
