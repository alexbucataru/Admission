package itsix.admission.model;

import java.util.ArrayList;
import java.util.List;

import itsix.admission.builders.IGradedSubjectBuilder;

public class Student implements IStudent {
	private Integer ssn;
	private String name;
	private Double bacGrade;
	private List<IPickedDepartment> pickedDepartments;
	private List<IGradedSubject> pickedSubjects;
	private IGradedSubjectBuilder gradedSubjectBuilder;
	private Double admissionGrade;
	private IPickedDepartment departmentAssignedTo;
	
	public Student(Integer ssn, String name, Double bacGrade, 
			List<IPickedDepartment> pickedDepartments, IGradedSubjectBuilder gradedSubjectBuilder) {
		this.ssn = ssn;
		this.name = name;
		this.bacGrade = bacGrade;
		this.pickedDepartments = new ArrayList<>(pickedDepartments);
		this.pickedSubjects = new ArrayList<>();
		this.gradedSubjectBuilder = gradedSubjectBuilder;
	}


	@Override
	public void addPickedSubject(ISubject subject) {
		pickedSubjects.add(new GradedSubject(subject, 0.0));
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getSSN() {
		return ssn;
	}

	@Override
	public Double getGrade(ISubject subject) {
		for (IGradedSubject gradedSubject : pickedSubjects) {
			if (gradedSubject.isSameSubject(subject)) {
				return gradedSubject.getGrade();
			}
		}
		
		return null;
	}

	@Override
	public void setGrade(ISubject subject, Double value) {
		for (IGradedSubject gradedSubject : pickedSubjects) {
			if (gradedSubject.isSameSubject(subject)) {
				gradedSubject.setGrade(value);
			}
		}
		
	}


	@Override
	public void repartition() {
		for (IPickedDepartment pickedDepartment : pickedDepartments) {
			admissionGrade = pickedDepartment.computeAdmissionGrade(this);
			if (pickedDepartment.notFull()) {
				pickedDepartment.enrollStudent(this);
				departmentAssignedTo = pickedDepartment;
				break;
			} else {
				if (pickedDepartment.canBeAccepted(admissionGrade)) {
					pickedDepartment.enrollStudent(this);
					departmentAssignedTo = pickedDepartment;
					break;
				}
			}
		}
	}


	@Override
	public Double getBacGrade() {
		return bacGrade;
	}


	@Override
	public Double computeWeightedGrade(IWeightedSubject pickedSubject) {
		for (IGradedSubject gradedSubject : pickedSubjects) {
			if (gradedSubject.isSameSubject(pickedSubject)) {
				return gradedSubject.computeWeightedGrade(pickedSubject);
			}
		}
		
		return null;
	}


	@Override
	public boolean hasBiggerGrade(IStudent student) {
		return student.hasSmallerGrade(admissionGrade);
	}


	@Override
	public boolean hasSmallerGrade(Double admissionGrade) {
		return this.admissionGrade < admissionGrade;
	}


	@Override
	public boolean hasAdmissionGradeSmallerThan(Double admissionGrade) {
		return this.admissionGrade < admissionGrade;
	}


	@Override
	public Double getAdmissionGrade() {
		return admissionGrade;
	}


	@Override
	public boolean hasSameSSN(IStudent student) {
		return student.hasSameSSN(ssn);
	}


	@Override
	public boolean hasSameSSN(Integer ssn) {
		return this.ssn.equals(ssn);
		
	}


	@Override
	public String getDepartmentName() {
		return departmentAssignedTo.getName();
	}	
	
}
