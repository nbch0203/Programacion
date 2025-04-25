package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JCheckBox Hamburguesa, Patatas, CocaCola, Helado;
	private JTextArea textArea;
	private JButton Añadir, Buscar, MostrarTodo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel PanleNombre = new JPanel();
		FlowLayout fl_PanleNombre = (FlowLayout) PanleNombre.getLayout();
		fl_PanleNombre.setHgap(10);
		PanleNombre.setBounds(20, 40, 406, 31);
		contentPane.add(PanleNombre);

		JLabel lblNewLabel = new JLabel("Nombre:");
		PanleNombre.add(lblNewLabel);

		textField = new JTextField();
		PanleNombre.add(textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 94, 406, 31);
		contentPane.add(panel_1);

		Hamburguesa = new JCheckBox("Hamburguesa");
		panel_1.add(Hamburguesa);

		Patatas = new JCheckBox("Patatas");
		panel_1.add(Patatas);

		CocaCola = new JCheckBox("Coca cola");
		panel_1.add(CocaCola);

		Helado = new JCheckBox("Helado");
		panel_1.add(Helado);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(20, 140, 406, 44);
		contentPane.add(panel_2);

		Añadir = new JButton("Añadir");
		panel_2.add(Añadir);

		Buscar = new JButton("Buscar");
		panel_2.add(Buscar);

		MostrarTodo = new JButton("Mostrar todo");
		panel_2.add(MostrarTodo);

		textArea = new JTextArea();
		textArea.setBounds(20, 199, 406, 54);
		contentPane.add(textArea);

	}

	public JCheckBox getHamburguesa() {
		return Hamburguesa;
	}

	public void setHamburguesa(JCheckBox hamburguesa) {
		Hamburguesa = hamburguesa;
	}

	public JCheckBox getPatatas() {
		return Patatas;
	}

	public void setPatatas(JCheckBox patatas) {
		Patatas = patatas;
	}

	public JCheckBox getCocaCola() {
		return CocaCola;
	}

	public void setCocaCola(JCheckBox cocaCola) {
		CocaCola = cocaCola;
	}

	public JCheckBox getHelado() {
		return Helado;
	}

	public void setHelado(JCheckBox helado) {
		Helado = helado;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getAñadir() {
		return Añadir;
	}

	public void setAñadir(JButton añadir) {
		Añadir = añadir;
	}

	public JButton getBuscar() {
		return Buscar;
	}

	public void setBuscar(JButton buscar) {
		Buscar = buscar;
	}

	public JButton getMostrarTodo() {
		return MostrarTodo;
	}

	public void setMostrarTodo(JButton mostrarTodo) {
		MostrarTodo = mostrarTodo;
	}

	public String getTextField() {
		String aux = textField.getText();
		return aux;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

}
