package tpInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import interface1.Livres;

public class MyBookListFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyBookListFrame frame = new MyBookListFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MyBookListFrame() {}
	/**
	 * Create the frame.
	 */
	public MyBookListFrame(List<Livres> l) {
		
    	setBounds(380, 180, 650, 400);
        setTitle("My Book List");
        setSize(600, 400);
       
        Object[][] data = {
        		
        };
        
        String[] columnNames = {"ISBN", "Author", "Title", "Year", "Price"};
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        
        JTable table = new JTable(model);
        table.setRowSelectionAllowed(false);
        
        table.setFillsViewportHeight(true);
        table.setGridColor(new Color(0, 150, 130)); 
        table.setSelectionBackground(new Color(135, 206, 250)); 
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(border);
        
        getContentPane().setBackground(new Color(240, 240, 240));
        
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
