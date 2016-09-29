package itsix.admission.model;

import java.util.List;

import itsix.admission.view.ISubscriber;

public class Publisher implements IInnerPublisher {
	List<ISubscriber> subscribers;
	
	public Publisher(List<ISubscriber> subscribers) {
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
	public void notifiySubscribers() {
		for (ISubscriber subscriber : subscribers) {
			subscriber.update();
		}		
	}
	
	
}
