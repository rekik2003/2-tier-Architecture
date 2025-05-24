package connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connector {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/projet1";
			String login = "root";
			String password = "Youyou.2003";
			conn  = DriverManager.getConnection(url,login,password);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace(); 
		}
		return conn;
	}
}