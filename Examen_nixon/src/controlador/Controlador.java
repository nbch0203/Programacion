package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

import modelo.Equipo;
import modelo.ObjectOutputStreamSinCabecera;
import vista.Vista;

public class Controlador {
	private File file = new File("equipos.txt");
	private File file_obj = new File("equipos.dat");
	private Vista vista;
	private Equipo modelo;

	public Controlador(Vista vista, Equipo modelo) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!file_obj.exists()) {
			try {
				file_obj.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.vista = vista;
		this.modelo = modelo;
		iniciarBotones();
	}

	public Vista getVista() {
		return vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

	public Equipo getModelo() {
		return modelo;
	}

	public void setModelo(Equipo modelo) {
		this.modelo = modelo;
	}

	public void iniciarBotones() {

		getVista().getBtn_obj().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				guardarOBJ();
			}
		});
		getVista().getBtn_mostrar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mostrarOBJ();
			}
		});
		getVista().getBtn_buscar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buscarTXT();
			}
		});
		getVista().getBtn_txt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				guardarTxT();
			}
		});

	}

	public void guardarTxT() {
		Equipo eq;

		String numero = getVista().getNumero_serie();
		String marca = getVista().getMarca();
		String ano_compra = getVista().getAno_compra();
		String tipo = getVista().getTipo();
		boolean ssd = false;
		boolean ram = false;
		boolean gpu = false;
		boolean wifi = false;
		if (getVista().getSsd().isSelected()) {
			ssd = true;
		}
		if (getVista().getRAM().isSelected()) {
			ram = true;
		}
		if (getVista().getGPU_dedicada().isSelected()) {
			gpu = true;
		}
		if (getVista().getWifi().isSelected()) {
			wifi = true;
		}

		try {
			eq = new Equipo(numero, marca, ano_compra, tipo, ssd, ram, gpu, wifi);

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(numero);

			fw.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	public void buscarTXT() {

		try {
			String numero = getVista().getNumero_serie();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String aux = "";
			while ((aux = br.readLine()) != null) {
				if (aux.contains(numero)) {
					getVista().setTextArea(aux);
					br.close();
					fr.close();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane jp = new JOptionPane(null, 0, 0, null, null, "Error en la lectura del fichero");
		}

	}

	public void guardarOBJ() {

		String numero = getVista().getNumero_serie();
		String marca = getVista().getMarca();
		String ano_compra = getVista().getAno_compra();
		String tipo = getVista().getTipo();
		boolean ssd = false;
		boolean ram = false;
		boolean gpu = false;
		boolean wifi = false;
		if (getVista().getSsd().isSelected()) {
			ssd = true;
		}
		if (getVista().getRAM().isSelected()) {
			ram = true;
		}
		if (getVista().getGPU_dedicada().isSelected()) {
			gpu = true;
		}
		if (getVista().getWifi().isSelected()) {
			wifi = true;
		}
		Equipo eq = new Equipo(numero, marca, ano_compra, tipo, ssd, ram, gpu, wifi);

		try {

			FileOutputStream fos = new FileOutputStream(file_obj, true);
			ObjectOutputStreamSinCabecera ois = new ObjectOutputStreamSinCabecera(fos);

			ois.writeObject(eq);
			ois.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	public void mostrarOBJ() {
		try {
			FileInputStream fis = new FileInputStream(file_obj);
			ObjectInputStream oos = new ObjectInputStream(fis);

			while (true) {

				Object eq = oos.readObject();

				getVista().getTextArea().setText(eq.toString());

			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
		}

	}
}
