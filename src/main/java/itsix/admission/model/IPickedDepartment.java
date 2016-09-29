package itsix.admission.model;

public interface IPickedDepartment extends IInnerPickedDepartment {

	Double computeAdmissionGrade(IStudent student);

	boolean weightsAreOk();

}
