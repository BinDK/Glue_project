package entities;

public class Customer {
	private int customer_id;
private String customer_name;
private int customer_phone;
private String description;
public int getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}
public String getCustomer_name() {
	return customer_name;
}
public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
}
public int getCustomer_phone() {
	return customer_phone;
}
public void setCustomer_phone(int customer_phone) {
	this.customer_phone = customer_phone;
}

public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Customer(int customer_id, String customer_name, int customer_phone, String description) {
	super();
	this.customer_id = customer_id;
	this.customer_name = customer_name;
	this.customer_phone = customer_phone;
	this.description = description;
}
public Customer() {
	super();
	// TODO Auto-generated constructor stub
}

}
