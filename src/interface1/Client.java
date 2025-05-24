package interface1;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connector;

public class Client extends User{	
	private static Connection c = Connector.getConnection();
	public Client(String login, String password) {
		super(login,password);
	}

	public void commander(String isbn) throws IOException {
	    try {
	        PreparedStatement old = c.prepareStatement("SELECT livreCommander FROM client WHERE login=? AND password=?");
	        old.setString(1, getLogin());
	        old.setString(2, getMdp());
	        ResultSet res = old.executeQuery();
	        
	        String newLivreCommander = isbn;
	        if (res.next()) {
	            String currentLivreCommander = res.getString("livreCommander");
	            if (currentLivreCommander != null && !currentLivreCommander.isEmpty()) {
	                newLivreCommander = isbn + " " + currentLivreCommander;
	            }
	        }
	        
	        PreparedStatement stm = c.prepareStatement("UPDATE client SET livreCommander=? WHERE login=? AND password=?");
	        stm.setString(1, newLivreCommander);
	        stm.setString(2, getLogin());
	        stm.setString(3, getMdp());
	        stm.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public List<Livres> getMyList() {
	        List<Livres> orderedBooks = new ArrayList<>();
	        try {
	            PreparedStatement statement = c.prepareStatement("SELECT livreCommander FROM client WHERE login=? AND password=?");
	            statement.setString(1, getLogin());
	            statement.setString(2, getMdp());
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                String livreCommander = resultSet.getString("livreCommander");
	                String[] isbns = livreCommander.split("\\s+");
	                
	                for (String isbn : isbns) {
	                    Livres book = getBookByISBN(isbn);
	                    if (book != null) {
	                        orderedBooks.add(book);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderedBooks;
	    }
	private static Livres getBookByISBN(String isbn) {
        Livres book = null;
        
        try {
            PreparedStatement statement = c.prepareStatement("SELECT * FROM livre WHERE ISBN=?");
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("Titre");
                String author = resultSet.getString("Auteur");
                int year = resultSet.getInt("Annee");
                double price = resultSet.getDouble("Prix");
                book = new Livres(isbn, title, author, year, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return book;
    }
}
