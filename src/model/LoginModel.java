package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import entities.User;


public class LoginModel {
	public User find(String username) {
		User user = null;
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement("Select * from db_user where username = ?");
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
	public User login(String username,String password) {
		User user = find(username);
		if(user!= null) {
			if (password.equalsIgnoreCase(user.getPassword())) {
				return user;
			}
		}
		return null;
	}
}
