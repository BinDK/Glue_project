package demo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import entities.User;
import model.UserModel;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanel_Profile extends JPanel {
	private JTextField userNameField;
	private Map<String, Object> values = new HashMap<String, Object>();
	private JLabel lblYourNameIs;
	private JLabel lblRole;
	private JPasswordField passwordField;
	private JButton btnUpdate;
	UserModel userModel = new UserModel();

	/**
	 * Create the panel.
	 */
	public JPanel_Profile() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(253, 245, 230));
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel lblWelcome = new JLabel("Welcome");
		panel_1.add(lblWelcome);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(25);
		flowLayout.setHgap(50);
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_2.setBackground(new Color(47, 79, 79));
		panel.add(panel_2, BorderLayout.CENTER);

		lblYourNameIs = new JLabel("Your Name is: ");
		lblYourNameIs.setForeground(new Color(253, 245, 230));
		panel_2.add(lblYourNameIs);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(253, 245, 230));
		panel_2.add(lblNewLabel);

		userNameField = new JTextField();
		panel_2.add(userNameField);
		userNameField.setColumns(10);
		userNameField.setEditable(false);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(253, 245, 230));
		panel_2.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setCaretColor(new Color(66, 37, 59));
		passwordField.setColumns(10);
		panel_2.add(passwordField);

		lblRole = new JLabel("Your Role is : ");
		lblRole.setForeground(new Color(253, 245, 230));
		panel_2.add(lblRole);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdate_actionPerformed(e);
			}
		});
		btnUpdate.setForeground(Color.DARK_GRAY);
		btnUpdate.setBackground(new Color(74, 161, 183));
		panel_2.add(btnUpdate);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.EAST);
	}

	public JPanel_Profile(Map<String, Object> values) {
		this();
		this.values = values;
		loadData();
	}

	public void loadData() {
		User user = (User) values.get("account");
		lblYourNameIs.setText("Your name is:  " + String.valueOf(user.getEmp_name()).toString());
		userNameField.setText(String.valueOf(user.getUsername()).toString());
		lblRole.setText("Your Role is:  " + String.valueOf(user.getEmp_role()).toString());
	}

	public void btnUpdate_actionPerformed(ActionEvent e) {
		User user = (User) values.get("account");
		int id = user.getId();
		String password = new String(passwordField.getPassword());
		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Chua nhap may khau");
		}
		JOptionPane.showMessageDialog(null, "Da cap nhat mat khau");
		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
		userModel.changePassword(user);
	}
}
