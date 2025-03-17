package boletin5_Ejercicio6;

public class Main {
	public static void main(String[] args) {
		Gato g = new Gato("Pepe", 2);
		
		System.out.println(g.Imprimir());
		
		g.setNombre("h");
		System.out.println(g.Imprimir());
		
		g.setEdad(-1);
	}
}
