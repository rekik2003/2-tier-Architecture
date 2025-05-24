package tpInterface;
import interface1.Client;
import interface1.Livres;
import interface1.User;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchText;
	private JTextField txtIsbn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame frame = new ClientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ClientFrame() {}

	public ClientFrame(User u) {
		Client c = new Client(u.getLogin(),u.getMdp());
		setTitle("My Space");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Books List");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel.setBounds(29, 66, 96, 19);
		contentPane.add(lblNewLabel);
		
		BookListFrame list = new BookListFrame();
		JButton books = new JButton("Show List");
		books.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		books.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setVisible(true);
			}
		});
		books.setBounds(184, 63, 96, 28);
		contentPane.add(books);
		
		JLabel listMsg = new JLabel("");
		listMsg.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		listMsg.setBounds(330, 101, 138, 19);
		contentPane.add(listMsg);
		
        List<Livres> l = c.getMyList();
        MyBookListFrame mylist = new MyBookListFrame(l);
        	
		JButton myList = new JButton("Show My List");
		myList.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		myList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!l.isEmpty()) {
					mylist.setVisible(true);
					listMsg.setText("");
				}
				else 
					listMsg.setText("You have no commands !");
			}
		});
		myList.setBounds(332, 63, 127, 28);
		contentPane.add(myList);
		
		JLabel lblNewLabel_1 = new JLabel("Search :");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(29, 145, 75, 28);
		contentPane.add(lblNewLabel_1);
		
		searchText = new JTextField();
		searchText.setForeground(new Color(0, 128, 128));
		searchText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchText.setHorizontalAlignment(SwingConstants.CENTER);
		searchText.setText("(Author)");
		searchText.setBounds(176, 147, 115, 28);
		contentPane.add(searchText);
		searchText.setColumns(10);
		
		JLabel msg = new JLabel("");
		msg.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		msg.setBounds(466, 145, 160, 28);
		contentPane.add(msg);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
					try {
						List<Livres> l = new ArrayList<Livres>();
				        l = u.cherche(searchText.getText());
				        if (l.isEmpty()) {
				        	msg.setText("No Boooks for this Author!");
				        }
				        else {
							SearchResult searchList;
							searchList = new SearchResult(l);
							searchList.setVisible(true);
				        }
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(344, 147, 96, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Command :");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(29, 224, 115, 27);
		contentPane.add(lblNewLabel_2);
		
		txtIsbn = new JTextField();
		txtIsbn.setForeground(new Color(0, 128, 128));
		txtIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		txtIsbn.setText("(ISBN)");
		txtIsbn.setBounds(176, 227, 115, 28);
		contentPane.add(txtIsbn);
		txtIsbn.setColumns(10);
		
		JButton commandButton = new JButton("Command");
		commandButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		
		JLabel msg2 = new JLabel("");
		msg2.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		msg2.setBounds(177, 294, 263, 28);
		contentPane.add(msg2);
		
		commandButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				try {
					if (c.verifBook(txtIsbn.getText()))  {
						c.commander(txtIsbn.getText());
						msg2.setText("The Book is added to your library :)");
					}
					else 
						msg2.setText("No book exists with this ISBN !");
				} catch (IOException e1) {
					System.out.println("Command prob!");
				}
			}
		});
		commandButton.setBounds(344, 225, 96, 28);
		contentPane.add(commandButton);
		
	}

}
