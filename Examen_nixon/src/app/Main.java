package app;

import controlador.Controlador;
import modelo.Equipo;
import vista.Vista;

public class Main {
	public static void main(String[] args) {

		Equipo modelo = new Equipo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista, modelo);
		vista.setVisible(true);
	}
}
