package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Alumno implements Serializable{
	private String dni;
	private String nombre;
	private String apellidos;
	private int edad;
	private ArrayList<Double> notas = new ArrayList<Double>();
	
	
	public Alumno(String dni, String nombre, String apellidos, int edad, ArrayList<Double> notas) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.notas = notas;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public ArrayList<Double> getNotas() {
		return notas;
	}


	public void setNotas(ArrayList<Double> notas) {
		this.notas = notas;
	}


	@Override
	public String toString() {
		return "Alumno [getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getApellidos()=" + getApellidos()
				+ ", getEdad()=" + getEdad() + ", getNotas()=" + getNotas() + "]";
	}
	
	
	

}
