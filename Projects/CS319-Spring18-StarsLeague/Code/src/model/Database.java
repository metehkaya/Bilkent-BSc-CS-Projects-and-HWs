package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Database {

	private static final String NEW_FILE_NAME = "data/new.txt";
	private static final String CURRENT_FILE_NAME = "data/current.txt";

	public static Tournament newGameLoad() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream( NEW_FILE_NAME );
		ObjectInputStream ois = new ObjectInputStream( file );
		Tournament.setInstance( ((Tournament) ois.readObject()) );
		ois.close();
		return Tournament.getInstance();
	}

	public static Tournament loadCurrentGame() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream( CURRENT_FILE_NAME );
		ObjectInputStream ois = new ObjectInputStream( file );
		Tournament.setInstance( ((Tournament) ois.readObject()) );
		ois.close();
		return Tournament.getInstance();
	}

	public static void saveCurrentGame( Tournament tournament ) throws IOException {
		FileOutputStream file = new FileOutputStream( CURRENT_FILE_NAME );
		ObjectOutputStream oos = new ObjectOutputStream( file );
		oos.writeObject( tournament );
		oos.close();
	}

	public static String getNewFileName() {
		return NEW_FILE_NAME;
	}

	public static String getCurrentFileName() {
		return CURRENT_FILE_NAME;
	}

}
