package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.ProductoInterface;
import model.Categorias;
import model.Productos;
import model.Proveedores;
import model.ReportexTipoProducto;
import utils.MySQLConexion8;

public class GestionProductos implements ProductoInterface{

	@Override
	public int registrar(Productos p) {
		int rs = 0;		
				
		Connection con = null; //sirve para obtener  la conexipon la bd
		PreparedStatement pst = null;
		
		try {
			
			con = MySQLConexion8.getConexion();			
			String sql = "insert into tb_productos values (?,?,?,?,?,?,?,?)";
			pst=con.prepareStatement(sql);			
			pst.setString(1, p.getIdprod());
			pst.setString(2, p.getDescripcion());
			pst.setInt(3, p.getStock());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5,p.getIdTipo());
			pst.setInt(6, p.getCodProvedor());
			pst.setString(7, p.getFchaRegistro());
			pst.setString(8, p.getFchaVencimiento());
			//4.- Ejecutar la sentencia, guardando el resultado de la ejecución
			rs = pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error al registrar Producto " +e.getMessage());
		} finally{
			try {				
				if(con!=null)con.close();
				if(pst!=null)pst.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar" +e.getMessage());
			}
		}
		
		//fin de plantilla
		return rs;

	}

	@Override
	public int actualizar(Productos p) {
		int rs=0;
		Connection con = null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql="update tb_productos set descripcion=?, stock=?, precio=?,idtipo=?,cod_prov=?,fcha_registro=?, "
					+ "fcha_vencimiento=? where idprod=?";
			pst= con.prepareStatement(sql);
			 pst.setString(8, p.getIdprod());
			   pst.setString(1, p.getDescripcion());
			   pst.setInt(2, p.getStock());
			   pst.setDouble(3, p.getPrecio());
			   pst.setInt(4, p.getIdTipo());
			   pst.setInt(5, p.getCodProvedor());
			   pst.setString(6, p.getFchaRegistro());
			   pst.setString(7, p.getFchaVencimiento());
			rs=pst.executeUpdate();

		}catch(Exception e) {
			System.out.println("Error al actualizar Producto"+ e);
		}finally {
			try {
				if(pst!= null)
					pst.close();
				if(con!=null)
					con.close();
			}catch(Exception e){
				System.out.println("Error al cerrar ");
			}
		}
		return rs;
	}
	
	
	@Override
	public int eliminar(String codigo) {
		int rs = 0;			
		Connection con = null; 
		PreparedStatement pst = null;
		
		try {			
			con = MySQLConexion8.getConexion();			
			String sql = "delete from tb_productos where idprod = ?";			
			pst=con.prepareStatement(sql);
			pst.setString(1, codigo);			
			rs = pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error al Eliminar" +e.getMessage());
		} finally{
			try {	
				if(con!=null)con.close();
				if(pst!=null)pst.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar" +e.getMessage());
			}
		}	
		
		return rs;

	}


	@Override
	public ArrayList<Productos> listado() {
		ArrayList<Productos> lista = null;
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // sirve para guardar el resultado de la consulta.
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "select * from tb_productos";
		   		   
		   pst = con.prepareStatement(sql);		   
		   		   		   
		   rs = pst.executeQuery();
		   
		  
		   lista = new ArrayList<Productos>();
		   //mientras haya datos por leer en el rs
		   while(rs.next()){
			   //creamos un objeto de tipo por cada fila leida.
			   Productos p = new Productos(rs.getString(1), rs.getString(2), 
					   rs.getString(7), rs.getString(8), rs.getInt(6), rs.getInt(3), rs.getInt(5), rs.getDouble(4));
					  
			   lista.add(p);
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

	
	@Override
	public ArrayList<Categorias> listadoCateogoriasCombo() {
		ArrayList<Categorias> lista = null;	
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
		   con = MySQLConexion8.getConexion(); 
		   
		   String sql = "select * from tb_categorias";
		   		   
		   pst = con.prepareStatement(sql);		
		     		   
		   rs = pst.executeQuery();		   
		   
		   lista = new ArrayList<Categorias>();		   
		   while(rs.next()){
			   Categorias c = new Categorias(rs.getInt(1), rs.getString(2));					   
			   lista.add(c);
		   }		   

		} catch (Exception e) {
		   System.out.println("Error en Consulta Categorias de Producto " + e.getMessage());
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
	public ArrayList<Proveedores> listadoProveedoresCombo() {
		ArrayList<Proveedores> lista = null;	
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
		   con = MySQLConexion8.getConexion(); 
		   
		   String sql = "select cod_prov, nombre_pro from tb_proveedores";
		   		   
		   pst = con.prepareStatement(sql);		
		     		   
		   rs = pst.executeQuery();		   
		   
		   lista = new ArrayList<Proveedores>();		   
		   while(rs.next()){
			   Proveedores p = new Proveedores();
			   p.setCodigo(rs.getInt(1));
			   p.setNombre(rs.getString(2));
					 					   
			   lista.add(p);
		   }		   

		} catch (Exception e) {
		   System.out.println("Error en listado de Proveedores " + e.getMessage());
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
	public String generCodProd() {
	
			String codigo = "P00001";
					
					Connection con = null;
					PreparedStatement pst = null;
					ResultSet rs = null; 
					try {
					   con = MySQLConexion8.getConexion(); 
					   String sql = "select substring(max(idprod),2) from tb_productos";
					   		   
					   pst = con.prepareStatement(sql);		   		   
					   rs = pst.executeQuery(); 	  
					 
					   if(rs.next()){			   
								codigo = String.format("P%05d", rs.getInt(1)+1);
					   }		   

					} catch (Exception e) {
					   System.out.println("Error al generar código de Producto" + e.getMessage());
					} finally {
					  try {
						con.close();
					} catch (SQLException e) {
						System.out.println("Error al cerrar : " + e.getMessage());
					}
					}
					
					
					return codigo;
	}

	
	
///// //////////////////////// JEAN (INCIO) /////////////////////// 	
	@Override
	public ArrayList<Productos> buscarXNombreCompleto(String nombreProducto) {
		ArrayList<Productos> lista = null;		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // sirve para guardar el resultado de la consulta.
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "select * from tb_productos where descripcion like ?";
		   		   
		   pst = con.prepareStatement(sql);		   
		     
		   pst.setString(1, nombreProducto + "%");
		   		   
		   rs = pst.executeQuery(); // Ejecuta la sentencia y guarda el resultado.
		   
		   // Pasar rs a la variable de retorno.
		   lista = new ArrayList<Productos>();
		   //mientras haya datos por leer en el rs
		   while(rs.next()){
			   //creamos un objeto de tipo por cada fila leida.
			   Productos p = new Productos(rs.getString(1), rs.getString(2), rs.getString(7), rs.getString(8), 
					   rs.getInt(3), rs.getInt(5), rs.getInt(6), rs.getDouble(4));					   
					   	  
			   lista.add(p);
		   }
		   

		} catch (Exception e) {
		   System.out.println("Error en Listado de Producto por nombre Completo " + e.getMessage());
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




	
	
///// //////////////////////// JEAN (INCIO) /////////////////////// 
}
