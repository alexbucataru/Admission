package itsix.admission.custom;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import itsix.admission.model.IPickedDepartment;

public class PickedDepartmentCellRenderer extends JLabel implements ListCellRenderer<IPickedDepartment> {

	@Override
	public Component getListCellRendererComponent(JList<? extends IPickedDepartment> list, IPickedDepartment
			department, int index, boolean isSelected, boolean hasFocus) {
		setText(department.getName());
		setOpaque(isSelected);
		return this;
	}

}
