package entities;

public class iTem {
private int item_id;
private String item_name;
private double item_store_price;
private double item_import_price;
private int store_quantity;
private String item_unit;
private String status;
private int category_id;
public int getItem_id() {
	return item_id;
}
public void setItem_id(int item_id) {
	this.item_id = item_id;
}
public String getItem_name() {
	return item_name;
}
public void setItem_name(String item_name) {
	this.item_name = item_name;
}
public double getItem_store_price() {
	return item_store_price;
}
public void setItem_store_price(double item_store_price) {
	this.item_store_price = item_store_price;
}
public double getItem_import_price() {
	return item_import_price;
}
public void setItem_import_price(double item_import_price) {
	this.item_import_price = item_import_price;
}
public int getStore_quantity() {
	return store_quantity;
}
public void setStore_quantity(int store_quantity) {
	this.store_quantity = store_quantity;
}
public String getItem_unit() {
	return item_unit;
}
public void setItem_unit(String item_unit) {
	this.item_unit = item_unit;
}

public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
}
public iTem() {
	super();
}
public iTem(int item_id, String item_name, double item_store_price, double item_import_price, int store_quantity,
		String item_unit, String status, int category_id) {
	super();
	this.item_id = item_id;
	this.item_name = item_name;
	this.item_store_price = item_store_price;
	this.item_import_price = item_import_price;
	this.store_quantity = store_quantity;
	this.item_unit = item_unit;
	this.status = status;
	this.category_id = category_id;
}

}
