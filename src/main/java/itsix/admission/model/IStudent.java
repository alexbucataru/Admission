package itsix.admission.model;

import java.io.Serializable;

public interface IStudent extends Serializable{

	void addPickedSubject(ISubject subject);

	String getName();

	Integer getSSN();

	Double getGrade(ISubject subject);

	void setGrade(ISubject subject, Double value);

	void repartition();

	Double getBacGrade();

	Double computeWeightedGrade(IWeightedSubject pickedSubject);

	boolean hasBiggerGrade(IStudent iStudent);

	boolean hasSmallerGrade(Double admissionGrade);

	boolean hasAdmissionGradeSmallerThan(Double admissionGrade);

	Double getAdmissionGrade();

	boolean hasSameSSN(IStudent student);

	boolean hasSameSSN(Integer ssn);

	String getDepartmentName();
	
}
