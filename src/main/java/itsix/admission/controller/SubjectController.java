package itsix.admission.controller;

import itsix.admission.builders.IRepartitionBuilder;
import itsix.admission.builders.ISubjectBuilder;
import itsix.admission.exceptions.ObjectAlreadyExistingException;
import itsix.admission.model.IRepartition;
import itsix.admission.model.ISubject;
import itsix.admission.repository.IDepartmentRepository;
import itsix.admission.repository.IStudentRepository;
import itsix.admission.repository.ISubjectRepository;
import itsix.admission.validators.IValidator;
import itsix.admission.view.IAdmissionExamGradesView;
import itsix.admission.view.IAdmissionRulesView;

import java.util.List;

import javax.swing.JOptionPane;

public class SubjectController implements ISubjectController {
	private ISubjectRepository subjectRepository;
	private List<ISubject> subjects;
	private ISubject selectedSubject;
	private IAdmissionExamGradesView admissionExamGradesView;
	private IAdmissionRulesView admissionRulesView;
	private ISubjectBuilder subjectBuilder;
	private IRepartition repartition;
	private IRepartitionBuilder repartitionBuilder;
	private IStudentRepository studentRepository;
	private IValidator validator;
	private IDepartmentRepository departmentRepository;
	
	public SubjectController(ISubjectRepository subjectRepository, ISubjectBuilder subjectBuilder, 
			IRepartitionBuilder repartitionBuilder, IStudentRepository studentRepository, IValidator validator,
			IDepartmentRepository departmentRepository) {
		this.subjectRepository = subjectRepository;
		subjects = subjectRepository.getSubjects();
		selectedSubject = subjects.get(0);
		this.subjectBuilder = subjectBuilder;
		this.repartitionBuilder = repartitionBuilder;
		this.studentRepository = studentRepository;
		this.validator = validator;
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<ISubject> getSubjects() {
		return subjects;
	}

	@Override
	public void setSelectedSubject(ISubject subject) {
		selectedSubject = subject;
		
	}

	@Override
	public ISubject getSelectedSubject() {
		return selectedSubject;
	}

	@Override
	public void setAdmissionExamGradesView(IAdmissionExamGradesView admissionExamGradesView) {
		this.admissionExamGradesView = admissionExamGradesView;
		
	}

	@Override
	public void newSubjectSelected() {
		admissionExamGradesView.dataChanged(selectedSubject);
		
	}

	@Override
	public void addSubject() {
		String subjectName = admissionRulesView.getNewSubjectName();
		String validationResult = "";
		validationResult = validator.validate("Subject name", subjectName);
		if (validationResult.equals("")) {
			ISubject subject = subjectBuilder.build(subjectName);
			try {
				subjectRepository.addSubject(subject);
			} catch (ObjectAlreadyExistingException oaee) {
				oaee.showMessage();
			}
		} else {
			JOptionPane.showMessageDialog(null, validationResult);
		}
		
	}

	@Override
	public void setAdmissionRulesView(IAdmissionRulesView admissionRulesView) {
		this.admissionRulesView = admissionRulesView;
		
	}

	@Override
	public void doRepartition() {
		repartition = repartitionBuilder.build(studentRepository.getStudents(), departmentRepository.getDepartments(), studentRepository.getAssignedStudents());
		repartition.doRepartition();
	}

	
	

}
