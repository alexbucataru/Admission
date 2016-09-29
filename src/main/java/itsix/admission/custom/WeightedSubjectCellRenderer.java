package itsix.admission.custom;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import itsix.admission.model.IDepartment;
import itsix.admission.model.IWeightedSubject;

public class WeightedSubjectCellRenderer extends JLabel implements ListCellRenderer<IWeightedSubject> {
	@Override
	public Component getListCellRendererComponent(JList<? extends IWeightedSubject> list, 
			IWeightedSubject subject, int index, boolean isSelected, boolean hasFocus) {
		setText(subject.getName() + " [" + subject.getWeight() + "%]");
		setOpaque(isSelected);
		return this;
	}
}
