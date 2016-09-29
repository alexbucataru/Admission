package itsix.admission.validators;

import itsix.admission.model.IPickedDepartment;

public class Validator implements IValidator {

	@Override
	public String validate(String fieldName, String fieldValue) {
		if (fieldValue.equals("") || fieldValue.equals(null)) {
			return "Please complete the " + fieldName + "field." + "\n";
		}
		return "";
	}

	@Override
	public String validate(String fieldName, Double bacGrade, Integer lowBound, Integer highBound) {
		if (bacGrade < lowBound || bacGrade > highBound) {
			return "The value of the " + fieldName + " must be between " + lowBound + " and " + highBound;
		}
		return "";
	}

	@Override
	public String validate(IPickedDepartment pickedDepartment) {
		if (pickedDepartment.weightsAreOk()) {
			return "";
		} else {
			return "Check the weighted of the subjects picked for " + pickedDepartment.getDepartment().getName();
		}
		
	}

}
