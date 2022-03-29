package model;

public class Categorias {
	
	private int idTipo;
	private String descripcion;
	
	public Categorias(int idTipo, String descripcion) {
		super();
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}	
	
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
