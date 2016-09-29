package itsix.admission.view;
import itsix.admission.model.IDepartment;


public interface IViewRepartition extends IView {

	IDepartment getSelectedDepartment();

	void dataChanged(IDepartment selectedDepartment);

}
