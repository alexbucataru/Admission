package itsix.admission.exceptions;

import javax.swing.JOptionPane;

public class ObjectAlreadyExistingException extends Exception {
	private String message;
	
	public ObjectAlreadyExistingException(String message) {
		this.message = message;
	}
	
	public void showMessage() {
		JOptionPane.showMessageDialog(null, message);
	}
}
