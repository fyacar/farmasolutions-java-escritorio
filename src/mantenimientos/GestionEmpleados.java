package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.EmpleadosInterface;
import model.Empleados;
import model.ReportexTipoEmpleado;
import model.Tipos;
import utils.MySQLConexion8;

public class GestionEmpleados implements EmpleadosInterface{

	@Override
	public int registrar(Empleados e) {
			 int rs = 0; 
			
			 Connection con = null;
			
			PreparedStatement pst = null;
			try {
				 con = MySQLConexion8.getConexion(); 			  
			   String sql = "insert into tb_Empleados values (null,?,?,?,?,?,?)";			      
			   pst = con.prepareStatement(sql);
			 
			   pst.setString(1, e.getNombre());
			   pst.setString(2, e.getApellido());
			   pst.setString(3, e.getUsuario());
			   pst.setString(4, e.getClave());
			   pst.setString(5, e.getFnacim());
			   pst.setInt(6, e.getId_tipo());
			   			   		   
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
			String sql = "delete from tb_Empleados where cod_emp = ?";			
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
	public int actualizar(Empleados e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Empleados> listado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleados buscar(int codigo) {
			Empleados u = null;
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null; 
			try {
				   con = MySQLConexion8.getConexion(); 
				   String sql = "select * from tb_Empleados where cod_emp = ?";
				   		   
				   pst = con.prepareStatement(sql);				  
				   pst.setInt(1, codigo);				   		   
				   rs = pst.executeQuery(); 
				   if(rs.next()){					 
					  u = new Empleados(rs.getInt(1), rs.getInt(7), rs.getString(2), 
							  rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));							  			
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
			
			return u;
		
	}

	@Override
	public ArrayList<ReportexTipoEmpleado> listadoxTipo(int tipo) {
		ArrayList<ReportexTipoEmpleado> lista=null;
		//PLANTILLA
				Connection con= null;  //Obtenemos la coneccion con la base de datos
				PreparedStatement pst= null; //Pausa las sentencias ,insert , delete 
				ResultSet rs= null; //SIRVE PARA GUAARDAR RESULTADO DE LA CONSULTA
				
				//En caso de algun problema lo manda en la excepcion , system.out.prntln
				try {
					//Obtener la conexion con la base de datos
					con= MySQLConexion8.getConexion();
					//2 crear cadena con la sentencia a ejecutar
					//Se consulta la tabla TB_USUARIOS porque allí hay un dato ID_TIPO (referencia)
					
					//>>< LOS PROCEDIMIENTOS ALMACENADOS SE PONEN ENTRE LLAVES
					String sql = "{call usp_listadoxtipo(?)}";
					
					//Preparar la sentencia con la cadena
					pst= con.prepareStatement(sql);
					
					
					//la cadena tiene ? , si hay se tiene que setear
					pst.setInt(1, tipo);
					
					//EJECUTA LA SENTENCIA Y GUARDA EL RESULTADO
					rs= pst.executeQuery(); 
					
					//pasamos el resultado rs a la variable de retorno
					lista= new ArrayList<ReportexTipoEmpleado>();
					//Mientras hay objetos por leer en "rs"
					while(rs.next()){
						//creamos  un objeto de tipo por cada fila de leida
						ReportexTipoEmpleado r= new  ReportexTipoEmpleado();
						r.setCodigo(rs.getInt(1));
						r.setNombrecompleto(rs.getString(2));
						r.setDescripcion(rs.getString(3));
						
						lista.add(r);
						
					}
					
				} catch (Exception e) {
					System.out.println("Error en ListadoXTipo:"+e.getMessage());
				} finally{ //Cerramos la conexion 
					try {
						con.close();
					} catch (SQLException e) {
						System.out.println("Error al cerrar:"+ e.getMessage());
						e.printStackTrace();
					}
				}
	
				return lista;
	}


	@Override
	public ArrayList<Empleados> listadoIntervaloxFecha(String fecha, String fecha2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleados validarAcceso(String usuario, String clave) {
		Empleados emp=null;
		//Plantilla
		Connection con= null;  //Obtenemos la coneccion con la base de datos
		PreparedStatement pst= null; //Pausa las sentencias ,insert , delete 
		ResultSet rs= null; //SIRVE PARA GUAARDAR RESULTADO DE LA CONSULTA
		
		//En caso de algun problema lo manda en la excepcion , system.out.prntln
		try {
			//Obtener la conexion con la base de datos
			con= MySQLConexion8.getConexion();
			//2 crear cadena con la sentencia a ejecutar
			//Se consulta la tabla TB_USUARIOS porque allí hay un dato ID_TIPO (referencia)
			String sql = "{call usp_validaempleado (? ,?)}";
			
			//Preparar la sentencia con la cadena
			pst= con.prepareStatement(sql);
			
			//la cadena tiene ? , si hay se tiene que setear
			pst.setString(1,usuario);
			pst.setString(2,clave);
			
			//EJECUTA LA SENTENCIA Y GUARDA EL RESULTADO
			rs= pst.executeQuery(); 
			
			//pasamos el resultado rs a la variable de retorno
			//Mientras hay objetos por leer en "rs"
			if(rs.next()){
				//creamos  un objeto de tipo por cada fila de leida
				emp= new Empleados();
				emp.setCodigo(rs.getInt(1));
				emp.setNombre(rs.getString(2));
				emp.setApellido(rs.getString(3));
				emp.setUsuario(rs.getString(4));
				emp.setClave(rs.getString(5));
				emp.setFnacim(rs.getString(6));
				emp.setId_tipo(rs.getInt(7));
				
			}
			
		} catch (Exception e) {
			System.out.println("Error en Validar Acceso :"+e.getMessage());
		} finally{ //Cerramos la conexion 
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar:"+ e.getMessage());
				e.printStackTrace();
			}
		}
		return emp;
	}

	@Override
	public ArrayList<Tipos> listadoTipos() {
		
		ArrayList<Tipos> lista = null;
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
		   con = MySQLConexion8.getConexion(); 
		   String sql = "select * from tb_tipo";
		   		   
		   pst = con.prepareStatement(sql);			   		   
		   rs = pst.executeQuery();    
		   
		   lista = new ArrayList<Tipos>();		  
		   while(rs.next()){			   
			  Tipos t = new Tipos(rs.getInt(1), rs.getString(2));			 
			   lista.add(t);
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

}
