package interfaces;

import java.util.ArrayList;

import model.Proveedores;

public interface ProveedoresInterface {
	
public int registrar(Proveedores p);
	
	public int eliminar(int codigo);
	
	public int actualizar(Proveedores p);
	
	
	public ArrayList<Proveedores> listado();

	public Proveedores buscar(int codigo);
	
	public String GenerarCodigo();
	

	public ArrayList<Proveedores> listadoxnombre(String nombre);
}
