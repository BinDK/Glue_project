package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import entities.Bill;
import entities.BillDetail;

public class BillModel {
	public List<Bill> findAll() {
		List<Bill> bills = new ArrayList<Bill>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM `db_bill` ORDER BY `db_bill`.`date_print` DESC");
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
					"INSERT INTO `db_bill_detail` (`bill_detail_id`, `item_id`, `store_price`, `item_unit`, `item_quantity`, `bill_id`) VALUES (NULL, ?, ?, ?, ?, ?);");
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
					bills.add(bill);				}
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
		 
	 
	 
	 public boolean deleteAll(int id) {
			try {
				
				PreparedStatement preparedStatement = ConnectDB.getConnection()
						.prepareStatement("delete from db_bill_detail where bill_id = ?");
				preparedStatement.setInt(1, id);
				return preparedStatement.executeUpdate() > 0;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				return false;
			}
		} 
	

}