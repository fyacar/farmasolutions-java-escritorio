package interfaces;

import java.util.ArrayList;

import model.Empleados;
import model.ReportexTipoEmpleado;
import model.Tipos;

public interface EmpleadosInterface {
	
public int registrar(Empleados e);
	// Jean
	public int eliminar(int codigo);
	// Jean
	public int actualizar(Empleados e);
	
	// Para el listado de la tabla Empleados (Javier)
	public ArrayList<Empleados> listado();
	
	//Para buscar los datos de un Empleado (Nayelli)
	public Empleados buscar(int codigo);
	
	// Para buscar Empleados por tipo (Javier)
	public ArrayList<ReportexTipoEmpleado> listadoxTipo(int tipo);
	

	//Para buscar Empleados por un intervalo de fechas de nacimiento (CAT)
	public ArrayList<Empleados> listadoIntervaloxFecha(String fecha, String fecha2);
	
	//Para el combo de tipos (Jean)
	public ArrayList<Tipos> listadoTipos();
	
	//Para la consulta con inner Join
	//public ArrayList<Reporte1> consultaxtipo(int tipo);
	
	//Para validar el acceso (Javier)
	public Empleados validarAcceso(String usuario, String clave);
}
