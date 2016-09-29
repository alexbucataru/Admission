package itsix.admission.custom;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import itsix.admission.model.IDepartment;

public class DepartmentCellRenderer extends JLabel implements ListCellRenderer<IDepartment> {


	@Override
	public Component getListCellRendererComponent(JList<? extends IDepartment> list, 
			IDepartment department, int index, boolean isSelected, boolean hasFocus) {
		setText(department.getName());
		setOpaque(isSelected);
		return this;
	}
	
}
