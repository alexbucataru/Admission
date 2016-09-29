package itsix.admission.controller;

import java.util.List;

import itsix.admission.model.ISubject;
import itsix.admission.validators.IValidator;
import itsix.admission.view.AdmissionExamGradesView;
import itsix.admission.view.IAdmissionExamGradesView;
import itsix.admission.view.IAdmissionRulesView;

public interface ISubjectController {
	public List<ISubject> getSubjects();
	public void setSelectedSubject(ISubject subject);
	public ISubject getSelectedSubject();
	void setAdmissionExamGradesView(IAdmissionExamGradesView admissionExamGradesView);
	public void newSubjectSelected();
	public void addSubject();
	void setAdmissionRulesView(IAdmissionRulesView admissionRulesView);
	public void doRepartition();
}
