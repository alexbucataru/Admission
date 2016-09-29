package itsix.admission.controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import itsix.admission.builders.IDepartmentBuilder;
import itsix.admission.builders.IGradedSubjectBuilder;
import itsix.admission.builders.IPickedDepartmentBuilder;
import itsix.admission.builders.IStudentBuilder;
import itsix.admission.exceptions.ObjectAlreadyExistingException;
import itsix.admission.model.IDepartment;
import itsix.admission.model.IPickedDepartment;
import itsix.admission.model.IStudent;
import itsix.admission.model.IWeightedSubject;
import itsix.admission.model.PickedDepartmentCombinedAdmission;
import itsix.admission.model.PickedDepartmentExamAdmission;
import itsix.admission.repository.IDepartmentRepository;
import itsix.admission.repository.IStudentRepository;
import itsix.admission.validators.IValidator;
import itsix.admission.view.IEnrollStudentView;

public class StudentController implements IStudentController {
	
	private IStudentBuilder studentBuilder;	
	private IDepartmentBuilder departmentBuilder;
	private IPickedDepartmentBuilder pickedDepartmentBuilder;
	private IGradedSubjectBuilder gradedSubjectBuilder;
	private IDepartmentRepository departmentRepository;
	private IStudentRepository studentRepository;
	private IEnrollStudentView enrollStudentView;
	private List<IDepartment> sourceDepartments;
	private List<IDepartment> destinationDepartments;
	private IPickedDepartment selectedPickedDepartment;
	private List<IPickedDepartment> pickedDepartments;
	private List<IWeightedSubject> availableSubjects;
	private List<IWeightedSubject> pickedSubjects;
	private IDepartment selectedDepartment;
	private List<IWeightedSubject> allPickedSubjects;
	private IValidator validator;
	private String validationResult;
	private List<IWeightedSubject> allSubjectsOfTheSelectedDepartment;
	
	
	public StudentController(IStudentBuilder studentBuilder, IDepartmentBuilder departmentBuilder,
			IPickedDepartmentBuilder pickedDepartmentBuilder, IGradedSubjectBuilder gradedSubjectBuilder,
			IDepartmentRepository departmentRepository, IStudentRepository studentRepository, IValidator validator) {
		this.departmentBuilder = departmentBuilder;
		this.pickedDepartmentBuilder = pickedDepartmentBuilder;
		this.departmentRepository = departmentRepository;
		this.studentRepository = studentRepository;
		this.studentBuilder = studentBuilder;
		this.sourceDepartments = new ArrayList<>(departmentRepository.getDepartments());
		this.destinationDepartments = new ArrayList<>();
		this.gradedSubjectBuilder = gradedSubjectBuilder;
		this.pickedDepartments = new ArrayList<>();
		this.availableSubjects = new ArrayList<>();
		this.pickedSubjects = new ArrayList<>();
		this.allPickedSubjects = new ArrayList<>();
		this.validator = validator;
		this.allSubjectsOfTheSelectedDepartment = new ArrayList<>();
	}

	@Override
	public void enroll() {
		String ssn = enrollStudentView.getStudentSSN();
		String name = enrollStudentView.getStudentName();
		String bacGrade = enrollStudentView.getStudentBACGrade();
		
		if (fieldsAreNotEmpty(ssn, name, bacGrade)) {
			Double bacGradeDouble = Double.valueOf(bacGrade);
			if (gradeIsValid(bacGradeDouble)) {
				if (weightOfSubjectsAreCorrect()) {
					IStudent student = studentBuilder.build(Integer.valueOf(ssn), name, bacGradeDouble, pickedDepartments, gradedSubjectBuilder);
					try {
						studentRepository.saveStudent(student);
						pickSubjects(student);
						enrollStudentView.tuck();
					} catch (ObjectAlreadyExistingException oaee) {
						oaee.showMessage();
					}
				} else {
					JOptionPane.showMessageDialog(null, validationResult);
				}
			} else {
				JOptionPane.showMessageDialog(null, validationResult);
			}
				
		} else {
			JOptionPane.showMessageDialog(null, validationResult);		
		}		
		
	}
	
