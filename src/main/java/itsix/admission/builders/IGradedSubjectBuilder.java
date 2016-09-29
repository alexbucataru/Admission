package itsix.admission.builders;

import java.io.Serializable;

import itsix.admission.model.IGradedSubject;
import itsix.admission.model.ISubject;
import itsix.admission.model.IWeightedSubject;

public interface IGradedSubjectBuilder extends Serializable {
	public IGradedSubject build(ISubject subject, Double grade);
	public IGradedSubject build(ISubject subject);
}
