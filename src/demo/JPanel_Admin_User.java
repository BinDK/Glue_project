package demo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.mindrot.jbcrypt.BCrypt;

import entities.Bill;
import entities.User;
import model.UserModel;

import javax.swing.JComboBox;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class JPanel_Admin_User extends JPanel {
	private JLabel lblName;
	private JTextField nameField;
	private JLabel lblUsername;
	private JTextField unameField;
	private JLabel lblRole;
	private JComboBox comboBox;
	private JButton btnAddUser;
	private JButton btnDeleteUser;
	private JButton btnChangePassword;
	private JTable tableUser;
	UserModel userModel = new UserModel();

	/**
	 * Create the panel.
	 */
	public JPanel_Admin_User() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		tableUser = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		scrollPane.setViewportView(tableUser);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(112, 128, 144));
		panel.add(panel_1, BorderLayout.SOUTH);

		lblName = new JLabel("Name");
		panel_1.add(lblName);

		nameField = new JTextField();
		panel_1.add(nameField);
		nameField.setColumns(5);
		nameField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (nameField.getText().length() == 250) {
					e.consume();
				}
			}

		});

		lblUsername = new JLabel("Username");
		panel_1.add(lblUsername);

		unameField = new JTextField();
		panel_1.add(unameField);
		unameField.setColumns(5);
		unameField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (unameField.getText().length() == 250) {
					e.consume();
				}
			}

		});
		lblRole = new JLabel("Role");
		panel_1.add(lblRole);

		String roleList[] = { "", "ADMIN", "SM", "IM", "SALE" };
		comboBox = new JComboBox(roleList);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(comboBox);

		btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddUser_actionPerformed(e);
			}
		});
		panel_1.add(btnAddUser);

		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteUser_actionPerformed(e);
			}
		});
		panel_1.add(btnDeleteUser);

		btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnChangePassword_actionPerformed(e);
			}
		});
		panel_1.add(btnChangePassword);

		loadData();
	}

	public void loadData() {

		fillDatatoTable(userModel.findAll());
	}

	public void fillDatatoTable(List<User> users) {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("ID");
		table.addColumn("Username");
		table.addColumn("Employee Name");
		table.addColumn("Role");
		for (User user : users) {
			table.addRow(new Object[] { user.getId(), user.getUsername(), user.getEmp_name(), user.getEmp_role() });
		}
		tableUser.setModel(table);
	}

	public void btnAddUser_actionPerformed(ActionEvent e) {
		String role = comboBox.getSelectedItem().toString();
		String fname = nameField.getText().toString().toLowerCase().trim();
		String uname = unameField.getText().toString().toLowerCase().trim();
		if (role == "") {
			JOptionPane.showMessageDialog(null, "Chon Role");
		} else if (fname == "" || uname == "") {
			JOptionPane.showMessageDialog(null, "Thieu Uname hoac Fname");
		} else if (userModel.find(uname) != null) {
			JOptionPane.showMessageDialog(null, "Trùng username");
		} else {
			JOptionPane.showMessageDialog(null, "Đã them nhân viên với password là 123");
			User user = new User();
			user.setEmp_name(fname);
			user.setUsername(uname);
			user.setPassword(BCrypt.hashpw("123", BCrypt.gensalt()));
			user.setEmp_role(role);
		userModel.createUser(user);
		}
	}

	public void btnDeleteUser_actionPerformed(ActionEvent e) {
		try {
			int row = tableUser.getSelectedRow();
			int id = (Integer) tableUser.getValueAt(row, 0);

			int option = JOptionPane.showConfirmDialog(null, "Chac chan xoa", "Delete account id: " + id,
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				userModel.deleteUser(id);
				fillDatatoTable(userModel.findAll());
			}
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Chon User de delete");
		}
	}

	public void btnChangePassword_actionPerformed(ActionEvent e) {
		try {
			int row = tableUser.getSelectedRow();
			int id = (Integer) tableUser.getValueAt(row, 0);

			int option = JOptionPane.showConfirmDialog(null, "Đổi mật khẩu thành 123 cho tài khoản có ID" + id,
					"Change password", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				userModel.changePassword(id);
				JOptionPane.showMessageDialog(null, "Nói nhân viên chuyển mật khẩu lại");
				fillDatatoTable(userModel.findAll());
			}
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Chon User de doi pass");
		}
	}
}
