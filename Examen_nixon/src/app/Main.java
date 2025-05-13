package app;

<<<<<<< HEAD
=======
import java.io.File;
import java.io.IOException;

>>>>>>> 152e0a7dba66cd4e533346e84df457d4270d8e6e
import controlador.Controlador;
import modelo.Equipo;
import vista.Vista;

public class Main {
	public static void main(String[] args) {
<<<<<<< HEAD

		Equipo modelo = new Equipo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista, modelo);
=======
		File file = new File("equipos.txt");
		File file2 = new File("equipos.dat");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			file2.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Equipo modelo = new Equipo();
		Vista vista = new Vista();
		new Controlador(vista, modelo);
>>>>>>> 152e0a7dba66cd4e533346e84df457d4270d8e6e
		vista.setVisible(true);
	}
}
