package itsix.admission.model;

public class GradedSubject implements IGradedSubject {
	private ISubject subject;
	private Double grade;
	
	
	public GradedSubject(ISubject subject, Double grade) {
		this.subject = subject;
		this.grade = grade;
	}


	@Override
	public void addStudent(IStudent student) {
		subject.addStudent(student);
		
	}


	@Override
	public boolean isSameSubject(ISubject subject) {
		return this.subject.isSameSubject(subject);
	}


	@Override
	public Double getGrade() {
		return grade;
	}


	@Override
	public void setGrade(Double value) {
		grade = value;
		
	}


	@Override
	public boolean isSameSubject(IWeightedSubject pickedSubject) {
		return pickedSubject.isSameSubject(subject);
	}


	@Override
	public Double computeWeightedGrade(IWeightedSubject pickedSubject) {
		return pickedSubject.computeWeightedgrade(grade);
	}

	
	
}
