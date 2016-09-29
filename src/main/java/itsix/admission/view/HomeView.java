package itsix.admission.view;

import javax.swing.JFrame;

import itsix.admission.controller.IHomeController;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class HomeView extends JFrame implements IHomeView{	
	private JButton enrollStudentBtn;
	private JButton admissionExamGradesBtn;
	private JButton admissionRulesBtn;
	private JButton viewRepartitionBtn;
	private IHomeController homeController;
	
	public HomeView(IHomeController homeController) {
		initialize();
		this.homeController = homeController;
	}
	private void initialize() {
		setBounds(100, 100, 400, 390);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				homeController.serializeData();
			}
		});
		getContentPane().setLayout(new GridLayout(0, 1, 10, 10));
		
		admissionRulesBtn = new JButton("Admission Rules");
		admissionRulesBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				homeController.goToAddmissionRules();
				
			}
			
		});
		getContentPane().add(admissionRulesBtn);
		
		enrollStudentBtn = new JButton("Enroll Student");
		enrollStudentBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				homeController.goToEnrollStudent();
				
			}
			
		});
		getContentPane().add(enrollStudentBtn);
		
		admissionExamGradesBtn = new JButton("Admission Exam Grades");
		admissionExamGradesBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				homeController.goToAdmissionExamGrades();
				
			}
		});
		getContentPane().add(admissionExamGradesBtn);
		
		
		
		viewRepartitionBtn = new JButton("View Repartition");
		viewRepartitionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				homeController.goToRepartition();
				
			}
		});
		getContentPane().add(viewRepartitionBtn);
	}
	@Override
	public void display() {
		setVisible(true);
		
	}
	@Override
	public void tuck() {
		setVisible(false);
		
	}

}
