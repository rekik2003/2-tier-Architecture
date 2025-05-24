package tpInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import interface1.Livres;
import interface1.User;

public class SearchResult extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchResult frame = new SearchResult();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchResult() {

	}
	public SearchResult(List<Livres> l) throws IOException {
    	User u = new User("Client1","c254mn");
    	setBounds(380, 180, 650, 400);
        setTitle("Book List");
        setSize(600, 400);
        
        
        // Sample data for the table (ISBN, Author, Title, Year, Price)
        Object[][] data = {
        		
        };
        
        // Column names
        String[] columnNames = {"ISBN", "Author", "Title", "Year", "Price"};
        
        // Create a DefaultTableModel with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        
        // Create a JTable with the model
        JTable table = new JTable(model);
        table.setRowSelectionAllowed(false);
        
        // Set table properties
        table.setFillsViewportHeight(true);
        table.setGridColor(Color.BLACK); // Set grid color
        table.setSelectionBackground(new Color(135, 206, 250)); // Set selection background color
        
        // Create a border for the scroll pane
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        // Create a JScrollPane with the table and border
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(border);
        
        // Set background color of the content pane
        getContentPane().setBackground(new Color(240, 240, 240));
        
        // Add the scroll pane to the frame
        getContentPane().add(scrollPane);
        
        for (Livres livre : l) {
        	Object[] newRowData = {
        			livre.ISBN,
        			livre.auteur,
        			livre.titre,
        			livre.annee,
        			livre.prix};	
        	model.addRow(newRowData);			
        	}
        }
}

