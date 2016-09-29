package itsix.admission.controller;

import itsix.admission.repository.MainRepository;
import itsix.admission.repository.RepositorySerializator;
import itsix.admission.view.IAdmissionExamGradesView;
import itsix.admission.view.IAdmissionRulesView;
import itsix.admission.view.IEnrollStudentView;
import itsix.admission.view.IViewRepartition;

import java.io.IOException;

public class HomeController implements IHomeController {
	private IEnrollStudentView enrollStudentView;
	private IAdmissionExamGradesView admissionExamGradesView;
	private IAdmissionRulesView admissionRulesView;
	private IViewRepartition viewRepartition;
	private RepositorySerializator repositorySerializator;
	private MainRepository mainRepository;
	
	public HomeController(IEnrollStudentView enrollStudentView,
			IAdmissionRulesView admissionRulesView, IViewRepartition viewRepartiton,
			RepositorySerializator repositorySerialization, MainRepository mainRepository) {
		this.enrollStudentView = enrollStudentView;
		this.admissionRulesView = admissionRulesView;
		this.viewRepartition = viewRepartiton;
		this.repositorySerializator = repositorySerialization;
		this.mainRepository = mainRepository;
	}
	

	@Override
	public void goToEnrollStudent() {	
		enrollStudentView.display();
	}

	@Override
	public void goToAdmissionExamGrades() {
		admissionExamGradesView.display();
		
	}

	@Override
	public void goToAddmissionRules() {
		admissionRulesView.display();
		
	}

	@Override
	public void goToRepartition() {
		viewRepartition.display();
		
	}

	@Override
	public void serializeData() {
		try {
			repositorySerializator.serialize(mainRepository);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void setAdmissionExamGradesView(IAdmissionExamGradesView admissionExamGradesView) {
		this.admissionExamGradesView = admissionExamGradesView;
		
	}

	
}
