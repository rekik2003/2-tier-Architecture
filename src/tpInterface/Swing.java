package tpInterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import interface1.User;
import java.awt.Color;

public class Swing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField log;
	private JTextField pwd;
	private JLabel lblNewLabel_1;
	private JLabel response;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing frame = new Swing();
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
	public Swing() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnNewButton.setBounds(375, 184, 84, 28);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u;
				String login = log.getText();
				String pass = pwd.getText();
				u = new User(login,pass);
				String resp= u.verif();
				response.setText(resp);
				if (resp.equals("Login succeed Admin !")) {
					AdminFrame adm = new AdminFrame(u);
					dispose();
					adm.setVisible(true);
				}
				else if (resp.equals("Login succeed !")) {
					ClientFrame adm = new ClientFrame(u);
					dispose();
					adm.setVisible(true);
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(248, 20, 1, 1);
		contentPane.add(panel);
		panel.setLayout(null);
		
		log = new JTextField();
		log.setForeground(new Color(0, 128, 128));
		log.setFont(new Font("Tahoma", Font.PLAIN, 14));
		log.setHorizontalAlignment(SwingConstants.LEFT);
		log.setBounds(180, 91, 115, 28);
		contentPane.add(log);
		log.setColumns(10);
		
		pwd = new JTextField();
		pwd.setForeground(new Color(0, 128, 128));
		pwd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pwd.setHorizontalAlignment(SwingConstants.LEFT);
		pwd.setBounds(180, 183, 115, 28);
		contentPane.add(pwd);
		pwd.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login :");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel.setBounds(34, 88, 106, 31);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel_1.setBounds(34, 171, 119, 52);
		contentPane.add(lblNewLabel_1);
		
		response = new JLabel("");
		response.setHorizontalAlignment(SwingConstants.CENTER);
		response.setFont(new Font("Tahoma", Font.PLAIN, 16));
		response.setBounds(107, 257, 331, 52);
		contentPane.add(response);
	}

}
