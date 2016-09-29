package itsix.admission.model;

import java.io.Serializable;
import java.util.List;

public interface IInnerPickedDepartment extends Serializable, IPublisher {

	String getName();

	IDepartment getDepartment();

	void hasBeenSelected();

	boolean notFull();

	void enrollStudent(Student student);

	boolean canBeAccepted(Double admissionGrade);
	
}
