package itsix.admission.model;

import java.util.ArrayList;
import java.util.List;

public class Subject implements ISubject {
	private String name;
	private List<IStudent> students;
	
	
	public Subject(String name) {
		this.name = name;
		students = new ArrayList<>();
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public void addStudent(IStudent student) {
		if (!students.contains(student)) {
			students.add(student);
			student.addPickedSubject(this);
		}
		
	}


	@Override
	public boolean isSameSubject(ISubject subject) {
		return this == subject;
	}


	@Override
	public List<IStudent> getStudents() {
		return students;
	}


	@Override
	public boolean hasSameName(ISubject newSubject) {
		return newSubject.hasSameName(name);
	}


	@Override
	public boolean hasSameName(String name) {
		return this.name.equals(name);
	}
	
}
