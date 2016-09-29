package itsix.admission.view;

import itsix.admission.model.ISubject;

public interface IAdmissionExamGradesView extends IView {

	void dataChanged(ISubject selectedSubject);

}
