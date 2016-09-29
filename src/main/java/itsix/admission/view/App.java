package itsix.admission.view;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import itsix.admission.builders.AdmissionTypeBuilder;
import itsix.admission.builders.DepartmentBuilder;
import itsix.admission.builders.GradedSubjectBuilder;
import itsix.admission.builders.PickedDepartmentBuilder;
import itsix.admission.builders.RepartitionBuilder;
import itsix.admission.builders.StudentBuilder;
import itsix.admission.builders.SubjectBuilder;
import itsix.admission.builders.WeightedSubjectBuilder;
import itsix.admission.controller.DepartmentController;
import itsix.admission.controller.HomeController;
import itsix.admission.controller.StudentController;
import itsix.admission.controller.SubjectController;
import itsix.admission.exceptions.ObjectAlreadyExistingException;
import itsix.admission.model.AdmissionTypePublisher;
import itsix.admission.model.BacBasedAdmission;
import itsix.admission.model.Department;
import itsix.admission.model.ExamAdmission;
import itsix.admission.model.GradeCalculator;
import itsix.admission.model.IWeightedSubject;
import itsix.admission.model.Subject;
import itsix.admission.model.WeightedSubject;
import itsix.admission.repository.DepartmentRepository;
import itsix.admission.repository.IDepartmentRepository;
import itsix.admission.repository.IStudentRepository;
import itsix.admission.repository.ISubjectRepository;
import itsix.admission.repository.MainRepository;
import itsix.admission.repository.RepositorySerializator;
import itsix.admission.repository.StudentRepository;
import itsix.admission.repository.SubjectRepository;
import itsix.admission.validators.IValidator;
import itsix.admission.validators.Validator;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		/*
		List<IWeightedSubject> subjectsList = new ArrayList<>();

		Subject math = new Subject("Mathematics");
		Subject info = new Subject("Informatics");
		Subject physics = new Subject("Physics");

		subjectsList.add(new WeightedSubject(math, 50));
		subjectsList.add(new WeightedSubject(info, 50));

		ISubjectRepository subjectRepository = new SubjectRepository();
		try {
			subjectRepository.addSubject(math);
			subjectRepository.addSubject(info);
			subjectRepository.addSubject(physics);
		} catch (ObjectAlreadyExistingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

		ExamAdmission examAdmission1 = new ExamAdmission(new ArrayList<>(subjectsList));
		ExamAdmission examAdmission2 = new ExamAdmission(new ArrayList<>(subjectsList));
		ExamAdmission examAdmission3 = new ExamAdmission(new ArrayList<>(subjectsList));
		ExamAdmission examAdmission4 = new ExamAdmission(new ArrayList<>(subjectsList));

		BacBasedAdmission bacAdmission1 = new BacBasedAdmission();
		BacBasedAdmission bacAdmission2 = new BacBasedAdmission();

		List<IWeightedSubject> emptyList = new ArrayList<>();
		IDepartmentRepository departmentRepository = new DepartmentRepository();
		departmentRepository.addDepartment(new Department("Computers in English", examAdmission1,
				new AdmissionTypePublisher(new ArrayList<ISubscriber>()), 56));
		departmentRepository.addDepartment(new Department("Computers in Romanian", examAdmission2, 
				new AdmissionTypePublisher(new ArrayList<ISubscriber>()), 56));

		departmentRepository.addDepartment(new Department("Applied Electronics", examAdmission3, 
				new AdmissionTypePublisher(new ArrayList<ISubscriber>()), 28));
		departmentRepository.addDepartment(new Department("Automation and Applied Informatics", bacAdmission1, 
				new AdmissionTypePublisher(new ArrayList<ISubscriber>()), 28));
		departmentRepository.addDepartment(new Department("Multimedia Systems Engineering", examAdmission4, 
				new AdmissionTypePublisher(new ArrayList<ISubscriber>()), 28));
		departmentRepository.addDepartment(new Department("Mechatronics and Robotics", bacAdmission2, 
				new AdmissionTypePublisher(new ArrayList<ISubscriber>()), 28));

		IStudentRepository studentRepository = new StudentRepository();

		MainRepository mainRepository = new MainRepository(studentRepository, departmentRepository, subjectRepository);

		RepositorySerializator repositorySerializator = new RepositorySerializator();
		*/
		
		
		// deserialize
		RepositorySerializator repositorySerializator = new RepositorySerializator(); 
		MainRepository mainRepository = null;

		List<AdmissionTypePublisher> publishers = new ArrayList<AdmissionTypePublisher>();

		try { 
			mainRepository = repositorySerializator.deserialize();
			for (int i = 0; i < mainRepository.getDepartmentRepository().getDepartments().size(); i++) {
				publishers.add(new AdmissionTypePublisher(new ArrayList<ISubscriber>()));
			}
			mainRepository.setDepartmentsPublisher(publishers); 
		} catch (ClassNotFoundException e2) { //
			// TODO Auto-generated catch block e2.printStackTrace(); 
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace(); 
		}		
		// end deserialize
		
		
		
		
		IValidator validator = new Validator();
		StudentBuilder studentBuilder = new StudentBuilder();
		DepartmentBuilder departmentBuilder = new DepartmentBuilder();
		WeightedSubjectBuilder weightedSubjectBuilder = new WeightedSubjectBuilder();
		GradedSubjectBuilder gradedSubjectBuilder = new GradedSubjectBuilder();
		PickedDepartmentBuilder pickedDepartmentBuilder = new PickedDepartmentBuilder(new GradeCalculator());
		StudentController studentController = new StudentController(studentBuilder, departmentBuilder,
				pickedDepartmentBuilder, gradedSubjectBuilder, mainRepository.getDepartmentRepository(),
				mainRepository.getStudentRepository(), validator);
		EnrollStudentView enrollStudentView = new EnrollStudentView(studentController);
		studentController.setEnrollStudentView(enrollStudentView);
		enrollStudentView.tuck();

		SubjectBuilder subjectBuilder = new SubjectBuilder();
		RepartitionBuilder repartitionBuilder = new RepartitionBuilder();
		SubjectController subjectController = new SubjectController(mainRepository.getSubjectRepository(),
				subjectBuilder, repartitionBuilder, mainRepository.getStudentRepository(), validator, mainRepository.getDepartmentRepository());

		

		AdmissionTypeBuilder admissionTypeBuilder = new AdmissionTypeBuilder();	
		

		DepartmentController departmentController = new DepartmentController(mainRepository.getDepartmentRepository(),
				admissionTypeBuilder, weightedSubjectBuilder, validator);
		AdmissionRulesView admissionRulesView = new AdmissionRulesView(departmentController, subjectController);
		departmentController.setAdmissionRulesView(admissionRulesView);
		subjectController.setAdmissionRulesView(admissionRulesView);

		AssignedStudentsView assignedStudentsView = new AssignedStudentsView(studentController);
		assignedStudentsView.tuck();
		departmentController.setAssignedStudentView(assignedStudentsView);
		
		ViewRepartition viewRepartition = new ViewRepartition(departmentController);
		departmentController.setViewRepartition(viewRepartition);
		viewRepartition.tuck();
		
		final HomeController homeController = new HomeController(enrollStudentView,
				admissionRulesView, viewRepartition, repositorySerializator, mainRepository);
		
		AdmissionExamGradesView admissionExamGradesView = new AdmissionExamGradesView(subjectController, homeController, validator);
		subjectController.setAdmissionExamGradesView(admissionExamGradesView);
		
		homeController.setAdmissionExamGradesView(admissionExamGradesView);

		admissionExamGradesView.tuck();
		
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView homeView = new HomeView(homeController);
					homeView.display();
					;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
