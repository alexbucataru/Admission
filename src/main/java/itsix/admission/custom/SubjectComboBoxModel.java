package itsix.admission.custom;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import itsix.admission.model.ISubject;

public class SubjectComboBoxModel extends AbstractListModel<ISubject> implements ComboBoxModel<ISubject> {
	private List<ISubject> subjects;
	private ISubject selectedItem;
	
	public SubjectComboBoxModel(List<ISubject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public ISubject getElementAt(int index) {
		return subjects.get(index);
	}

	@Override
	public int getSize() {
		return subjects.size();
	}

	@Override
	public ISubject getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object selectedItem) {
		this.selectedItem = (ISubject) selectedItem;
		
	}

	public void update() {
		fireContentsChanged(this, 0, getSize());
		
	}

}
