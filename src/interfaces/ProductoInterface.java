package interfaces;

import java.util.ArrayList;

import model.Categorias;
import model.Productos;
import model.Proveedores;



public interface ProductoInterface {

	public int registrar(Productos p);
	
	public int eliminar(String codigo);
	
	public int actualizar(Productos p);	
	
	
	public ArrayList<Categorias> listadoCateogoriasCombo();
	public ArrayList<Proveedores> listadoProveedoresCombo();	
	
	public ArrayList<Productos> listado();
	
	public String generCodProd();	
	
	
	

	///// //////////////////////// JEAN (INCIO) /////////////////////// 
	public ArrayList<Productos> buscarXNombreCompleto(String nombreProducto);
	//// /////////////////////// JEAN (FIN) /////////////////////// 
}
