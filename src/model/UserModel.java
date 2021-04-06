package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;


import entities.Bill;
import entities.User;

public class UserModel {
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM `db_user`");
			ResultSet resultSet = preparedStatement.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setEmp_name(resultSet.getString("emp_name"));
				user.setPassword(resultSet.getString("password"));
				user.setEmp_role(resultSet.getString("emp_role"));
				users.add(user);
			}
		} catch (Exception e) {
			users = null;
		}
		return users;
	}
	
	public boolean createUser(User user) {
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_user`(`id`, `username`, `password`, `emp_name`, `emp_role`) VALUES (null,?,?,?,?)");
			querry.setString(1, user.getUsername());
			querry.setString(2, user.getPassword());			
			querry.setString(3, user.getEmp_name());
			querry.setString(4, user.getEmp_role());
			
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean deleteUser(int id) {
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"DELETE FROM `db_user` WHERE ?");
			querry.setInt(1, id);
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Không thể xóa nhân viên vì nhân viên đã xuất hóa đơn trước đó","",JOptionPane.WARNING_MESSAGE);
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean changePassword(int id) {
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"UPDATE `db_user` SET `password` =  '123' where id = ?");
			querry.setInt(1, id);
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean changePassword(User user) {
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"UPDATE `db_user` SET `password` =  ? where id = ?");
			querry.setString(1, user.getPassword());
			querry.setInt(2, user.getId());
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}
	public User find(String username) {
		User user = null;
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("Select * from db_user where username = ?");
			querry.setString(1, username);
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				user = new User();
				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setEmp_name(result.getString("emp_name"));
				user.setEmp_role(result.getString("emp_role"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			user = null;
			System.err.println(e.getMessage());
		}
		return user;
	}
	public User login(String username, String password) {
		User user = find(username);
		if (user != null) {
			if (BCrypt.checkpw(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}
}
