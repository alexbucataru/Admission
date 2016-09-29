package itsix.admission.model;

import java.io.Serializable;
import java.util.List;

public class GradeCalculator implements Serializable {
	public Double bacGrade(IStudent student) {
		return student.getBacGrade();
	}

	public Double combinedGrade(IStudent student, Integer bacGradeWeight, List<IWeightedSubject> pickedSubjects) {		
		return examsGrade(student, pickedSubjects) + (bacGradeWeight * student.getBacGrade() / 100);
	}

	public Double examsGrade(IStudent student, List<IWeightedSubject> pickedSubjects) {
		Double sum = 0.0;
		
		for (IWeightedSubject pickedSubject : pickedSubjects) {
			sum += student.computeWeightedGrade(pickedSubject);
		}
		
		return sum / 100;
	}
}
