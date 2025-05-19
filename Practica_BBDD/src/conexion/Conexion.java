package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Alumno;

public class Conexion {

    // Datos de conexión a Oracle
    private static String bd       = "xe";
    private static String login    = "C##COLEGIO";
    private static String password = "password";
    private static String url      = "jdbc:oracle:thin:@localhost:1521:" + bd;

    // Objetos compartidos para consultas
    static Connection connection = null;
    private static Statement st;
    private static ResultSet rs;

    /** 
     * Establece conexión con la base de datos Oracle.
     */
    public void conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");                      // Carga driver JDBC
            connection = DriverManager.getConnection(url, login, password);       // Intenta conectar
            if (connection != null) System.out.println("Conexión realizada con éxito\n");
            else                  System.out.println("Conexión fallida\n");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC no encontrado\n");
        } catch (SQLException e) {
            System.out.println("Credenciales o URL incorrectos\n");
        }
    }

    /**
     * Cierra ResultSet, Statement y Connection si están abiertos.
     */
    public void cerrar() {
        if (rs != null) {
            try {
                rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión\n");
            }
        }
        System.out.println("Conexión cerrada\n");
    }

    /**
     * Recupera e imprime por consola todos los alumnos ordenados por ID descendente.
     */
    public void ejecutarConsulta() {
        String query = "SELECT ID, nombre, EDAD, NOTA FROM alumnos ORDER BY id DESC";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            // Cabecera de tabla
            System.out.println("+------------+---------------+---------------+---------------+");
            System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n",
                              "Id", "Nombre", "Edad", "Nota");
            System.out.println("+------------+---------------+---------------+---------------+");

            // Recorre cada fila y la imprime formateada
            while (rs.next()) {
                System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n",
                                  rs.getInt("ID"),
                                  rs.getString("nombre"),
                                  rs.getInt("EDAD"),
                                  rs.getDouble("NOTA"));
                System.out.println("+------------+---------------+---------------+---------------+");
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error SQL al ejecutar consulta\n");
        }
    }

    /**
     * Inserta un nuevo alumno usando PreparedStatement para evitar inyección.
     */
    public void insertarAlumno(Alumno alumno) {
        String query = "INSERT INTO alumnos (nombre, edad, nota) VALUES (?,?,?)";
        try (Connection cn = DriverManager.getConnection(url, login, password);
             PreparedStatement pstm = cn.prepareStatement(query)) {

            // Asigna valores a los parámetros de la consulta
            pstm.setString(1, alumno.getNombre());
            pstm.setInt(2, alumno.getEdad());
            pstm.setDouble(3, alumno.getNota());
            pstm.executeUpdate();

            // Muestra el alumno insertado
            System.out.println("+------------+---------------+---------------+---------------+");
            System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n",
                              "Id", "Nombre", "Edad", "Nota");
            System.out.println("+------------+---------------+---------------+---------------+");
            System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n",
                              alumno.getId(),
                              alumno.getNombre(),
                              alumno.getEdad(),
                              alumno.getNota());
            System.out.println("+------------+---------------+---------------+---------------+\n");
        } catch (SQLException e) {
            System.out.println("Error SQL al insertar alumno\n");
        }
    }

    /**
     * Actualiza nombre, edad y nota de un alumno identificado por id.
     */
    public void actualizarAlumno(int id, String nombre, int edad, double nota) {
        String query = "UPDATE alumnos SET nombre=?, edad=?, nota=? WHERE id=?";
        try (Connection cn = DriverManager.getConnection(url, login, password);
             PreparedStatement pstm = cn.prepareStatement(query)) {

            // Rellena parámetros y ejecuta
            pstm.setString(1, nombre);
            pstm.setInt(2, edad);
            pstm.setDouble(3, nota);
            pstm.setInt(4, id);
            pstm.executeUpdate();

            // Muestra el registro actualizado
            System.out.println("+------------+---------------+---------------+---------------+");
            System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n",
                              "Id", "Nombre", "Edad", "Nota");
            System.out.println("+------------+---------------+---------------+---------------+");
            System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n",
                              id, nombre, edad, nota);
            System.out.println("+------------+---------------+---------------+---------------+\n");
        } catch (SQLException e) {
            System.out.println("Error SQL al actualizar alumno\n");
        }
    }

    /**
     * Elimina de la tabla el alumno con el id dado.
     */
    public void borrarAlumno(int id) {
        String query = "DELETE FROM alumnos WHERE id=?";
        try (Connection cn = DriverManager.getConnection(url, login, password);
             PreparedStatement pstmt = cn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Alumno borrado con éxito\n");
        } catch (SQLException e) {
            System.out.println("Error SQL al borrar alumno\n");
        }
    }

    /**
     * Lista alumnos con nota >= n_superior.
     */
    public void listarAlumnosSuperior(Double n_superior) {
        String query = "SELECT id, nombre, EDAD, NOTA FROM alumnos";
        ArrayList<Alumno> lista = new ArrayList<>();

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            // Carga todos los alumnos en lista
            while (rs.next()) {
                lista.add(new Alumno(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("EDAD"),
                    rs.getDouble("NOTA")
                ));
            }

            // Cabecera de la tabla
            System.out.println("+------------+---------------+---------------+---------------+");
            System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n",
                              "Id", "Nombre", "Edad", "Nota");
            System.out.println("+------------+---------------+---------------+---------------+");

            // Filtra e imprime aquellos con nota >= n_superior
            for (Alumno alu : lista) {
                if (alu.getNota() >= n_superior) {
                    System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n",
                                      alu.getId(), alu.getNombre(),
                                      alu.getEdad(), alu.getNota());
                    System.out.println("+------------+---------------+---------------+---------------+");
                }
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar alumnos superiores\n");
        }
    }

    /**
     * Lista alumnos cuya edad coincide con el parámetro.
     */
    public void listarAlumnosEdad(int edad) {
        String query = "SELECT id, nombre, edad, nota FROM alumnos WHERE edad=?";
        try (Connection cn = DriverManager.getConnection(url, login, password);
             PreparedStatement pstm = cn.prepareStatement(query)) {

            pstm.setInt(1, edad);
            rs = pstm.executeQuery();

            // Cabecera de la tabla
            System.out.println("+------------+---------------+---------------+---------------+");
            System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n",
                              "Id", "Nombre", "Edad", "Nota");
            System.out.println("+------------+---------------+---------------+---------------+");

            // Imprime cada fila resultante
            while (rs.next()) {
                System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n",
                                  rs.getInt("id"),
                                  rs.getString("nombre"),
                                  rs.getInt("edad"),
                                  rs.getDouble("nota"));
                System.out.println("+------------+---------------+---------------+---------------+");
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar alumnos por edad\n");
        }
    }

    /**
     * Muestra los 3 alumnos con mayor nota, de mayor a menor.
     */
    public void mostrarAlumnosMejorNota() {
        String query = "SELECT nombre, edad, nota FROM alumnos " +
                       "ORDER BY nota DESC FETCH FIRST 3 ROWS ONLY";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            // Cabecera simplificada (sin ID)
            System.out.println("+---------------+---------------+---------------+");
            System.out.printf("| %-13s | %-13s | %-13s |\n",
                              "Nombre", "Edad", "Nota");
            System.out.println("+---------------+---------------+---------------+");

            while (rs.next()) {
                System.out.printf("| %-13s | %-13d | %-13.2f |\n",
                                  rs.getString("nombre"),
                                  rs.getInt("edad"),
                                  rs.getDouble("nota"));
                System.out.println("+---------------+---------------+---------------+");
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error SQL al mostrar mejores notas\n");
        }
    }

    /**
     * Calcula e imprime nota media, nº de aprobados y nº de suspensos.
     */
    public void mostrarEstadisticasgenerales() {
        String query = "SELECT nombre, EDAD, NOTA FROM alumnos";
        ArrayList<Alumno> lista = new ArrayList<>();
        int aprobados = 0, suspensos = 0;
        double sumaNotas = 0;

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            // Carga en lista
            while (rs.next()) {
                lista.add(new Alumno(
                    rs.getString("nombre"),
                    rs.getInt("EDAD"),
                    rs.getDouble("NOTA")
                ));
            }

            // Calcula totales
            for (Alumno a : lista) {
                sumaNotas += a.getNota();
                if (a.getNota() > 5) aprobados++;
                else suspensos++;
            }
            double notaMedia = sumaNotas / lista.size();

            // Cabecera de estadísticas
            System.out.println("+------------+---------------------+---------------------+");
            System.out.printf("| %-10s | %-19s | %-19s |\n",
                              "Nota media", "Nº aprobados", "Nº suspensos");
            System.out.println("+------------+---------------------+---------------------+");
            System.out.printf("| %-10.2f | %-19d | %-19d |\n",
                              notaMedia, aprobados, suspensos);
            System.out.println("+------------+---------------------+---------------------+\n");

        } catch (SQLException e) {
            System.out.println("Error SQL al mostrar estadísticas generales\n");
        }
    }
}
