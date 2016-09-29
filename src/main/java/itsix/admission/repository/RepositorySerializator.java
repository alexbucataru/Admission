package itsix.admission.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RepositorySerializator {
	
	public RepositorySerializator() {
	}
	
	public void serialize(MainRepository mainRepository) throws IOException {
		FileOutputStream fout = new FileOutputStream("repository.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(mainRepository);
		oos.close();
	}
	
	public MainRepository deserialize() throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream("repository.ser");
		ObjectInputStream ois = new ObjectInputStream(fin);
		MainRepository mainRepository = (MainRepository) ois.readObject();
		ois.close();
		return mainRepository;
		
	}
}
