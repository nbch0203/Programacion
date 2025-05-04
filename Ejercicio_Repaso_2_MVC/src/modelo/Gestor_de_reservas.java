package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Gestor_de_reservas {
	private Reservas reserva;
	private static File file = new File("reservas.txt");

	public Reservas getReserva() {
		return reserva;
	}

	public void setReserva(Reservas reserva) {
		this.reserva = reserva;
	}

	public String reservar(Reservas reserva) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bfw = new BufferedWriter(fw);

		bfw.write(reserva.toString() + "\n");

		fw.close();
		bfw.close();
		return "La reserva se esta guardando....";
	}

	public static String cancelar(String nombre) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}

		FileReader fr = new FileReader(file);
		BufferedReader bfr = new BufferedReader(fr);
		String linea;
		while ((linea = bfr.readLine()) != null) {
			if (linea.contains(nombre)) {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("-----------------------------");
				fw.close();
				bw.close();
			}
		}
		fr.close();
		bfr.close();

		return "La reserva con el nombre: " + nombre + " ha sido cancelada";
	}

}
