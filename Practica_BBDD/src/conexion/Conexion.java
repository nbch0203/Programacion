package conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.sql.DATE;
import modelo.Alumno;

public class Conexion {

	private static String bd = "xe";
	private static String login = "C##COLEGIO";
	private static String password = "password";

	// Ruta del servidor, nosotros localhost
	private static String url = "jdbc:oracle:thin:@localhost:1521:" + bd;
	static Connection connection = null;
	private static Statement st;
	private static ResultSet rs;

	public void conectar() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, login, password);
			if (connection != null) {
				System.out.println("Conexion realizada con exito");
			} else {
				System.out.println("Conexion fallida");
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void ejecutarConsulta() throws SQLException {
		int id_alumno, edad_alumno;
		String nombre;
		Double nota_alumno;

		st = connection.createStatement();
		rs = st.executeQuery("select ID,nombre,EDAD,NOTA from alumnos ORDER BY nota desc");
	
		// Imprimir línea superior
		System.out.println("+------------+---------------+---------------+---------------+");

		// Imprimir encabezados
		System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "id", "nombre", "edad_alumno", "nota_alumno");

		
		while (rs.next()) {
			id_alumno = rs.getInt("ID");
			nombre = rs.getString("nombre");
			edad_alumno = rs.getInt("EDAD");
			nota_alumno = rs.getDouble("NOTA");

		
			
			// Línea de separación
			System.out.println("+------------+---------------+---------------+---------------+");

			// Imprimir valores
			System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", id_alumno, nombre, edad_alumno, nota_alumno);

			// Línea inferior
			System.out.println("+------------+---------------+---------------+---------------+");

		}
	}

	public void insertarAlumno(Alumno alumno) {
		String query = "INSERT INTO alumnos (nombre,edad_alumno,nota_alumno) VALUES (?,?,?)";
		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = connection.prepareStatement(query)) {
			pstm.setString(2, alumno.getNombre());
			pstm.setInt(3, alumno.getEdad());
			pstm.setDouble(4, alumno.getNota());
			pstm.executeUpdate();
			// Línea de separación
			System.out.println("+------------+---------------+---------------+---------------+");

			// Imprimir valores
			System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", alumno.getId(), alumno.getNombre(), alumno.getEdad(), alumno.getNota());

			// Línea inferior
			System.out.println("+------------+---------------+---------------+---------------+");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cerrar() {
		if (rs != null) {
			try {
				rs.close();
				if (st != null) {
					st.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Conexion cerrada");
	}

	public void borrarAlumno(int id) {

		String query = "DELETE FROM alumnos WHERE id= ?";
		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("Alumno borrado con exito");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void actualizarAlumno(int id, String nombre, int edad, double nota) {
		String query = ("UPDATE alumnos SET nombre=?,edad=?,nota=? where id=?");
		try (Connection cn = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = cn.prepareStatement(query)) {
			pstm.executeUpdate(nombre, 2);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void listarAlumnosSuperior(Double n_superior) throws SQLException {
		int edad_alumno;
		String nombre;
		Double nota_alumno;

		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		st = connection.createStatement();
		rs = st.executeQuery("select nombre,EDAD,NOTA from alumnos");

		while (rs.next()) {
			nombre = rs.getString("nombre");
			edad_alumno = rs.getInt("EDAD");
			nota_alumno = rs.getDouble("NOTA");
			Alumno alumno = new Alumno(nombre, edad_alumno, nota_alumno);
			lista.add(alumno);
		}
		for (Alumno alumno : lista) {
			if (alumno.getNota() >= n_superior) {
				System.out.println(alumno.toString());
			}
		}

	}

	public void listarAlumnosEdad(int edad) {

		String query = ("Select id,nombre,edad,nota from alumnos where edad=?");
		try (Connection cn = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = cn.prepareStatement(query)) {

			System.out.println(pstm.executeQuery(query));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void mostrarEstadisticasgenerales() throws SQLException {
		int edad_alumno, numero_aprobados = 0, numero_suspensos = 0;
		String nombre;
		Double nota_alumno, nota_media = 0.0;

		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		st = connection.createStatement();
		rs = st.executeQuery("select nombre,EDAD,NOTA from alumnos");

		while (rs.next()) {
			nombre = rs.getString("nombre");
			edad_alumno = rs.getInt("EDAD");
			nota_alumno = rs.getDouble("NOTA");
			Alumno alumno = new Alumno(nombre, edad_alumno, nota_alumno);
			lista.add(alumno);
		}

		for (int i = 0; i < lista.size(); i++) {
			nota_media = lista.get(i).getNota() + nota_media;
			if (lista.get(i).getNota() > 5) {
				numero_aprobados++;
			} else {
				numero_suspensos++;
			}
		}
		nota_media = nota_media / lista.size();

		System.out.printf("\n La nota media general es : %.1f", nota_media);
		System.out.println("\n El numero de aprobados es de : " + numero_aprobados
				+ "\n El numero de suspensos es de : " + numero_suspensos + "\n");
	}

	public void mostrarAlumnosMejorNota() throws SQLException {
		int edad_alumno;
		String nombre;
		Double nota_alumno;

		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		st = connection.createStatement();
		rs = st.executeQuery("select nombre,EDAD,NOTA from alumnos order by nota desc");

		while (rs.next()) {
			nombre = rs.getString("nombre");
			edad_alumno = rs.getInt("EDAD");
			nota_alumno = rs.getDouble("NOTA");
			Alumno alumno = new Alumno(nombre, edad_alumno, nota_alumno);
			lista.add(alumno);
		}

		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
			if (i == 2) {

				break;
			}
		}
	}

}
