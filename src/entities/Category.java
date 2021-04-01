package entities;

public class Category {
private int id;
private String cate_name;
private String description;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCate_name() {
	return cate_name;
}
public void setCate_name(String cate_name) {
	this.cate_name = cate_name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Category(int id, String cate_name, String description) {
	super();
	this.id = id;
	this.cate_name = cate_name;
	this.description = description;
}
public Category() {
	super();
	// TODO Auto-generated constructor stub
}

}
