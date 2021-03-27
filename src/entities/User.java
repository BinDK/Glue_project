package entities;

public class User {
private int id;
private String username;
private String password;
private String emp_name;
private String emp_role;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmp_name() {
	return emp_name;
}
public void setEmp_name(String emp_name) {
	this.emp_name = emp_name;
}
public String getEmp_role() {
	return emp_role;
}
public void setEmp_role(String emp_role) {
	this.emp_role = emp_role;
}
public User(int id, String username, String password, String emp_name, String emp_role) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.emp_name = emp_name;
	this.emp_role = emp_role;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}

}
