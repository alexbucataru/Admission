package itsix.admission.validators;

import itsix.admission.model.IPickedDepartment;

public interface IValidator {

	String validate(String fieldName, String fieldValue);

	String validate(String fieldName, Double bacGrade, Integer lowBound, Integer highBound);

	String validate(IPickedDepartment pickedDepartment);

}
