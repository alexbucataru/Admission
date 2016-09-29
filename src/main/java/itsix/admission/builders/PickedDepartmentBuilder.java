package itsix.admission.builders;

import java.util.ArrayList;

import itsix.admission.model.AdmissionTypePublisher;
import itsix.admission.model.GradeCalculator;
import itsix.admission.model.IDepartment;
import itsix.admission.model.IInnerPickedDepartment;
import itsix.admission.model.IPickedDepartment;
import itsix.admission.model.InnerPickedDepartment;
import itsix.admission.model.PickedDepartmentBacAdmission;
import itsix.admission.model.PickedDepartmentCombinedAdmission;
import itsix.admission.model.PickedDepartmentExamAdmission;
import itsix.admission.view.ISubscriber;

public class PickedDepartmentBuilder implements IPickedDepartmentBuilder {
	private GradeCalculator gradeCalculator;
	
	public PickedDepartmentBuilder(GradeCalculator gradeCalculator) {
		this.gradeCalculator = gradeCalculator;
	}

	@Override
	public IInnerPickedDepartment build(IDepartment department) {
		return new InnerPickedDepartment(department);
	}

	@Override
	public IPickedDepartment buildPickedDepartmentBacAdmission(IDepartment department) {
		IInnerPickedDepartment pickedDepartment = build(department);
		return new PickedDepartmentBacAdmission(pickedDepartment, gradeCalculator);
	}

	@Override
	public IPickedDepartment buildPickedDepartmentCombinedAdmission(IDepartment department, Integer bacGradeWeight) {
		IInnerPickedDepartment pickedDepartment = build(department);
		return new PickedDepartmentCombinedAdmission(pickedDepartment, bacGradeWeight, gradeCalculator);
	}

	@Override
	public IPickedDepartment buildPickedDepartmentExamAdmission(IDepartment department) {
		IInnerPickedDepartment pickedDepartment = build(department);
		return new PickedDepartmentExamAdmission(pickedDepartment, gradeCalculator);
	}
	
	

	
}
