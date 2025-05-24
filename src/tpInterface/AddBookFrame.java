package tpInterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connexion.Connector;
import interface1.Admin;
import interface1.Livres;

public class AddBookFrame extends JFrame {
	private static Connection c = Connector.getConnection();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel ISBN;
	private JLabel author;
	private JLabel title;
	private JTextField isbn;
	private JTextField auteur;
	private JTextField titre;
	private JTextField annee;
	private JTextField prix;
	private JLabel lblNewLabel1;
	private JLabel lblNewLabel2;
	private JLabel msg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookFrame frame = new AddBookFrame();
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
	public AddBookFrame() {
		setTitle("Add Book");
		setBounds(380, 180, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel;
		author = new JLabel("Author :");
		author.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		author.setBounds(32, 110, 76, 21);
		contentPane.add(author);
		
		JTextField searchText;
		auteur = new JTextField();
		auteur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		auteur.setHorizontalAlignment(SwingConstants.CENTER);
		auteur.setBounds(128, 108, 115, 28);
		contentPane.add(auteur);
		auteur.setColumns(10);
		
		title = new JLabel("Title :");
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		title.setBounds(32, 165, 65, 21);
		contentPane.add(title);
		
		titre = new JTextField();
		titre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setBounds(128, 163, 115, 28);
		contentPane.add(titre);
		titre.setColumns(10);
		
		lblNewLabel1 = new JLabel("Year :");
		lblNewLabel1.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel1.setBounds(32, 220, 53, 21);
		contentPane.add(lblNewLabel1);
		
		annee = new JTextField();
		annee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		annee.setHorizontalAlignment(SwingConstants.CENTER);
		annee.setBounds(128, 218, 115, 28);
		contentPane.add(annee);
		annee.setColumns(10);
		
		lblNewLabel2 = new JLabel("Price :");
		lblNewLabel2.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel2.setBounds(32, 275, 65, 21);
		contentPane.add(lblNewLabel2);
		
		prix = new JTextField();
		prix.setFont(new Font("Tahoma", Font.PLAIN, 14));
		prix.setHorizontalAlignment(SwingConstants.CENTER);
		prix.setBounds(128, 273, 115, 28);
		contentPane.add(prix);
		prix.setColumns(10);
		
		ISBN = new JLabel("ISBN :");
		ISBN.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		ISBN.setBounds(32, 55, 53, 21);
		contentPane.add(ISBN);
		
		isbn = new JTextField();
		isbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		isbn.setHorizontalAlignment(SwingConstants.CENTER);
		isbn.setBounds(128, 53, 115, 28);
		contentPane.add(isbn);
		isbn.setColumns(10);
		
		JButton Add = new JButton("Add Book ");
		Add.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		Add.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				if (checkEmpty()) 
					if (checkBooks(isbn.getText(),titre.getText())) {
						Livres book = new Livres(isbn.getText(),auteur.getText()
								,titre.getText(),Integer.parseInt(annee.getText()),Double.parseDouble(prix.getText()));
						try {
							Admin.ajoute(book);
						} catch (IOException e1) {
							msg.setText("ERROR!");
						}
						msg.setText("Book Added Successfully :)");
					}
					else {
						msg.setText("We have this book already !");
					}
				else {
					msg.setText("You need to fill all the fields !");
				}
			}

			private boolean checkEmpty() {
				return !(isbn.getText().isEmpty() || auteur.getText().isEmpty() || titre.getText().isEmpty() 
						|| annee.getText().isEmpty() || prix.getText().isEmpty());
			}

			private boolean checkBooks(String isbn, String title) {
				try {
					PreparedStatement stm;
					stm = c.prepareStatement("select * from livre where titre = ?");
					stm.setString(1, title);
					ResultSet res = stm.executeQuery();
					if (res.next()) {
						return false;
					}
					else {
						stm = c.prepareStatement("select * from livre where isbn = ?");
						stm.setString(1, isbn);
						res = stm.executeQuery();
						return !res.next();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return false;
			}
		});
		Add.setBounds(333, 164, 103, 27);
		contentPane.add(Add);
		
		msg = new JLabel("");
		msg.setBounds(294, 206, 191, 35);
		contentPane.add(msg);
	}

}
