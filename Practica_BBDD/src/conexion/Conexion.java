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

	// Variables con los datos de la base de datos

	private static String bd = "xe";
	private static String login = "C##COLEGIO";
	private static String password = "password";

	// Ruta del servidor, nosotros localhost

	private static String url = "jdbc:oracle:thin:@localhost:1521:" + bd;
	static Connection connection = null;
	private static Statement st;
	private static ResultSet rs;

	// Metodo para conectar con la base de datos con la que se
	// realizara todas la operaciones

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
			System.out.println("No se ha podido encontrar la base de datos");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Los datos de la base de datos estan mal introducidos");
		}
	}

	// Metodo para cerrar la conexion con la base de datos

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
				System.out.println("Error en el cierre de la conexion");
			}

		}
		System.out.println("Conexion cerrada");
	}

	// Metodo para mostrar todos los alumnos existentes en la BBDD

	public void ejecutarConsulta() {

		// Variables con los datos a introducir
		int id_alumno, edad_alumno;
		String nombre;
		Double nota_alumno;

		// seteamos la conexion para poder crear la query

		try (Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("select ID,nombre,EDAD,NOTA from alumnos ORDER BY nota desc");) {

			// Imprimir línea superior
			System.out.println("+------------+---------------+---------------+---------------+");

			// Imprimir encabezados
			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");

			while (rs.next()) {

				// Valores a introducir con el bucle por medio
				// de la informacion de la query
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

		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	// Metodo para insertar el alumno que se pasara por medio del escaner

	public void insertarAlumno(Alumno alumno) {

		// Query para insertar los datos
		String query = "INSERT INTO alumnos (nombre,edad,nota) VALUES (?,?,?)";

		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = connection.prepareStatement(query)) {

			// Datos a introducir con sus respectivas columnas

			pstm.setString(1, alumno.getNombre());
			pstm.setInt(2, alumno.getEdad());
			pstm.setDouble(3, alumno.getNota());
			pstm.executeUpdate();

			// Imprimir línea superior
			System.out.println("+------------+---------------+---------------+---------------+");

			// Imprimir encabezados
			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");

			// Línea de separación
			System.out.println("+------------+---------------+---------------+---------------+");

			// Imprimir valores
			System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", alumno.getId(), alumno.getNombre(),
					alumno.getEdad(), alumno.getNota());

			// Línea inferior
			System.out.println("+------------+---------------+---------------+---------------+");

		} catch (SQLException e) {
			System.out.println("Error de sql al intentar insertar un nuevo alumno \n");
		}
	}

	// Metodo para eliminar un alumno de la base de datos

	public void borrarAlumno(int id) {

		// Query de eliminacion del alumno por id

		String query = "DELETE FROM alumnos WHERE id= ?";

		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstmt = connection.prepareStatement(query)) {

			// Eliminamos al alumno por medio del id y actualizamos para
			// salvar los cambios

			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			System.out.println("Alumno borrado con exito");

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error de sql al intentar borrar al alumno \n");
		}
	}

	// Metodo para actualizar la informacion de un alumno

	public void actualizarAlumno(int id, String nombre, int edad, double nota) {

		// Query para actualizar la informacion de un alumno

		String query = ("UPDATE alumnos SET nombre=?,edad=?,nota=? where id=?");

		try (Connection cn = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = cn.prepareStatement(query)) {

			pstm.setInt(1, id);
			pstm.setString(2, nombre);
			pstm.setInt(3, edad);
			pstm.setDouble(4, nota);

			// Imprimir línea superior
			System.out.println("+------------+---------------+---------------+---------------+");

			// Imprimir encabezados
			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");

			// Línea de separación
			System.out.println("+------------+---------------+---------------+---------------+");

			// Imprimir valores
			System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", id, nombre, edad, nota);

			// Línea inferior
			System.out.println("+------------+---------------+---------------+---------------+");

		} catch (SQLException e) {
			System.out.println("Error de sql en la actualizacion del alumno");
		}
	}

	// Metodo para listar a los alumnos con una nota superior al pasado por escaner

	public void listarAlumnosSuperior(Double n_superior) {
		int id, edad_alumno;
		String nombre;
		Double nota_alumno;

		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		try (Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("select id,nombre,EDAD,NOTA from alumnos")) {

			while (rs.next()) {
				id = rs.getInt("id");
				nombre = rs.getString("nombre");
				edad_alumno = rs.getInt("EDAD");
				nota_alumno = rs.getDouble("NOTA");
				Alumno alumno = new Alumno(id, nombre, edad_alumno, nota_alumno);
				lista.add(alumno);
			}

			// Imprimir línea superior
			System.out.println("+------------+---------------+---------------+---------------+");

			// Imprimir encabezados
			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");

			for (Alumno alumno : lista) {
				if (alumno.getNota() >= n_superior) {

					// Línea de separación
					System.out.println("+------------+---------------+---------------+---------------+");

					// Imprimir valores
					System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", alumno.getId(), alumno.getNombre(),
							alumno.getEdad(), alumno.getNota());

					// Línea inferior
					System.out.println("+------------+---------------+---------------+---------------+");

				}
			}

			System.out.println("\n");

		} catch (SQLException e) {
			System.out.println("Error de sql al listar a los alumnos");
		}

	}

	// Metodo para listar a los alumnos por la edad
	// pasada por escaner

	public void listarAlumnosEdad(int edad) {

		// Query para mostrar los datos de los alumnos que cumplan la condicion

		String query = ("Select id,nombre,edad,nota from alumnos where edad=?");

		try (Connection cn = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = cn.prepareStatement(query)) {

			System.out.println(pstm.executeQuery(query));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Metodo para mostrar todas la estadisticas generales de los alumnos

	public void mostrarEstadisticasgenerales() {

		// Variables para almacenar toda la informacion que nos interesa

		int edad_alumno, numero_aprobados = 0, numero_suspensos = 0;
		String nombre;
		Double nota_alumno, nota_media = 0.0;

		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		try (Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("select nombre,EDAD,NOTA from alumnos");) {

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

		} catch (SQLException e) {
			System.out.println("Error de sql al mostrar la estadisticas generales \n");
		}

	}

	public void mostrarAlumnosMejorNota() {
		int edad_alumno;
		String nombre;
		Double nota_alumno;

		ArrayList<Alumno> lista = new ArrayList<Alumno>();

		try (Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("select nombre,EDAD,NOTA from alumnos order by nota desc");) {

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

		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

}
