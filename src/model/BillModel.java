package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import entities.Bill;
import entities.BillDetail;
import entities.Import;
import entities.ImportDetail;

public class BillModel {
	public List<Bill> findAll() {
		List<Bill> bills = new ArrayList<Bill>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM `db_bill` ORDER BY `db_bill`.`date_print` DESC");
			ResultSet resultSet = preparedStatement.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setBill_id(resultSet.getInt("bill_id"));
				bill.setDate_print(resultSet.getDate("date_print"));
				bill.setBill_status(resultSet.getString("bill_status"));
				bill.setPayment(resultSet.getString("payment"));
				bill.setTotal_price(resultSet.getDouble("total_price"));
				bill.setCustomer_id(resultSet.getInt("customer_id"));
				bill.setEmp_id(resultSet.getInt("emp_id"));
				bills.add(bill);
			}
		} catch (Exception e) {
			bills = null;
		}
		return bills;
	}

	public List<BillDetail> findAllBasedMainID(int bill_id) {
		List<BillDetail> bills = new ArrayList<BillDetail>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM `db_bill_detail` WHERE `bill_id` = ?");
			preparedStatement.setInt(1, bill_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BillDetail bill = new BillDetail();
				bill.setBill_detail_id(resultSet.getInt("bill_detail_id"));
				bill.setItem_id(resultSet.getInt("item_id"));
				bill.setStore_price(resultSet.getDouble("store_price"));
				bill.setItem_unit(resultSet.getString("item_unit"));
				bill.setItem_quantity(resultSet.getInt("item_quantity"));
				bill.setBill_status(resultSet.getString("bill_status"));
				bill.setBill_id(resultSet.getInt("bill_id"));
				bills.add(bill);
			}
		} catch (Exception e) {
			bills = null;
		}
		return bills;
	}

	public Integer createBill(Bill bill) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int number = 0;
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_bill` (`bill_id`, `date_print`, `bill_status`, `payment`, `total_price`, `customer_id`, `emp_id`)"
							+ " VALUES (NULL, ?, ?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			querry.setString(1, format.format(new Date()).toString());
			querry.setString(2, bill.getBill_status());
			querry.setString(3, bill.getPayment());
			querry.setDouble(4, bill.getTotal_price());
			querry.setInt(5, bill.getCustomer_id());
			querry.setInt(6, bill.getEmp_id());
			querry.executeUpdate();

			ResultSet rs = querry.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
	}

	public boolean createBillDetail(BillDetail detailBill, int bill_id) {

		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_bill_detail` (`bill_detail_id`, `item_id`, `store_price`, `item_unit`, `item_quantity`, `bill_status`, `bill_id`) VALUES (NULL, ?, ?, ?,?, 'APPROVED', ?);");
			querry.setInt(1, detailBill.getItem_id());
			querry.setDouble(2, detailBill.getStore_price());
			querry.setString(3, detailBill.getItem_unit());
			querry.setInt(4, detailBill.getItem_quantity());
			querry.setInt(5, bill_id);
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean removeItemQuantity(int qty, int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE `db_item` SET `store_quantity`=(`store_quantity` - ?) WHERE `item_id` = ?;");
			preparedStatement.setInt(1, qty);
			preparedStatement.setInt(2, id);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean updateReturnedItemQuantity(int qty, int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE `db_item` SET `store_quantity`=(`store_quantity` + ?) WHERE `item_id` = ?;");
			preparedStatement.setInt(1, qty);
			preparedStatement.setInt(2, id);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean reStock(int qty, int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE `db_item` SET `store_quantity`=(`store_quantity` + ?) WHERE `item_id` = ?;");
			preparedStatement.setInt(1, qty);
			preparedStatement.setInt(2, id);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean updateStatusReturnedItem(int bill_detail_id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE `db_bill_detail` SET `bill_status`='RETURNED', `item_quantity`= 0 WHERE `bill_detail_id` = ?;");
			preparedStatement.setInt(1, bill_detail_id);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public List<Bill> searchCURRENTdate() {
		List<Bill> bills = new ArrayList<Bill>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from db_bill where date_print = current_date() ");

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setBill_id(resultSet.getInt("bill_id"));
				bill.setDate_print(resultSet.getDate("date_print"));
				bill.setBill_status(resultSet.getString("bill_status"));
				bill.setPayment(resultSet.getString("payment"));
				bill.setTotal_price(resultSet.getDouble("total_price"));
				bill.setCustomer_id(resultSet.getInt("customer_id"));
				bill.setEmp_id(resultSet.getInt("emp_id"));
				bills.add(bill);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			bills = null;
		}
		return bills;
	}

	public List<BillDetail> searchDetail(int bill_id) {
		List<BillDetail> bills = new ArrayList<BillDetail>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from db_bill_detail where bill_id = ?;");
			preparedStatement.setInt(1, bill_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BillDetail bill = new BillDetail();
				bill.setBill_detail_id(resultSet.getInt("bill_detail_id"));
				bill.setItem_id(resultSet.getInt("item_id"));
				bill.setStore_price(resultSet.getDouble("store_price"));
				bill.setItem_unit(resultSet.getString("item_unit"));
				bill.setItem_quantity(resultSet.getInt("item_quantity"));
				bill.setBill_id(resultSet.getInt("bill_id"));
				bills.add(bill);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			bills = null;
		}
		return bills;
	}

	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("delete from db_bill where bill_id = ?");
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public Integer createBillImport(Import importt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int number = 0;
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_bill_import`(`bill_import_id`, `supplier_id`, `total_price`, `emp_id`, `bill_status`, `date_import`)"
							+ " VALUES (NULL, ?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			querry.setInt(1, importt.getSupplier_id());
			querry.setDouble(2, importt.getTotal_price());
			querry.setInt(3, importt.getEmp_id());
			querry.setString(4, importt.getBill_satus());
			querry.setString(5, format.format(new Date()).toString());
			querry.executeUpdate();

			ResultSet rs = querry.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Main"+e.getMessage());
			return null;
		}
	}

	public boolean createBillImportDetail(ImportDetail detailImport, int bill_id) {

		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO `db_bill_import_detail`(`import_detail_id`, `item_name`, `import_price`, `store_price`, `item_quantity`, `item_unit`, `bill_status`, `bill_import_id`)"
					+ " VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);");
			querry.setString(1, detailImport.getItem_name());
			querry.setDouble(2, detailImport.getImport_price());
			querry.setDouble(3, detailImport.getStore_price());
			querry.setInt(4, detailImport.getItem_quantity());
			querry.setString(5, detailImport.getItem_unit());
			querry.setString(6, detailImport.getBill_status());
			querry.setInt(7, bill_id);
			return querry.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Detail"+e.getMessage());
			return false;
		}
	}
	public List<BillDetail> findItemSoldinDAY() {
		List<BillDetail> bills = new ArrayList<BillDetail>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select `item_id`, SUM(`item_quantity`) as count from `db_bill_detail` WHERE bill_id IN (select bill_id from `db_bill` where CURRENT_DATE()) group by `item_id` order by count desc ");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BillDetail bill = new BillDetail();
				bill.setItem_id(resultSet.getInt("item_id"));
				bill.setItem_quantity(resultSet.getInt("count"));
				bills.add(bill);
			}
		} catch (Exception e) {
			bills = null;
		}
		return bills;
	}
	//CHART
	public List<BillDetail> findItemSoldinMONTH() {
		List<BillDetail> bills = new ArrayList<BillDetail>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select `item_id`, SUM(`item_quantity`) as count from `db_bill_detail` WHERE bill_id IN (select bill_id from `db_bill` where MONTH(CURRENT_DATE())) group by `item_id` order by count desc ");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BillDetail bill = new BillDetail();
				bill.setItem_id(resultSet.getInt("item_id"));
				bill.setItem_quantity(resultSet.getInt("count"));
				bills.add(bill);
			}
		} catch (Exception e) {
			bills = null;
		}
		return bills;
	}
	public List<ImportDetail> findItemImportinMONTH() {
		List<ImportDetail> bills = new ArrayList<ImportDetail>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select `item_name`, SUM(`item_quantity`) as count from `db_bill_import_detail` WHERE bill_import_id IN (select bill_import_id from `db_bill_import` where MONTH(CURRENT_DATE())) group by `item_name` order by count desc ");
			ResultSet resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next()) {
				ImportDetail bill = new ImportDetail();
				bill.setItem_name(resultSet.getString("item_name"));
				bill.setItem_quantity(resultSet.getInt("count"));
				bills.add(bill);
			}
		} catch (Exception e) {
			bills = null;
		}
		return bills;
	}
	// END CHART
	
	public Double sumMoneyinDAY() {
		double total = 0;
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("	SELECT SUM(total_price) as toto FROM `db_bill` WHERE date_print = CURRENT_DATE");
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				total = result.getDouble("toto");
			}
			return total;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Got no transaction yet!!");
			return null;
		}
	}
	public Double sumImportMoneyinDAY() {
		double total = 0;
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("	SELECT SUM(total_price) as toto FROM `db_bill_import` WHERE date_import = CURRENT_DATE()");
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				total = result.getDouble("toto");
			}
			return total;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Got no transaction yet!!");
			return null;
		}
	}
	public Integer billReturninDAY() {
		int total = 0;
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("SELECT COUNT(bill_detail_id) as count from `db_bill_detail` WHERE bill_id IN (select bill_id from `db_bill` where date_print = CURRENT_DATE) and `bill_status` = 'RETURNED';");
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				total = result.getInt("count");
			}
			return total;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "No item returned yet!!");
			return null;
		}
	}
	//IMPORT
	public List<Import> searchALLImport() {
		List<Import> imports= new ArrayList<Import>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM `db_bill_import` ORDER BY `db_bill_import`.`date_import` DESC;");

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Import importt = new Import();
				importt.setBill_import_id(resultSet.getInt("bill_import_id"));
				importt.setSupplier_id(resultSet.getInt("supplier_id"));
				importt.setTotal_price(resultSet.getDouble("total_price"));
				importt.setEmp_id(resultSet.getInt("emp_id"));
				importt.setBill_satus(resultSet.getString("bill_status"));
				importt.setDate_import(resultSet.getDate("date_import"));
				imports.add(importt);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			imports = null;
		}
		return imports;
	}
	
	public List<ImportDetail> findAllBasedMainImportID(int bill_id) {
		List<ImportDetail> bills = new ArrayList<ImportDetail>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM `db_bill_import_detail` WHERE `bill_import_id` = ?");
			preparedStatement.setInt(1, bill_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ImportDetail bill = new ImportDetail();
				bill.setImport_detail_id(resultSet.getInt("import_detail_id"));
				bill.setItem_name(resultSet.getString("item_name"));
				bill.setImport_price(resultSet.getDouble("import_price"));
				bill.setStore_price(resultSet.getDouble("store_price"));
				bill.setItem_quantity(resultSet.getInt("item_quantity"));
				bill.setItem_unit(resultSet.getString("item_unit"));
				bill.setBill_status(resultSet.getString("bill_status"));
				bill.setBill_import_id(resultSet.getInt("bill_import_id"));
				bills.add(bill);
			}
		} catch (Exception e) {
			bills = null;
		}
		return bills;
	}
	
	public List<Import> searchImportCURRENTdate() {
		List<Import> imports= new ArrayList<Import>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from db_bill_import where date_import = current_date();");

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Import importt = new Import();
				importt.setBill_import_id(resultSet.getInt("bill_import_id"));
				importt.setSupplier_id(resultSet.getInt("supplier_id"));
				importt.setTotal_price(resultSet.getDouble("total_price"));
				importt.setEmp_id(resultSet.getInt("emp_id"));
				importt.setBill_satus(resultSet.getString("bill_status"));
				importt.setDate_import(resultSet.getDate("date_import"));
				imports.add(importt);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			imports = null;
		}
		return imports;
	}
}