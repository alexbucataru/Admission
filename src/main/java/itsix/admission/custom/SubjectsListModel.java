package itsix.admission.custom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import itsix.admission.model.IWeightedSubject;

public class SubjectsListModel extends AbstractListModel<IWeightedSubject> {
	
	private List<IWeightedSubject> subjects;
	
	public SubjectsListModel(List<IWeightedSubject> subjects) {
		this.subjects = subjects;
	}
	
	public SubjectsListModel() {
		this.subjects = new ArrayList<>();
	}
	
	public void setData(List<IWeightedSubject> subjects) {
		this.subjects = subjects;
		fireContentsChanged(this, 0, getSize());
	}
	
	@Override
	public IWeightedSubject getElementAt(int index) {
		return subjects.get(index);
	}

	@Override
	public int getSize() {
		return subjects.size();
	}	
	
	public void update() {
		fireContentsChanged(this, 0, getSize());
	}

	public void removeData() {
		subjects.clear();
		fireContentsChanged(this, 0, getSize());
		
	}

}

