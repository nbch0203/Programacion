package ejercicio_4_plus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Main {

	static File file = new File("alumnos.dat");

	public void escribir(ArrayList<Alumno> lista) throws FileNotFoundException {

		RandomAccessFile fr = new RandomAccessFile(file, "rw");

	}

	public void leer() {
	}

	public static void main(String[] args) {

		ArrayList<Alumno> lista = new ArrayList<Alumno>();

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		lista.add(new Alumno(5, "Antonio"));

	}

}
