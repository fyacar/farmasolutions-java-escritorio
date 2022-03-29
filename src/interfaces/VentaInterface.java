package interfaces;

import java.util.ArrayList;

import model.CabeceraBoleta;
import model.DetalleBoleta;
import model.VentaInforme;


public interface VentaInterface {

	public String generaNumBoleta();		
	public int realizarVenta(CabeceraBoleta cab, ArrayList<DetalleBoleta> det);
	public ArrayList<VentaInforme> listadoxFecha(String fecha);		
	public ArrayList<VentaInforme> listadoIntervaloxFecha(String fecha, String fecha2);
}
