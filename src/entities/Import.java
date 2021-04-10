package entities;

import java.util.Date;

public class Import {
private int bill_import_id;
private int supplier_id;
private double total_price;
private int emp_id;
private String bill_satus;
private Date date_import;
public int getBill_import_id() {
	return bill_import_id;
}
public void setBill_import_id(int bill_import_id) {
	this.bill_import_id = bill_import_id;
}
public int getSupplier_id() {
	return supplier_id;
}
public void setSupplier_id(int supplier_id) {
	this.supplier_id = supplier_id;
}
public double getTotal_price() {
	return total_price;
}
public void setTotal_price(double total_price) {
	this.total_price = total_price;
}
public int getEmp_id() {
	return emp_id;
}
public void setEmp_id(int emp_id) {
	this.emp_id = emp_id;
}
public String getBill_satus() {
	return bill_satus;
}
public void setBill_satus(String bill_satus) {
	this.bill_satus = bill_satus;
}
public Date getDate_import() {
	return date_import;
}
public void setDate_import(Date date_import) {
	this.date_import = date_import;
}
public Import(int bill_import_id, int supplier_id, double total_price, int emp_id, String bill_satus,
		Date date_import) {
	super();
	this.bill_import_id = bill_import_id;
	this.supplier_id = supplier_id;
	this.total_price = total_price;
	this.emp_id = emp_id;
	this.bill_satus = bill_satus;
	this.date_import = date_import;
}
public Import() {
	super();
	// TODO Auto-generated constructor stub
}

}
