package tpInterface;
import interface1.Admin;
import interface1.User;
import interface1.Livres;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchText;
	private JTextField deleteText;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AdminFrame() {}
	
	public AdminFrame(User u) {
		Admin a = new Admin(u.getLogin(),u.getMdp());
		setTitle("My Control Room");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Books List");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel.setBounds(29, 70, 96, 13);
		contentPane.add(lblNewLabel);
		
		BookListFrame list = new BookListFrame();
		JButton books = new JButton("Show");
		books.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		books.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					list.setVisible(true);			}
		});
		books.setBounds(173, 64, 96, 28);
		contentPane.add(books);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(29, 140, 59, 13);
		contentPane.add(lblNewLabel_1);
		
		searchText = new JTextField();
		searchText.setForeground(new Color(0, 128, 128));
		searchText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchText.setHorizontalAlignment(SwingConstants.CENTER);
		searchText.setText("(Author)");
		searchText.setBounds(165, 134, 115, 28);
		contentPane.add(searchText);
		searchText.setColumns(10);
		
		JLabel msg = new JLabel("");
		msg.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		msg.setBounds(445, 140, 153, 13);
		contentPane.add(msg);
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Livres> l = new ArrayList<Livres>();
			        l = u.cherche(searchText.getText());
			        if (l.isEmpty()) {
			        	msg.setText("No Boooks for this Author!");
			        }
			        else {
			        	msg.setText("");
						SearchResult searchList;
						searchList = new SearchResult(l);
						searchList.setVisible(true);
			        }
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		searchButton.setBounds(313, 134, 96, 28);
		contentPane.add(searchButton);
		
		JLabel lblNewLabel_2 = new JLabel("Add Book");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(29, 210, 90, 13);
		contentPane.add(lblNewLabel_2);
		
		AddBookFrame add = new AddBookFrame();
		JButton addButton = new JButton("Add");
		addButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add.setVisible(true);
			}
		});
		addButton.setBounds(173, 204, 96, 28);
		contentPane.add(addButton);
		
		JLabel lblNewLabel_3 = new JLabel("Delete Book");
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(29, 280, 115, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel msg2 = new JLabel("");
		msg2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		msg2.setBounds(430, 280, 168, 21);
		contentPane.add(msg2);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (a.supprime(deleteText.getText())) {
						msg2.setText("Book Deleted");						
					}
					else {
						msg2.setText("No book exists with this ISBN !");
					}
				} catch (IOException e1) {
					System.out.println("Delete prob !");
				}
			}
		});
		deleteButton.setBounds(313, 274, 96, 28);
		contentPane.add(deleteButton);
		
		deleteText = new JTextField();
		deleteText.setForeground(new Color(0, 128, 128));
		deleteText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteText.setHorizontalAlignment(SwingConstants.CENTER);
		deleteText.setText("(ISBN)");
		deleteText.setBounds(165, 274, 115, 28);
		contentPane.add(deleteText);
		deleteText.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel(a.getLogin()+"'s Room");
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel_4.setBounds(250, 21, 138, 21);
		contentPane.add(lblNewLabel_4);
		
	}
}