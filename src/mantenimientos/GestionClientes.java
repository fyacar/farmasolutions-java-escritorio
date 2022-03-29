package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.ClientesInterface;
import model.Clientes;
import model.Empleados;
import utils.MySQLConexion8;

public class GestionClientes implements ClientesInterface{
///////////////////////listado
	@Override
	public ArrayList<Clientes> listado() {
		ArrayList<Clientes> lista = null;
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // sirve para guardar el resultado de la consulta.
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "select * from tb_clientes";
		   		   
		   pst = con.prepareStatement(sql);		   
		   // la cadena tiene interrogaciones? -> En este caso no tiene. Así que se deja así.   
		   		   
		   rs = pst.executeQuery(); // Ejecuta la sentencia y guarda el resultado.
		   
		   // Pasar rs a la variable de retorno.
		   lista = new ArrayList<Clientes>();
		   //mientras haya datos por leer en el rs
		   while(rs.next()){
			   //creamos un objeto de tipo por cada fila leida.
			   Clientes c = new Clientes(rs.getInt(1), rs.getString(2), rs.getString(3), 
					   rs.getString(4), rs.getString(5), rs.getInt(6));			  
			   lista.add(c);
		   }
		   

		} catch (Exception e) {
		   System.out.println("Error en Listado " + e.getMessage());
		} finally {
		  try {
			if (con != null)con.close();
			if(pst !=null) pst.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar : " + e.getMessage());
		}
		}
		
		return lista ;
	
	}
/////////////////////////////////////buscar
	@Override
	public Clientes buscar(int codigo) {
		// TODO Auto-generated method stub
		Clientes c = null;
		// TODO Auto-generated method stub
		Connection con = null;
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			 con = MySQLConexion8.getConexion(); 			  
		   String sql = "select * from  tb_Clientes where cod_cli = ?";			      
		   pst = con.prepareStatement(sql);	
		   
		   pst.setInt(1, codigo);
		   			   		   
		   rs = pst.executeQuery(); 
		   
		   
		   if (rs.next()) {
			   
			   c = new Clientes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
			   
			  
		   }

		} catch (Exception ex) {	 
		   System.out.println("Error en buscar " + ex.getMessage());
		} finally {
		  try {
			con.close();
		} catch (SQLException ex) {
			System.out.println("Error al cerrar : " + ex.getMessage());
		}
		}
		return c;
	}

	@Override
	public Empleados validarAcceso(String usuario, String clave) {
		// TODO Auto-generated method stub
		return null;
	}
///////////////////////////eliminar
	@Override
	public int eliminar(int codigo) {
		// TODO Auto-generated method stub
		int rs = 0;
		 Connection con = null;
			
			PreparedStatement pst = null;
			try {
				 con = MySQLConexion8.getConexion(); 			  
			   String sql = "delete from tb_clientes where cod_cli =?";			      
			   pst = con.prepareStatement(sql);
			 
			  pst.setInt(1, codigo);

			
			   			   		   
			   rs = pst.executeUpdate(); 

			} catch (Exception ex) {
			   System.out.println("Error en eliminar " + ex.getMessage());
			} finally {
			  try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (SQLException ex) {
				System.out.println("Error al cerrar : " + ex.getMessage());
			}
			}
		
		return rs;
	}
///////////////////////actualizar
	@Override
	public int actualizar(Clientes c) {
		// TODO Auto-generated method stub
		int rs = 0;
		Connection con = null;
		
		PreparedStatement pst = null;
		try {
			 con = MySQLConexion8.getConexion(); 			  
		   String sql = "update from tb_Clientes nombre=?, apellido=?, dni=?,  clave= ?, puntos=? where cod_cli= ? ";			      
		   pst = con.prepareStatement(sql);
		   pst.setString(1, c.getNombre());
		   pst.setString(2, c.getApellido());
		   pst.setString(3, c.getDni());
		   pst.setString(4, c.getClave());
		   pst.setInt(5, c.getPuntos());
		   pst.setInt(6, c.getCodCli());
		   rs = pst.executeUpdate(); 

		} catch (Exception ex) {
		   System.out.println("Error en actualizacion " + ex.getMessage());
		} finally {
		  try {
			  con.close();
		} catch (SQLException ex) {
			System.out.println("Error al cerrar : " + ex.getMessage());
		}
		}
		return rs;
	}

	


	@Override
	public ArrayList<Clientes> buscarXNombreCompleto(String nombreCompleto) {
		
		ArrayList<Clientes> lista = null;		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // sirve para guardar el resultado de la consulta.
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "select * from tb_clientes where concat( nombre, ' ', apellido) like ?";
		   		   
		   pst = con.prepareStatement(sql);		   
		     
		   pst.setString(1, nombreCompleto + "%");
		   		   
		   rs = pst.executeQuery(); // Ejecuta la sentencia y guarda el resultado.
		   
		   // Pasar rs a la variable de retorno.
		   lista = new ArrayList<Clientes>();
		   //mientras haya datos por leer en el rs
		   while(rs.next()){
			   //creamos un objeto de tipo por cada fila leida.
			   Clientes c = new Clientes(rs.getInt(1), rs.getString(2), rs.getString(3), 
					   rs.getString(4), rs.getString(5), rs.getInt(6));			  
			   lista.add(c);
		   }
		   

		} catch (Exception e) {
		   System.out.println("Error en Listado Por nombre Completo" + e.getMessage());
		} finally {
		  try {
			if (con != null)con.close();
			if(pst !=null) pst.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar : " + e.getMessage());
		}
		}
		
		return lista ;
	}
////////////////////////registrar
	@Override
	public int registrar(Clientes c) {
		// TODO Auto-generated method stub
		int rs = 0; 
		
		 Connection con = null;
		
		PreparedStatement pst = null;
		try {
		   con = MySQLConexion8.getConexion(); 			  
		   String sql = "insert into tb_clientes values (?,?,?,?,?,?)";			      
		   pst = con.prepareStatement(sql);
		   pst.setInt(1, c.getCodCli());
		   pst.setString(2, c.getNombre());
		   pst.setString(3, c.getApellido());
		   pst.setString(4, c.getDni());
		   pst.setString(5, c.getClave());
		   pst.setInt(6, c.getPuntos());

		   rs = pst.executeUpdate(); 

		} catch (Exception ex) {
		   System.out.println("Error en registrar " + ex.getMessage());
		} finally {
		  try {
			if(con != null) con.close();
			if(pst != null) pst.close();
		} catch (SQLException ex) {
			System.out.println("Error al cerrar : " + ex.getMessage());
		}
		}
		
		return rs; 
	}
@Override
public String GenerarCodigo() {
	// TODO Auto-generated method stub
	String codigo="";
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	try {
		con = MySQLConexion8.getConexion(); 
		String sql= "select max(cod_cli) from tb_clientes";
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery(); 
		if (rs.next()) {
			codigo= String.format("%05d", rs.getInt(1)+1);
		}
	}catch (Exception e) {
		   System.out.println("Error al generar codigo " + e.getMessage());
		} finally {
		  try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar base de datos : " + e.getMessage());
		}
		}
	return codigo;}

	
}
