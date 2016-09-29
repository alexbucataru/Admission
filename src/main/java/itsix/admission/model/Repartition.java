package itsix.admission.model;

import java.util.List;

public class Repartition implements IRepartition {
	private List<IStudent> students;
	private List<IDepartment> departments;
	private List<IStudent> assignedStudents;
	
	public Repartition(List<IStudent> students, List<IDepartment> departments, List<IStudent> assignedStudents) {
		this.students = students;
		this.departments = departments;
		this.assignedStudents = assignedStudents;
	}

	@Override
	public void doRepartition() {
		for (IStudent student : students) {
			student.repartition();
		}
		for (IDepartment department : departments) {
			department.addAssignedStudents(assignedStudents);
		}
	}

}
