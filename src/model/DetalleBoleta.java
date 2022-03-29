package model;

public class DetalleBoleta {

	private String num_bol, idprod;
	private int cantidad;
	private double precioVenta, importe;
	public DetalleBoleta(String num_bol, String idprod, int cantidad, double precioVenta, double importe) {
		super();
		this.num_bol = num_bol;
		this.idprod = idprod;
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
		this.importe = importe;
	}
	
	public String getNum_bol() {
		return num_bol;
	}
	public void setNum_bol(String num_bol) {
		this.num_bol = num_bol;
	}
	public String getIdprod() {
		return idprod;
	}
	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	
	
}
