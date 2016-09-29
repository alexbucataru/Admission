package itsix.admission.view;

import java.util.List;

import itsix.admission.model.IWeightedSubject;

public interface ISubscriber {
	public void update();

	public void update(List<IWeightedSubject> weightedSubjects);

	public void update(List<IWeightedSubject> weightedSubjects, Integer bacGradeWeight);
}
