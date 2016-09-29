package itsix.admission.view;

import javax.swing.JRadioButton;

public interface IAdmissionRulesView extends IView {

	void newDepartmentSelected();

	void newSubjectAdded();

	String getNewSubjectName();

	String getBacGradeWeight();

	JRadioButton getBacGradeRdBtn();

	JRadioButton getExamAdmissionRdBtn();

	JRadioButton getCombinedAdmissionRdBtn();

	void setSelectedRadioButton(JRadioButton jRadioButton);

	void setBacGradeWeight(Integer bacGradeWeight);

	String getNumberOfPlaces();


}