	private boolean weightOfSubjectsAreCorrect() {
		validationResult = "";
		for (IPickedDepartment pickedDepartment : pickedDepartments) {
			validationResult = validator.validate(pickedDepartment);
		}
		
		if (validationResult.equals("")) {
			return true;
		}
		
		return false;
	}

	private boolean fieldsAreNotEmpty(String ssn, String name, String bacGrade) {
		validationResult = "";
		validationResult += validator.validate("SSN", ssn);
		validationResult += validator.validate("name", name);
		validationResult += validator.validate("bacGrade", bacGrade);
		if (validationResult.equals("")) {
			return true;
		} 
		
		return false;
	}
	
	private boolean gradeIsValid(Double bacGrade) {
		validationResult = "";
		validationResult += validator.validate("BAC grade", bacGrade, 5, 10);
		if (validationResult.equals("")) {
			return true;
		}
		return false;
	}


	private void pickSubjects(IStudent student) {
		for (IWeightedSubject subject : allPickedSubjects) {
			subject.addStudent(student);
		}
	}

	@Override
	public List<IDepartment> getSourceDepartmentsList() {
		return sourceDepartments;
	}
	
	

	@Override
	public void setEnrollStudentView(IEnrollStudentView enrollStudentView) {
		this.enrollStudentView = enrollStudentView;		
	}

	@Override
	public void pickDepartment() {		
		//pickedDepartments.add(pickedDepartmentBuilder.build(enrollStudentView.getSelectedSourceDepartment()));
		//sourceDepartments.remove(enrollStudentView.getSelectedSourceDepartment());
		//enrollStudentView.updateDepartmentChoices();
		selectedDepartment = enrollStudentView.getSelectedSourceDepartment();
		IPickedDepartment pickedDepartment = selectedDepartment.buildPickDepartment(pickedDepartmentBuilder);
		pickedDepartments.add(pickedDepartment);
		enrollStudentView.subscribe(pickedDepartment);
		sourceDepartments.remove(selectedDepartment);
		enrollStudentView.updateDepartmentChoices();
		//selectedDepartment.publishItHasBeenSelected();
	}

	@Override
	public void unpickDepartment() {
		IPickedDepartment pickedDepartment = enrollStudentView.getSelectedDestinationDepartment();
		sourceDepartments.add(pickedDepartment.getDepartment());
		pickedDepartments.remove(pickedDepartment);
		enrollStudentView.updateDepartmentChoices();
	}
	

	@Override
	public List<IDepartment> getDestinationDepartmentsList() {
		return destinationDepartments;
	}

	@Override
	public void pickAllDepartments() {
		for (IDepartment department : sourceDepartments) {
			//IPickedDepartment pickedDepartment = pickedDepartmentBuilder.build(department);
			//enrollStudentView.subscribe(pickedDepartment);
			//pickedDepartments.add(pickedDepartment);
			IPickedDepartment pickedDepartment = department.buildPickDepartment(pickedDepartmentBuilder);
			enrollStudentView.subscribe(pickedDepartment);
			pickedDepartments.add(pickedDepartment);
		}
		sourceDepartments.clear();
		enrollStudentView.updateDepartmentChoices();
	}

	@Override
	public void unpickAllDepartments() {	
		for (IPickedDepartment pickedDepartment : pickedDepartments) {
			sourceDepartments.add(pickedDepartment.getDepartment());
		}
		pickedDepartments.clear();
		enrollStudentView.updateDepartmentChoices();
	}
	
	private void moveAllDepartments(List<IDepartment> source, List<IDepartment> destination) {
		for (IDepartment department : source) {
			destination.add(department);
		}	
		source.clear();
		enrollStudentView.updateDepartmentChoices();
	}


	@Override
	public List<IWeightedSubject> getSourceSubjects() {
		return availableSubjects;
	}

	@Override
	public List<IWeightedSubject> getDestinationSubjects() {
		return pickedSubjects;
	}

