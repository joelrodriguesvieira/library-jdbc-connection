package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conecta {
	
	static final String URL = "jdbc:postgresql://localhost:5432/script_3_fase_bd";
	static final String USER_NAME = "postgres";
	static final String PASSWORD = "22022003";
	
	public static Connection conexao(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			if (conn!= null) {
				System.out.println("Banco Conectado!");
			} else {
				System.out.println("Conexão Falhada!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}	
	public static void desconexao(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void desconexao(PreparedStatement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
