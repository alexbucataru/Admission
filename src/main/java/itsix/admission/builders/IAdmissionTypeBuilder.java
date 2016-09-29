package itsix.admission.builders;

import itsix.admission.model.IAdmissionType;
import itsix.admission.model.IWeightedSubject;

import java.io.Serializable;
import java.util.List;

public interface IAdmissionTypeBuilder {
	IAdmissionType build();
	IAdmissionType build(List<IWeightedSubject> subjects);
	IAdmissionType build(Integer bacGradeWeight, List<IWeightedSubject> subjects);
}
