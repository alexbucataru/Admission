package itsix.admission.repository;

import java.util.ArrayList;
import java.util.List;

import itsix.admission.exceptions.ObjectAlreadyExistingException;
import itsix.admission.model.ISubject;

public class SubjectRepository implements ISubjectRepository{
	private List<ISubject> subjects;
	
	public SubjectRepository() {
		subjects = new ArrayList<>();
	}

	@Override
	public void addSubject(ISubject newSubject) throws ObjectAlreadyExistingException {
		for (ISubject subject : subjects) {
			if (subject.hasSameName(newSubject)) {
				throw new ObjectAlreadyExistingException("Subject is already in the DB");
			}
		}
		subjects.add(newSubject);
		
	}

	@Override
	public void print() {
		System.out.println(subjects.size());
		
	}

	@Override
	public List<ISubject> getSubjects() {
		return subjects;
	}
}
