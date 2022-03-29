package model;

public class Productos {

	private String idprod, descripcion, fchaRegistro, fchaVencimiento;
	private int stock, idTipo, codProvedor;
	private double precio;
	
	
	public Productos(String idprod, String descripcion, String fchaRegistro, String fchaVencimiento, int stock,
			int idTipo, int codProvedor, double precio) {
		super();
		this.idprod = idprod;
		this.descripcion = descripcion;
		this.fchaRegistro = fchaRegistro;
		this.fchaVencimiento = fchaVencimiento;
		this.stock = stock;
		this.idTipo = idTipo;
		this.codProvedor = codProvedor;
		this.precio = precio;
	}
	
	public String getIdprod() {
		return idprod;
	}
	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFchaRegistro() {
		return fchaRegistro;
	}
	public void setFchaRegistro(String fchaRegistro) {
		this.fchaRegistro = fchaRegistro;
	}
	public String getFchaVencimiento() {
		return fchaVencimiento;
	}
	public void setFchaVencimiento(String fchaVencimiento) {
		this.fchaVencimiento = fchaVencimiento;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public int getCodProvedor() {
		return codProvedor;
	}
	public void setCodProvedor(int codProvedor) {
		this.codProvedor = codProvedor;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	

	
	
}
