package itsix.admission.repository;

import java.io.Serializable;
import java.util.List;

import itsix.admission.model.AdmissionTypePublisher;
import itsix.admission.model.IAdmissionTypeInnerPublisher;
import itsix.admission.model.IInnerPublisher;

public class MainRepository implements Serializable {
	private IStudentRepository studentRepository;
	private ISubjectRepository subjectRepository;
	private IDepartmentRepository departmentRepository;
	
	public MainRepository(IStudentRepository studentRepository,
			IDepartmentRepository departmentRepository,
			ISubjectRepository subjectRepository) {
		this.studentRepository = studentRepository;
		this.departmentRepository = departmentRepository;
		this.subjectRepository = subjectRepository;
	}
	
	public MainRepository() {
		
	}

	public IStudentRepository getStudentRepository() {
		return studentRepository;
	}

	public ISubjectRepository getSubjectRepository() {
		return subjectRepository;
	}

	public IDepartmentRepository getDepartmentRepository() {
		return departmentRepository;
	}
	
	public void setDepartmentsPublisher(List<AdmissionTypePublisher> publishers) {
		departmentRepository.setPublishers(publishers);
	}
	
	
	
}
