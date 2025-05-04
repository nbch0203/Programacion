package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton Reservar, Cancelar, Modificar;
	private JCheckBox Castellano, Ingles;
	private JComboBox comboBox;
	private JRadioButton Tarjeta, Efectivo;
	private JTextArea textArea;

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
		setTitle("Reserva de tours");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 566, 144);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Nombre.setBounds(26, 10, 63, 20);
		panel.add(Nombre);

		textField = new JTextField();
		textField.setBounds(89, 13, 125, 19);
		panel.add(textField);
		textField.setColumns(10);

		Castellano = new JCheckBox("Castellano");
		Castellano.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Castellano.setBounds(273, 8, 93, 21);
		panel.add(Castellano);

		Ingles = new JCheckBox("Inglés");
		Ingles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Ingles.setBounds(414, 10, 93, 21);
		panel.add(Ingles);

		JLabel lblNewLabel = new JLabel("Elige un servicio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(26, 40, 119, 13);
		panel.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "", "Free Tour", "Leyendas nocturnas", "Leyendas vivientes", "Tour express" }));
		comboBox.setBounds(26, 64, 188, 21);
		panel.add(comboBox);

		JPanel forma_pago = new JPanel();
		forma_pago.setName("Forma de pago");
		forma_pago.setBounds(273, 35, 203, 78);
		forma_pago.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.add(forma_pago);

		JLabel tituloFormaPago = new JLabel("Forma de pago");
		tituloFormaPago.setFont(new Font("Arial", Font.BOLD, 16));
		forma_pago.add(tituloFormaPago);

		Tarjeta = new JRadioButton("Tarjeta");
		Tarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		forma_pago.add(Tarjeta);

		JRadioButton Efectivo = new JRadioButton("Efectivo");
		Efectivo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		forma_pago.add(Efectivo);

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(10, 164, 566, 69);
		contentPane.add(panelBotones);

		Reservar = new JButton("Reservar");
		Reservar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelBotones.add(Reservar);

		Cancelar = new JButton("Cancelar");
		Cancelar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelBotones.add(Cancelar);

		Modificar = new JButton("Modificar");
		Modificar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Modificar.setForeground(Color.BLACK);
		panelBotones.add(Modificar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 243, 566, 210);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setText("Pulsa un botón para ver resultados");
		textArea.setBackground(Color.PINK);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getReservar() {
		return Reservar;
	}

	public void setReservar(JButton reservar) {
		Reservar = reservar;
	}

	public JButton getCancelar() {
		return Cancelar;
	}

	public void setCancelar(JButton cancelar) {
		Cancelar = cancelar;
	}

	public JButton getModificar() {
		return Modificar;
	}

	public void setModificar(JButton modificar) {
		Modificar = modificar;
	}

	public JCheckBox getCastellano() {
		return Castellano;
	}

	public void setCastellano(JCheckBox castellano) {
		Castellano = castellano;
	}

	public JCheckBox getIngles() {
		return Ingles;
	}

	public void setIngles(JCheckBox ingles) {
		Ingles = ingles;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JRadioButton getTarjeta() {
		return Tarjeta;
	}

	public void setTarjeta(JRadioButton tarjeta) {
		Tarjeta = tarjeta;
	}

	public JRadioButton getEfectivo() {
		return Efectivo;
	}

	public void setEfectivo(JRadioButton efectivo) {
		Efectivo = efectivo;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String string) {
		this.textArea.setText(string);
	}
}
