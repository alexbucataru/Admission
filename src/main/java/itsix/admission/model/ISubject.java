package itsix.admission.model;

import java.io.Serializable;
import java.util.List;

public interface ISubject extends Serializable {
	public String getName();

	public void addStudent(IStudent student);

	public boolean isSameSubject(ISubject subject);

	public List<IStudent> getStudents();

	public boolean hasSameName(ISubject newSubject);

	public boolean hasSameName(String name);
}
