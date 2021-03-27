package entities;

public class BillDetail {
private int bill_detail_id;
private int item_id;
private double store_price;
private String item_unit;
private int item_quantity;
private int bill_id;
public int getBill_detail_id() {
	return bill_detail_id;
}
public void setBill_detail_id(int bill_detail_id) {
	this.bill_detail_id = bill_detail_id;
}
public int getItem_id() {
	return item_id;
}
public void setItem_id(int item_id) {
	this.item_id = item_id;
}
public double getStore_price() {
	return store_price;
}
public void setStore_price(double store_price) {
	this.store_price = store_price;
}
public String getItem_unit() {
	return item_unit;
}
public void setItem_unit(String item_unit) {
	this.item_unit = item_unit;
}
public int getItem_quantity() {
	return item_quantity;
}
public void setItem_quantity(int item_quantity) {
	this.item_quantity = item_quantity;
}
public int getBill_id() {
	return bill_id;
}
public void setBill_id(int bill_id) {
	this.bill_id = bill_id;
}
public BillDetail(int bill_detail_id, int item_id, double store_price, String item_unit, int item_quantity,
		int bill_id) {
	super();
	this.bill_detail_id = bill_detail_id;
	this.item_id = item_id;
	this.store_price = store_price;
	this.item_unit = item_unit;
	this.item_quantity = item_quantity;
	this.bill_id = bill_id;
}
public BillDetail() {
	super();
	// TODO Auto-generated constructor stub
}

}
