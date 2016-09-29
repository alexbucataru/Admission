package itsix.admission.builders;

import itsix.admission.model.ISubject;
import itsix.admission.model.IWeightedSubject;
import itsix.admission.model.WeightedSubject;

public class WeightedSubjectBuilder implements IWeightedSubjectBuilder {

	@Override
	public IWeightedSubject build(ISubject subject, Integer weight) {
		return new WeightedSubject(subject, weight);
	}

	@Override
	public IWeightedSubject build(ISubject subject) {
		return new WeightedSubject(subject, 0);
	}
	
}
