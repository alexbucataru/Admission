package itsix.admission.repository;

import java.io.Serializable;
import java.util.List;

import itsix.admission.exceptions.ObjectAlreadyExistingException;
import itsix.admission.model.ISubject;
import itsix.admission.model.Subject;

public interface ISubjectRepository extends Serializable {
	public void addSubject(ISubject subject) throws ObjectAlreadyExistingException;
	public void print();
	public List<ISubject> getSubjects();
}