	@Override
	public void pickSubject() {
		IWeightedSubject subject = enrollStudentView.getSelectedSourceSubject();
		pickedSubjects.add(subject);
		allPickedSubjects.add(subject);
		for (IWeightedSubject weightedSubject : new ArrayList<>(availableSubjects) ){
			if (subject.isSameSubject(weightedSubject)) {
				availableSubjects.remove(weightedSubject);
			}
		}
		
		enrollStudentView.updateSubjectChoices();
		//selectedPickedDepartment.pickSubject(enrollStudentView.getSelectedSourceSubject());
		
	}

	@Override
	public void unpickSubject() {
		IWeightedSubject subject = enrollStudentView.getSelectedDestinationSubject();
		for (IWeightedSubject weightedSubject : allSubjectsOfTheSelectedDepartment ){
			if (subject.isSameSubject(weightedSubject)) {
				availableSubjects.add(weightedSubject);
			}
		}
		allPickedSubjects.remove(subject);
		pickedSubjects.remove(subject);
		enrollStudentView.updateSubjectChoices();
		//selectedPickedDepartment.unpickSubject(enrollStudentView.getSelectedDestinationSubject());
		
	}


	@Override
	public List<IPickedDepartment> getPickedDepartments() {
		return pickedDepartments;
	}

	@Override
	public void setSelectedDepartment(IPickedDepartment pickedDepartment) {
		this.selectedPickedDepartment = pickedDepartment;
		
	}

	@Override
	public void pickedDepartmentHasBeenSelected() {
		selectedPickedDepartment = enrollStudentView.getSelectedDestinationDepartment();
		selectedPickedDepartment.hasBeenSelected();		
	}

	@Override
	public void setSubjectLists(List<IWeightedSubject> weightedSubjects) {
		allSubjectsOfTheSelectedDepartment = weightedSubjects;
		availableSubjects = new ArrayList<>(weightedSubjects);
		for (IWeightedSubject pickedSubject : pickedSubjects) {
			for (IWeightedSubject weightedSubject : allSubjectsOfTheSelectedDepartment ){
				if (pickedSubject.isSameSubject(weightedSubject)) {
					availableSubjects.remove(pickedSubject);
				}
			}
		}
		enrollStudentView.updateSubjectChoices();
	}

	@Override
	public List<IWeightedSubject> getAvailableSubjects() {
		return availableSubjects;
	}

	@Override
	public List<IWeightedSubject> getPickedSubjects() {
		return pickedSubjects;
	}

	@Override
	public void departmentHasBeenSelected() {
		selectedDepartment = enrollStudentView.getSelectedSourceDepartment();
		selectedDepartment.publishItHasBeenSelected();
		
	}
	@Override
	public void emptySubjectLists() {
		availableSubjects = new ArrayList<>();
		pickedSubjects = new ArrayList<>();
		enrollStudentView.updateSubjectChoices();
	}

	@Override
	public void setSubjectListsExamAdmission(List<IWeightedSubject> weightedSubjects) {
		PickedDepartmentExamAdmission pickedDepartment = (PickedDepartmentExamAdmission) enrollStudentView.getSelectedDestinationDepartment();
		pickedSubjects = pickedDepartment.getSubjects();
		setSubjectLists(weightedSubjects);
		
	}

	@Override
	public void setSubjectListsCombinedAdmission(List<IWeightedSubject> weightedSubjects) {
		PickedDepartmentCombinedAdmission pickedDepartment = (PickedDepartmentCombinedAdmission) enrollStudentView.getSelectedDestinationDepartment();
		pickedSubjects = pickedDepartment.getSubjects();
		setSubjectLists(weightedSubjects);
		
	}

	@Override
	public void reinitializeDepartmentLists() {
		this.pickedDepartments = new ArrayList<>();
		this.sourceDepartments = new ArrayList<>(departmentRepository.getDepartments());
	}

	@Override
	public void reinitializePickedSubjects() {
		allPickedSubjects = new ArrayList<>();
		
	}

	@Override
	public void noPickedDepartmentSelected() {
		enrollStudentView.noPickedDepartmentSelected();
		
	}

	@Override
	public List<IStudent> getAssignedStudents() {
		return studentRepository.getAssignedStudents();
	}	

	
}
