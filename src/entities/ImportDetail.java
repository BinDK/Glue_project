package entities;

public class ImportDetail {
private int import_detail_id;
private String item_name; 	
private double import_price;
private double store_price;
private int item_quantity;
private String item_unit;
private String bill_status;
private int bill_import_id;
public int getImport_detail_id() {
	return import_detail_id;
}
public void setImport_detail_id(int import_detail_id) {
	this.import_detail_id = import_detail_id;
}
public String getItem_name() {
	return item_name;
}
public void setItem_name(String item_name) {
	this.item_name = item_name;
}
public double getImport_price() {
	return import_price;
}
public void setImport_price(double import_price) {
	this.import_price = import_price;
}
public double getStore_price() {
	return store_price;
}
public void setStore_price(double store_price) {
	this.store_price = store_price;
}
public int getItem_quantity() {
	return item_quantity;
}
public void setItem_quantity(int item_quantity) {
	this.item_quantity = item_quantity;
}
public String getItem_unit() {
	return item_unit;
}
public void setItem_unit(String item_unit) {
	this.item_unit = item_unit;
}
public String getBill_status() {
	return bill_status;
}
public void setBill_status(String bill_status) {
	this.bill_status = bill_status;
}
public int getBill_import_id() {
	return bill_import_id;
}
public void setBill_import_id(int bill_import_id) {
	this.bill_import_id = bill_import_id;
}
public ImportDetail() {
	super();
	// TODO Auto-generated constructor stub
}
public ImportDetail(int import_detail_id, String item_name, double import_price, double store_price, int item_quantity,
		String item_unit, String bill_status, int bill_import_id) {
	super();
	this.import_detail_id = import_detail_id;
	this.item_name = item_name;
	this.import_price = import_price;
	this.store_price = store_price;
	this.item_quantity = item_quantity;
	this.item_unit = item_unit;
	this.bill_status = bill_status;
	this.bill_import_id = bill_import_id;
}

}
