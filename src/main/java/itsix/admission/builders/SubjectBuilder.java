package itsix.admission.builders;

import itsix.admission.model.ISubject;
import itsix.admission.model.Subject;

public class SubjectBuilder implements ISubjectBuilder {

	@Override
	public ISubject build(String name) {
		return new Subject(name);
	}

}
