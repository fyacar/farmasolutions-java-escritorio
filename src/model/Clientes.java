package model;

public class Clientes {	
	private int codCli, puntos;
	private String nombre, apellido, dni, clave;
		
	public Clientes(int codCli,  String nombre, String apellido, String dni, String clave,int puntos) {
		super();
		this.codCli = codCli;
		this.puntos = puntos;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.clave = clave;
	}
	public Clientes() {
		// TODO Auto-generated constructor stub
	}
	public int getCodCli() {
		return codCli;
	}
	public void setCodCli(int codCli) {
		this.codCli = codCli;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
