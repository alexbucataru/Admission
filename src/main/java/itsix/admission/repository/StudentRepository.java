package itsix.admission.repository;

import java.util.ArrayList;
import java.util.List;

import itsix.admission.exceptions.ObjectAlreadyExistingException;
import itsix.admission.model.IStudent;

public class StudentRepository implements IStudentRepository {
	
	private List<IStudent> students;
	
	private List<IStudent> assignedStudents;
	
	public StudentRepository() {
		students = new ArrayList<>();	
		assignedStudents = new ArrayList<>();
	}

	@Override
	public void saveStudent(IStudent newStudent) throws ObjectAlreadyExistingException {
		for (IStudent student : students ){
			if (newStudent.hasSameSSN(student)) {
				throw new ObjectAlreadyExistingException("SSN already found in the DB");
			}
		}
		students.add(newStudent);
		
	}

	@Override
	public List<IStudent> getStudents() {
		return students;
	}

	@Override
	public List<IStudent> getAssignedStudents() {
		return assignedStudents;
	}

}
