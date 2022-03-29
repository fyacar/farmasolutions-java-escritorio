package interfaces;

import java.util.ArrayList;

import model.Clientes;
import model.Empleados;

public interface ClientesInterface {
	public int eliminar(int codigo);
	public int actualizar(Clientes e);
	public int registrar(Clientes c);
	public String GenerarCodigo();
	
	public ArrayList<Clientes> listado();
	
	public Clientes buscar(int codigo);		//Para  Empleados por un intervalo de fechas de nacimiento (CAT)
	

	
	public Empleados validarAcceso(String usuario, String clave);
	
	///// //////////////////////// JEAN (INCIO) /////////////////////// 
	public ArrayList<Clientes> buscarXNombreCompleto(String nombreCompleto);
	//// /////////////////////// JEAN (FIN) /////////////////////// 
	
}



