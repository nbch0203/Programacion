package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;

import modelo.Gestor_de_reservas;
import modelo.Reservas;
import vista.Interfaz;

public class Controlador {
	private Gestor_de_reservas modelo;
	private Interfaz vista;

	public Controlador(Gestor_de_reservas modelo, Interfaz vista) {
		this.modelo = modelo;
		this.vista = vista;
		iniciartodo();
	}

	public Gestor_de_reservas getModelo() {
		return modelo;
	}

	public void setModelo(Gestor_de_reservas modelo) {
		this.modelo = modelo;
	}

	public Interfaz getVista() {
		return vista;
	}

	public void setVista(Interfaz vista) {
		this.vista = vista;
	}
	
	public void iniciartodo() {
		getVista().getReservar().addActionListener(e -> reservar());
	}

	public void reservar() {
		JButton reservar=getVista().getReservar();
		reservar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Reservas rs = null;
				if (getVista().getCastellano().isSelected()) {
					rs.setIdioma("Castellano");
				}
				if (getVista().getIngles().isSelected()) {
					rs.setIdioma("Ingles");
				}
				if (getVista().getTarjeta().isSelected()) {
					rs.setMetodo_pago("Tarjeta");
				}
				if (getVista().getEfectivo().isSelected()) {
					rs.setMetodo_pago("Efectivo");
				}
				JComboBox<String> comboBox = getVista().getComboBox();
				String opcionseleccionada= (String) comboBox.getSelectedItem();
			
				rs.setTipo(opcionseleccionada);
				
				try {
					getModelo().reservar(rs);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		

	}
}
