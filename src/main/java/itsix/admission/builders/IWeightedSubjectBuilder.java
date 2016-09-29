package itsix.admission.builders;

import java.io.Serializable;

import itsix.admission.model.ISubject;
import itsix.admission.model.IWeightedSubject;

public interface IWeightedSubjectBuilder extends Serializable {
	public IWeightedSubject build(ISubject subject, Integer weight);

	public IWeightedSubject build(ISubject subject);
}
