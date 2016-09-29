package itsix.admission.custom;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import itsix.admission.model.ISubject;

public class SubjectCellRenderer extends JLabel implements ListCellRenderer<ISubject> {

	@Override
	public Component getListCellRendererComponent(JList<? extends ISubject> list, ISubject subject, int index, boolean isSelected,
			boolean hasFocus) {
		setText(subject.getName());
		setOpaque(true);
		return this;
	}

}
