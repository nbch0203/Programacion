package ejercicio4;

public class Producto {
	String nombre;
	Double precio;
	
	
	public Producto(String nombre, Double precio) {
		
		this.nombre = nombre;
		this.precio = precio;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	
	
}
