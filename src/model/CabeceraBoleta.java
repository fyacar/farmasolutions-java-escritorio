package model;

public class CabeceraBoleta {

	private String num_bol, fechaBoleta; 
	private int codCliente, codVendedor;
	private double total;
	public CabeceraBoleta(String num_bol, String fechaBoleta, int codCliente, int codVendedor, double total) {
		super();
		this.num_bol = num_bol;
		this.fechaBoleta = fechaBoleta;
		this.codCliente = codCliente;
		this.codVendedor = codVendedor;
		this.total = total;
	}
	public String getNum_bol() {
		return num_bol;
	}
	public void setNum_bol(String num_bol) {
		this.num_bol = num_bol;
	}
	public String getFechaBoleta() {
		return fechaBoleta;
	}
	public void setFechaBoleta(String fechaBoleta) {
		this.fechaBoleta = fechaBoleta;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public int getCodVendedor() {
		return codVendedor;
	}
	public void setCodVendedor(int codVendedor) {
		this.codVendedor = codVendedor;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
