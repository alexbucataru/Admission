package itsix.admission.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import itsix.admission.controller.IStudentController;
import itsix.admission.custom.DepartmentCellRenderer;
import itsix.admission.custom.DepartmentsListModel;
import itsix.admission.custom.PickedDepartmentCellRenderer;
import itsix.admission.custom.PickedDepartmentListModel;
import itsix.admission.model.IDepartment;
import itsix.admission.model.IPickedDepartment;
import itsix.admission.model.IWeightedSubject;

public class DepartmentDualList extends JPanel{
	private JList departmentSourceList;
	private JList departmentDestinationList;
	private DepartmentsListModel departmentSourceListModel;
	private PickedDepartmentListModel departmentDestinationListModel;
	private IStudentController studentController;
	private JButton sendToRightButton;
	
	public DepartmentDualList(IStudentController studentController) {
		this.studentController = studentController;
		initialize();
	}

	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JScrollPane departmentsSourceScrollPane = new JScrollPane();
		add(departmentsSourceScrollPane);
		
		departmentSourceList = new JList();
		departmentSourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		departmentSourceListModel = new DepartmentsListModel(studentController.getSourceDepartmentsList());
		departmentSourceList.setModel(departmentSourceListModel);
		departmentSourceList.setCellRenderer(new DepartmentCellRenderer());
		departmentsSourceScrollPane.setViewportView(departmentSourceList);
		
		JPanel departmentsDestListPane = new JPanel();
		
		departmentsDestListPane.setLayout(new BoxLayout(departmentsDestListPane, BoxLayout.Y_AXIS));
		
		JScrollPane departmentsDestListScrollPane = new JScrollPane();		
		
		departmentDestinationListModel = new PickedDepartmentListModel(studentController.getPickedDepartments());
		departmentDestinationList = new JList();
		departmentDestinationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		departmentDestinationList.setModel(departmentDestinationListModel);
		departmentDestinationList.setCellRenderer(new PickedDepartmentCellRenderer());
		addDepartmentDestinationListListener();
		
		JPanel listButtonsPanel = new JPanel();
		add(listButtonsPanel);
		listButtonsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		sendToRightButton = new JButton(">");
		
		sendToRightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				studentController.pickDepartment();
			}
		});
		listButtonsPanel.add(sendToRightButton);
		
		JButton sendToLeftButton = new JButton("<");
		sendToLeftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentController.unpickDepartment();
				invalidateDestinationSelection();
				addDepartmentDestinationListListener();
				studentController.noPickedDepartmentSelected();
			}
		});
		listButtonsPanel.add(sendToLeftButton);
		
		JButton sendAllToRightButton = new JButton(">>");
		sendAllToRightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentController.pickAllDepartments();
				
			}
		});
		listButtonsPanel.add(sendAllToRightButton);
		
		JButton sendAllToLeftButton = new JButton("<<");
		sendAllToLeftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentController.unpickAllDepartments();
				invalidateDestinationSelection();
				addDepartmentDestinationListListener();
				studentController.noPickedDepartmentSelected();
			}
		});
		listButtonsPanel.add(sendAllToLeftButton);		
		
		add(departmentsDestListPane);
		departmentsDestListPane.add(departmentsDestListScrollPane);
		departmentsDestListScrollPane.setViewportView(departmentDestinationList);
		
		JPanel optionListButtonsPanel = new JPanel();
		departmentsDestListPane.add(optionListButtonsPanel);
		optionListButtonsPanel.setLayout(new GridLayout(0, 2, 5, 0));
		
		JButton upBtn = new JButton("Up");
		upBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentDestinationList.setSelectedIndex(departmentDestinationListModel.moveUp(departmentDestinationList.getSelectedIndex()));
				
			}
		});
		optionListButtonsPanel.add(upBtn);
		
		JButton downBtn = new JButton("Down");
		downBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentDestinationList.setSelectedIndex(departmentDestinationListModel.moveDown(departmentDestinationList.getSelectedIndex()));
				
			}
		});
		optionListButtonsPanel.add(downBtn);
		
	}

	public void dataChanged() {
		departmentDestinationListModel.update();
		departmentSourceListModel.update();
	}
	
	public IDepartment getSelectedSourceDepartment() {
		return departmentSourceListModel.getElementAt(departmentSourceList.getSelectedIndex());
	}
	
	public IPickedDepartment getSelectedDestinationDepartment() {
		return departmentDestinationListModel.getElementAt(departmentDestinationList.getSelectedIndex());
	}

	public void reinitialize() {
		invalidateDestinationSelection();
		addDepartmentDestinationListListener();
		studentController.reinitializeDepartmentLists();
		setDepartmentLists();
		departmentSourceList.clearSelection();
	}

	private void invalidateDestinationSelection() {
		departmentDestinationList.removeListSelectionListener(departmentDestinationList.getListSelectionListeners()[0]);
		departmentDestinationList.clearSelection();
	}

	private void addDepartmentDestinationListListener() {
		departmentDestinationList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				studentController.pickedDepartmentHasBeenSelected();
				//setSubjectListsModel();				
			}

		});
	}

	private void setDepartmentLists() {
		departmentDestinationListModel.setData(studentController.getPickedDepartments());
		departmentSourceListModel.setData(studentController.getSourceDepartmentsList());
	}
	
	
}
