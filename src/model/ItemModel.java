package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entities.Bill;
import entities.Category;
import entities.Customer;
import entities.iTem;

public class ItemModel {

	public List<iTem> findAll() {
		List<iTem> items = new ArrayList<iTem>();
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("Select * from db_item");

			ResultSet result = querry.executeQuery();
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			while (result.next()) {
				iTem item = new iTem();
				item.setItem_id(result.getInt("item_id"));
				item.setItem_name(result.getString("item_name"));
				item.setItem_store_price(result.getDouble("item_store_price"));
				item.setItem_import_price(result.getDouble("item_import_price"));
				item.setStore_quantity(result.getInt("store_quantity"));
				item.setItem_unit(result.getString("item_unit"));
				item.setStatus(result.getString("status"));
				item.setCategory_id(result.getInt("category_id"));
				items.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return items;
	}
	public List<iTem> findAllLessthan10() {
		List<iTem> items = new ArrayList<iTem>();
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("Select * from db_item where store_quantity < 10");

			ResultSet result = querry.executeQuery();
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			while (result.next()) {
				iTem item = new iTem();
				item.setItem_id(result.getInt("item_id"));
				item.setItem_name(result.getString("item_name"));
				item.setItem_store_price(result.getDouble("item_store_price"));
				item.setItem_import_price(result.getDouble("item_import_price"));
				item.setStore_quantity(result.getInt("store_quantity"));
				item.setItem_unit(result.getString("item_unit"));
				item.setStatus(result.getString("status"));
				item.setCategory_id(result.getInt("category_id"));
				items.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return items;
	}
	public List<Category> findAllCate() {
		List<Category> cates = new ArrayList<Category>();
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement("Select * from db_category");

			ResultSet result = querry.executeQuery();

			while (result.next()) {
				Category cate = new Category();
				cate.setId(result.getInt("id"));
				cate.setCate_name(result.getString("cate_name"));
				cate.setDescription(result.getString("desciption"));
				cates.add(cate);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return cates;
	}

	public iTem findiTemOnID(int id) {
		iTem item = null;
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("Select * from db_item where item_id = ?");
			querry.setInt(1, id);
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				item = new iTem();
				item.setItem_id(result.getInt("item_id"));
				item.setItem_name(result.getString("item_name"));
				item.setItem_store_price(result.getDouble("item_store_price"));
				item.setItem_import_price(result.getDouble("item_import_price"));
				item.setStore_quantity(result.getInt("store_quantity"));
				item.setItem_unit(result.getString("item_unit"));
				item.setStatus(result.getString("status"));
				item.setCategory_id(result.getInt("category_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			item = null;
			System.err.println(e.getMessage());
		}
		return item;
	}

	public List<iTem> findiTemOnCateID(int cate_id) {
		List<iTem> items = new ArrayList<iTem>();
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("Select * from db_item where category_id = ?");
			querry.setInt(1, cate_id);
			ResultSet result = querry.executeQuery();
			while (result.next()) {
				iTem item = new iTem();
				item.setItem_id(result.getInt("item_id"));
				item.setItem_name(result.getString("item_name"));
				item.setItem_store_price(result.getDouble("item_store_price"));
				item.setItem_import_price(result.getDouble("item_import_price"));
				item.setStore_quantity(result.getInt("store_quantity"));
				item.setItem_unit(result.getString("item_unit"));
				item.setStatus(result.getString("status"));
				item.setCategory_id(result.getInt("category_id"));
				items.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
			items = null;
			System.err.println(e.getMessage());
		}
		return items;
	}
	public List<iTem> findiTemOnCateIDqtyOver10(int cate_id) {
		List<iTem> items = new ArrayList<iTem>();
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("Select * from db_item where category_id = ? and store_quantity > 10");
			querry.setInt(1, cate_id);
			ResultSet result = querry.executeQuery();
			while (result.next()) {
				iTem item = new iTem();
				item.setItem_id(result.getInt("item_id"));
				item.setItem_name(result.getString("item_name"));
				item.setItem_store_price(result.getDouble("item_store_price"));
				item.setItem_import_price(result.getDouble("item_import_price"));
				item.setStore_quantity(result.getInt("store_quantity"));
				item.setItem_unit(result.getString("item_unit"));
				item.setStatus(result.getString("status"));
				item.setCategory_id(result.getInt("category_id"));
				items.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
			items = null;
			System.err.println(e.getMessage());
		}
		return items;
	}
	public boolean createCustomer(Customer customer) {
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_customer` (`customer_name`, `customer_phone`, `description`) VALUES (?, ?, '')");
			querry.setString(1, customer.getCustomer_name());
			querry.setInt(2, customer.getCustomer_phone());
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean createmainBill(Bill bill) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_bill` ( `date_print`, `bill_status`, `payment`, `total_price`, `customer_id`, `emp_id`) VALUES (?, ?, ?, 0, ?, ?)");
			querry.setString(1, format.format(bill.getDate_print()));
			querry.setString(2, bill.getBill_status());
			querry.setString(3, bill.getPayment());
			querry.setInt(4, bill.getCustomer_id());
			querry.setInt(5, bill.getEmp_id());
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}

	public Integer itemQty(int id) {
		int quantity = 0;
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("SELECT store_quantity from db_item WHERE item_id = ?");
			querry.setInt(1, id);
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				quantity = result.getInt("store_quantity");
			}
			return quantity;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
	}
	public String itemStatus(int id) {
		String status = "";
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("SELECT status from db_item WHERE item_id = ?");
			querry.setInt(1, id);
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				status = result.getString("status");
			}
			return status;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
	}
	public boolean notSellStatus(int id) {

		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("UPDATE `db_item` set `status` = 'Unavailable' WHERE `item_id` = ?");
			querry.setInt(1, id);
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Cannot find item's ID.");
			return false;
		}
	}
	public String itemName(int id) {
		String name = "";
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("SELECT item_name from db_item WHERE item_id = ?");
			querry.setInt(1, id);
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				name = result.getString("item_name");
			}
			return name;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
	}
	public boolean updateStock(int qty, String name) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE `db_item` SET `store_quantity` =  ?  WHERE `item_name` = ? ");
			preparedStatement.setInt(1, qty);
			preparedStatement.setString(2, name);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean updateSaleStatus(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE `db_item` SET `status` =  'Out of Stock'  WHERE `item_id` = ? ");
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean updateReStockStatus(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE `db_item` SET `status` =  'Available'  WHERE `item_id` = ? ");
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public Integer findIDCate(String name) {
		int quantity = 0;
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("	SELECT id FROM `db_category` WHERE cate_name = ?;");
			querry.setString(1, name);
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				quantity = result.getInt("id");
			}
			return quantity;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Can't find Category ID!");
			return null;
		}
	}
	public boolean insertItem(iTem item) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_item`(`item_id`, `item_name`, `item_store_price`, `item_import_price`, `store_quantity`, `item_unit`, `status`, `category_id`) VALUES (NULL, ?, ?, ?, ?, ?, 'Available', ?)");
			querry.setString(1,item.getItem_name());
			querry.setDouble(2,item.getItem_import_price());
			querry.setDouble(3,item.getItem_store_price());
			querry.setInt(4,item.getStore_quantity());
			querry.setString(5,item.getItem_unit());
			querry.setInt(6,item.getCategory_id());
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean insertCate(Category cate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_item`(`id`, `cate_name`, `desciption`) VALUES (NULL, ?, '')");
			querry.setString(1,cate.getCate_name());
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}
}
