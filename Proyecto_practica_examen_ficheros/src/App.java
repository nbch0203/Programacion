import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("1- fichero txt \n 2- fichero obj \n 3- leer fichero txt \n 4- leer fich obj");
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1: {
				System.out.println("nombre");
				String nombre= sc.next();
				System.out.println("Ape");
				String ape=sc.next();
				System.out.println("Dni");
				String dni=sc.next();
				System.out.println("cuantas notas");
				int cantidad=sc.nextInt();
				ArrayList<Double> notas= new ArrayList<Double>();
				for (int i = 0; i< cantidad; i++) {
					System.out.println("nota num:"+i+1);
					Double nota
				}
				break;
			}
			case 2: {

				break;
			}
			case 3: {

				break;
			}
			case 4: {

				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + key);
			}
		}
	}

}
