package model;

public class Empleados {
	private int codigo, id_tipo	;
	private String nombre, apellido, usuario, clave, fnacim;		
	
	public Empleados(){}
	
	public Empleados(int codigo, int id_tipo, String nombre, String apellido, String usuario, String clave,
			String fnacim) {
		super();
		this.codigo = codigo;
		this.id_tipo = id_tipo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.clave = clave;
		this.fnacim = fnacim;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getFnacim() {
		return fnacim;
	}
	public void setFnacim(String fnacim) {
		this.fnacim = fnacim;
	}

}
