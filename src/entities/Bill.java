package entities;

import java.util.Date;

public class Bill {
private int bill_id;
private Date date_print;
private String bill_status;
private String payment;
private double total_price;
private int customer_id;
private int emp_id;

public Bill() {
	super();
	// TODO Auto-generated constructor stub
}

public Bill(int bill_id, Date date_print, String bill_status, String payment, double total_price, int customer_id,
		int emp_id) {
	super();
	this.bill_id = bill_id;
	this.date_print = date_print;
	this.bill_status = bill_status;
	this.payment = payment;
	this.total_price = total_price;
	this.customer_id = customer_id;
	this.emp_id = emp_id;
}

public int getBill_id() {
	return bill_id;
}

public void setBill_id(int bill_id) {
	this.bill_id = bill_id;
}

public Date getDate_print() {
	return date_print;
}

public void setDate_print(Date date_print) {
	this.date_print = date_print;
}

public String getBill_status() {
	return bill_status;
}

public void setBill_status(String bill_status) {
	this.bill_status = bill_status;
}

public String getPayment() {
	return payment;
}

public void setPayment(String payment) {
	this.payment = payment;
}

public double getTotal_price() {
	return total_price;
}

public void setTotal_price(double total_price) {
	this.total_price = total_price;
}

public int getCustomer_id() {
	return customer_id;
}

public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}

public int getEmp_id() {
	return emp_id;
}

public void setEmp_id(int emp_id) {
	this.emp_id = emp_id;
}


}
