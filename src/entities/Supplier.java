package entities;

public class Supplier {
private int id;
private String supplier_name;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getSupplier_name() {
	return supplier_name;
}
public void setSupplier_name(String supplier_name) {
	this.supplier_name = supplier_name;
}
public Supplier(int id, String supplier_name) {
	super();
	this.id = id;
	this.supplier_name = supplier_name;
}
public Supplier() {
	super();
	// TODO Auto-generated constructor stub
}

}
