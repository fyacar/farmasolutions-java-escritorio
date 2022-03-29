package utils;
// para conectarse a MySQL 8
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion8 {
	public static Connection getConexion() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/farmaSolutions?useSSL=false&useTimezone=true&serverTimezone=UTC";
			String usr = "root";
			String psw = "sql159456";
			con = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error >> Driver no Instalado!!");
		} catch (SQLException ex) {
			System.out.println("Error >> de conexi�n con la BD");
		} catch (Exception ex) {
			System.out.println("Error >> general : " + ex.getMessage());
		} 
		return con;
	}

}
