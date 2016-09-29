package itsix.admission.model;

import java.util.List;

public interface IAdmissionTypeInnerPublisher extends IPublisher {
	void notifySubscribers();
	void notifySubscribers(List<IWeightedSubject> weightedSubjects);
	void notifySubscribers(List<IWeightedSubject> weightedSubjects, Integer bacGradeWeight);
}
