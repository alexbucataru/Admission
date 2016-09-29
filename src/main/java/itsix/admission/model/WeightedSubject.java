package itsix.admission.model;

public class WeightedSubject implements IWeightedSubject{
	private ISubject subject;
	private Integer weight;
	public WeightedSubject(ISubject subject, Integer weight) {
		super();
		this.subject = subject;
		this.weight = weight;
	}
	@Override
	public String getName() {
		return subject.getName();
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
	public Integer getWeight() {
		return weight;
	}
	@Override
	public ISubject getSubject() {
		return subject;
	}
	@Override
	public Double computeWeightedgrade(Double grade) {
		return weight * grade;
	}
	@Override
	public boolean isSameSubject(IWeightedSubject weightedSubject) {
		return weightedSubject.isSameSubject(subject);
	}
	@Override
	public void setWeight(Integer weight) {
		this.weight = weight;
		
	}
	@Override
	public boolean hasSameWeight(Integer weightInt) {
		return weight.equals(weightInt);
	}
}
