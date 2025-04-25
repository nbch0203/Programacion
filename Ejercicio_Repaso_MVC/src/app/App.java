package app;

import java.io.IOException;
import java.util.ArrayList;

import controlador.Controlador;
import modelo.*;
import vista.Interfaz;

public class App {
	public static void main(String[] args) {
		Interfaz vista = new Interfaz();
		GestorPedidos gp = new GestorPedidos();
		new Controlador(vista, gp);
//		ArrayList<Producto> lista = new ArrayList<Producto>();
//		Producto pro = new Producto("tomate", 11);
//		lista.add(pro);
//		Pedido pedido = new Pedido("Nixon", lista);
//		try {
//			gp.Guardar(pedido);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		vista.setVisible(true);
	}
}
