package interface1;

import connexion.Connector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class User {
	private String login;
	private String mdp;
	private static Connection c = Connector.getConnection();
	
	public User(String l , String m) {
		login = l;
		mdp = m ;
	}
	
	public boolean verifBook(String isbn) {
		try {
			PreparedStatement stm;
			stm = c.prepareStatement("select * from livre where isbn = ?");
			stm.setString(1, isbn);
			ResultSet res = stm.executeQuery(); 
			if(res.next()) 
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String verif() {
	    try {
	        PreparedStatement stm = c.prepareStatement("SELECT * FROM admin WHERE login = ?");
	        stm.setString(1, login);
	        ResultSet result = stm.executeQuery();
	        
	        if (result.next()) {
	            String passwordFromDB = result.getString("password");
	            if (!passwordFromDB.equals(mdp)) {
	            	return ("Wrong password!");
	            }
	            else {
	            	return "Login succeed Admin !";
	            }
	        } else {
	        	stm = c.prepareStatement("SELECT * FROM client WHERE login = ?");
		        stm.setString(1, login);
		        result = stm.executeQuery();
		        if (result.next()) {
		            String passwordFromDB = result.getString("password");
		            if (!passwordFromDB.equals(mdp)) {
		            	return ("Wrong password!");
		            }
		            else {
		            	return "Login succeed !";
		            }
		        }
		        else {
		        	return ("Client with login '" + login + "' not found.");		        	
		        }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "";
	    }
	}
	
	public static List<Livres> afficher() {
		try {
			List<Livres> list = new ArrayList<Livres>() ;
			Statement stm = c.createStatement();
			String sql = "select * from livre";
			ResultSet result = stm.executeQuery(sql);
			while (result.next()) {
				Livres livre = new Livres(
				result.getString("ISBN"),
            	result.getString("Auteur"),
            	result.getString("Titre"),
            	Integer.parseInt(result.getString("Annee")),
            	Double.parseDouble(result.getString("Prix")));
				list.add(livre);
            }
			return list;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Livres> cherche(String auteur) throws IOException {
		try {
			List<Livres> list = new ArrayList<Livres>() ;
			PreparedStatement stm = c.prepareStatement("select * from livre where auteur = ?");
			stm.setString(1, auteur);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				Livres livre = new Livres(
					res.getString("ISBN"),
                	res.getString("Auteur"),
                	res.getString("Titre"),
                	Integer.parseInt(res.getString("Annee")),
                	Double.parseDouble(res.getString("Prix")));
				list.add(livre);
			}
			return list;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
