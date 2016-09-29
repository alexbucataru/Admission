package itsix.admission.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import itsix.admission.controller.IStudentController;
import itsix.admission.custom.SubjectsListModel;
import itsix.admission.custom.WeightedSubjectCellRenderer;
import itsix.admission.model.IPickedDepartment;
import itsix.admission.model.IWeightedSubject;

public class SubjectDualList extends JPanel implements ISubscriber {	
	private JList subjectSourceList;
	private JList subjectDestinationList;
	private SubjectsListModel subjectSourceListModel;
	private SubjectsListModel subjectDestinationListModel;
	private JLabel subjectLabel;
	private IStudentController studentController;
	private JPanel subjectsListsButtonsPanel;
	private JPanel subjectsListPanel;
	
	public SubjectDualList(IStudentController studentController) {
		this.studentController = studentController;
		initialize();
	}

	private void initialize() {
		subjectLabel = new JLabel("");
		add(subjectLabel);
		
		subjectsListPanel = new JPanel();
		add(subjectsListPanel);
		subjectsListPanel.setLayout(new BoxLayout(subjectsListPanel, BoxLayout.X_AXIS));		
		
		JScrollPane originalSubjectsScrollPane = new JScrollPane();
		subjectsListPanel.add(originalSubjectsScrollPane);
		
		subjectSourceList = new JList();
		subjectSourceList.setCellRenderer(new WeightedSubjectCellRenderer());
		subjectSourceListModel = new SubjectsListModel(studentController.getAvailableSubjects());
		subjectSourceList.setModel(subjectSourceListModel);
		originalSubjectsScrollPane.setViewportView(subjectSourceList);
		
		subjectsListsButtonsPanel = new JPanel();
		subjectsListPanel.add(subjectsListsButtonsPanel);
		subjectsListsButtonsPanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel upDummyLabel = new JLabel("");
		subjectsListsButtonsPanel.add(upDummyLabel);
		
		JButton sendSubjectsToRightButton = new JButton(">");
		sendSubjectsToRightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentController.pickSubject();
				
			}
		});
		subjectsListsButtonsPanel.add(sendSubjectsToRightButton);
		
		JButton sendSubjectsToLeftButton = new JButton("<");
		sendSubjectsToLeftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentController.unpickSubject();
				
			}
		});
		subjectsListsButtonsPanel.add(sendSubjectsToLeftButton);
		
		JLabel downDummyLabel = new JLabel("");
		subjectsListsButtonsPanel.add(downDummyLabel);
		
		JScrollPane pickedSubjectsScrollPane = new JScrollPane();
		subjectsListPanel.add(pickedSubjectsScrollPane);
		
		subjectDestinationList = new JList();
		subjectDestinationList.setCellRenderer(new WeightedSubjectCellRenderer());
		subjectDestinationListModel = new SubjectsListModel(studentController.getPickedSubjects());
		subjectDestinationList.setModel(subjectDestinationListModel);
		

		subjectSourceListModel = new SubjectsListModel();
		subjectSourceList.setModel(subjectSourceListModel);
		subjectDestinationListModel = new SubjectsListModel();
		subjectDestinationList.setModel(subjectDestinationListModel);
		
		pickedSubjectsScrollPane.setViewportView(subjectDestinationList);
		
	}
	
	public IWeightedSubject getSelectedSourceSubject() {
		return subjectSourceListModel.getElementAt(subjectSourceList.getSelectedIndex());
	}
	
	
	public IWeightedSubject getSelectedDestinationSubject() {
		return subjectDestinationListModel.getElementAt(subjectDestinationList.getSelectedIndex());
	}

	public void dataChanged() {
		subjectSourceListModel.update();
		subjectDestinationListModel.update();
		
	}
	private void setSubjectLists() {
		subjectSourceListModel.setData(studentController.getAvailableSubjects());
		subjectDestinationListModel.setData(studentController.getPickedSubjects());
		
	}

	@Override
	public void update() {
		subjectsListPanel.setVisible(false);
		subjectLabel.setText("There is not an exam admission for this Department.");
		studentController.emptySubjectLists();
		setSubjectLists();
		
	}

	@Override
	public void update(List<IWeightedSubject> weightedSubjects) {
		subjectsListPanel.setVisible(true);
		subjectLabel.setText("<html>Pick subjects for the exam.<br> The weight of the subjects combined must be 100%</html>");
		studentController.setSubjectListsExamAdmission(weightedSubjects);
		setSubjectLists();
		
	}

	@Override
	public void update(List<IWeightedSubject> weightedSubjects, Integer bacGradeWeight) {
		subjectsListPanel.setVisible(true);
		subjectLabel.setText("<html>PickSubjects for the exam.<br> The weight of the subjects cominbed must be " + (100 - bacGradeWeight) + "%</html>" );
		studentController.setSubjectListsCombinedAdmission(weightedSubjects);
		setSubjectLists();
		
	}
	

	public void subscribe(IPickedDepartment pickedDepartment) {
		pickedDepartment.subscribe(this);
		
	}

	public void reinitialize() {
		subjectLabel.setText("");
		studentController.emptySubjectLists();
		setSubjectLists();
	}

	public void hideButtonsPanel() {
		subjectsListPanel.setVisible(false);
	}
}
