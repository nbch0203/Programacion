package app;

import java.io.File;
import java.io.IOException;

import controlador.Controlador;
import modelo.Equipo;
import vista.Vista;

public class Main {
	public static void main(String[] args) {
		File file = new File("equipo.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Equipo modelo = new Equipo();
		Vista vista = new Vista();
		new Controlador(vista, modelo);
		vista.setVisible(true);
	}
}
