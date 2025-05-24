package interface1;

import java.io.IOException;
import java.sql.*;


import connexion.Connector;

public class Admin extends User {
	private static Connection c = Connector.getConnection();
	public Admin(String login , String password) {
		super(login,password);
	}
	
	public static void ajoute(Livres l) throws IOException {
		try {
		    String sql = "INSERT INTO livre (ISBN, titre, auteur, annee, prix) VALUES (?, ?, ?, ?, ?)";
		    PreparedStatement stm = c.prepareStatement(sql);
		    
		    stm.setString(1, l.ISBN);
		    stm.setString(2, l.auteur);
		    stm.setString(3, l.titre);
		    stm.setInt(4, l.annee);
		    stm.setDouble(5, l.prix);
		    int result = stm.executeUpdate();

		} catch (SQLException e) {
		    e.printStackTrace();
		}
}

	
	public boolean supprime(String isbn) throws IOException {
		try {
		    String sql = "DELETE FROM livre WHERE isbn = ?";
		    PreparedStatement stm = c.prepareStatement(sql);
		    stm.setString(1, isbn);
		    int res = stm.executeUpdate();
		    if (res == 0) {
		    	return false;
		    }
		    return true;
		} catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		}

	}
}
