package itsix.admission.model;

import itsix.admission.view.ISubscriber;

public interface IPublisher {
	public void subscribe(ISubscriber subscriber);
	
	public void unsubscribe(ISubscriber subscriber);
}
