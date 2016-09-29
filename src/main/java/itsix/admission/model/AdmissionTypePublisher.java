package itsix.admission.model;

import java.util.List;

import itsix.admission.view.ISubscriber;

public class AdmissionTypePublisher implements IAdmissionTypeInnerPublisher {
	List<ISubscriber> subscribers;

	public AdmissionTypePublisher(List<ISubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		subscribers.add(subscriber);
		
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		subscribers.remove(subscriber);
		
	}

	@Override
	public void notifySubscribers() {
		for (ISubscriber subscriber : subscribers) {
			subscriber.update();
		}
		
	}

	@Override
	public void notifySubscribers(List<IWeightedSubject> weightedSubjects) {
		for (ISubscriber subscriber : subscribers) {
			subscriber.update(weightedSubjects);
		}
		
	}

	@Override
	public void notifySubscribers(List<IWeightedSubject> weightedSubjects, Integer bacGradeWeight) {
		for (ISubscriber subscriber : subscribers) {
			subscriber.update(weightedSubjects, bacGradeWeight);
		}
		
	}

}
