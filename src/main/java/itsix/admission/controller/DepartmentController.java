package itsix.admission.controller;

import itsix.admission.builders.IAdmissionTypeBuilder;
import itsix.admission.builders.IWeightedSubjectBuilder;
import itsix.admission.model.BacBasedAdmission;
import itsix.admission.model.CombinedAdmission;
import itsix.admission.model.ExamAdmission;
import itsix.admission.model.IAdmissionType;
import itsix.admission.model.IDepartment;
import itsix.admission.model.ISubject;
import itsix.admission.model.IWeightedSubject;
import itsix.admission.repository.IDepartmentRepository;
import itsix.admission.validators.IValidator;
import itsix.admission.view.IAdmissionRulesView;
import itsix.admission.view.IAssignedStudentsView;
import itsix.admission.view.IViewRepartition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class DepartmentController implements IDepartmentController {
	private IAdmissionRulesView admissionRulesView;
	private IDepartmentRepository departmentRepository;
	private List<IDepartment> departments;
	private IDepartment selectedDepartment;
	private IAdmissionTypeBuilder admissionTypeBuilder;
	private List<IWeightedSubject> pickedSubjects;
	private IWeightedSubjectBuilder weightedSubjectBuilder;
	private Map<Class<? extends IAdmissionType>, JRadioButton> admissionTypeMap;
	private Integer bacGradeWeight;
	private IViewRepartition viewRepartition;
	private IValidator validator;
	private IAssignedStudentsView assignedStudentsView;
	
	public DepartmentController(IDepartmentRepository departmentRepository,	IAdmissionTypeBuilder admissionTypeBuilder, 
			IWeightedSubjectBuilder weightedSubjectBuilder, IValidator validator) {
		this.departmentRepository = departmentRepository;
		this.departments = departmentRepository.getDepartments();
		this.selectedDepartment = departments.get(0);		
		this.admissionTypeBuilder = admissionTypeBuilder;
		this.pickedSubjects = new ArrayList<>();
		this.weightedSubjectBuilder = weightedSubjectBuilder;
		this.validator = validator;
		
	}

	private void populateMap() {
		admissionTypeMap = new HashMap<Class<? extends IAdmissionType>, JRadioButton>();
		admissionTypeMap.put(BacBasedAdmission.class, admissionRulesView.getBacGradeRdBtn());
		admissionTypeMap.put(ExamAdmission.class, admissionRulesView.getExamAdmissionRdBtn());
		admissionTypeMap.put(CombinedAdmission.class, admissionRulesView.getCombinedAdmissionRdBtn());
	}
	
	@Override
	public void setAdmissionRulesView(IAdmissionRulesView admissionRulesView) {
		this.admissionRulesView = admissionRulesView;
		populateMap();
		
	}

	@Override
	public List<IDepartment> getDepartments() {
		return departments;
	}

	@Override
	public IDepartment getSelectedDepartment() {
		return selectedDepartment;
	}

	@Override
	public void saveBacBasedAdmission() {
		IAdmissionType bacBasedAdmission = admissionTypeBuilder.build();
		String numberOfPlaces = admissionRulesView.getNumberOfPlaces();	
		String validationResult = validator.validate("Number of places", numberOfPlaces);
		if (validationResult.equals("")) {
			selectedDepartment.setAdmissionType(bacBasedAdmission);
			selectedDepartment.setNumberOfPlaces(Integer.valueOf(numberOfPlaces));
			JOptionPane.showMessageDialog(null, "Changes have been saved");
		} else {
			JOptionPane.showMessageDialog(null, validationResult);
		}
				
	}

	@Override
	public void saveExamAdmission() {
		IAdmissionType examAdmission = admissionTypeBuilder.build(pickedSubjects);
		String numberOfPlaces = admissionRulesView.getNumberOfPlaces();	
		String validationResult = validator.validate("Number of places", numberOfPlaces);
		if (validationResult.equals("")) {
			selectedDepartment.setAdmissionType(examAdmission);
			selectedDepartment.setNumberOfPlaces(Integer.valueOf(numberOfPlaces));
			JOptionPane.showMessageDialog(null, "Changes have been saved");
		} else {
			JOptionPane.showMessageDialog(null, validationResult);
		}
	}

	@Override
	public void saveCombinedAdmission() {
		String bacGradeWeight = admissionRulesView.getBacGradeWeight();
		String numberOfPlaces = admissionRulesView.getNumberOfPlaces();	
		String validationResult = validator.validate("Bac grade weight", bacGradeWeight);
		if (validationResult.equals("")) {
			IAdmissionType combinedAdmission = admissionTypeBuilder.build(Integer.valueOf(bacGradeWeight), pickedSubjects);
			validationResult = validator.validate("Number of places", numberOfPlaces);
			if (validationResult.equals("")) {
				selectedDepartment.setAdmissionType(combinedAdmission);
				selectedDepartment.setNumberOfPlaces(Integer.valueOf(admissionRulesView.getNumberOfPlaces()));
				JOptionPane.showMessageDialog(null, "Changes have been saved");
			} else {
				JOptionPane.showMessageDialog(null, validationResult);
			}
		} else {
			JOptionPane.showMessageDialog(null, validationResult);
		}
		
	}

	@Override
	public List<IWeightedSubject> getPickedSubjects() {
		return pickedSubjects;
	}

	@Override
	public void newDepartmentSelected(IDepartment selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
		this.pickedSubjects = new ArrayList<> (selectedDepartment.getAvailableSubjects());
		//this.selectedDepartment.publishItHasBeenSelected();
		admissionRulesView.newDepartmentSelected();
		admissionRulesView.setSelectedRadioButton(admissionTypeMap.get(selectedDepartment.getAdmissionTypeClass()));
		this.bacGradeWeight = selectedDepartment.getBacGradeWeight();
		this.admissionRulesView.setBacGradeWeight(bacGradeWeight);
	}

	@Override
	public void addSubject(ISubject selectedSubject) {
		IWeightedSubject weightedSubject = weightedSubjectBuilder.build(selectedSubject);
		pickedSubjects.add(weightedSubject);
		admissionRulesView.newSubjectAdded();
	}

	@Override
	public void setPickedSubjects(List<IWeightedSubject> weightedSubjects) {
		this.pickedSubjects = weightedSubjects;
		
	}

	@Override
	public void bacGradeAdmissionPicked() {
		this.pickedSubjects = new ArrayList<>();
		admissionRulesView.newDepartmentSelected();
	}

	@Override
	public void examAdmissionPicked() {
		ExamAdmission examAdmission = (ExamAdmission)selectedDepartment.getAdmissionType();
		this.pickedSubjects = examAdmission.getAvailableSubjects();
		admissionRulesView.newDepartmentSelected();
	}

	@Override
	public void newSelectedDepartment() {
		viewRepartition.dataChanged(viewRepartition.getSelectedDepartment());
	}

	@Override
	public void setViewRepartition(IViewRepartition viewRepartition) {
		this.viewRepartition = viewRepartition;
		
	}

	@Override
	public String getNumberOfPlaces() {
		return selectedDepartment.getNumberOfPlaces().toString();
	}

	@Override
	public void setAssignedStudentView(IAssignedStudentsView assignedStudentsView) {
		this.assignedStudentsView = assignedStudentsView;
		
	}

	@Override
	public void goToAllAssignedStudents() {
		assignedStudentsView.display();
		
	}

	@Override
	public void updateAvailableSubjects() {
		this.pickedSubjects = new ArrayList<> (selectedDepartment.getAvailableSubjects());
		
	}


	
}
