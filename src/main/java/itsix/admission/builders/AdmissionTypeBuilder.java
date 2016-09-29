package itsix.admission.builders;

import java.util.List;

import itsix.admission.model.BacBasedAdmission;
import itsix.admission.model.CombinedAdmission;
import itsix.admission.model.ExamAdmission;
import itsix.admission.model.IAdmissionType;
import itsix.admission.model.IWeightedSubject;

public class AdmissionTypeBuilder implements IAdmissionTypeBuilder {

	@Override
	public IAdmissionType build() {
		return new BacBasedAdmission();
		
	}

	@Override
	public IAdmissionType build(List<IWeightedSubject> subjects) {
		return new ExamAdmission(subjects);
		
	}


	@Override
	public IAdmissionType build(Integer bacGradeWeight, List<IWeightedSubject> subjects) {
		ExamAdmission examAdmission = (ExamAdmission) build(subjects);
		return new CombinedAdmission(bacGradeWeight, examAdmission);
	}

}
