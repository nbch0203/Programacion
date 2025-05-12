package conexion;

import java.sql.Connection;

public class Conexion {
	private static String bd = "xe";
	private static String login = "C##TALLER";
	private static String password = "password";

	private static String url = "jdbc:oracle:thin:@localhost:1521:" + bd;
	static Connection connection=null;
	private static Statement st;
	private static

}
