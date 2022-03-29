package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.ProveedoresInterface;
import model.Proveedores;
import utils.MySQLConexion8;

public class GestionProveedores implements ProveedoresInterface{

	public int registrar(Proveedores p) {
		 int rs = 0; 
			
		 Connection con = null;
		
		PreparedStatement pst = null;
		try {
		   con = MySQLConexion8.getConexion(); 			  
		   String sql = "insert into tb_proveedores values (?,?,?,?,?,?)";			      
		   pst = con.prepareStatement(sql);
		 
		   pst.setInt(1, p.getCodigo());
		   pst.setString(2, p.getNombre());
		   pst.setString(3, p.getTelefono());
		   pst.setString(4, p.getDireccion());
		   pst.setString(5, p.getCorreo());
		   pst.setString(6, p.getDescripcion());
		   			   		   
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
	public int eliminar(int codigo) {
		int rs = 0;			
		Connection con = null; 
		PreparedStatement pst = null;
		
		try {			
			con = MySQLConexion8.getConexion();			
			String sql = "delete from tb_proveedores where cod_prov = ?";			
			pst=con.prepareStatement(sql);
			pst.setInt(1, codigo);			
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
	public int actualizar(Proveedores p) {
		// TODO Auto-generated method stub
		int rs=0;
		Connection con = null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql="update tb_proveedores set nombre_pro=?, telefono=?,direccion=?,correo=?,descripcion=? where cod_prov=?";
			pst= con.prepareStatement(sql);
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getTelefono());
			pst.setString(3, p.getDireccion());
			pst.setString(4, p.getCorreo());
			pst.setString(5, p.getDescripcion());
			pst.setInt(6, p.getCodigo());
			rs=pst.executeUpdate();

		}catch(Exception e) {
			System.out.println("Error en la sentencia..."+ e);
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
	public ArrayList<Proveedores> listado() {
	// TODO Auto-generated method stub
		ArrayList<Proveedores>lista=null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
			try {
				con = MySQLConexion8.getConexion(); 
				String sql = "select * from tb_proveedores";
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery(); 
				lista =new ArrayList<Proveedores>();
				while (rs.next()) {
				    Proveedores t= new Proveedores (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
					lista.add(t);
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
	public Proveedores buscar(int codigo) {
		Proveedores p = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; 
		try {
			   con = MySQLConexion8.getConexion(); 
			   String sql = "select * from tb_proveedores where cod_prov = ?";
			   		   
			   pst = con.prepareStatement(sql);				  
			   pst.setInt(1, codigo);				   		   
			   rs = pst.executeQuery(); 
			   if(rs.next()){					 
				  p = new Proveedores(rs.getInt(1), rs.getString(2), rs.getString(3), 
						  rs.getString(4), rs.getString(5), rs.getString(6));							  			
			    }			   

			} catch (Exception e) {
			   System.out.println("Error en Listado " + e.getMessage());
			} finally {
			  try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
			}			
		
		return p;
	}

	@Override
	public ArrayList<Proveedores> listadoxnombre(String nombre) {
		ArrayList<Proveedores> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "{call usp_consultanombreproveedor(?)}";
		   		   
		   pst = con.prepareStatement(sql);
		   pst.setString(1, nombre);
		   rs = pst.executeQuery(); 
		   lista =new ArrayList<Proveedores>();
		   while (rs.next()) {
			   Proveedores pr= new Proveedores (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			   lista.add(pr);
		   }
		   
		} catch (Exception e) {
		   System.out.println("Error en listadoxnomProveedores " + e.getMessage());
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
	public String GenerarCodigo() {
		// TODO Auto-generated method stub
		String codigo="";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con = MySQLConexion8.getConexion(); 
			String sql= "select max(cod_prov) from tb_proveedores";
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
		return codigo;
	}

}
