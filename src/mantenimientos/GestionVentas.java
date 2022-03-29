package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.VentaInterface;
import model.CabeceraBoleta;
import model.DetalleBoleta;
import model.VentaInforme;
import utils.MySQLConexion8;

public class GestionVentas implements VentaInterface {

	@Override
	public String generaNumBoleta() {
String codigo = "B0001";
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; 
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "select substring(max(num_bol),2) from tb_cab_boleta";
		   		   
		   pst = con.prepareStatement(sql);		   		   
		   rs = pst.executeQuery(); 	  
		 
		   if(rs.next()){			   
					codigo = String.format("B%04d", rs.getInt(1)+1);
		   }		   

		} catch (Exception e) {
		   System.out.println("Error al generar número de Boleta" + e.getMessage());
		} finally {
		  try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar : " + e.getMessage());
		}
		}
		
		
		return codigo;
	}

	@Override
	public int realizarVenta(CabeceraBoleta cab, ArrayList<DetalleBoleta> det) {
		int rs = 0;
		
		Connection con = null;				
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		try {					
		   con = MySQLConexion8.getConexion(); 
		   //desactivamos la confirmación automática
		   con.setAutoCommit(false);	
		   
		   // Registramos los datos de la cabecera				   
		   String sql1 = "insert into tb_cab_boleta values (?,?,?,?,?)";
		   							   					   
		   pst1 = con.prepareStatement(sql1);  
		   
		   pst1.setString(1, cab.getNum_bol());
		   pst1.setString(2, cab.getFechaBoleta());
		   pst1.setInt(3, cab.getCodCliente());
		   pst1.setInt(4, cab.getCodVendedor());
		   pst1.setDouble(5, cab.getTotal());
		   
		   rs = pst1.executeUpdate(); 				   
		   
		  	//Registrar los datos del detalle 
		   String sql2 = "insert into tb_det_boleta values (?,?,?,?,?)";
		   for(DetalleBoleta d : det){
			   pst2 = con.prepareStatement(sql2);			
			   pst2.setString(1, cab.getNum_bol()); // El número de boleta se obtiene de la cabecera
			   pst2.setString(2, d.getIdprod());
			   pst2.setInt(3, d.getCantidad());
			   pst2.setDouble(4, d.getPrecioVenta());
			   pst2.setDouble(5, d.getImporte());
			   rs = pst2.executeUpdate(); 
		   }
		   		   				  			   		   

		   // Actualizar los datos del producto				   
		   String sql3 = "update tb_productos set stock = stock - ? where idprod=?";
		   for (DetalleBoleta d : det){
			   pst3 = con.prepareStatement(sql3); 
			  pst3.setInt(1, d.getCantidad());
			  pst3.setString(2, d.getIdprod());
			  rs = pst3.executeUpdate(); 
		   }
		    
		   // Confirmar las transacciones
		   con.commit();	  
		   
		} catch (Exception e) {
		   System.out.println("Error en Realizar Venta " + e.getMessage());
		   rs = 0;
		   try {
			con.rollback();
		} catch (SQLException e1) {
			System.out.println("Error al restaurar " + e.getMessage());
		} 
		   
		} finally {
		  try {
			if(con != null) con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al cerrar : " + e.getMessage());
		}
		}				
return rs;
	}

	@Override
	public ArrayList<VentaInforme> listadoxFecha(String fecha) {
		ArrayList<VentaInforme> lista = null;		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // sirve para guardar el resultado de la consulta.
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "Select * from tb_cab_boleta where fch_bol=?";
		   		   
		   pst = con.prepareStatement(sql);		   
		    pst.setString(1, fecha);
		   		   
		   rs = pst.executeQuery(); // Ejecuta la sentencia y guarda el resultado.
		   
		   // Pasar rs a la variable de retorno.
		   lista = new ArrayList<VentaInforme>();
		   //mientras haya datos por leer en el rs
		   while(rs.next()){
			   //creamos un objeto de tipo por cada fila leida.
			   VentaInforme u =  new VentaInforme(rs.getString(1), rs.getString(2), rs.getInt(3), 
						  rs.getInt(4), rs.getDouble(5)); 	
			lista.add(u);	
		   }		   

		} catch (Exception e) {
		   System.out.println("Error en Listado Tipos " + e.getMessage());
		} finally {
		  try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar : " + e.getMessage());
		}
		}
		
		return lista;
	}

	@Override
	public ArrayList<VentaInforme> listadoIntervaloxFecha(String fecha, String fecha2) {
		ArrayList<VentaInforme> lista = null;		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // sirve para guardar el resultado de la consulta.
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "select * from tb_cab_boleta where fch_bol between ? and ?";
		   		   
		   pst = con.prepareStatement(sql);		   
		    pst.setString(1, fecha);
		    pst.setString(2, fecha2);
		    
		   		   
		   rs = pst.executeQuery(); // Ejecuta la sentencia y guarda el resultado.
		   
		   // Pasar rs a la variable de retorno.
		   lista = new ArrayList<VentaInforme>();
		   //mientras haya datos por leer en el rs
		   while(rs.next()){
			   //creamos un objeto de tipo por cada fila leida.
			   VentaInforme u =  new VentaInforme(rs.getString(1), rs.getString(2), rs.getInt(3), 
						  rs.getInt(4), rs.getDouble(5)); 			  
			  		lista.add(u);	
		   }		   

		} catch (Exception e) {
		   System.out.println("Error en Listado Intervalo por fecha Ventas" + e.getMessage());
		} finally {
		  try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar : " + e.getMessage());
		}
		}
		
		return lista;
	}

}
