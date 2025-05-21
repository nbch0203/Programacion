package vista;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton mostrar_alumnos, mostrar_alumnos_nota;
	private JTextField Nombre;

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
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 864, 100);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 24, 46, 14);
		panel.add(lblNewLabel);

		Nombre = new JTextField();
		Nombre.setBounds(66, 21, 190, 20);
		panel.add(Nombre);
		Nombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Edad:");
		lblNewLabel_1.setBounds(10, 60, 46, 14);
		panel.add(lblNewLabel_1);

		JComboBox<Integer> comboEdad = new JComboBox<>();
		comboEdad.addItem(null);
		for (int i = 1; i <= 30; i++) {
			comboEdad.addItem(i);
		}
		comboEdad.setBounds(66, 56, 190, 22);
		panel.add(comboEdad);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 138, 864, 171);
		contentPane.add(panel_1);
		
		JButton btnNewButton = new JButton("Mostrar todos los alumnos");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Añadir un nuevo alumno");
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_1.add(btnNewButton_2);

	}
}
