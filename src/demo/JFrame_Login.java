package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import model.LoginModel;
import entities.User;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class JFrame_Login extends JFrame {

	private JPanel contentPane;
	private JTextField uNameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_Login frame = new JFrame_Login();
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
	public JFrame_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginForm = new JLabel("Login Form");
		lblLoginForm.setBounds(188, 40, 77, 23);
		contentPane.add(lblLoginForm);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(33, 129, 77, 15);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(33, 167, 77, 15);
		contentPane.add(lblPassword);
		
		uNameField = new JTextField();
		uNameField.setBounds(132, 123, 195, 27);
		contentPane.add(uNameField);
		uNameField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin_actionPerformed(e);
			}
		});
		btnLogin.setBounds(165, 211, 100, 27);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 157, 195, 27);
		contentPane.add(passwordField);
	}
	public void btnLogin_actionPerformed(ActionEvent e) {
		LoginModel model = new LoginModel();
		String username = uNameField.getText();
		String password = new String(passwordField.getPassword());
		User user= model.login(username,  password);
		if(user == null) JOptionPane.showMessageDialog(null, "Invalid");
		else {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("account", user);
			JFrame_Main jframe_main = new JFrame_Main(values);
			jframe_main.setTitle("Welcome user: " + username + "Your role is: " + user.getEmp_role());
			jframe_main.setVisible(true);
			this.setVisible(false);
		}
	}
}
