package itsix.admission.builders;

import itsix.admission.model.GradedSubject;
import itsix.admission.model.IGradedSubject;
import itsix.admission.model.ISubject;
import itsix.admission.model.IWeightedSubject;

public class GradedSubjectBuilder implements IGradedSubjectBuilder {
	

	@Override
	public IGradedSubject build(ISubject subject, Double grade) {
		return new GradedSubject(subject, grade);
	}

	@Override
	public IGradedSubject build(ISubject subject) {
		return new GradedSubject(subject, 0.0);
	}


	
}
