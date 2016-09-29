package itsix.admission.model;

import java.io.Serializable;

public interface IGradedSubject extends Serializable {

	void addStudent(IStudent student);

	boolean isSameSubject(ISubject subject);

	Double getGrade();

	void setGrade(Double value);

	boolean isSameSubject(IWeightedSubject pickedSubject);

	Double computeWeightedGrade(IWeightedSubject pickedSubject);

}
