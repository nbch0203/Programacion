package controlador;

import vista.Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import modelo.GestorPedidos;
import modelo.Pedido;
import modelo.Producto;

public class Controlador {
	private Interfaz vista;
	private GestorPedidos modelo;

	public Controlador(Interfaz vista, GestorPedidos modelo) {
		this.vista = vista;
		this.modelo = modelo;
		inicioBotones();
	}

	public Interfaz getVista() {
		return vista;
	}

	public void setVista(Interfaz vista) {
		this.vista = vista;
	}

	public GestorPedidos getModelo() {
		return modelo;
	}

	public void setModelo(GestorPedidos modelo) {
		this.modelo = modelo;
	}

	public void inicioBotones() {
		getVista().getAñadir().addActionListener(e -> GuardarInformacion());

//		getVista().getMostrarTodo().addActionListener(e -> mostrarTodo());
	}

	public void GuardarInformacion() {
		JButton btnañadir = getVista().getAñadir();

		btnañadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Producto> lista = new ArrayList<Producto>();

				if (getVista().getHamburguesa().isSelected()) {
					lista.add(new Producto("Hamburguesa", 11.1));
				}
				if (getVista().getHelado().isSelected()) {
					lista.add(new Producto("Helado", 12.2));
				}
				if (getVista().getPatatas().isSelected()) {
					lista.add(new Producto("Patatas", 13.3));
				}
				if (getVista().getCocaCola().isSelected()) {
					lista.add(new Producto("Coca Cola", 1.2));
				}

				if (!lista.isEmpty()) {
					Pedido pedido = new Pedido(getVista().getTextField(), lista);
					try {
						getModelo().Guardar(pedido);
						JOptionPane.showMessageDialog(null, "✅ Pedido guardado en pedidos.txt");
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "❌ Error al guardar: " + ex.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

//	public void mostrarTodo() {
//		JButton btmostrar= getVista().getMostrarTodo();
//		btmostrar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					String texto=getModelo().mostrarTodo();
//					getVista().setTextArea(texto);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				
//			}
//		});
//	}
}
