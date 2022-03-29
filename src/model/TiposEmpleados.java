package model;

public class TiposEmpleados {
	
	private int id_tipo;
	private String des_tipo;
	
	
		public TiposEmpleados(int id_tipo, String des_tipo) {
		super();
		this.id_tipo = id_tipo;
		this.des_tipo = des_tipo;		
	}
		
		
	public int getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getDes_tipo() {
		return des_tipo;
	}
	public void setDes_tipo(String des_tipo) {
		this.des_tipo = des_tipo;
	}
	
	
	
	
}
