package itsix.admission.model;

import java.io.Serializable;

public interface IWeightedSubject extends Serializable {

	String getName();

	void addStudent(IStudent student);

	boolean isSameSubject(ISubject subject);

	Integer getWeight();

	ISubject getSubject();

	Double computeWeightedgrade(Double grade);

	boolean isSameSubject(IWeightedSubject weightedSubject);

	void setWeight(Integer valueOf);

	boolean hasSameWeight(Integer weightInt);


}
