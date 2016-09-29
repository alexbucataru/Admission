package itsix.admission.repository;

import java.io.Serializable;
import java.util.List;

import itsix.admission.exceptions.ObjectAlreadyExistingException;
import itsix.admission.model.IStudent;

public interface IStudentRepository extends Serializable{
	public void saveStudent(IStudent student) throws ObjectAlreadyExistingException;

	public List<IStudent> getStudents();

	public List<IStudent> getAssignedStudents();
}
