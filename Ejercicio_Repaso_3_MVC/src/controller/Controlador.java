package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.Cliente;
import model.Gesto_clientes;
import view.Interfaz;

public class Controlador {
	private Gesto_clientes modelo;
	private Interfaz vista;

	public Controlador(Gesto_clientes modelo, Interfaz vista) {
		this.modelo = modelo;
		this.vista = vista;
		Iniciarboton();
	}

	public Gesto_clientes getModelo() {
		return modelo;
	}

	public void setModelo(Gesto_clientes modelo) {
		this.modelo = modelo;
	}

	public Interfaz getVista() {
		return vista;
	}

	public void setVista(Interfaz vista) {
		this.vista = vista;
	}

	public void Iniciarboton() {
		getVista().getBoton_enviar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guardarTxt();

			}
		});
	}

	public void guardarTxt() {

		Cliente cl = new Cliente(getVista().getTextField_nombre(), getVista().getTextField_correo());
		try {
			getModelo().guardarTxt(cl.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarObjt() {
		Cliente cl = new Cliente(getVista().getTextField_nombre(), getVista().getTextField_correo());
		
	}
}
